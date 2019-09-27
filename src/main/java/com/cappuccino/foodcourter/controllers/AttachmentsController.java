package com.cappuccino.foodcourter.controllers;

import com.cappuccino.foodcourter.models.db.Role;
import com.cappuccino.foodcourter.models.db.User;
import com.cappuccino.foodcourter.services.FileAttachmentsService;
import com.cappuccino.foodcourter.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@RestController
@RequestMapping("/api/attachments")
public class AttachmentsController {

    private final FileAttachmentsService service;
    private final UsersService usersService;

    @Autowired
    public AttachmentsController(
            FileAttachmentsService service,
            UsersService usersService
    ) {
        this.service = service;
        this.usersService = usersService;
    }

    @RequestMapping("/deleteAttachment")
    public ResponseEntity<Boolean> deleteAttachment(
            Principal principal,
            @RequestParam("id") long id
    ){
        User user = principal != null ? usersService.getByEmail(principal.getName()) : null;
        if (user != null && !user.getRole().getCode().equals(Role.StandardRoles.SUPERUSER)) {
            return ResponseEntity
                    .badRequest()
                    .body(false);
        }
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/addAttachment", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addAttachmentTest(
            @RequestParam("images") MultipartFile[] images
    ) throws IOException {
        service.saveAll(images, 1, User.class);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/byId/{attachmentId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<Resource> getFile(
            @PathVariable long attachmentId
    ) throws IOException {
        Resource file = service.getFileAttachmentById(attachmentId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @RequestMapping(
            value = "/byFilename/{fileName}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<Resource> getFile(
            @PathVariable String fileName
    ) throws IOException {
        Resource file = service.getFileAttachmentByFileName(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}

