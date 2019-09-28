package com.cappuccino.foodcourter.services;

import com.cappuccino.foodcourter.models.db.FileAttachment;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
public interface FileAttachmentsService {

    FileAttachment saveAttachment(InputStream inputStream) throws IOException;
    Resource getFileAttachmentByFileName(String fileName) throws MalformedURLException;
    Resource getFileAttachmentById(long id) throws MalformedURLException;
    List<FileAttachment> saveAll(MultipartFile[] attachments) throws IOException;
    FileAttachment save(MultipartFile attachment) throws IOException;
    boolean deleteById(long id);

}
