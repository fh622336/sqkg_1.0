package com.kingdee.sqkg.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingdee.sqkg.domain.entity.*;
import com.kingdee.sqkg.mapper.*;
import com.kingdee.sqkg.service.TBdSupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kingdee.sqkg.util.EasUtil;
import com.kingdee.sqkg.util.Xml2JsonUtil;
import com.kingdee.sqkg.util.esbMdmUtil;
import com.kingdee.sqkg.util.getBeforeDate;
import org.apache.commons.lang.StringUtils;
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
public class TBdSupplierServiceImpl extends ServiceImpl<TBdSupplierMapper, TBdSupplier> implements TBdSupplierService {
    @Autowired
    private TOrgBaseunitMapper baseunitMapper;
    @Autowired
    private TOrgCtrlunitMapper ctrlunitMapper;
    @Autowired
    private TBdDatabasedassignMapper databasedassignMapper;
    @Autowired
    private TBdSupplierMapper supplierMapper;
    @Autowired
    private TBdSuppliercompanyinfoMapper suppliercompanyinfoMapper;
    @Autowired
    private TBdSuppliercompanybankMapper suppliercompanybankMapper;
    @Autowired
    private EasUtil easutil;
    @Value(value = "${eas.login.path}")
    String loginUrl;
    @Value(value = "${eas.supplier.path}")
    String supplierpath;
    @Value(value = "${eas.customSupplier.path}")
    String customSupplierpath;
    @Value(value = "${eas.assignSupplier.path}")
    String assignSupplierpath;

