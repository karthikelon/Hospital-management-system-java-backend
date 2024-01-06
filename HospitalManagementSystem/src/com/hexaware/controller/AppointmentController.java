package com.hexaware.controller;

import com.hexaware.dao.AppointmentDao;
import com.hexaware.entities.Appointment;
import com.hexaware.exception.PatientNumberNotFoundException;

import java.util.List;
import java.util.Scanner;

public class AppointmentController implements AppointmentInterface {

    public AppointmentDao appointmentDao=new AppointmentDao();
    public Scanner sc=new Scanner(System.in);


    public void getAppointmentById() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = sc.nextInt();
        Appointment appointment = appointmentDao.getAppointmentById(appointmentId);
        if (appointment != null) {
            System.out.println("Appointment Details:");
            System.out.println(appointment);
        } else {
            System.out.println("Appointment not found with ID: " + appointmentId);
        }
    }

    
    public void getAppointmentsForPatient() {
    	
        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();
        List<Appointment> appointments=null;
		try {
			appointments = appointmentDao.getAppointmentsForPatient(patientId);
		} catch (PatientNumberNotFoundException e) {
			
			e.printStackTrace();
		}
        if (!appointments.isEmpty()) {
            System.out.println("Appointments for Patient ID " + patientId + ":");
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        } else {
            System.out.println("No appointments found for Patient ID " + patientId);
        }
    }

    
    public void getAppointmentsForDoctor() {
        System.out.print("Enter Doctor ID: ");
        int doctorId = sc.nextInt();
        List<Appointment> appointments = appointmentDao.getAppointmentsForDoctor(doctorId);
        if (!appointments.isEmpty()) {
            System.out.println("Appointments for Doctor ID " + doctorId + ":");
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        } else {
            System.out.println("No appointments found for Doctor ID " + doctorId);
        }
    }

    
    public void scheduleAppointment() {
        
    	System.out.print("Enter appointmentId: ");
        int appointmentId = sc.nextInt();
        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String appointmentDateStr = sc.nextLine();
        java.sql.Date appointmentDate = java.sql.Date.valueOf(appointmentDateStr);
        System.out.print("Enter Appointment Description: ");
        String description = sc.nextLine();

        Appointment appointment = new Appointment(appointmentId,patientId, doctorId,appointmentDate,description);

       
        boolean success = appointmentDao.scheduleAppointment(appointment);

        if (success) {
            System.out.println("Appointment scheduled successfully!");
        } else {
            System.out.println("Failed to schedule the appointment.");
        }
    }

    
    public void updateAppointment() {
    	 try {
             
             System.out.print("Enter Appointment ID to update: ");
             int appointmentId = sc.nextInt();

             

             
             Appointment existingAppointment = appointmentDao.getAppointmentById(appointmentId);
             if (existingAppointment != null) {
                 System.out.println("Existing Appointment Details:");
                 System.out.println(existingAppointment);

               
                 System.out.print("Enter updated Patient ID: ");
                 int updatedPatientId = sc.nextInt();

                 System.out.print("Enter updated Doctor ID: ");
                 int updatedDoctorId = sc.nextInt();

                 
                 sc.nextLine();
                 System.out.print("Enter updated Appointment Date (YYYY-MM-DD): ");
                 String updatedAppointmentDateStr = sc.nextLine();
                 
                 java.sql.Date updatedAppointmentDate = java.sql.Date.valueOf(updatedAppointmentDateStr);

                 System.out.print("Enter updated Appointment Description: ");
                 String updatedDescription = sc.nextLine();

                 
                 Appointment updatedAppointment = new Appointment(
                         appointmentId, updatedPatientId, updatedDoctorId, updatedAppointmentDate, updatedDescription
                 );

                 
                 boolean success = appointmentDao.updateAppointment(updatedAppointment);

                 if (success) {
                     System.out.println("Appointment updated successfully!");
                 } else {
                     System.out.println("Failed to update the appointment.");
                 }
             } else {
                 System.out.println("Appointment not found with ID: " + appointmentId);
             }
         } catch (Exception e) {
             
             System.out.println("Error: Invalid input. Please enter valid values.");
              
         }
    }

    
    public void cancelAppointment() {
        
        System.out.print("Enter Appointment ID to cancel: ");
        int appointmentId = sc.nextInt();

        
        boolean success = appointmentDao.cancelAppointment(appointmentId);

        if (success) {
            System.out.println("Appointment canceled successfully!");
        } else {
            System.out.println("Failed to cancel the appointment.");
        }
    }
}
