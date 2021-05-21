package com.heaven.ppmtool.services;

import com.heaven.ppmtool.domain.Backlog;
import com.heaven.ppmtool.domain.Project;
import com.heaven.ppmtool.domain.ProjectTask;
import com.heaven.ppmtool.exception.ProjectNotFoundException;
import com.heaven.ppmtool.repository.BacklogRepository;
import com.heaven.ppmtool.repository.ProjectRepository;
import com.heaven.ppmtool.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask task){
        try{
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            task.setBacklog(backlog);
            Integer BacklogSequence = backlog.getPTSequence();
            BacklogSequence++;
            backlog.setPTSequence(BacklogSequence);
            task.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
            task.setProjectIdentifier(projectIdentifier);

            if(task.getPriority()==null || task.getPriority()==0){
                task.setPriority(3);
            }
            if(task.getStatus()=="" || task.getStatus()==null){
                task.setStatus("TO_DO");
            }
            return projectTaskRepository.save(task);
        }catch(Exception ex){
            throw new ProjectNotFoundException("Project not found");
        }

    }

    public List<ProjectTask> findBacklogById(String id){

        Project project = projectRepository.findByProjectIdentifier(id);

        if(project==null){
            throw new ProjectNotFoundException("Project with ID: " + id + " does not exist");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id,String pt_id){

        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if(backlog==null)
            throw new ProjectNotFoundException("Project with ID " + backlog_id + " not existed");
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if(projectTask==null)
            throw new ProjectNotFoundException(("Project task with : " + pt_id + " not found"));

        if(!projectTask.getProjectIdentifier().equals(backlog_id))
            throw new ProjectNotFoundException("Project task " + pt_id +  "not existed in project: " + backlog_id);
        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updateTask,String backlog_id,String pt_id){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id);
        projectTask = updateTask;
        return projectTaskRepository.save(projectTask);
    }

    public void deletePTByProjectSequence(String backlog_id,String pt_id){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id);


        projectTaskRepository.delete(projectTask);


    }
}