    @Override
    public void addSupplier() throws Exception {
        String sessionId = easutil.getSessionId(loginUrl);
        if (!StrUtil.isEmpty(sessionId)) {
            String beforeDate = getBeforeDate.getSpecifiedDayBefore();
            String[] pages = {"1", "2","3"};
            for (String currentPage : pages) {
                List<Custom> customList = esbMdmUtil.SupplierQuery(supplierpath, "", "eas:welcome1", beforeDate, currentPage);
                for (Custom custom : customList) {
                    String obj = JSON.toJSONString(custom);
                    String result = easutil.addSupplier(sessionId, obj, customSupplierpath);
                    String status = JSONObject.parseObject(result).getString("status");
                    if (StringUtils.equals(status, "0")) {
                        //供应商
                        QueryWrapper<TBdSupplier> supplierQueryWrapper = new QueryWrapper<>();
                        supplierQueryWrapper.eq("fnumber", custom.getNumber());
                        TBdSupplier tBdSupplier = supplierMapper.selectOne(supplierQueryWrapper);
                        System.err.println("fid:::" + tBdSupplier.getFid());
                        //管理单元
                        QueryWrapper<TOrgCtrlunit> ctrlQueryWrapper = new QueryWrapper<>();
                        ctrlQueryWrapper.eq("fnumber", custom.getDESC4());
                        TOrgCtrlunit tOrgCtrlunit = ctrlunitMapper.selectOne(ctrlQueryWrapper);

                        if (!org.springframework.util.StringUtils.isEmpty(tOrgCtrlunit)) {
                            //基础资料分配记录
                            QueryWrapper<TBdDatabasedassign> databasedassignQueryWrapper = new QueryWrapper<>();
                            databasedassignQueryWrapper.eq("fdatabasedid", tBdSupplier.getFid());
                            databasedassignQueryWrapper.eq("fassigncuid", tOrgCtrlunit.getFid());
                            Integer row = databasedassignMapper.selectCount(databasedassignQueryWrapper);
                            if (row == 0) {
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
                                String s = easutil.assignSupplier(sessionId, sb.toString(), assignSupplierpath);
                                s = Xml2JsonUtil.xml2JSON(s);
                                JSONObject object = JSON.parseObject(s);
                                JSONObject object1 = JSON.parseObject(object.getString("Result"));
                                String retstatus = object1.getString("retstatus");
                                String strip = StringUtils.strip(retstatus.toString(), "[]");
                                String str = strip.replace("\"", "");
                                if (str.equals("0")) {
                                    TBdSupplier supplier = new TBdSupplier();
                                    supplier.setFid(tBdSupplier.getFid());
                                    supplier.setCfkdtextfield(flongnumber);
                                    supplierMapper.updateById(supplier);
                                    QueryWrapper<TBdSuppliercompanyinfo> suppliercompanyinfoQueryWrapper = new QueryWrapper<>();
                                    suppliercompanyinfoQueryWrapper.eq("fcontrolunitid", tOrgCtrlunit.getFid());
                                    suppliercompanyinfoQueryWrapper.eq("fsupplierid", tBdSupplier.getFid());
                                    TBdSuppliercompanyinfo tBdSuppliercompanyinfo = suppliercompanyinfoMapper.selectOne(suppliercompanyinfoQueryWrapper);
                                    TBdSuppliercompanyinfo suppliercompanyinfo = new TBdSuppliercompanyinfo();
                                    suppliercompanyinfo.setCfstatus(custom.getDESC6());
                                    QueryWrapper<TBdSuppliercompanyinfo> tBdSuppliercompanyinfoQueryWrapper = new QueryWrapper<>();
                                    tBdSuppliercompanyinfoQueryWrapper.eq("fsupplierid", tBdSupplier.getFid());
                                    tBdSuppliercompanyinfoQueryWrapper.eq("fcontrolunitid", tOrgCtrlunit.getFid());
                                    suppliercompanyinfoMapper.update(suppliercompanyinfo, tBdSuppliercompanyinfoQueryWrapper);
                                    String fid = tBdSuppliercompanyinfo.getFid();

                                    QueryWrapper<TBdSuppliercompanybank> suppliercompanybankQueryWrapper = new QueryWrapper<>();
                                    suppliercompanybankQueryWrapper.eq("fsuppliercompanyinfoid", fid);
                                    Integer bankrows = suppliercompanybankMapper.selectCount(suppliercompanybankQueryWrapper);
                                    if (bankrows == 0) {
                                        if (!custom.getDESC22().equals("")) {
                                            JSONObject bankobj = new JSONObject();
                                            bankobj.put("bank", custom.getDESC22());
                                            bankobj.put("bankAccount", custom.getDESC21());
                                            bankobj.put("supplierInfo", fid);
                                            easutil.importSupplierCompanyBank(sessionId, bankobj.toString(), customSupplierpath);
                                        }
                                    }
                                }
                            } else {
                                TBdSupplier supplier = new TBdSupplier();
                                supplier.setFid(tBdSupplier.getFid());
                                supplier.setFtaxregisterno(custom.getDESC20());
                                supplierMapper.updateById(supplier);
                                QueryWrapper<TBdSuppliercompanyinfo> suppliercompanyinfoQueryWrapper = new QueryWrapper<>();
                                suppliercompanyinfoQueryWrapper.eq("fcontrolunitid", tOrgCtrlunit.getFid());
                                suppliercompanyinfoQueryWrapper.eq("fsupplierid", tBdSupplier.getFid());
                                TBdSuppliercompanyinfo tBdSuppliercompanyinfo = suppliercompanyinfoMapper.selectOne(suppliercompanyinfoQueryWrapper);
                                TBdSuppliercompanyinfo suppliercompanyinfo = new TBdSuppliercompanyinfo();
                                suppliercompanyinfo.setCfstatus(custom.getDESC6());
                                QueryWrapper<TBdSuppliercompanyinfo> tBdSuppliercompanyinfoQueryWrapper = new QueryWrapper<>();
                                tBdSuppliercompanyinfoQueryWrapper.eq("fsupplierid", tBdSupplier.getFid());
                                tBdSuppliercompanyinfoQueryWrapper.eq("fcontrolunitid", tOrgCtrlunit.getFid());
                                suppliercompanyinfoMapper.update(suppliercompanyinfo, tBdSuppliercompanyinfoQueryWrapper);
                                QueryWrapper<TBdSuppliercompanybank> suppliercompanybankQueryWrapper = new QueryWrapper<>();
                                suppliercompanybankQueryWrapper.eq("fsuppliercompanyinfoid", tBdSuppliercompanyinfo.getFid());
                                Integer bankrows = suppliercompanybankMapper.selectCount(suppliercompanybankQueryWrapper);
                                if (bankrows == 0) {
                                    if (!custom.getDESC22().equals("")) {
                                        JSONObject bankobj = new JSONObject();
                                        bankobj.put("bank", custom.getDESC22());
                                        bankobj.put("bankAccount", custom.getDESC21());
                                        bankobj.put("supplierInfo", tBdSuppliercompanyinfo.getFid());
                                        easutil.importSupplierCompanyBank(sessionId, bankobj.toString(), customSupplierpath);
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
