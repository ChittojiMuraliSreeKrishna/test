package com.testing.test.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor // Add this annotation to generate a default constructor
public class Users implements Serializable {

    private static final long serialVersionUID = 487410612967806332L;

    @Id
    private Long id;

    private Long userId;

    private String title;

    private boolean completed;

    public Users(Long id, Long userId, String title, boolean completed, boolean isUserValid) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public Long getId(Long todoId) {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	
}
