package com.jjkj.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jjkj.api.mapper.DepartmentMapper;
import com.jjkj.api.mapper.EmployeeMapper;
import com.jjkj.api.modle.Department;
import com.jjkj.api.modle.Employee;
import com.jjkj.api.service.DepartmentService;

@RestController
public class testController {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("/department/{id}")
	public Department select(@PathVariable("id") int id) {
		System.out.println("id:"+id);
		return departmentService.selectById(id);
	}
	
	/*
	 * 插入語句
	 * 請求方式：http://localhost:8080/department?number=xx&name=xx
	 * return ： 不能使用對象類型
	 * @author yc
	 */
	@GetMapping("/department")
	public int insert(Department department) {
		int i=0;
		try {
			i=departmentService.insertByModle(department);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	@GetMapping("/employee/{name}")
	public Employee select(@PathVariable("name")String name) {
		return employeeMapper.selectEmployeeByName(name);
	}
	
	@GetMapping()
	public Employee insert(Employee employee) {
		return employeeMapper.insertEmployee(employee);
	}
}
