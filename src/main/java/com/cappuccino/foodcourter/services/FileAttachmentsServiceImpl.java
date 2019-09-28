package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.db.FileAttachment;
import com.cappuccino.foodcourter.repositories.FileAttachmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@Service
public class FileAttachmentsServiceImpl implements FileAttachmentsService{

    private final FileAttachmentsRepository repository;

    private Path mediaFolder;

    @Value("${mediaDirectory}")
    private String mediaPath;

    @Autowired
    public FileAttachmentsServiceImpl(FileAttachmentsRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        try {
            mediaFolder = Paths.get(mediaPath);
            if(Files.notExists(mediaFolder))
                Files.createDirectory(mediaFolder);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    public FileAttachment saveAttachment(InputStream inputStream, long attachmentId, Class<?> attachmentClass) throws IOException {
        String filename = UUID.randomUUID().toString() + ".png";

        Files.copy(inputStream, mediaFolder.resolve(filename));

        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setDiskName(filename);
        fileAttachment.setAttachmentId(attachmentId);
        fileAttachment.setAttachmentClassType(attachmentClass.getName());
        return repository.save(fileAttachment);
    }

    public Set<Resource> getFileAttachments(long attachmentId, Class<?> attachmentClass) throws MalformedURLException {
        Set<FileAttachment> attachments = repository.getAllByAttachmentIdAndAttachmentClassType(attachmentId, attachmentClass.getName());
        Set<Resource> result = new HashSet<>();

        for(FileAttachment attachment: attachments){
            Path file = mediaFolder.resolve(attachment.getDiskName());
            result.add(new UrlResource(file.toUri()));
        }

        return result;
    }

    public Set<FileAttachment> getFileAttachmentsDescriptions(long attachmentId, Class<?> attachmentClass) {
        return repository.getAllByAttachmentIdAndAttachmentClassType(attachmentId, attachmentClass.getName());
    }

    public Resource getFileAttachmentByFileName(String fileName) throws MalformedURLException {
        Path file = mediaFolder.resolve(fileName);
        Resource resource = new UrlResource(file.toUri());
        if(!resource.exists() || !resource.isReadable())
            throw new RuntimeException("File was not found! FileName: " + fileName);
        return resource;
    }

    public Resource getFileAttachmentById(long id) throws MalformedURLException {
        FileAttachment fileAttachment = repository.getOne(id);
        if(fileAttachment == null)
            return null;
        Path file = mediaFolder.resolve(fileAttachment.getDiskName());
        Resource resource = new UrlResource(file.toUri());
        if(!resource.exists() || !resource.isReadable())
            throw new RuntimeException("File was not found! FileId: " + fileAttachment.getId());

        return resource;

    }

    public List<FileAttachment> saveAll(MultipartFile[] attachments, long attachmentId, Class<?> attachmentClass) throws IOException {
        List<FileAttachment> result = new ArrayList<>();
        for(MultipartFile file: attachments){
            if(!file.isEmpty())
                result.add(saveAttachment(file.getInputStream(), attachmentId, attachmentClass));
        }
        return result;
    }

    @Override
    public FileAttachment save(MultipartFile attachment, long attachmentId, Class<?> attachmentClass) throws IOException {
        if(attachment.isEmpty())
            return null;
        return saveAttachment(attachment.getInputStream(), attachmentId, attachmentClass);
    }

    public boolean deleteById(long id){
        FileAttachment attachment = repository.getOne(id);
        File file = new File(mediaPath + "/" + attachment.getDiskName());
        repository.delete(attachment);
        if(file.exists()){
            return file.delete();
        }
        return false;
    }

}
