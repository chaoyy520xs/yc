package com.jjkj.api.service;

import com.jjkj.api.modle.Department;

public interface DepartmentService {
	public int insertByModle(Department department);

	public Department selectById(int id);

}