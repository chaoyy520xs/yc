package com.jjkj.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjkj.api.mapper.DepartmentMapper;
import com.jjkj.api.modle.Department;
import com.jjkj.api.service.DepartmentService;
@Service("DepartmentServiceImpl")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	DepartmentMapper departmentMapper;
	
	public int insertByModle(Department department) {
		return departmentMapper.insertDepartment(department);
	}

	@Override
	public Department selectById(int id) {
		return departmentMapper.selectById(id);
	}

}
