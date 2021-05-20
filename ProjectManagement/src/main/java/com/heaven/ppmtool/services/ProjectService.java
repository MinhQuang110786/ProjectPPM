package com.heaven.ppmtool.services;

import com.heaven.ppmtool.domain.Project;
import com.heaven.ppmtool.exception.ProjectIdException;
import com.heaven.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch(Exception ex){
            throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase() + " already existed");
        }

    }

    public Project findProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null){
            throw new ProjectIdException( "Project " + projectId + " not existed");
        }
        return project;
    }

    public List<Project> findAllProject(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null){
            throw new ProjectIdException( "Project " + projectId + " not existed and not deleted");
        }
        projectRepository.delete(project);
    }

    public Project updateProject(String projectId,Project updateProject){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null){
            throw new ProjectIdException( "Project " + projectId + " not existed");
        }
        project.setProjectName(updateProject.getProjectName());
        project.setDescription(updateProject.getDescription());
        project.setUpdate_At(updateProject.getUpdate_At());
        project.setCreated_At(updateProject.getCreated_At());
        project.setStartDate(updateProject.getStartDate());
        project.setEndDate(updateProject.getEndDate());

        projectRepository.saveAndFlush(project);
        return project;
    }

}
