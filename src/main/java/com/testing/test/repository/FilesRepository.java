package com.testing.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testing.test.model.FilesEntity;


@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, Long> {

}
