package com.cappuccino.foodcourter.models.db;

import javax.persistence.*;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
@Entity
@Table(name = "file_attachments")
public class FileAttachment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "disk_name")
    private String diskName;

    @Column(name = "attachment_id")
    private long attachmentId;

    @Column(name = "attachment_type")
    private String attachmentClassType;

    public long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentClassType() {
        return attachmentClassType;
    }

    public void setAttachmentClassType(String attachmentClassType) {
        this.attachmentClassType = attachmentClassType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiskName() {
        return diskName;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }
}
