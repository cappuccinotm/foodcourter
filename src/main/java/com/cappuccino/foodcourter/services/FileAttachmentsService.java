package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.db.FileAttachment;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
public interface FileAttachmentsService {

    FileAttachment saveAttachment(InputStream inputStream, long attachmentId, Class<?> attachmentClass) throws IOException;
    Set<Resource> getFileAttachments(long attachmentId, Class<?> attachmentClass) throws MalformedURLException;
    Set<FileAttachment> getFileAttachmentsDescriptions(long attachmentId, Class<?> attachmentClass);
    Resource getFileAttachmentByFileName(String fileName) throws MalformedURLException;
    Resource getFileAttachmentById(long id) throws MalformedURLException;
    List<FileAttachment> saveAll(MultipartFile[] attachments, long attachmentId, Class<?> attachmentClass) throws IOException;
    boolean deleteById(long id);

}
