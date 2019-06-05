package com.xika.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.xika.exception.BizExceptionEnum;
import com.xika.exception.XikaException;

public class BaseController {

	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 校验请求参数
	 * 
	 * @param arr     请求参数校验
	 * @param encType 加密类型1001 md5 1002 rsa
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected Map<String, String> reqJsonStr(String arr[],HttpServletRequest request) throws Exception {
		Map<String, String> dataMap = new HashMap<>();
		// body中获取业务参
		String inParam = request.getParameter("data");
		// header中获取公共参
		String sign = request.getHeader("sign"); // 签名
		String osName = request.getHeader("osName"); // 版本 1001 ios 1002 android 1003 h5
		String memberNo = request.getHeader("memberNo"); // 会员账号
		String version = request.getHeader("version");
		String source = request.getHeader("source");// 来源 0 门店 1用户 2推客
		
		JSONObject jsonObject = JSONObject.parseObject(inParam);
		String reqTime = jsonObject.get("reqTime").toString();// 请求时间，放于业务参数内
		// 判断入参是否一致
		JSONObject reqParam = jsonObject.getJSONObject("reqParam");
		dataMap.put("memberNo", memberNo);
		dataMap.put("osName", osName);
		dataMap.put("sign", sign);
		dataMap.put("version", version);
		dataMap.put("source", source);
		dataMap.put("reqTime", reqTime);
		if (arr != null && arr.length > 0) {
			for (String key : arr) {
				String val = reqParam.get(key).toString();
				if (StringUtils.isEmpty(val)) {
					throw new XikaException(BizExceptionEnum.NECESSARY_PARAM_NULL);
				}
				dataMap.put(key, val);
			}

		}
		return dataMap;
	}


	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
	 * 
	 * @return ip
	 */
	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		logger.info("获取客户端ip: " + ip);
		return ip;
	}

}
