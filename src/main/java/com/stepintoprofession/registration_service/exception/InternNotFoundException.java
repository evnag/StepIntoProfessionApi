package com.stepintoprofession.registration_service.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class InternNotFoundException extends RuntimeException {
    public InternNotFoundException(String firstName, String lastName) {
        super("Intern with firstName: " + firstName + "and lastname:" + lastName +" not found");
    }

    public InternNotFoundException (Integer id) {
        super("Intern with id" + id +" not found");
    }
}
