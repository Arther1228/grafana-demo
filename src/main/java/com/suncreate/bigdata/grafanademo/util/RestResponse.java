package com.suncreate.bigdata.grafanademo.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author wangyushuai@fang.com
 * @date 2018/9/26
 * REST请求响应工具类 ,在controller控制器中，做为接口返回值  return RestResponse.buildSuccess(articles);
 */
@Data
public class RestResponse<T> implements Serializable {
 
    private final static long serialVersionUID = 1L;
    /**
     * 成功
     */
    private final static int STATUS_SUCCESS = 200;
 
    /**
     * 代码错误
     */
    private final static int STATUS_ERROR_INTERNAL_SERVER_ERROR = 500;
 
    /**
     * 服务不可用（针对熔断&服务降级的情况）
     */
    private final static int STATUS_ERROR_SERVICE_UNAVAILIABLE = 503;
 
 
    private int status;
    private Object data;
    private String message;
 
    /**
     * 时间戳并格式化
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "lz",timezone = "GMT+8")
    private Date timestamp;
 
    /**
     * 程序耗时
     */
    private long time;
 
    public RestResponse(int code, String message, Object data) {
        super();
        this.status = code;
        this.message = message;
        this.data = data;
    }
 
    /**
     * 请求成功
     * @param data
     * @return
     */
    public static RestResponse ok(Object data) {
        return new RestResponse(STATUS_SUCCESS, "success", data);
    }
 
 
    /**
     * 代码错误
     * @param data
     * @return
     */
    public static RestResponse buildError_InternalServerError(Object data) {
        return new RestResponse(STATUS_ERROR_INTERNAL_SERVER_ERROR, "error", data);
    }
 
    /**
     * 返回错误码（服务不可用时，返回此方法），但推荐直接使用异常类
     * @param data
     * @return
     */
    public static RestResponse buildError_ServiceUnavailable(Object data) {
        return new RestResponse(STATUS_ERROR_SERVICE_UNAVAILIABLE, "error", data);
    }
}