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
