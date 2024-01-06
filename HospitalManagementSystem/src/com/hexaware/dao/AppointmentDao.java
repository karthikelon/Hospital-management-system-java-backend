package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entities.Appointment;
import com.hexaware.exception.PatientNumberNotFoundException;
import com.hexaware.util.DBConnection;

public class AppointmentDao implements IHospitalService{
	Connection connection;
    Statement statement;
    PreparedStatement preparedStatement; 
    ResultSet resultSet;
    
    public Appointment getAppointmentById(int appointmentId) {
        Appointment appointment=null ;
        try {
             
            connection = DBConnection.getConnection();

            String sql = "SELECT * FROM Appointment WHERE appointmentId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, appointmentId);

            
            resultSet = preparedStatement.executeQuery();

            
            if (resultSet.next()) {
                
                appointment = new Appointment(
                        resultSet.getInt("appointmentId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getDate("appointmentDate"),
                        resultSet.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

	
	public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException {
		List<Appointment> appointments = new ArrayList<>();
        try {
            
            connection = DBConnection.getConnection();

            
            String sql = "SELECT * FROM Appointment WHERE patientId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, patientId);

            
            resultSet = preparedStatement.executeQuery();

            
            while (resultSet.next()) {
                
                Appointment appointment = new Appointment(
                        resultSet.getInt("appointmentId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getDate("appointmentDate"),
                        resultSet.getString("description")
                );
                appointments.add(appointment);
            }
            if (appointments.isEmpty()) {
                throw new PatientNumberNotFoundException();
            }
        } catch (SQLException | PatientNumberNotFoundException e) {
            e.printStackTrace();
        } 
        return appointments;
		
	}

	
	public List<Appointment> getAppointmentsForDoctor(int doctorId) {
		List<Appointment> appointments = new ArrayList<>();
        try {
            
            connection = DBConnection.getConnection();

            
            String sql = "SELECT * FROM Appointment WHERE doctorId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, doctorId);

            
            resultSet = preparedStatement.executeQuery();

            
            while (resultSet.next()) {
                
                Appointment appointment = new Appointment(
                        resultSet.getInt("appointmentId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getDate("appointmentDate"),
                        resultSet.getString("description")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return appointments;
		
	}

	@Override
	public boolean scheduleAppointment(Appointment appointment) {
		   boolean success = false;
	        try {
	            
	            connection = DBConnection.getConnection();

	            
	            String sql = "INSERT INTO Appointment (appointmentId,patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?, ?)";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, appointment.getAppointmentId());
	            preparedStatement.setInt(2, appointment.getPatientId());
	            preparedStatement.setInt(3, appointment.getDoctorId());
	            preparedStatement.setDate(4, new java.sql.Date(appointment.getAppointmentDate().getTime()));
	            preparedStatement.setString(5, appointment.getDescription());

	            
	            int rowsAffected = preparedStatement.executeUpdate();
	            success = rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 

	        return success;
		
	}

	
	public boolean updateAppointment(Appointment appointment) {
		boolean success = false;
        try {
            
            connection = DBConnection.getConnection();

            
            String sql = "UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, appointment.getPatientId());
            preparedStatement.setInt(2, appointment.getDoctorId());
            preparedStatement.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            preparedStatement.setString(4, appointment.getDescription());
            preparedStatement.setInt(5, appointment.getAppointmentId());

            
            int rowsAffected = preparedStatement.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return success;
		
	}

	
	public boolean cancelAppointment(int appointmentId) {
		boolean success = false;
        try {
            
            connection = DBConnection.getConnection();

            
            String sql = "DELETE FROM Appointment WHERE appointmentId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, appointmentId);

            
            int rowsAffected = preparedStatement.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return success;
		
	}
	
}
