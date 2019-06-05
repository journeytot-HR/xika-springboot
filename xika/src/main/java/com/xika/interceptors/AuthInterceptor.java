package com.xika.interceptors;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.xika.config.Resource;
import com.xika.utils.JsonUtils;
import com.xika.utils.MD5;
import com.xika.utils.XikaJSONResult;

public class AuthInterceptor implements HandlerInterceptor  {
	
	final static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object object) throws Exception {
		logger.error("AuthInterceptor handle request uri-{}",request.getRequestURI());
		String reqMethod = request.getMethod().toLowerCase();
		// body中获取业务参
		String inParam = request.getParameter("data");
		// header中获取公共参
		String sign = request.getHeader("sign"); // 签名
		String osName = request.getHeader("osName"); // 版本 1001 ios; 1002 android; 1003 h5
		String memberNo = request.getHeader("memberNo"); // 会员账号
		String version = request.getHeader("version");
		String source = request.getHeader("source");// 来源 0 门店 1用户 2推客
		logger.info("Request-Method==>{},memberNo==>{},source==>{}",reqMethod,memberNo,source);
		// 验证请求参数是否为空
		boolean checkReqParamIfBlank = checkReqParam(inParam, sign, osName, memberNo, reqMethod,source,version);
		if (checkReqParamIfBlank) {
			returnErrorResponse(response, XikaJSONResult.errorMsg("Auth fail..Necessary parameters are null"));
			return false;
		}
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		Resource resource = (Resource) wac.getBean("Resource");
		String signs = MD5.MD5EncodeUTF8(inParam + resource.getSignKey());
		if (!sign.equals(signs)) {
			returnErrorResponse(response, XikaJSONResult.errorMsg("Auth.fail..Illegal Signature Verification"));
			return false;
		}
		return true;
	}
	
	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object object, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行
	 * （主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object object, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void returnErrorResponse(HttpServletResponse response, XikaJSONResult result) 
			throws IOException, UnsupportedEncodingException {
		OutputStream out=null;
		try{
		    response.setCharacterEncoding("utf-8");
		    response.setContentType("text/json");
		    out = response.getOutputStream();
		    out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
		    out.flush();
		} finally{
		    if(out!=null){
		        out.close();
		        out=null;
		    }
		}
	}
	
	public static boolean checkReqParam(String encParam, String sign, String osName, String memberNo, String reqMethod,String source,String version) {
		boolean flag = true;
		if (encParam == null || "".equals(encParam)) {
			return flag;
		}
		if (sign == null || "".equals(sign)) {
			return flag;
		}
		if (osName == null || "".equals(osName)) {
			return flag;
		}
		if (memberNo == null || "".equals(memberNo)) {
			return flag;
		}
		if (!"1001".equals(osName) && !"1002".equals(osName) && !"1003".equals(osName) && !"1004".equals(osName)) {
			return flag;
		}
		if (!"post".equals(reqMethod)) {
			return flag;
		}
		if (StringUtils.isEmptyOrWhitespace(source)) {
			return flag;
		}
		if (StringUtils.isEmptyOrWhitespace(version)) {
			return flag;
		}
		return false;
	}
}
