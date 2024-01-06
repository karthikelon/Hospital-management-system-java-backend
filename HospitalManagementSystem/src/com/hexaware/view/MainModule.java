package com.hexaware.view;

import java.util.Scanner;
import com.hexaware.controller.*;
import com.hexaware.exception.AuthorizationException;

public class MainModule {

    public static void main(String[] args) {

        System.out.println("Welcome to Hospital Management System");

        PatientInterface patientInterface = new PatientController();
        DoctorInterface doctorInterface = new DoctorController();
        AppointmentInterface appointmentInterface = new AppointmentController();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String unm = sc.next();

        System.out.println("Enter password: ");
        String pas = sc.next();
        String ch;
        int choice;
        try {
            if (unm.equals("karthik") && pas.equals("bankai")) {
                do {
                    System.out.println("----- Hospital Management System Menu -----");
                    System.out.println("1. Get Appointment by ID");
                    System.out.println("2. Get Appointments for Patient");
                    System.out.println("3. Get Appointments for Doctor");
                    System.out.println("4. Schedule Appointment");
                    System.out.println("5. Update Appointment");
                    System.out.println("6. Cancel Appointment");
                    System.out.println("7. Exit");
                    System.out.print("Enter your choice: ");

                    choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            appointmentInterface.getAppointmentById();
                            break;
                        case 2:
                            appointmentInterface.getAppointmentsForPatient();
                            break;
                        case 3:
                            appointmentInterface.getAppointmentsForDoctor();
                            break;
                        case 4:
                            appointmentInterface.scheduleAppointment();
                            break;
                        case 5:
                            appointmentInterface.updateAppointment();
                            break;
                        case 6:
                            appointmentInterface.cancelAppointment();
                            break;
                        case 7:
                            System.out.println("Exiting the Hospital Management System. Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }

                    
                    System.out.println("Do you want to continue? (Y/N): ");
                    ch = sc.next();

                } while (ch.equals("Y") || ch.equals("y"));
                System.out.println("Thank you for using the Hospital Management System");
            } else {
                throw new AuthorizationException();
            }
        } catch (AuthorizationException e) {
            System.out.println(e);
        } finally {
            sc.close(); 
        }
    }
}
