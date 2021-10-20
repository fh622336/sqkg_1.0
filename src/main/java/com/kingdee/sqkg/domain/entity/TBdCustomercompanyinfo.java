package com.kingdee.sqkg.domain.entity;

import java.math.BigDecimal;
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
 * @since 2021-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_BD_CUSTOMERCOMPANYINFO")
public class TBdCustomercompanyinfo extends Model<TBdCustomercompanyinfo> {

    private static final long serialVersionUID = 1L;

    @TableId("FID")
    private String fid;

    @TableField("FCREATORID")
    private String fcreatorid;

    @TableField("FCREATETIME")
    private LocalDateTime fcreatetime;

    @TableField("FLASTUPDATEUSERID")
    private String flastupdateuserid;

    @TableField("FLASTUPDATETIME")
    private LocalDateTime flastupdatetime;

    @TableField("FCONTROLUNITID")
    private String fcontrolunitid;

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

    @TableField("FADMINCUID")
    private String fadmincuid;

    @TableField("FCOMORGID")
    private String fcomorgid;

    @TableField("FSETTLEMENTCURRENCYID")
    private String fsettlementcurrencyid;

    @TableField("FSETTLEMENTTYPEID")
    private String fsettlementtypeid;

    @TableField("FPAYMENTTYPEID")
    private String fpaymenttypeid;

    @TableField("FACCOUNTCLASSID")
    private String faccountclassid;

    @TableField("FISTICKETFREEZED")
    private Long fisticketfreezed;

    @TableField("FEFFECTEDSTATUS")
    private Long feffectedstatus;

    @TableField("FCUSTOMERID")
    private String fcustomerid;

    @TableField("FFREEZEORGUNITID")
    private String ffreezeorgunitid;

    @TableField("FCONTACTPERSON")
    private String fcontactperson;

    @TableField("FCONTACTPERSONPOST")
    private String fcontactpersonpost;

    @TableField("FPHONE")
    private String fphone;

    @TableField("FMOBILE")
    private String fmobile;

    @TableField("FFAX")
    private String ffax;

    @TableField("FPOSTALCODE")
    private String fpostalcode;

    @TableField("FEMAIL")
    private String femail;

    @TableField("FBANK")
    private String fbank;

    @TableField("FBANKACCOUNT")
    private String fbankaccount;

    @TableField("FOPERATIONERID")
    private String foperationerid;

    @TableField("FBANKADDRESS")
    private String fbankaddress;

    @TableField("FADMINORGUNITID")
    private String fadminorgunitid;

    @TableField("FPAYCONDITIONID")
    private String fpayconditionid;

    @TableField("FUSINGSTATUS")
    private Long fusingstatus;

    @TableField("FBILLINGLIMIT")
    private BigDecimal fbillinglimit;

    @TableField("CFSTATUS")
    private String cfstatus;


    @Override
    protected Serializable pkVal() {
        return this.fid;
    }

}
