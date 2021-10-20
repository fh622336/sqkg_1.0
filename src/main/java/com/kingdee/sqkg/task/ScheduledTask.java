package com.kingdee.sqkg.task;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingdee.sqkg.domain.entity.AccountingDepartment;
import com.kingdee.sqkg.domain.entity.TOrgBaseunit;
import com.kingdee.sqkg.mapper.*;
import com.kingdee.sqkg.service.TBdCustomerService;
import com.kingdee.sqkg.service.TBdSupplierService;
import com.kingdee.sqkg.util.EasUtil;
import com.kingdee.sqkg.util.RedisUtil;
import com.kingdee.sqkg.util.esbMdmUtil;
import com.kingdee.sqkg.util.getBeforeDate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {
    private Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    @Value(value = "${mdm.banl.path}")
    String bankUrl;
    @Value(value = "${mdm.department.path}")
    String departMentUrl;
    @Value(value = "${eas.unit.path}")
    String unitUrl;
    @Value(value = "${eas.login.path}")
    String loginUrl;
    @Value(value = "${eas.admin.path}")
    String adminPath;
    @Value(value = "${mdm.account.path}")
    String accountPath;
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
    private TBdSupplierMapper supplierMapper;
    @Autowired
    private TBdSuppliercompanyinfoMapper suppliercompanyinfoMapper;

    @Autowired
    private TBdSupplierService supplierService;
    @Autowired
    private TBdCustomerService customerService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private  EasUtil easutil;

    //    // cron接受cron表达式，根据cron表达式确定定时规则
    // @Scheduled(cron = "0/2 * * * * ?")  //每5秒执行一次
    public void testCron() throws Exception {
//         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//         Date dBefore = new Date();
//         String lastDate = sdf.format(dBefore); // 格式化前一天
//         String beforeDate = getBeforeDate.getSpecifiedDayBefore(lastDate);
//         String sdata = beforeDate + "~" + lastDate;
//         System.err.println(sdata);
//        String sessionId = EasUtil.getSessionId(loginUrl);
//        List<BeAccountBank> bankList = esbMdmUtil.bankAccountQuery(bankUrl, "", "eas:welcome1",sdata);
//        List<BeAccountBank> collect = bankList.stream()
//                .filter(s -> s.getFstatus().equals("1"))
//                .collect(Collectors.toList());
//        System.err.println("collect:::" + collect);
//         System.err.println("bankList:::" + JSON.toJSONString(bankList));
//        for (BeAccountBank bank : collect) {
//             String result = JSON.toJSONString(bank);
//             String bankResult = EasUtil.addBank(result, sessionId);
//             System.err.println(bankResult);
//        }
    }

    //  @Scheduled(cron = "0/1 * * * * ? ")  //每1秒执行一次
    public void testCron1() throws Exception {
        System.err.println(unitUrl);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(new Date());
        String sessionId = easutil.getSessionId(loginUrl);
        List<AccountingDepartment> departmentList = esbMdmUtil.accountingDepartmentQuery(departMentUrl, "", "eas:welcome1");
        for (AccountingDepartment department : departmentList) {
            String level = department.getLevel();
            String parentNumber = department.getParentNumber();
            String number = department.getNumber();
            if (StringUtils.equals(level, "1") || StringUtils.equals(level, "2")) {
                QueryWrapper<TOrgBaseunit> baseunitParentQueryWrapper = new QueryWrapper<>();
                baseunitParentQueryWrapper.eq("fnumber", parentNumber);
                TOrgBaseunit orgBaseUnit = baseunitMapper.selectOne(baseunitParentQueryWrapper);
                if (org.springframework.util.StringUtils.isEmpty(orgBaseUnit)) {
                    System.err.println("上级组织编码不存在！" + parentNumber);
                } else {
                    QueryWrapper<TOrgBaseunit> baseunitQueryWrapper = new QueryWrapper<>();
                    baseunitQueryWrapper.eq("fnumber", number);
                    TOrgBaseunit tOrgBaseunit = baseunitMapper.selectOne(baseunitQueryWrapper);
                    if (org.springframework.util.StringUtils.isEmpty(tOrgBaseunit)) {
                        System.err.println("组织编码不存在！允许新增:" + number);
                        String paramS = "<![CDATA[<DataInfo bostype=\"CCE7AED4\" op=\"4\">" +
                                "<DataHead><creator>user</creator><CU>" + number.substring(0, 3) + "</CU>" +
                                "<parent>" + parentNumber + "</parent>" +
                                "<name>" + department.getFname() + "</name>" +
                                "<number>" + number + "</number></DataHead>" +
                                "</DataInfo>]]>";
                        String orgResult = easutil.addBaseUnit(sessionId, paramS, unitUrl);
                        String adminResult = easutil.addAdminOrg(number, parentNumber, createTime, adminPath, sessionId);
                        String relationResult = easutil.addUnitRelation(number, number.substring(0, 3), adminPath, sessionId);
                    }
                }
            }
        }
    }

    // @Scheduled(cron = "0 */1 * * * ?") //每2秒执行一次
  //  @Scheduled(cron = "0 0/10 * * * ?")
    public void testCron2() throws Exception {
        supplierService.addSupplier();
    }

   //  @Scheduled(cron = "0 0/10 * * * ?") //每3秒执行一次
    public void testCron3() throws Exception {
        customerService.addCustomer();
    }

     //@Scheduled(cron = "0/4 * * * * ? ")  //每4秒执行一次
    public void testCron4() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date dBefore = new Date();
//        String lastDate = sdf.format(dBefore); // 格式化前一天
//        String beforeDate = getBeforeDate.getSpecifiedDayBefore(lastDate);
//        String sdata = beforeDate + "~" + lastDate;
//        System.err.println(sdata);
//        List<AccountView> accountViewList = esbMdmUtil.AccountViewQuery(accountPath, "", "eas:welcome1", sdata);
//        String s = JSON.toJSONString(accountViewList);
//        System.err.println(s);
    //     System.err.println(RedisUtil.hasKey("Session"));
      //   System.err.println(easutil.getSessionId("http://172.16.102.36:8080/ormrpc/services/EASLogin?wsdl"));
//        String sessionId = esbMdmUtil.getSessionId("http://172.16.102.36:8080/ormrpc/services/EASLogin?wsdl");
//        System.err.println(sessionId);



    }
}
