package com.kingdee.sqkg.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingdee.sqkg.domain.entity.*;
import com.kingdee.sqkg.mapper.TBdPersonMapper;
import com.kingdee.sqkg.mapper.TOrgCtrlunitMapper;
import com.kingdee.sqkg.service.EmployeeService;
import com.kingdee.sqkg.util.EasUtil;
import com.kingdee.sqkg.util.Xml2JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebService(serviceName = "EmployeeService", // 与接口中指定的name一致
        targetNamespace = "http://service.sqkg.kingdee.com",
        endpointInterface = "com.kingdee.sqkg.service.EmployeeService"// 接口地址
)
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EasUtil easUtil;

    @Autowired
    private TBdPersonMapper personMapper;
    @Autowired
    private TOrgCtrlunitMapper ctrlunitMapper;
    @Value(value = "${eas.employee.path}")
    String employeePath;
    @Value(value = "${eas.login.path}")
    String loginUrl;
    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Override
    public ResponseMessage save(User users, String CustomSOAPHeader) throws Exception {
        logger.info("get Person:"+JSON.toJSONString(users));
        String now = DateUtil.now();
        String sessionId = easUtil.getSessionId(loginUrl);
        if (!StrUtil.isEmpty(sessionId)) {
            try {
                String companyCode = users.getCompanyCode();
                String organizationCode = users.getOrganizationCode();
                String organizationName = users.getOrganizationName();
                String departmentCode = users.getDepartmentCode();
                String departmentName = users.getDepartmentName();
                System.err.println(users.getLoginName());
                if (StrUtil.hasBlank(users.getLoginName())) {
                    return ResponseMessage.fail("用户名不能为空");
                }
                if (StrUtil.hasBlank(users.getUserName())) {
                    return ResponseMessage.fail( "用户名不能为空");
                }
                if (StrUtil.hasBlank(users.getOrganizationCode())) {
                    return ResponseMessage.fail( "组织编码不能为空");
                }
                QueryWrapper<TOrgCtrlunit> ctrlunitQueryWrapper = new QueryWrapper<>();
                ctrlunitQueryWrapper.eq("fnumber",users.getOrganizationCode());
                TOrgCtrlunit tOrgCtrlunit = ctrlunitMapper.selectOne(ctrlunitQueryWrapper);
                if (!ObjectUtil.isNotNull(tOrgCtrlunit)){
                    return ResponseMessage.fail("组织编码不存在");
                }
                    String result = easUtil.importPersonData(employeePath, sessionId, users.getLoginName(),
                            users.getUserName(), users.getGender(), users.getMobile(), users.getEmail(),
                            users.getOfficeNunmber(), users.getOrganizationCode(), now);
                    String s = Xml2JsonUtil.xml2JSON(result);
                    JSONObject object = JSON.parseObject(s);
                    JSONObject object1 = JSON.parseObject(object.getString("Result"));
                    String retstatus = object1.getString("retstatus");
                    String strip = StringUtils.strip(retstatus.toString(), "[]");
                    retstatus = strip.replace("\"", "");
                    if (retstatus.equals("0")) {
                        return ResponseMessage.succ();
                    } else {
                        String errorMessages = object1.getString("errorMessages");
                        errorMessages = StringUtils.strip(errorMessages.toString(), "[]");
                        String errorMessage = StringUtils.strip(JSONObject.parseObject(errorMessages).getString("errorMessage"),
                                "[]").replace("\"", "");
                        System.err.println(errorMessage);
                        QueryWrapper<TBdPerson> personQueryWrapper = new QueryWrapper<>();
                        personQueryWrapper.eq("fnumber",users.getLoginName());
                        TBdPerson tBdPerson = personMapper.selectOne(personQueryWrapper);
                        if (ObjectUtil.isNotNull(tBdPerson)) {
                            return ResponseMessage.succ("修改成功");
                        }
                        return ResponseMessage.fail(errorMessage);
                    }
            } catch (Exception e) {
                return ResponseMessage.fail( e.getMessage());
            }
        } else {
            return ResponseMessage.fail( "网络通信异常");
        }
    }
}

