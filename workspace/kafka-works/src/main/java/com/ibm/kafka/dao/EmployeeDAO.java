package com.ibm.kafka.dao;

import java.sql.SQLException;

import com.ibm.kafka.beans.Employee;
import com.ibm.kafka.connection.GetConnection;
import com.ibm.kafka.interfaces.IEmployeeDAO;

public class EmployeeDAO implements IEmployeeDAO {

	@Override
	public Employee getemployee(int empId) {
		String sql = "select empname from emp where empid=?";

		GetConnection gc = new GetConnection();

		try {
			gc.ps = GetConnection.getMySQLConnection().prepareStatement(sql);

			gc.ps.setInt(1, empId);

			gc.rs1 = gc.ps.executeQuery();

			// there is no hasNext method
			if (gc.rs1.next()) {
				Employee employee = new Employee();
				employee.setEmpId(empId);
				employee.setEmpName(gc.rs1.getString(1));

				return employee;
			} else {
				System.out.println("Sorry record not found... " + empId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
