package com.kingdee.sqkg.util;

import com.kingdee.sqkg.domain.entity.AccountView;
import com.kingdee.sqkg.domain.entity.AccountingDepartment;
import com.kingdee.sqkg.domain.entity.BeAccountBank;
import com.kingdee.sqkg.domain.entity.Custom;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class esbMdmUtil {
    private final static Logger logger = LoggerFactory.getLogger(esbMdmUtil.class);
 //
       public  static    List<BeAccountBank>   bankAccountQuery(String url,String param,String password,String date){
           String result="";
           List<BeAccountBank> bankList=new ArrayList<>();
           String params="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"http://www.sxqc.com/osb/MasterDataSB/BankAccountQuery/Mdm/Schema/v1.0-GET\">\n" +
                   "   <soapenv:Header/>\n" +
                   "   <soapenv:Body>\n" +
                   "      <v1:Request>\n" +
                   "         <v1:ESB>\n" +
                   "            <v1:DATA>\n" +
                   "               <v1:DATAINFOS>\n" +
                   "                  <!--1 or more repetitions:-->\n" +
                   "                  <v1:DATAINFO>\n" +
                   "                     <v1:CODE></v1:CODE>\n" +
                   "                     <v1:DESC1></v1:DESC1>\n" +
                   "                     <v1:DESC3></v1:DESC3>\n" +
                   "                     <v1:DESC10></v1:DESC10>\n" +
                   "                     <v1:DESC11>201791000016</v1:DESC11>\n" +
                   "                     <v1:DESC12></v1:DESC12>\n" +
                   "                     <v1:DESC14></v1:DESC14>\n" +
                   "                     <v1:DESC17></v1:DESC17>\n" +
                   "                     <v1:DESC5></v1:DESC5>\n" +
                   "                     <v1:DESC6></v1:DESC6>\n" +
                   "                     <v1:DESC7></v1:DESC7>\n" +
                   "                     <v1:LASTMODIFYRECORDTIME>"+ date+"</v1:LASTMODIFYRECORDTIME>\n"+
                   "                     <v1:UUID></v1:UUID>\n" +
                   "                  </v1:DATAINFO>\n" +
                   "                  <v1:PUUID></v1:PUUID>\n" +
                   "               </v1:DATAINFOS>\n" +
                   "               <v1:SPLITPAGE>\n" +
                   "                  <v1:COUNTPERPAGE>200</v1:COUNTPERPAGE>\n" +
                   "                  <v1:CURRENTPAGE>1</v1:CURRENTPAGE>\n" +
                   "               </v1:SPLITPAGE>\n" +
                   "            </v1:DATA>\n" +
                   "         </v1:ESB>\n" +
                   "      </v1:Request>\n" +
                   "   </soapenv:Body>\n" +
                   "</soapenv:Envelope>";
       try {
           result = HttpUtil.sendclientPost(url, params, password);
           if (StringUtils.isEmpty(result)){
               return  bankList;
           }
           Document doc = DocumentHelper.parseText(result);
           Element root = doc.getRootElement();
           Element head = root.element("Body");
           Element element = head.element("Response");
           Element esb = element.element("ESB");
           Element data = esb.element("DATA");
           Element INFOS = data.element("DATAINFOS");
           List<Element> elements = INFOS.elements("DATAINFO");
           for (int i=0;i<elements.size();i++){
               BeAccountBank beAccountBank=   BeAccountBank.builder().number( elements.get(i).element("FAccountBankId").getTextTrim()).
                       fname( elements.get(i).element("FName").getTextTrim()
                       ).fstatus(elements.get(i).element("DESC14").
                       getTextTrim()).accountNumber(elements.get(i).element("DESC7").
                       getTextTrim()).adminNumber(elements.get(i).element("DESC1").
                       getTextTrim()).openingBank(elements.get(i).element("DESC10").
                       getTextTrim()).bankNumber(elements.get(i).element("DESC11").
                       getTextTrim()).date(elements.get(i).element("DESC12").
                       getTextTrim()).currenyName(elements.get(i).element("DESC17").
                       getTextTrim()).accountViewNumber(elements.get(i).element("DESC3").
                       getTextTrim()).build();
               bankList.add(beAccountBank);
           }
           return  bankList;
       }catch (Exception ex) {
           logger.info("获取主数据银行信息有误,报错信息为:" + ex.getMessage());
           return bankList;
       }
    }


    public  static    List<AccountingDepartment>   accountingDepartmentQuery(String url, String param, String password){
        String result="";
        List<AccountingDepartment> departmentList=new ArrayList<>();
        String params="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"http://www.sxqc.com/osb/MasterDataSB/AccountingOrgQuery/Mdm/Schema/v1.0-GET\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <v1:Request>\n" +
                "         <v1:ESB>\n" +
                "            <v1:DATA>\n" +
                "               <v1:DATAINFOS>\n" +
                "                  <!--1 or more repetitions:-->\n" +
                "                  <v1:DATAINFO>\n" +
                "                     <v1:CODE>1002115</v1:CODE>\n" +
                "                     <v1:DESC1></v1:DESC1>\n" +
                "                     <v1:DESC10></v1:DESC10>\n" +
                "                     <v1:DESC15></v1:DESC15>\n" +
                "                     <v1:DESC19></v1:DESC19>\n" +
                "                     <v1:LASTMODIFYRECORDTIME>2021-08-26 00:00:00~2021-08-27 16:18:45</v1:LASTMODIFYRECORDTIME>\n" +
                "                     <v1:PARENTCODE></v1:PARENTCODE>\n" +
                "                     <v1:UUID></v1:UUID>\n" +
                "                  </v1:DATAINFO>\n" +
                "                  <v1:PUUID></v1:PUUID>\n" +
                "               </v1:DATAINFOS>\n" +
                "               <v1:SPLITPAGE>\n" +
                "                  <v1:COUNTPERPAGE></v1:COUNTPERPAGE>\n" +
                "                  <v1:CURRENTPAGE></v1:CURRENTPAGE>\n" +
                "               </v1:SPLITPAGE>\n" +
                "            </v1:DATA>\n" +
                "         </v1:ESB>\n" +
                "      </v1:Request>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        try {
            result = HttpUtil.sendclientPost(url, params, password);
            System.err.println(result);
            Document doc = DocumentHelper.parseText(result);
            Element root = doc.getRootElement();
            Element head = root.element("Body");
            Element element = head.element("Response");
            Element esb = element.element("ESB");
            Element data = esb.element("DATA");
            Element INFOS = data.element("DATAINFOS");
            List<Element> elements = INFOS.elements("DATAINFO");
            for (int i=0;i<elements.size();i++){
                AccountingDepartment departMent = AccountingDepartment.builder().number(elements.get(i).
                        element("CODE").getTextTrim()).
                        fname(elements.get(i).element("FName").getTextTrim()).fstatus(elements.get(i).element("FIsStart").getTextTrim()).
                        level(elements.get(i).element("DESC15").getTextTrim())
                        .parentNumber(elements.get(i).element("PARENTCODE").getTextTrim()).build();
                departmentList.add(departMent);
            }
            return  departmentList;
        }catch (Exception ex) {
            logger.info("获取主数据银行信息有误,报错信息为:" + ex.getMessage());
            return departmentList;
        }
    }


















    public  static    List<Custom>   CustomQuery(String url, String param, String password,String date,String page){
        String result="";
        List<Custom> customList=new ArrayList<>();
        String params="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"http://www.sxqc.com/osb/MasterDataSB/CustomerQuery/Mdm/Schema/v1.0-GET\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <v1:Request>\n" +
                "         <v1:ESB>\n" +
                "            <v1:DATA>\n" +
                "               <v1:DATAINFOS>\n" +
                "                  <!--1 or more repetitions:-->\n" +
                "                  <v1:DATAINFO>\n" +
                "                     <v1:CODE></v1:CODE>\n" +
                "                     <v1:DESC1></v1:DESC1>\n" +
                "                     <v1:DESC10></v1:DESC10>\n" +
                "                     <v1:DESC11></v1:DESC11>\n" +
                "                     <v1:DESC12></v1:DESC12>\n" +
                "                     <v1:DESC13></v1:DESC13>\n" +
                "                     <v1:DESC14></v1:DESC14>\n" +
                "                     <v1:DESC15></v1:DESC15>\n" +
                "                     <v1:DESC16></v1:DESC16>\n" +
                "                     <v1:DESC17></v1:DESC17>\n" +
                "                     <v1:DESC18></v1:DESC18>\n" +
                "                     <v1:DESC19></v1:DESC19>\n" +
                "                     <v1:DESC2></v1:DESC2>\n" +
                "                     <v1:DESC20></v1:DESC20>\n" +
                "                     <v1:DESC21></v1:DESC21>\n" +
                "                     <v1:DESC22></v1:DESC22>\n" +
                "                     <v1:DESC23></v1:DESC23>\n" +
                "                     <v1:DESC24></v1:DESC24>\n" +
                "                     <v1:DESC25></v1:DESC25>\n" +
                "                     <v1:DESC3></v1:DESC3>\n" +
                "                     <v1:DESC4></v1:DESC4>\n" +
                "                     <v1:DESC5></v1:DESC5>\n" +
                "                     <v1:DESC6></v1:DESC6>\n" +
                "                     <v1:DESC7></v1:DESC7>\n" +
                "                     <v1:DESC8></v1:DESC8>\n" +
                "                     <v1:DESC9></v1:DESC9>\n" +
                "                     <v1:LASTMODIFYRECORDTIME>"+date+"</v1:LASTMODIFYRECORDTIME>\n" +
                "                     <v1:UUID></v1:UUID>\n" +
                "                  </v1:DATAINFO>\n" +
                "               </v1:DATAINFOS>\n" +
                "               <v1:SPLITPAGE>\n" +
                "                  <v1:COUNTPERPAGE>1000</v1:COUNTPERPAGE>\n" +
                "                  <v1:CURRENTPAGE>" +page+"</v1:CURRENTPAGE>\n"+
                "               </v1:SPLITPAGE>\n" +
                "            </v1:DATA>\n" +
                "         </v1:ESB>\n" +
                "      </v1:Request>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        try {
            result = HttpUtil.sendclientPost(url, params, password);
            System.err.println("result:::"+result);
            logger.info(result);
            Document doc = DocumentHelper.parseText(result);
            Element root = doc.getRootElement();
            Element head = root.element("Body");
            Element element = head.element("Response");
            Element esb = element.element("ESB");
            Element data = esb.element("DATA");
            Element INFOS = data.element("DATAINFOS");
            List<Element> elements = INFOS.elements("DATAINFO");
            for (int i=0;i<elements.size();i++){
                Custom custom = Custom.builder().number(elements.get(i).
                        element("DESC3").getTextTrim()).
                        name(elements.get(i).element("DESC1").getTextTrim()).dESC20(elements.get(i).
                        element("DESC20").getTextTrim()).dESC15(elements.get(i).
                        element("DESC15").getTextTrim()).dESC16(elements.get(i).
                        element("DESC16").getTextTrim()).dESC17(elements.get(i).
                        element("DESC17").getTextTrim()).dESC4(elements.get(i).
                        element("DESC4").getTextTrim()).dESC6(elements.get(i).
                        element("DESC6").getTextTrim()).dESC11(elements.get(i).
                        element("DESC11").getTextTrim()).dESC23(elements.get(i).
                        element("DESC23").getTextTrim()).dESC21(elements.get(i).
                        element("DESC21").getTextTrim()).dESC12(elements.get(i).
                        element("DESC12").getTextTrim()).build();
                customList.add(custom);
            }
            return  customList;
        }catch (Exception ex) {
            logger.info("获取主数据客户信息有误,报错信息为:" + ex.getMessage());
            return customList;
        }
    }

    public  static    List<Custom>   SupplierQuery(String url, String param, String password,String date,String page){
        String result="";
        List<Custom> customList=new ArrayList<>();
        String params="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"http://www.sxqc.com/osb/MasterDataSB/SupplierQuery/Mdm/Schema/v1.0-GET\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <v1:Request>\n" +
                "         <v1:ESB>\n" +
                "            <v1:DATA>\n" +
                "               <v1:DATAINFOS>\n" +
                "                  <!--1 or more repetitions:-->\n" +
                "                  <v1:DATAINFO>\n" +
                "                     <v1:CODE></v1:CODE>\n" +
                "                     <v1:DESC1></v1:DESC1>\n" +
                "                     <v1:DESC10></v1:DESC10>\n" +
                "                     <v1:DESC11></v1:DESC11>\n" +
                "                     <v1:DESC12></v1:DESC12>\n" +
                "                     <v1:DESC13></v1:DESC13>\n" +
                "                     <v1:DESC14></v1:DESC14>\n" +
                "                     <v1:DESC15></v1:DESC15>\n" +
                "                     <v1:DESC16></v1:DESC16>\n" +
                "                     <v1:DESC17></v1:DESC17>\n" +
                "                     <v1:DESC18></v1:DESC18>\n" +
                "                     <v1:DESC19></v1:DESC19>\n" +
                "                     <v1:DESC2></v1:DESC2>\n" +
                "                     <v1:DESC20></v1:DESC20>\n" +
                "                     <v1:DESC21></v1:DESC21>\n" +
                "                     <v1:DESC22></v1:DESC22>\n" +
                "                     <v1:DESC23></v1:DESC23>\n" +
                "                     <v1:DESC24></v1:DESC24>\n" +
                "                     <v1:DESC25></v1:DESC25>\n" +
                "                     <v1:DESC26></v1:DESC26>\n" +
                "                     <v1:DESC3></v1:DESC3>\n" +
                "                     <v1:DESC4></v1:DESC4>\n" +
                "                     <v1:DESC5></v1:DESC5>\n" +
                "                     <v1:DESC6></v1:DESC6>\n" +
                "                     <v1:DESC7></v1:DESC7>\n" +
                "                     <v1:DESC8></v1:DESC8>\n" +
                "                     <v1:DESC9></v1:DESC9>\n" +
                "                     <v1:LASTMODIFYRECORDTIME>"+date+ "</v1:LASTMODIFYRECORDTIME>\n"+
                "                     <v1:UUID></v1:UUID>\n" +
                "                  </v1:DATAINFO>\n" +
                "               </v1:DATAINFOS>\n" +
                "               <v1:SPLITPAGE>\n" +
                "                  <v1:COUNTPERPAGE>1000</v1:COUNTPERPAGE>\n" +
                "                  <v1:CURRENTPAGE>" +page+"</v1:CURRENTPAGE>\n"+
                "               </v1:SPLITPAGE>\n" +
                "            </v1:DATA>\n" +
                "         </v1:ESB>\n" +
                "      </v1:Request>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        try {
            result = HttpUtil.sendclientPost(url, params, password);
            System.err.println(result);
            Document doc = DocumentHelper.parseText(result);
            Element root = doc.getRootElement();
            Element head = root.element("Body");
            Element element = head.element("Response");
            Element esb = element.element("ESB");
            Element data = esb.element("DATA");
            Element INFOS = data.element("DATAINFOS");
            List<Element> elements = INFOS.elements("DATAINFO");
            for (int i=0;i<elements.size();i++){
                Custom custom = Custom.builder().number(elements.get(i).
                        element("DESC3").getTextTrim()).
                        name(elements.get(i).element("DESC1").getTextTrim()).dESC20(elements.get(i).
                        element("DESC20").getTextTrim()).dESC15(elements.get(i).
                        element("DESC15").getTextTrim()).dESC16(elements.get(i).
                        element("DESC16").getTextTrim()).dESC17(elements.get(i).
                        element("DESC17").getTextTrim()).dESC4(elements.get(i).
                        element("DESC4").getTextTrim()).dESC6(elements.get(i).
                        element("DESC6").getTextTrim()).dESC11(elements.get(i).
                        element("DESC11").getTextTrim()).dESC23(elements.get(i).
                        element("DESC23").getTextTrim()).dESC21(elements.get(i).
                        element("DESC21").getTextTrim()).dESC22(elements.get(i).
                        element("DESC22").getTextTrim()) .dESC12(elements.get(i).
                        element("DESC12").getTextTrim()) .build();

                customList.add(custom);
            }
            return  customList;
        }catch (Exception ex) {
            logger.info("获取主数据供应商信息有误,报错信息为:" + ex.getMessage());
            return customList;
        }
    }


    public  static    List<AccountView>   AccountViewQuery(String url, String param, String password, String date){
        String result="";
        List<AccountView> accountViewList=new ArrayList<>();
        String params="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"http://www.sxqc.com/osb/MasterDataSB/AccountingSubjectQuery/Mdm/Schema/v1.0-GET\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <v1:Request>\n" +
                "         <v1:ESB>\n" +
                "            <v1:DATA>\n" +
                "               <v1:DATAINFOS>\n" +
                "                  <!--1 or more repetitions:-->\n" +
                "                  <v1:DATAINFO>\n" +
                "                     <v1:CODE>1001010103</v1:CODE>\n" +
                "                     <v1:DESC1></v1:DESC1>\n" +
                "                     <v1:DESC11></v1:DESC11>\n" +
                "                     <v1:DESC12></v1:DESC12>\n" +
                "                     <v1:DESC13></v1:DESC13>\n" +
                "                     <v1:DESC14></v1:DESC14>\n" +
                "                     <v1:DESC15></v1:DESC15>\n" +
                "                     <v1:DESC16></v1:DESC16>\n" +
                "                     <v1:DESC17></v1:DESC17>\n" +
                "                     <v1:DESC18></v1:DESC18>\n" +
                "                     <v1:DESC19></v1:DESC19>\n" +
                "                     <v1:DESC2></v1:DESC2>\n" +
                "                     <v1:DESC20></v1:DESC20>\n" +
                "                     <v1:DESC21></v1:DESC21>\n" +
                "                     <v1:DESC22></v1:DESC22>\n" +
                "                     <v1:DESC23></v1:DESC23>\n" +
                "                     <v1:DESC24></v1:DESC24>\n" +
                "                     <v1:DESC25></v1:DESC25>\n" +
                "                     <v1:DESC26></v1:DESC26>\n" +
                "                     <v1:DESC27></v1:DESC27>\n" +
                "                     <v1:DESC28></v1:DESC28>\n" +
                "                     <v1:DESC29></v1:DESC29>\n" +
                "                     <v1:DESC3></v1:DESC3>\n" +
                "                     <v1:DESC30></v1:DESC30>\n" +
                "                     <v1:DESC31></v1:DESC31>\n" +
                "                     <v1:DESC32></v1:DESC32>\n" +
                "                     <v1:DESC33></v1:DESC33>\n" +
                "                     <v1:DESC34></v1:DESC34>\n" +
                "                     <v1:DESC35></v1:DESC35>\n" +
                "                     <v1:DESC36></v1:DESC36>\n" +
                "                     <v1:DESC37></v1:DESC37>\n" +
                "                     <v1:DESC38></v1:DESC38>\n" +
                "                     <v1:DESC39></v1:DESC39>\n" +
                "                     <v1:DESC40></v1:DESC40>\n" +
                "                     <v1:DESC41></v1:DESC41>\n" +
                "                     <v1:DESC42></v1:DESC42>\n" +
                "                     <v1:DESC43></v1:DESC43>\n" +
                "                     <v1:DESC44></v1:DESC44>\n" +
                "                     <v1:DESC45></v1:DESC45>\n" +
                "                     <v1:DESC46></v1:DESC46>\n" +
                "                     <v1:DESC47></v1:DESC47>\n" +
                "                     <v1:DESC48></v1:DESC48>\n" +
                "                     <v1:DESC49></v1:DESC49>\n" +
                "                     <v1:DESC7></v1:DESC7>\n" +
                "                     <v1:DESC9></v1:DESC9>\n" +
                "                     <v1:LASTMODIFYRECORDTIME>2021-02-18 09:20:10~2021-09-06 09:20:10</v1:LASTMODIFYRECORDTIME>\n" +
                "                     <v1:PARENTCODE></v1:PARENTCODE>\n" +
                "                     <v1:UUID></v1:UUID>\n" +
                "                  </v1:DATAINFO>\n" +
                "                  <v1:PUUID></v1:PUUID>\n" +
                "               </v1:DATAINFOS>\n" +
                "               <v1:SPLITPAGE>\n" +
                "                  <v1:COUNTPERPAGE>200</v1:COUNTPERPAGE>\n" +
                "                  <v1:CURRENTPAGE>1</v1:CURRENTPAGE>\n" +
                "               </v1:SPLITPAGE>\n" +
                "            </v1:DATA>\n" +
                "         </v1:ESB>\n" +
                "      </v1:Request>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        try {
            result = HttpUtil.sendclientPost(url, params, password);
            Document doc = DocumentHelper.parseText(result);
            Element root = doc.getRootElement();
            Element head = root.element("Body");
            Element element = head.element("Response");
            Element esb = element.element("ESB");
            Element data = esb.element("DATA");
            Element INFOS = data.element("DATAINFOS");
            List<Element> elements = INFOS.elements("DATAINFO");
            for (int i=0;i<elements.size();i++){
                AccountView view = AccountView.builder().FNumber(elements.get(i).
                        element("FNumber").getTextTrim()).FName(elements.get(i).
                        element("FName").getTextTrim()).FIsGFreeze(elements.get(i).
                        element("FIsGFreeze").getTextTrim())
                        .FGAA(elements.get(i).
                                element("FGAA").getTextTrim()).FIsCFreeze(elements.get(i).
                                element("FIsCFreeze").getTextTrim()).FHelpCode(elements.get(i).
                                element("FHelpCode").getTextTrim()).FCompanyID(elements.get(i).
                                element("FCompanyID").getTextTrim()).FDC(elements.get(i).
                                element("FDC").getTextTrim()).
                                FIsChangeCurrency(elements.get(i).
                                        element("FIsChangeCurrency").getTextTrim()).FIsBizChangeCurrency(elements.get(i).
                                element("FIsBizChangeCurrency").getTextTrim()).FLongName(elements.get(i).
                                element("FLongName").getTextTrim()).
                                FIsCashEquivalent(elements.get(i).
                                        element("FIsCashEquivalent").getTextTrim()).FIsQty(elements.get(i).
                                element("FIsQty").getTextTrim()).FMeasureUnitGroupID(elements.get(i).
                                element("FMeasureUnitGroupID").getTextTrim()).FMeasureUnitID(elements.get(i).
                                element("FMeasureUnitID").getTextTrim()).
                                FAC(elements.get(i).
                                        element("FAC").getTextTrim()).FPLType(elements.get(i).
                                element("FPLType").getTextTrim()).FControl(elements.get(i).
                                element("FControl").getTextTrim()).FCAA(elements.get(i).
                                element("FCAA").getTextTrim()).FAcctCurrency(elements.get(i).
                                element("FAcctCurrency").getTextTrim()).FIsBank(elements.get(i).
                                element("FIsBank").getTextTrim()).FAccountTypeID(elements.get(i).
                                element("FAccountTypeID").getTextTrim()).FIsCash(elements.get(i).
                                element("FIsCash").getTextTrim())
                        .FhasUserProperty(elements.get(i).
                                element("FhasUserProperty").getTextTrim()).FIsAllowCA(elements.get(i).
                                element("FIsAllowCA").getTextTrim()).FParentAAID(elements.get(i).
                                element("FParentAAID").getTextTrim()).FIsUpperAllowCA(elements.get(i).
                                element("FIsUpperAllowCA").getTextTrim()).FIsSelfFreeze(elements.get(i).
                                element("FIsSelfFreeze").getTextTrim()).FIsParentFreeze(elements.get(i).
                                element("FIsParentFreeze").getTextTrim())
                        .FIsOutDailyAccount(elements.get(i).
                                element("FIsOutDailyAccount").getTextTrim()).FACNotice(elements.get(i).
                                element("FACNotice").getTextTrim()).FBw(elements.get(i).
                                element("FBw").getTextTrim()).FBorrowerMainCashFlowItemID(elements.get(i).
                                element("FBorrowerMainCashFlowItemID").getTextTrim())
                        .FBorrowerAttCashFlowItemID(elements.get(i).
                                element("FBorrowerAttCashFlowItemID").getTextTrim()).FLenderMainCashFlowItemID(elements.get(i).
                                element("FLenderMainCashFlowItemID").getTextTrim()).FLenderAttCashFlowItemID(elements.get(i).
                                element("FLenderAttCashFlowItemID").getTextTrim()).
                                FIsControl(elements.get(i).
                                        element("FIsControl").getTextTrim()).FAccrualDirection(elements.get(i).
                                element("FAccrualDirection").getTextTrim()).FIsProfitCenter(elements.get(i).
                                element("FIsProfitCenter").getTextTrim()).FCategory(elements.get(i).
                                element("FCategory").getTextTrim()).FControlUnitID(elements.get(i).
                                element("FControlUnitID").getTextTrim()).FParentID(elements.get(i).
                                element("FParentID").getTextTrim()).PARENTCODE(elements.get(i).
                                element("PARENTCODE").getTextTrim()).build();
                accountViewList.add(view);
            }
            return  accountViewList;
        }catch (Exception ex) {
            logger.info("获取主数据供应商信息有误,报错信息为:" + ex.getMessage());
            return accountViewList;
        }
    }
}
