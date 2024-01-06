package com.hexaware.exception;

public class PatientNumberNotFoundException extends Exception {

    public PatientNumberNotFoundException() {
        System.out.println("Patient Number Not Found Exception");
    }

    @Override
    public String toString() {
        return "Incorrect patient number";
    }
}
