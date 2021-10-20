package com.kingdee.sqkg.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EasUtil {
    @Autowired
    private RedisUtil redisUtil;
    private final Logger logger = LoggerFactory.getLogger(EasUtil.class);
    public String getSessionId(String url) {
        try {
//            if (redisUtil.hasKey("Session")) {
//                logger.info("redis...........");
//                return redisUtil.get("Session").toString();
//            }
        //   ZSJ ,fh622336
            String session = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:log=\"http://login.webservice.bos.kingdee.com\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <log:login soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                    "         <userName xsi:type=\"xsd:string\">ZSJ</userName>\n" +
                    "         <password xsi:type=\"xsd:string\">fh622336</password>\n" +
                    "         <slnName xsi:type=\"xsd:string\">eas</slnName>\n" +
                    "         <dcName xsi:type=\"xsd:string\">sqcw</dcName>\n" +
                    "         <language xsi:type=\"xsd:string\">l2</language>\n" +
                    "         <dbType xsi:type=\"xsd:int\">2</dbType>\n" +
                    "      </log:login>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            String s = HttpUtil.sendPost(url, session);
            Document doc = DocumentHelper.parseText(s);
            Element root = doc.getRootElement();
            Element head = root.element("Body");
            Element element = head.element("multiRef");
            String sessionId = element.element("sessionId").getTextTrim();
        //    redisUtil.set("Session", sessionId, 1260);
            return sessionId;
        } catch (Exception e) {
            logger.info("获取SESSIONID失效,报错信息为:" + e.getMessage());
            return "";
        }
    }

    public String addBank(String param, String sessionId) throws Exception {
        String bankXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.app.mateial.custom.eas.kingdee.com\">\n" +
                "   <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">" + sessionId + "</ns1:SessionId>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:addBank soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <BankInfo xsi:type=\"xsd:string\">" + param + "</BankInfo>\n" +
                "      </web:addBank>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String result = HttpUtil.sendPost("http://172.16.7.89:6888/ormrpc/services/WSWSBankFacade?wsdl", bankXml);
        Document doc = DocumentHelper.parseText(result);
        Element root = doc.getRootElement();
        Element head = root.element("Body");
        Element element = head.element("addBankResponse");
        String bankReturn = element.element("addBankReturn").getTextTrim();
        logger.info("****Log Start****");
        logger.info("Log param:" + bankXml);
        logger.info("Log result:" + bankReturn);
        logger.info("****Log end****");
        return bankReturn;
    }

    public String addAccountViewInfo(String param, String sessionId) throws Exception {
        String accountXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.app.mateial.custom.eas.kingdee.com\">\n" +
                "     <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">" + sessionId + "</ns1:SessionId>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:addAccount soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <XML xsi:type=\"xsd:string\">" + param + "</XML>\n" +
                "      </web:addAccount>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String result = HttpUtil.sendPost("http://172.16.7.89:6888/ormrpc/services/WSAccountViewInfoFacade?wsdl", accountXml);
        Document doc = DocumentHelper.parseText(result);
        Element root = doc.getRootElement();
        Element head = root.element("Body");
        Element element = head.element("addAccountResponse");
        String addAccountReturn = element.element("addAccountReturn").getTextTrim();
        return addAccountReturn;
    }

    public String addBaseUnit(String sessionId, String param, String url) throws Exception {
        String baseUnitXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.org.ws.basedata.eas.kingdee.com\">\n" +
                "   <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">" + sessionId + "</ns1:SessionId>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:importFullOrgData soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <xmlData xsi:type=\"xsd:string\">\n" + param +
                "         </xmlData>\n" +
                "      </web:importFullOrgData>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String result = HttpUtil.sendPost(url, baseUnitXml);
        Document doc = DocumentHelper.parseText(result);
        Element root = doc.getRootElement();
        Element head = root.element("Body");
        Element element = head.element("importFullOrgDataResponse");
        result = element.element("importFullOrgDataReturn").getTextTrim();
        logger.info("****Log Start****");
        logger.info("Log param:" + baseUnitXml);
        logger.info("Log result:" + result);
        logger.info("****Log end****");
        return result;
    }

    public String addAdminOrg(String number, String parent, String createTime, String url, String sessionId) throws Exception {
        String param = "<![CDATA[\n" +
                "<easrecord product=\"EAS\" version=\"8.6.0\" objectName=\"admin\" objectDesc=\"行政组织\">\n" +
                "\t<struct>\n" +
                "\t\t<field name=\"FNumber\" desc=\"编码\" isNeed=\"true\" memo=\"\"/>\n" +
                "\t\t<field name=\"FParentID\" desc=\"父级编码\" isNeed=\"true\" memo=\"\"/>\n" +
                "\t\t<field name=\"FLayerTypeID\" desc=\"组织层次类型\" isNeed=\"true\" memo=\"\"/>\n" +
                "\t\t<field name=\"FAddressID\" desc=\"地址编码\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FPhoneNumber\" desc=\"电话\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FZipCode\" desc=\"邮编\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FFax\" desc=\"传真\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FIsVirtual\" desc=\"是否虚拟团队\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FBaseDuty_L1\" desc=\"描述_英\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FBaseDuty_L2\" desc=\"描述_简\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FBaseDuty_L3\" desc=\"描述_繁\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FIndustry\" desc=\"行业\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FTerritory\" desc=\"地区\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FJuridicalPersonID\" desc=\"法人代表\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FTaxNumber\" desc=\"税务号\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FRegisteredCapital\" desc=\"注册资本\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FCreatorNumber\" desc=\"创建人编码\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FCreateTime\" desc=\"创建时间\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FSetupDate\" desc=\"成立日期\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FEndupDate\" desc=\"营业有效期\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FLayer\" desc=\"组织单元层次\" isNeed=\"false\" memo=\"层级ID或名称\"/>\n" +
                "\t\t<field name=\"FOrgCode\" desc=\"组织机构代码\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FIsIndependence\" desc=\"是否独立核算\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FIsChurchyard\" desc=\"是否境内\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FIsJuridicalCompany\" desc=\"是否法人公司\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t\t<field name=\"FArea_number\" desc=\"区域编码\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t</struct>\n" +
                "\t<records> \n" +
                "\t\t<record>\n" +
                "\t\t\t<field name=\"FNumber\">" + number + "</field>\n" +
                "\t\t\t<field name=\"FParentID\">" + parent + "</field>\n" +
                "\t\t\t<field name=\"FLayerTypeID\">部门</field>\n" +
                "\t\t\t<field name=\"FCreatorNumber\">user</field>\n" +
                "\t\t\t<field name=\"FCreateTime\">" + createTime + "</field>\n" +
                "\t\t\t<field name=\"FIsChurchyard\">" + true + "</field>\n" +
                "\t\t</record>\n" +
                "\t</records>\n" +
                "</easrecord>\n" +
                "]]>";
        Pattern p = Pattern.compile("\t|\r|\n");
        Matcher m = p.matcher(param);
        param = m.replaceAll("");
        String adminOrgXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.app.datatask.tools.eas.kingdee.com\">\n" +
                "    <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">" + sessionId + "</ns1:SessionId>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:importBizData soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <easTemplaeNum xsi:type=\"xsd:string\">admin</easTemplaeNum>\n" +
                "         <xmlData xsi:type=\"xsd:string\">\n" + param +
                "         </xmlData>\n" +
                "         <isUpdate xsi:type=\"xsd:boolean\">false</isUpdate>\n" +
                "      </web:importBizData>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String replace = adminOrgXml.replace("\n", "");
        String result = HttpUtil.sendPost(url, replace);
        logger.info("****Log Start****");
        logger.info("Log param:" + replace);
        logger.info("Log result:" + result);
        logger.info("****Log end****");
        return result;
    }

    public String addUnitRelation(String fromunit, String toUnit, String url, String sessionId) {
        String param = "<![CDATA[\n" +
                "<easrecord product=\"EAS\" version=\"8.6.0\" objectName=\"OrgRel\" objectDesc=\"组织委托关系\">\n" +
                "\t<struct>\n" +
                "\t\t<field name=\"fromType\" desc=\"委托组织类型\" isNeed=\"true\" memo=\"\"/>\n" +
                "\t\t<field name=\"toType\" desc=\"受托组织类型\" isNeed=\"true\" memo=\"\"/>\n" +
                "\t\t<field name=\"fromUnit\" desc=\"委托组织单元\" isNeed=\"true\" memo=\"\"/>\n" +
                "\t\t<field name=\"toUnit\" desc=\"受托组织单元\" isNeed=\"true\" memo=\"\"/>\n" +
                "\t\t<field name=\"isDefault\" desc=\"是否缺省\" isNeed=\"false\" memo=\"\"/>\n" +
                "\t</struct>\n" +
                "\t<records> \n" +
                "\t\t<record>\n" +
                "\t\t\t<field name=\"fromType\">0</field>\n" +
                "\t\t\t<field name=\"toType\">1</field>\n" +
                "\t\t\t<field name=\"fromUnit\">" + fromunit + "</field>\n" +
                "\t\t\t<field name=\"toUnit\">" + toUnit + "</field>\n" +
                "\t\t\t<field name=\"isDefault\">000</field>\n" +
                "\t\t</record>\n" +
                "\t</records>\n" +
                "</easrecord>\n" +
                "]]>\n";
        Pattern p = Pattern.compile("\t|\r|\n");
        Matcher m = p.matcher(param);
        param = m.replaceAll("");
        String relationXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.app.datatask.tools.eas.kingdee.com\">\n" +
                "    <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">fb01c0a6-7812-4c82-8530-fd8a1f60b3b1</ns1:SessionId>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:importBizData soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <easTemplaeNum xsi:type=\"xsd:string\">OrgRel</easTemplaeNum>\n" +
                "         <xmlData xsi:type=\"xsd:string\">\n" + param +
                "         </xmlData>\n" +
                "         <isUpdate xsi:type=\"xsd:boolean\">false</isUpdate>\n" +
                "      </web:importBizData>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String result = HttpUtil.sendPost(url, relationXml);
        logger.info("****Log Start****");
        logger.info("Log param:" + relationXml);
        logger.info("Log result:" + result);
        logger.info("****Log end****");
        return result;
    }

    public String assignCustomer(String sessionId, String param, String url) throws Exception {
        String customerXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.customer.ws.basedata.eas.kingdee.com\">\n" +
                "  <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">" + sessionId + "</ns1:SessionId>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:assignCustomer soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <xmlData xsi:type=\"xsd:string\">\n" + param +
                "         </xmlData>\n" +
                "      </web:assignCustomer>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String result = HttpUtil.sendPost(url, customerXml);
        Document doc = DocumentHelper.parseText(result);
        Element root = doc.getRootElement();
        Element head = root.element("Body");
        Element element = head.element("assignCustomerResponse");
        result = element.element("assignCustomerReturn").getTextTrim();
        logger.info("****Log Start****");
        logger.info("Log param:" + customerXml);
        logger.info("Log result:" + result);
        logger.info("****Log end****");
        return result;
    }


    public String addCustomer(String sessionId, String param, String url) throws Exception {
        System.err.println("param:::" + param);
        System.err.println("url:::" + url);


        Pattern p = Pattern.compile("\t|\r|\n");
        Matcher m = p.matcher(param);
        param = m.replaceAll("");
        String customerXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.app.mateial.custom.eas.kingdee.com\">\n" +
                "    <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">" + sessionId + "</ns1:SessionId>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:importCustomer soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <xmlData xsi:type=\"xsd:string\">\n" + param +
                "         </xmlData>\n" +
                "      </web:importCustomer>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        logger.info("customerXml:::::" + customerXml);
        String result = HttpUtil.sendPost(url, customerXml);
        System.err.println("result:::" + result);
        Document doc = DocumentHelper.parseText(result);
        Element root = doc.getRootElement();
        Element head = root.element("Body");
        Element element = head.element("importCustomerResponse");
        result = element.element("importCustomerReturn").getTextTrim();
        logger.info("****Log Start****");
        logger.info("Log param:" + customerXml);
        logger.info("Log result:" + result);
        logger.info("****Log end****");
        return result;
    }


    public String importCustomerCompanyBank(String sessionId, String param, String url) throws Exception {
        String customerBankXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.app.mateial.custom.eas.kingdee.com\">\n" +
                "    <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">" + sessionId + "</ns1:SessionId>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:importCustomerCompanyBank soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <xmlData xsi:type=\"xsd:string\">\n" + param +
                "         </xmlData>\n" +
                "      </web:importCustomerCompanyBank>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String result = HttpUtil.sendPost(url, customerBankXml);
        Document doc = DocumentHelper.parseText(result);
        Element root = doc.getRootElement();
        Element head = root.element("Body");
        Element element = head.element("importCustomerCompanyBankResponse");
        result = element.element("importCustomerCompanyBankReturn").getTextTrim();
        logger.info("****Log Start****");
        logger.info("Log param:" + customerBankXml);
        logger.info("Log result:" + result);
        logger.info("****Log end****");
        return result;
    }

    public String addSupplier(String sessionId, String param, String url) throws Exception {
        String customerXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.app.mateial.custom.eas.kingdee.com\">\n" +
                "     <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">\n" + sessionId + "</ns1:SessionId>" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:importSupplier soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <xmlData xsi:type=\"xsd:string\">\n" + param +
                "         </xmlData>\n" +
                "      </web:importSupplier>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String result = HttpUtil.sendPost(url, customerXml);
        Document doc = DocumentHelper.parseText(result);
        Element root = doc.getRootElement();
        Element head = root.element("Body");
        Element element = head.element("importSupplierResponse");
        result = element.element("importSupplierReturn").getTextTrim();
        logger.info("****Log Start****");
        logger.info("Log param:" + customerXml);
        logger.info("Log result:" + result);
        logger.info("****Log end****");
        return result;
    }

    public String assignSupplier(String sessionId, String param, String url) throws Exception {
        String assignSupplierXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.supplier.ws.basedata.eas.kingdee.com\">\n" +
                "   <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">" + sessionId + "</ns1:SessionId>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:assignSupplier soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <xmlData xsi:type=\"xsd:string\">\n" + param +
                "         </xmlData>\n" +
                "      </web:assignSupplier>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String result = HttpUtil.sendPost(url, assignSupplierXml);
        Document doc = DocumentHelper.parseText(result);
        Element root = doc.getRootElement();
        Element head = root.element("Body");
        Element element = head.element("assignSupplierResponse");
        result = element.element("assignSupplierReturn").getTextTrim();
        logger.info("****Log Start****");
        logger.info("Log param:" + assignSupplierXml);
        logger.info("Log result:" + result);
        logger.info("****Log end****");
        return result;
    }

    public String importSupplierCompanyBank(String sessionId, String param, String url) throws Exception {
        String supplierBankXml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.app.mateial.custom.eas.kingdee.com\">\n" +
                "    <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">" + sessionId + "</ns1:SessionId>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:importSupplierCompanyBank soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <xmlData xsi:type=\"xsd:string\">\n" + param +
                "         </xmlData>\n" +
                "      </web:importSupplierCompanyBank>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String result = HttpUtil.sendPost(url, supplierBankXml);
        Document doc = DocumentHelper.parseText(result);
        Element root = doc.getRootElement();
        Element head = root.element("Body");
        Element element = head.element("importSupplierCompanyBankResponse");
        result = element.element("importSupplierCompanyBankReturn").getTextTrim();
        logger.info("****Log Start****");
        logger.info("Log param:" + supplierBankXml);
        logger.info("Log result:" + result);
        logger.info("****Log end****");
        return result;
    }
    public String  importPersonData(String  url,String sessionId,String LoginName,String UserName,String gender,String Mobile,
                                    String Email,String OfficeNunmber,String adminNumber,
                                    String createTime) throws  Exception{
        String xmlData="<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.person.ws.basedata.eas.kingdee.com\">\n" +
                "    <soapenv:Header>\n" +
                "   <ns1:SessionId xmlns:ns1=\"http://login.webservice.bos.kingdee.com\">" +sessionId+"</ns1:SessionId>\n"+
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <web:importPersonData soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <xmlData xsi:type=\"xsd:string\">\n" +
                "           <![CDATA[\n" +
                "         <DataInfo  bostype=\"80EF7DED\" op=\"4\">\n" +
                "<DataHead>\n" +
                " <creator>user</creator>\n" +
                "<CU>" + adminNumber +"</CU>\n"+
                "<name>" +UserName+"</name>\n"+
                "<number>" +LoginName+"</number>\n"+
                "<createTime></createTime>\n"+
                "<bloodType>0</bloodType>\n" +
                "<employeeType>006</employeeType>\n" +
                "<hrOrgUnit>"+adminNumber+"</hrOrgUnit>\n"+
                "<gender>" +gender+"</gender>\n"+
                "<idCardNO></idCardNO>\n" +
                "<homePhone></homePhone>\n" +
                "<state>1</state>\n" +
                "<officePhone></officePhone>\n" +
                "<nativePlace></nativePlace>\n" +
                "<simpleNamePingYin></simpleNamePingYin>\n" +
                "<birthday>1999-04-28</birthday>\n" +
                "<cell>"+Mobile +"</cell>\n"+
                "<email>" +Email+"</email>\n"+
                "<NOPOSITION_PERSON_SAVE>1</NOPOSITION_PERSON_SAVE>\n" +
                "<gkadmin>" +adminNumber+"</gkadmin>\n"+
                "<position></position>\n" +
                "<adminOrgUnit></adminOrgUnit>\n" +
                " <isPrimary></isPrimary>\n" +
                "</DataHead>\n" +
                "</DataInfo>\n" +
                "         ]]>\n" +
                "         \n" +
                "         </xmlData>\n" +
                "      </web:importPersonData>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String result = HttpUtil.sendPost(url, xmlData);
        Document doc = DocumentHelper.parseText(result);
        Element root = doc.getRootElement();
        Element head = root.element("Body");
        Element element = head.element("importPersonDataResponse");
        result = element.element("importPersonDataReturn").getTextTrim();
        logger.info("****Log Start****");
        logger.info("Log param:" + xmlData);
        logger.info("Log result:" + result);
        logger.info("****Log end****");
        return result;
    }
}
