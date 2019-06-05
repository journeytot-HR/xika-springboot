package com.xika.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
import com.xika.mapper.SysRoleMapper;
import com.xika.pojo.SysRole;
import com.xika.service.UserService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SysRoleMapper roleMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveRole(SysRole role) {
		// TODO Auto-generated method stub
		roleMapper.insert(role);
		int a = 1/0;
		role.setDeptid(24);
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public List<SysRole> queryRoles(SysRole role, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, pageSize);
		Example example = new Example(SysRole.class);
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespaceOnly(role.getName())) {
			criteria.andLike("name", "%"+ role.getName() + "%");
		}
		example.orderBy("deptid").desc();
		List<SysRole> list = roleMapper.selectByExample(example);
		
		List<SysRole> listAll = roleMapper.selectAllRoles();
		return listAll;
	}
	
}
