package com.xika.service;

import java.util.List;

import com.xika.pojo.SysRole;

public interface UserService {
	
	void saveRole(SysRole role);

	List<SysRole> queryRoles(SysRole role, Integer page, Integer pageSize);

}
