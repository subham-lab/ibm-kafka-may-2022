package com.ibm.kafka.interfaces;

import java.util.List;

import com.ibm.kafka.beans.Employee;

// contract 
public interface IEmployeeDAO {
	
	public Employee getemployee(int empId); 
	
}