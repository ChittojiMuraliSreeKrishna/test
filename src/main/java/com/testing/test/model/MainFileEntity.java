package com.testing.test.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class MainFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mainFileName;

    @OneToMany(mappedBy = "mainFileEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FilesEntity> files;
    
    public MainFileEntity() {
    	
    }

	public MainFileEntity(Long id, String mainFileName, List<FilesEntity> files) {
		super();
		this.id = id;
		this.mainFileName = mainFileName;
		this.files = files;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMainFileName() {
		return mainFileName;
	}

	public void setMainFileName(String mainFileName) {
		this.mainFileName = mainFileName;
	}

	public List<FilesEntity> getFiles() {
		return files;
	}

	public void setFiles(List<FilesEntity> files) {
		this.files = files;
	}

    // getters and setters

    // other fields and methods
}