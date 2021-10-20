package com.kingdee.sqkg.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingdee.sqkg.domain.entity.*;
import com.kingdee.sqkg.mapper.*;
import com.kingdee.sqkg.service.TBdCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kingdee.sqkg.util.EasUtil;
import com.kingdee.sqkg.util.Xml2JsonUtil;
import com.kingdee.sqkg.util.esbMdmUtil;
import com.kingdee.sqkg.util.getBeforeDate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 樊浩
 * @since 2021-09-01
 */
@Service
public class TBdCustomerServiceImpl extends ServiceImpl<TBdCustomerMapper, TBdCustomer> implements TBdCustomerService {
    private Logger logger = LoggerFactory.getLogger(TBdCustomerServiceImpl.class);
    @Autowired
    private TOrgBaseunitMapper baseunitMapper;
    @Autowired
    private TOrgCtrlunitMapper ctrlunitMapper;
    @Autowired
    private TBdDatabasedassignMapper databasedassignMapper;
    @Autowired
    private TBdCustomerMapper customerMapper;
    @Autowired
    private TBdCustomercompanyinfoMapper customercompanyinfoMapper;
    @Autowired
    private TBdCustomercompanybankMapper customercompanybankMapper;
    @Autowired
    private TOrgAdminMapper adminMapper;
    @Autowired
    private EasUtil easutil;
    @Value(value = "${eas.login.path}")
    String loginUrl;
    @Value(value = "${eas.assignCustom.path}")
    String assignCustompath;
    @Value(value = "${eas.custom.path}")
    String custompath;
    @Value(value = "${eas.customSupplier.path}")
    String customSupplierpath;

