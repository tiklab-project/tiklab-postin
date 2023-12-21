package io.thoughtware.postin.support.sendrequest.util;

import io.thoughtware.core.exception.SystemException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * http请求类型
 */
@Service
public class HttpMethodUtils {

    public HttpMethod getMehtod(String method){
        //GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;

        if("get".equals(method)){
            return HttpMethod.GET;
        }else if("post".equals(method)){
            return HttpMethod.POST;
        }else if("head".equals(method)){
            return HttpMethod.HEAD;
        }else if("put".equals(method)){
            return HttpMethod.PUT;
        }else if("patch".equals(method)){
            return HttpMethod.PATCH;
        }else if("delete".equals(method)){
            return HttpMethod.DELETE;
        }else if("options".equals(method)){
            return HttpMethod.OPTIONS;
        }else if("trace".equals(method)){
            return HttpMethod.TRACE;
        }else{
            throw new SystemException("not support http method type:" + method);
        }
    }
}
