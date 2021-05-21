package com.heaven.ppmtool.repository;

import com.heaven.ppmtool.domain.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BacklogRepository extends JpaRepository<Backlog,Long> {
    Backlog findByProjectIdentifier(String identifier);
}
