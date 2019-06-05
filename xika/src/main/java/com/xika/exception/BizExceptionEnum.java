package com.xika.exception;
/**
 * 所有业务异常的枚举
 *
 * @author huangrui
 * @date 2019年5月12日 下午5:04:51
 */
public enum BizExceptionEnum implements ServiceExceptionEnum {
	 
    /**
     * token异常
     */
    TOKEN_EXPIRED(700, "token过期"),
    
    TOKEN_ERROR(701, "token验证失败"),

    /**
     * 签名异常
     */
    SIGN_ERROR(702, "签名验证失败"),

    /**
     * 其他
     */
    AUTH_REQUEST_ERROR(703, "账号密码错误"),
	  /**
     * 账号不存在，请注册
     */
    ACCOUNT_NOT_FUND(704, " 账号不存在，请前往注册！"),
    /**
     * 账号已存在，请登录
     */
    ACCOUNT_HAS_EXISTS(706, "账号已存在，请登录"),
	/**
     *手机号不合法
     */
    PHONE_NUM_IS_ILLEGAL(707, "手机号不合法"),
	
	/**
	 * 请先获取验证码
	 */
	PLEASE_GET_MESSAGE(708, "请先获取验证码"),
	
	/**
	 * 验证码已失效，请重新获取
	 */
	MESSAGE_HAS_PASSTIME(708, "验证码已失效，请重新获取"),
	
	/**
	 * 密码不能和修改前相同
	 */
	OLDPASSWORD_SAME(709, "密码不能和修改前相同"),
	
	/**
	 * 获取微信信息失败
	 */
	GET_WEIXIN_INFO_EXCEPTION(710, "获取微信信息失败"),
	
	/**
	 * 不合法参数信息
	 */
	ILLEGAL_INFO(711, "不合法参数信息"),
	
	/**
	 * 无数据
	 */
	DATA_NOT_EXISTS(712, "无数据"),
	
	/**
	 * 图片过大
	 */
	IMG_SIZE_IS_TOO_BIG(713, "图片过大"),
	
	/**
     * 系统未知异常，请稍后重试
     */
    SYSTEM_UNKNOW_EXCEPTION(400, "系统未知异常，请稍后重试"),
	
	/**
     * 必传参数为空
     */
    NECESSARY_PARAM_NULL(714, "必传参数为空");

    BizExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
