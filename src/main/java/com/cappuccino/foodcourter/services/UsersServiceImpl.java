package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.api.UserRegistrationData;
import com.cappuccino.foodcourter.models.db.Privilege;
import com.cappuccino.foodcourter.models.db.Role;
import com.cappuccino.foodcourter.models.db.User;
import com.cappuccino.foodcourter.models.exceptions.RoleNotFoundException;
import com.cappuccino.foodcourter.models.exceptions.UsernameAlreadyTakenException;
import com.cappuccino.foodcourter.repositories.PrivilegesRepository;
import com.cappuccino.foodcourter.repositories.RolesRepository;
import com.cappuccino.foodcourter.repositories.UsersRepository;
import com.cappuccino.foodcourter.resources.StaticStrings;
import com.cappuccino.foodcourter.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PrivilegesRepository privilegesRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailingClient mailingClient;
    private final RolesRepository rolesRepository;

    @Value("${admin.email}")
    private String defaultAdminEmail;

    @Value("${admin.password}")
    private String defaultAdminPassword;

    public UsersServiceImpl(
            UsersRepository usersRepository,
            PrivilegesRepository privilegesRepository,
            BCryptPasswordEncoder passwordEncoder,
            MailingClient mailingClient,
            RolesRepository rolesRepository){
        this.usersRepository = usersRepository;
        this.privilegesRepository = privilegesRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailingClient = mailingClient;
        this.rolesRepository = rolesRepository;
    }

    @PostConstruct
    public void init(){
        // Создаём стандартные привелегии
        Privilege createNewBackendUsersPrivilege = createPrivilegeIfNotExist(Privilege.StandartPrivileges.CREATE_NEW_BACKEND_USERS);
        Privilege editBackendUsersPrivilege = createPrivilegeIfNotExist(Privilege.StandartPrivileges.EDIT_BACKEND_USERS);

        // Создаём стандартные роли
        Role superuser = createRoleIfNotExist(
                Role.StandardRoles.SUPERUSER,
                new HashSet<>(Arrays.asList(
                        createNewBackendUsersPrivilege,
                        editBackendUsersPrivilege
                ))
        );

        // Создаём администратора, если таковой в базе отсутствует
        User admin = createAdminIfNotExist(defaultAdminEmail, superuser);
    }

    /**
     * Возвращает роль, если она существует, в противном случае - создаёт её и возвращает
     * @param code - код роли
     * @return - привилегия
     */
    private Role createRoleIfNotExist(String code, Set<Privilege> privileges){
        Role role = rolesRepository.findByCode(code);
        if(role == null)
            role = rolesRepository.save(
                    new Role()
                        .setCode(code)
                        .setPrivileges(privileges)
            );
        return role;
    }

    /**
     * Возвращает привилегию, если она существует, в противном случае - создаёт её и возвращает
     * @param code - код привилегии
     * @return - привилегия
     */
    private Privilege createPrivilegeIfNotExist(String code){
        Privilege privilege = privilegesRepository.findByCode(code);
        if(privilege == null)
            privilege = privilegesRepository.save(
                    new Privilege()
                            .setCode(code)
            );
        return privilege;
    }

    private User createAdminIfNotExist(String email, Role role){
        User admin = usersRepository.findFirstByEmail(email);
        if(admin == null)
            admin = usersRepository.save(
                    new User()
                        .setEmail(defaultAdminEmail)
                        .setPassword(passwordEncoder.encode(defaultAdminPassword))
                        .setRole(role)
            );
        return admin;
    }

    /**
     * Создаёт временный пароль пользователю и отправляет его на почту
     * @param email - почта, кому отправить
     */
    public void resetUserPassword(String email) throws UsernameNotFoundException{
        // Меняем пользователю пароль и записываем, что он должен его поменять
        User user = usersRepository.findFirstByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("");
        }

        String userPassword = PasswordGenerator.generatePassword();
        user.setPassword(passwordEncoder.encode(userPassword));
        user.setNeedToChangePassword(true);
        usersRepository.save(user);

        // Формируем письмо
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(StaticStrings.EMAIL_PASSWORD_RESET_SUBJECT);
        mailMessage.setText(String.format(
                StaticStrings.YOUR_PASSWORD_MESSAGE,
                userPassword
        ));
        mailingClient.sendEmail(mailMessage);
    }

    @Override
    public User register(UserRegistrationData userData) throws UsernameAlreadyTakenException, RoleNotFoundException {
        if(usersRepository.existsByEmail(userData.getUsername()))
            throw new UsernameAlreadyTakenException();

        Role role = rolesRepository.findByCode(userData.getRoleCode());
        if(role == null)
            throw new RoleNotFoundException();

        return usersRepository.save(
                new User()
                    .setEmail(userData.getUsername())
                    .setPassword(passwordEncoder.encode(userData.getPassword()))
                    .setRole(role)
                    .setNeedToChangePassword(true)
                    .setPhoneNumber(userData.getPhoneNumber())
        );
    }

    @Override
    public User getByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
