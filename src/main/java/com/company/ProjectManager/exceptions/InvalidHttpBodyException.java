package com.company.ProjectManager.exceptions;

public class InvalidHttpBodyException extends RuntimeException{
    public InvalidHttpBodyException() {
        super("Invalid http body");
    }
}
