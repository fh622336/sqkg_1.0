package com.kingdee.sqkg.domain.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@Data
public class User {
    @XmlElement(name="LoginName",required = true)
    private  String LoginName;
    @XmlElement(name="UserName",required = true)
    private  String UserName;
    @XmlElement(name="UserType",required = true)
    private  String UserType;
    @XmlElement(name="Password",required = true)
    private  String Password;
    @XmlElement(name="Status",required = true)
    private  String Status;
    @XmlElement(name="Gender",required = true)
    private  String Gender;
    @XmlElement(name="Mobile",required = true)
    private  String Mobile;
    @XmlElement(name="Email",required = true)
    private  String Email;
    @XmlElement(name="OfficeNunmber",required = true)
    private  String OfficeNunmber;
    @XmlElement(name="CompanyCode",required = true)
    private  String CompanyCode;
    @XmlElement(name="Description",required = true)
    private  String Description;
    @XmlElement(name="OrganizationCode",required = true)
    private  String OrganizationCode;
    @XmlElement(name="OrganizationName",required = true)
    private  String OrganizationName;
    @XmlElement(name="DepartmentCode",required = true)
    private  String DepartmentCode;
    @XmlElement(name="DepartmentName",required = true)
    private  String DepartmentName;
    @XmlElement(name="InsuranceNumber",required = true)
    private  String InsuranceNumber;
    @XmlElement(name="SysCode",required = true)
    private  String SysCode;
}
