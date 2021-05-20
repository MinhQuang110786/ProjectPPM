package com.heaven.ppmtool.services;

import com.heaven.ppmtool.domain.Project;
import com.heaven.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project){

        return projectRepository.save(project);
    }



}
