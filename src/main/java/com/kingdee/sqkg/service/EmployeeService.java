package com.kingdee.sqkg.service;

import com.kingdee.sqkg.domain.entity.ResponseMessage;
import com.kingdee.sqkg.domain.entity.User;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "EmployeeService", // 暴露服务名称
        targetNamespace = "http://service.sqkg.kingdee.com"// 命名空间,一般是接口的包名倒序
)
public interface EmployeeService {
    public ResponseMessage save(@WebParam(name = "userInfo") User users, @WebParam(header = true, name = "CustomSOAPHeader") String CustomSOAPHeader) throws Exception;
}
