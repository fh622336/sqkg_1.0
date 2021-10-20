package com.kingdee.sqkg.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 樊浩
 * @since 2021-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_BD_PERSON")
public class TBdPerson extends Model<TBdPerson> {

    private static final long serialVersionUID = 1L;

    @TableId("FID")
    private String fid;

    @TableField("FIDNUM")
    private String fidnum;

    @TableField("FNAME_L1")
    private String fnameL1;

    @TableField("FNAME_L2")
    private String fnameL2;

    @TableField("FNAME_L3")
    private String fnameL3;

    @TableField("FNUMBER")
    private String fnumber;

    @TableField("FDESCRIPTION_L1")
    private String fdescriptionL1;

    @TableField("FDESCRIPTION_L2")
    private String fdescriptionL2;

    @TableField("FDESCRIPTION_L3")
    private String fdescriptionL3;

    @TableField("FSIMPLENAME")
    private String fsimplename;

    @TableField("FGENDER")
    private Long fgender;

    @TableField("FBIRTHDAY")
    private LocalDateTime fbirthday;

    @TableField("FEMAIL")
    private String femail;

    @TableField("FADDRESS_L1")
    private String faddressL1;

    @TableField("FADDRESS_L2")
    private String faddressL2;

    @TableField("FADDRESS_L3")
    private String faddressL3;

    @TableField("FSTATE")
    private Long fstate;

    @TableField("FHOMEPHONE")
    private String fhomephone;

    @TableField("FOFFICEPHONE")
    private String fofficephone;

    @TableField("FCELL")
    private String fcell;

    @TableField("FBACKUPCELL")
    private String fbackupcell;

    @TableField("FBACKUPEMAIL")
    private String fbackupemail;

    @TableField("FRTX")
    private String frtx;

    @TableField("FIDCARDNO")
    private String fidcardno;

    @TableField("FIDCARDADRESS_L1")
    private String fidcardadressL1;

    @TableField("FIDCARDADRESS_L2")
    private String fidcardadressL2;

    @TableField("FIDCARDADRESS_L3")
    private String fidcardadressL3;

    @TableField("FPASSPORTNO")
    private String fpassportno;

    @TableField("FOLDNAME_L1")
    private String foldnameL1;

    @TableField("FOLDNAME_L2")
    private String foldnameL2;

    @TableField("FOLDNAME_L3")
    private String foldnameL3;

    @TableField("FHEIGHT")
    private Long fheight;

    @TableField("FNATIVEPLACE_L1")
    private String fnativeplaceL1;

    @TableField("FNATIVEPLACE_L2")
    private String fnativeplaceL2;

    @TableField("FNATIVEPLACE_L3")
    private String fnativeplaceL3;

    @TableField("FBLOODTYPE")
    private Long fbloodtype;

    @TableField("FCREATORID")
    private String fcreatorid;

    @TableField("FCREATETIME")
    private LocalDateTime fcreatetime;

    @TableField("FLASTUPDATEUSERID")
    private String flastupdateuserid;

    @TableField("FLASTUPDATETIME")
    private LocalDateTime flastupdatetime;

    @TableField("FKACLASSFICATIONID")
    private String fkaclassficationid;

    @TableField("FWEDID")
    private String fwedid;

    @TableField("FHEALTHID")
    private String fhealthid;

    @TableField("FPOLITICALFACEID")
    private String fpoliticalfaceid;

    @TableField("FSTANDINGID")
    private String fstandingid;

    @TableField("FFOLKID")
    private String ffolkid;

    @TableField("FBIRTHID")
    private String fbirthid;

    @TableField("FCONTROLUNITID")
    private String fcontrolunitid;

    @TableField("FHRORGUNITID")
    private String fhrorgunitid;

    @TableField("FRESIDENCEPLACE")
    private String fresidenceplace;

    @TableField("FLENOFACTUALSERVICE")
    private Long flenofactualservice;

    @TableField("FEMPLOYEETYPEID")
    private String femployeetypeid;

    @TableField("FHIGHESTTECHPOSTID")
    private String fhighesttechpostid;

    @TableField("FHIGHESTDEGREEID")
    private String fhighestdegreeid;

    @TableField("FEMPLOYEECLASSIFYID")
    private String femployeeclassifyid;

    @TableField("FDELETEDSTATUS")
    private Long fdeletedstatus;

    @TableField("FLCMRATIONLEVELID")
    private String flcmrationlevelid;

    @TableField("FNATIONALITYID")
    private String fnationalityid;

    @TableField("FCHECKSTATE")
    private Long fcheckstate;

    @TableField("FINDEX")
    private Long findex;

    @TableField("FEMPLOYTECHPOSTID")
    private String femploytechpostid;

