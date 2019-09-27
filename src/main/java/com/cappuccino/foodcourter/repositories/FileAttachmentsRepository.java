package com.cappuccino.foodcourter.repositories;

import com.cappuccino.foodcourter.models.db.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
public interface FileAttachmentsRepository extends JpaRepository<FileAttachment, Long> {

    Set<FileAttachment> getAllByAttachmentIdAndAttachmentClassType(long attachmentId, String attachmentType);

}
