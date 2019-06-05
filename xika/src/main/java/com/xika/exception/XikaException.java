package com.xika.exception;

/**   
* Copyright: Copyright (c) 2019 xika ltd
* 
* @ClassName: XikaException.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: nec
* @date: 2019年6月5日 上午11:33:53 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月5日     nec           v1.0.0               修改原因
*/
public class XikaException extends RuntimeException {

    private Integer code;

    private String message;

    public XikaException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

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
