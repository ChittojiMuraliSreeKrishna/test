package com.testing.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.test.model.MainFileEntity;

public interface MainFileRepository extends JpaRepository<MainFileEntity, Long> {
    // additional methods if needed
}