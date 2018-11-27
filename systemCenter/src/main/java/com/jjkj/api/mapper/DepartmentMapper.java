package com.jjkj.api.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.jjkj.api.modle.Department;

@Mapper
public interface DepartmentMapper {
	@Select("select * from e_department where id=#{id}")
	public Department selectById(Integer id);

	@Insert("insert into e_department(number,name) values(#{number},#{name})")
	public int insertDepartment(Department department);

}
