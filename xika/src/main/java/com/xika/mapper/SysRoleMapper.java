package com.xika.mapper;

import java.util.List;

import com.xika.pojo.SysRole;
import com.xika.utils.MyMapper;

public interface SysRoleMapper extends MyMapper<SysRole> {

	List<SysRole> selectAllRoles();
}