    public void addCustomer() throws Exception {
        String sessionId = easutil.getSessionId(loginUrl);
        if (!StrUtil.isEmpty(sessionId)) {
            String beforeDate = getBeforeDate.getSpecifiedDayBefore();
            String[] pages = {"1", "2","3"};
            for (String currentPage : pages) {
                List<Custom> customList = esbMdmUtil.CustomQuery(custompath, "", "eas:welcome1", beforeDate, currentPage);
                for (Custom custom : customList) {
                    String obj = JSON.toJSONString(custom);
                    String result = easutil.addCustomer(sessionId, obj.toString(), customSupplierpath);
                    String status = JSONObject.parseObject(result).getString("status");
                    if (StringUtils.equals(status, "0")) {
                        //客户
                        QueryWrapper<TBdCustomer> customerQueryWrapper = new QueryWrapper<>();
                        customerQueryWrapper.eq("fnumber", custom.getNumber());
                        TBdCustomer tBdCustomer = customerMapper.selectOne(customerQueryWrapper);
                        //管理单元
                        QueryWrapper<TOrgCtrlunit> ctrlQueryWrapper = new QueryWrapper<>();
                        ctrlQueryWrapper.eq("fnumber", custom.getDESC4());
                        TOrgCtrlunit tOrgCtrlunit = ctrlunitMapper.selectOne(ctrlQueryWrapper);
                        if (org.springframework.util.StringUtils.isEmpty(tOrgCtrlunit)) {
                            System.err.println("管理单元不存在！");
                        } else {
                            //基础资料分配记录
                            QueryWrapper<TBdDatabasedassign> databasedassignQueryWrapper = new QueryWrapper<>();
                            databasedassignQueryWrapper.eq("fdatabasedid", tBdCustomer.getFid());
                            databasedassignQueryWrapper.eq("fassigncuid", tOrgCtrlunit.getFid());
                            Integer row = databasedassignMapper.selectCount(databasedassignQueryWrapper);
                            if (row == 0) {
                                QueryWrapper<TOrgAdmin> adminQueryWrapper = new QueryWrapper<>();
                                //财务
                                adminQueryWrapper.eq("fiscompanyorgunit", 1);
                                //销售
                                //      adminQueryWrapper.eq("fissaleorgunit", 1);
                                adminQueryWrapper.eq("FNumber", custom.getDESC4());
                                TOrgAdmin tOrgAdmin = adminMapper.selectOne(adminQueryWrapper);
                                if (ObjectUtil.isNotNull(tOrgAdmin)) {
                                    String flongnumber = tOrgCtrlunit.getFlongnumber();
                                    String[] split = flongnumber.split("!");
                                    StringBuffer sb = new StringBuffer();
                                    sb.append("<![CDATA[");
                                    sb.append("<AssignData>");
                                    sb.append("<AssignHead>");
                                    sb.append("<sourceCU>210</sourceCU>");
                                    sb.append("<dataNumber>").append(custom.getNumber()).append("</dataNumber>");
                                    sb.append("</AssignHead>");
                                    sb.append("<AssignCUS>");
                                    for (int i = 0; i < split.length; i++) {
                                        String String = split[i];
                                        if (!String.equals("210")) {
                                            sb.append("<AssignCU>");
                                            sb.append("<cuNumber>").append(String).append("</cuNumber>");
                                            sb.append("</AssignCU>");
                                        }
                                    }
                                    sb.append("</AssignCUS>");
                                    sb.append("</AssignData>");
                                    sb.append(" ]]>");
                                    String assignCustomerResult = easutil.assignCustomer(sessionId, sb.toString(), assignCustompath);
                                    String s = Xml2JsonUtil.xml2JSON(assignCustomerResult);
                                    JSONObject object = JSON.parseObject(s);
                                    JSONObject object1 = JSON.parseObject(object.getString("Result"));
                                    String retstatus = object1.getString("retstatus");
                                    String strip = StringUtils.strip(retstatus.toString(), "[]");
                                    String str = strip.replace("\"", "");
                                    if (str.equals("0")) {
                                        TBdCustomer customer = new TBdCustomer();
                                        customer.setFid(tBdCustomer.getFid());
                                        customer.setCfkdtextfield(flongnumber);
                                        customerMapper.updateById(customer);
                                        QueryWrapper<TBdCustomercompanyinfo> customercompanyinfoQueryWrapper = new QueryWrapper<>();
                                        customercompanyinfoQueryWrapper.eq("fcontrolunitid", tOrgCtrlunit.getFid());
                                        customercompanyinfoQueryWrapper.eq("fcustomerid", tBdCustomer.getFid());
                                        TBdCustomercompanyinfo tBdCustomercompanyinfo = customercompanyinfoMapper.selectOne(customercompanyinfoQueryWrapper);

                                        TBdCustomercompanyinfo customercompanyinfo = new TBdCustomercompanyinfo();
                                        customercompanyinfo.setFid(tBdCustomercompanyinfo.getFid());
                                        customercompanyinfo.setCfstatus(custom.getDESC6());
                                        customercompanyinfoMapper.updateById(customercompanyinfo);
                                        String fid = tBdCustomercompanyinfo.getFid();
                                        QueryWrapper<TBdCustomercompanybank> customercompanybankQueryWrapper = new QueryWrapper<>();
                                        customercompanybankQueryWrapper.eq("fcustomercompanyinfoid", fid);
                                        Integer bankrows = customercompanybankMapper.selectCount(customercompanybankQueryWrapper);
                                        if (bankrows == 0) {
                                            if (!custom.getDESC23().equals("")) {
                                                JSONObject bankobj = new JSONObject();
                                                bankobj.put("bankaccount", custom.getDESC23());
                                                bankobj.put("bankaddress", "");
                                                bankobj.put("accountName", custom.getDESC21());
                                                bankobj.put("customerInfo", fid);
                                                easutil.importCustomerCompanyBank(sessionId, bankobj.toString(), customSupplierpath);
                                            }
                                        }
                                    }
                                }
                            } else {
                                TBdCustomer customer = new TBdCustomer();
                                customer.setFid(tBdCustomer.getFid());
                                customer.setFtxregisterno(custom.getDESC20());
                                customerMapper.updateById(customer);
                                QueryWrapper<TBdCustomercompanyinfo> customercompanyinfoQueryWrapper = new QueryWrapper<>();
                                customercompanyinfoQueryWrapper.eq("fcontrolunitid", tOrgCtrlunit.getFid());
                                customercompanyinfoQueryWrapper.eq("fcustomerid", tBdCustomer.getFid());
                                TBdCustomercompanyinfo tBdCustomercompanyinfo = customercompanyinfoMapper.selectOne(customercompanyinfoQueryWrapper);
                                TBdCustomercompanyinfo customercompanyinfo = new TBdCustomercompanyinfo();
                                customercompanyinfo.setFid(tBdCustomercompanyinfo.getFid());
                                customercompanyinfo.setCfstatus(custom.getDESC6());
                                customercompanyinfoMapper.updateById(customercompanyinfo);
                                QueryWrapper<TBdCustomercompanybank> customercompanybankQueryWrapper = new QueryWrapper<>();
                                customercompanybankQueryWrapper.eq("fcustomercompanyinfoid", tBdCustomercompanyinfo.getFid());
                                Integer bankrows = customercompanybankMapper.selectCount(customercompanybankQueryWrapper);
                                if (bankrows == 0) {
                                    if (!custom.getDESC23().equals("")) {
                                        JSONObject bankobj = new JSONObject();
                                        bankobj.put("bankaccount", custom.getDESC23());
                                        bankobj.put("bankaddress", "");
                                        bankobj.put("accountName", custom.getDESC21());
                                        bankobj.put("customerInfo", tBdCustomercompanyinfo.getFid());
                                        easutil.importCustomerCompanyBank(sessionId, bankobj.toString(), customSupplierpath);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
