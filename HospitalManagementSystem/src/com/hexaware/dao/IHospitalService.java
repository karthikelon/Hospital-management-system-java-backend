package com.hexaware.dao;

import java.util.List;

import com.hexaware.entities.Appointment;
import com.hexaware.exception.PatientNumberNotFoundException;

public interface IHospitalService {

	Appointment getAppointmentById(int appointmentId);
	List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException;
    List<Appointment> getAppointmentsForDoctor(int doctorId);
    boolean scheduleAppointment(Appointment appointment);
    boolean updateAppointment(Appointment appointment);
    boolean cancelAppointment(int appointmentId);
}
