package com.heaven.ppmtool.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectIdExceptionResponse{
    private String projectIdentifier;

    public ProjectIdExceptionResponse(String msg){
        this.projectIdentifier=msg;
    }


}