    @TableField("FHIGHESTSUBDEGREEID")
    private String fhighestsubdegreeid;

    @TableField("FZDY1")
    private String fzdy1;

    @TableField("FZDY2")
    private String fzdy2;

    @TableField("FZDY3")
    private String fzdy3;

    @TableField("FZDY4")
    private String fzdy4;

    @TableField("FZDY5")
    private String fzdy5;

    @TableField("FZDY6")
    private String fzdy6;

    @TableField("FZDY7")
    private String fzdy7;

    @TableField("FZDY8")
    private String fzdy8;

    @TableField("FZDY9")
    private LocalDateTime fzdy9;

    @TableField("FZDY10")
    private LocalDateTime fzdy10;

    @TableField("FZDY11")
    private LocalDateTime fzdy11;

    @TableField("FZDY12")
    private LocalDateTime fzdy12;

    @TableField("FZDY13")
    private LocalDateTime fzdy13;

    @TableField("FZDY14")
    private LocalDateTime fzdy14;

    @TableField("FZDY15")
    private String fzdy15;

    @TableField("FZDY16")
    private String fzdy16;

    @TableField("FZDY17")
    private String fzdy17;

    @TableField("FZDY18")
    private Double fzdy18;

    @TableField("FZDY19")
    private Double fzdy19;

    @TableField("FZDY20")
    private Long fzdy20;

    @TableField("FZDY21")
    private Long fzdy21;

    @TableField("FZDY22")
    private String fzdy22;

    @TableField("FZDY23")
    private String fzdy23;

    @TableField("FZDY24")
    private String fzdy24;

    @TableField("FZDY25")
    private String fzdy25;

    @TableField("FZDY26")
    private Long fzdy26;

    @TableField("FFULLNAMEPINGYIN")
    private String ffullnamepingyin;

    @TableField("FSIMPLENAMEPINGYIN")
    private String fsimplenamepingyin;

    @TableField("FISSTANDBY")
    private Long fisstandby;

    @TableField("FISSTANDBYCADRE")
    private Long fisstandbycadre;

    @TableField("FHIGHESTCOMPETENCYID")
    private String fhighestcompetencyid;

    @TableField("FNAMEPINYIN")
    private String fnamepinyin;

    @TableField("FNAMESHORTPINYIN")
    private String fnameshortpinyin;

    @TableField("FREGRESIDENCEID")
    private String fregresidenceid;

    @TableField("FHJADDRESS")
    private String fhjaddress;

    @TableField("FIDCARDADDRESS")
    private String fidcardaddress;

    @TableField("FHOMEPLACE")
    private String fhomeplace;

    @TableField("FEFFDT")
    private LocalDateTime feffdt;

    @TableField("FLEFFDT")
    private LocalDateTime fleffdt;

    @TableField("FHISTORYRELATEID")
    private String fhistoryrelateid;

    @TableField("FIDCARDBEGINDATE")
    private LocalDateTime fidcardbegindate;

    @TableField("FIDCARDENDDATE")
    private LocalDateTime fidcardenddate;

    @TableField("FEXTENDFIELDNUMONE")
    private String fextendfieldnumone;

    @TableField("FPROTOCOL")
    private String fprotocol;

    @TableField("FIDCARDISSUEORG")
    private String fidcardissueorg;

    @TableField("FGKADMIN")
    private String fgkadmin;

    @TableField("FISHIDESENSITIVE")
    private Long fishidesensitive;

    @TableField("FFIRSTNAME_L1")
    private String ffirstnameL1;

    @TableField("FFIRSTNAME_L2")
    private String ffirstnameL2;

    @TableField("FFIRSTNAME_L3")
    private String ffirstnameL3;

    @TableField("FLASTNAME_L1")
    private String flastnameL1;

    @TableField("FLASTNAME_L2")
    private String flastnameL2;

    @TableField("FLASTNAME_L3")
    private String flastnameL3;

    @TableField("FTITLE_L1")
    private String ftitleL1;

    @TableField("FTITLE_L2")
    private String ftitleL2;

    @TableField("FTITLE_L3")
    private String ftitleL3;

    @TableField("FMIDDLENAME_L1")
    private String fmiddlenameL1;

    @TableField("FMIDDLENAME_L2")
    private String fmiddlenameL2;

    @TableField("FMIDDLENAME_L3")
    private String fmiddlenameL3;

    @TableField("FGLOBALROAMING")
    private String fglobalroaming;

    @TableField("FNCELL")
    private String fncell;

    @TableField("FHIREDATE")
    private LocalDateTime fhiredate;


    @Override
    protected Serializable pkVal() {
        return this.fid;
    }

}
