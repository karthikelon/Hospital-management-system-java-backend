package com.hexaware.controller;

public interface AppointmentInterface {

	void getAppointmentById();

	void getAppointmentsForPatient();

	void getAppointmentsForDoctor();

	void scheduleAppointment();

	void updateAppointment();

	void cancelAppointment();

}
