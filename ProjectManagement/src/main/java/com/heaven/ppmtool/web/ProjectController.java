package com.heaven.ppmtool.web;

import com.heaven.ppmtool.domain.Project;
import com.heaven.ppmtool.exception.ProjectIdException;
import com.heaven.ppmtool.services.MapValidationErrorService;
import com.heaven.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = "*")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService errorService;
    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
            if(result.hasErrors())
                return errorService.MapValidationService(result);
            projectService.saveOrUpdate(project);
            return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable("projectId") String projectId){
        Project project = projectService.findProjectByIdentifier(projectId);

        return new ResponseEntity<>(project,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getALlProject(){
        return new ResponseEntity<>(projectService.findAllProject(),HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable("projectId") String projectId){
       projectService.deleteProjectByIdentifier(projectId);
       return new ResponseEntity<>("Project " + projectId +  " was deleted successfully",HttpStatus.OK);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable String projectId,@Valid@RequestBody Project editproject,BindingResult result){
        if(result.hasErrors())
            return errorService.MapValidationService(result);
        Project project = projectService.updateProject(projectId,editproject);
        return new ResponseEntity<>(projectService.updateProject(projectId,project),HttpStatus.OK);
    }
}
