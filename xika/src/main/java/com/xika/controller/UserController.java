package com.xika.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xika.common.BaseController;
import com.xika.config.Resource;
import com.xika.pojo.SysRole;
import com.xika.pojo.User;
import com.xika.service.UserService;
import com.xika.utils.JsonUtils;
import com.xika.utils.XikaJSONResult;
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private Resource resource;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private Sid sid;
	
	@Autowired
	private StringRedisTemplate strRedis;
	
	@RequestMapping("/getInfo")
	public User getInfo() {
		User u = new User();
		u.setAge(15);
		u.setDesc(null);
		u.setName("张三");
		u.setPassword("123456");
		u.setBirthday(new Date());
		int i = 1/0;
		return u;
	}


	@RequestMapping("/getInfoJson")
	public XikaJSONResult getInfoJson(HttpServletRequest request) throws Exception {
		//业务参
		String arr[] = {"page","size"}; //
		Map<String, String> dataMap = reqJsonStr(arr,request);
		User u = new User();
		u.setAge(15);
		u.setDesc(null);
		u.setName("张三风");
		u.setPassword("123456");
		u.setBirthday(new Date());
		return XikaJSONResult.ok(u);
	}
	
	@RequestMapping("/getResource")
	public XikaJSONResult getResource() {
		Resource bean = new Resource();
		BeanUtils.copyProperties(resource, bean);
		return XikaJSONResult.ok(bean);
	}
	
	@RequestMapping("/saveRole")
	public XikaJSONResult saveRole() {
		String num = sid.nextShort();
		SysRole role = new SysRole();
		role.setName("项目经理");
		role.setNum(3);
		role.setPid(1);
		role.setTips(num);
		role.setVersion(1);
		role.setDeptid(26);
		userService.saveRole(role);
		return XikaJSONResult.ok();
	}
	
	@RequestMapping("/queryRoles")
	public XikaJSONResult queryRoles() {
		Integer page=1;
		Integer pageSize=2;
		SysRole role = new SysRole();
		role.setName("管理员");
		List<SysRole> roleLists= userService.queryRoles(role,page,pageSize);
		return XikaJSONResult.ok(roleLists);
	}
	
	@RequestMapping("/saveRedis")
	public XikaJSONResult saveRedis() {
		strRedis.opsForValue().set("xika001", "xika2019");
		
		User u = new User();
		u.setAge(15);
		u.setDesc(null);
		u.setName("张三风");
		u.setPassword("123456");
		u.setBirthday(new Date());
		
		strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(u));
		User jsonToPojo = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), User.class);
		return XikaJSONResult.ok(jsonToPojo);
	}
	
}
