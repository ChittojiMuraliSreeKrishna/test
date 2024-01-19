package com.testing.test.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilesEntity implements Serializable {

    private static final long serialVersionUID = 487410612967806332L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_date")
    private LocalDate fileDate;

    @Column(name = "file_time")
    private LocalTime fileTime;
    
    @ManyToOne
    @JoinColumn(name = "main_file_entity_id")
    private MainFileEntity mainFileEntity;
    
    public FilesEntity() {
    	
    }
    
    public FilesEntity(String fileName, String fileType, LocalDate fileDate, LocalTime fileTime) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileDate = fileDate;
        this.fileTime = fileTime;
    }
    
    public FilesEntity(long id, String fileName, String fileType, LocalDate fileDate, LocalTime fileTime,
			MainFileEntity mainFileEntity) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileDate = fileDate;
		this.fileTime = fileTime;
		this.mainFileEntity = mainFileEntity;
	}

	public MainFileEntity getMainFileEntity() {
		return mainFileEntity;
	}

	public void setMainFileEntity(MainFileEntity mainFileEntity) {
		this.mainFileEntity = mainFileEntity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public LocalDate getFileDate() {
		return fileDate;
	}

	public void setFileDate(LocalDate fileDate) {
		this.fileDate = fileDate;
	}

	public LocalTime getFileTime() {
		return fileTime;
	}

	public void setFileTime(LocalTime fileTime) {
		this.fileTime = fileTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
