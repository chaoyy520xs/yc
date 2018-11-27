package com.jjkj.api.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jjkj.api.modle.Employee;

@Mapper
public interface EmployeeMapper {
	@Select("select * from e_employee where name=#{name}")
	public Employee selectEmployeeByName(String name);

	@Insert("insert into employee(number,name,phone,email,phone,email,IDcard,hiredate,salary,department_id,department_name,level,employee_id,create_time) "
			+ "values(#{number},#{name},#{phone},#{email},#{phone},#{email},#{IDcard},#{hiredate},#{salary},#{department_id},#{department_name},#{level},#{employee_id},#{create_time})")
	public Employee insertEmployee(Employee employee);

}
