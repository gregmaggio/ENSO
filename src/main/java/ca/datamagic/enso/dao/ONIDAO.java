package ca.datamagic.enso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.datamagic.enso.dto.ONIDTO;

public class ONIDAO extends BaseDAO {
	public ONIDAO() {
	}

	public void clear() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = openConnection("oni.db");
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM oni");
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public ONIDTO get(Integer year, Integer month) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = openConnection("oni.db");
			statement = connection.prepareStatement("SELECT * FROM oni WHERE year = ? AND month = ?");
			statement.setInt(1, year);
			statement.setInt(2, month);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				ONIDTO dto = new ONIDTO();
				dto.setYear(year);
				dto.setMonth(month);
				dto.setAnom(resultSet.getDouble("anom"));
				return dto;
			}
			return null;
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public void save(ONIDTO oni) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = openConnection("oni.db");
			statement = connection.prepareStatement("INSERT INTO oni (year, month, anom) VALUES  (?, ?, ?)");
			statement.setInt(1, oni.getYear());
			statement.setInt(2, oni.getMonth());
			statement.setDouble(3, oni.getAnom());
			statement.executeUpdate();
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public List<ONIDTO> getAll() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<ONIDTO> all = new ArrayList<ONIDTO>();			
			connection = openConnection("oni.db");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM oni");
			while(resultSet.next()) {
				ONIDTO dto = new ONIDTO();
				dto.setYear(resultSet.getInt("year"));
				dto.setMonth(resultSet.getInt("month"));
				dto.setAnom(resultSet.getDouble("anom"));
				all.add(dto);
			}
			return all;
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public List<Integer> getYears() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Integer> years = new ArrayList<Integer>();
			connection = openConnection("oni.db");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT DISTINCT year FROM oni");
			while(resultSet.next()) {
				years.add(new Integer(resultSet.getInt("year")));
			}
			return years;
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public List<ONIDTO> getAll(Integer year) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			List<ONIDTO> all = new ArrayList<ONIDTO>();
			connection = openConnection("oni.db");
			statement = connection.prepareStatement("SELECT * FROM oni WHERE year = ?");
			statement.setInt(1, year);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ONIDTO dto = new ONIDTO();
				dto.setYear(year);
				dto.setMonth(resultSet.getInt("month"));
				dto.setAnom(resultSet.getDouble("anom"));
				all.add(dto);
			}
			return all;
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}
