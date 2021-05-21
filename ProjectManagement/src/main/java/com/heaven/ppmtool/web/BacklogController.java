package com.heaven.ppmtool.web;

import com.heaven.ppmtool.domain.ProjectTask;
import com.heaven.ppmtool.services.MapValidationErrorService;
import com.heaven.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin(origins = "*")
public class BacklogController {
    @Autowired
    private ProjectTaskService projectTaskService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result,@PathVariable String backlog_id){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;
        ProjectTask task = projectTaskService.addProjectTask(backlog_id,projectTask);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public ResponseEntity<?> getProjectBacklog(@PathVariable String backlog_id){
        return new ResponseEntity<>(projectTaskService.findBacklogById(backlog_id),HttpStatus.OK);
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable("backlog_id") String backlog_id, @PathVariable("pt_id") String pt_id){
        ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id,pt_id);
        return new ResponseEntity<>(projectTask,HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask,BindingResult result,
                                                @PathVariable("backlog_id") String backlog_id, @PathVariable("pt_id") String pt_id){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;
        ProjectTask updateTask = projectTaskService.updateByProjectSequence(projectTask,backlog_id,pt_id);
        return new ResponseEntity<>(updateTask,HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask( @PathVariable("backlog_id") String backlog_id, @PathVariable("pt_id") String pt_id){
        projectTaskService.deletePTByProjectSequence(backlog_id,pt_id);
        return new ResponseEntity<>("Project task with "+ pt_id + "delete successfully",HttpStatus.OK);
    }
}
