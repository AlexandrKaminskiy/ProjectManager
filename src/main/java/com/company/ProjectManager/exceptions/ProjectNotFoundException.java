package com.company.ProjectManager.exceptions;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException() {
        super("Project not found");
    }
}
