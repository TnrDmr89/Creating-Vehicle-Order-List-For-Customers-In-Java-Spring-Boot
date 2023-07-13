package com.example.tanerworking1.exceptions;

import org.springframework.stereotype.Component;

@Component
public class DataNotFoundInDB extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DataNotFoundInDB(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public DataNotFoundInDB(){

    }
}
