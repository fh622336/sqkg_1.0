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
@TableName("T_BD_SUPPLIER")
public class TBdSupplier extends Model<TBdSupplier> {

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

    @TableField("FARTIFICIALPERSON")
    private String fartificialperson;

    @TableField("FBARCODE")
    private String fbarcode;

    @TableField("FBIZANALYSISCODEID")
    private String fbizanalysiscodeid;

    @TableField("FBROWSEGROUPID")
    private String fbrowsegroupid;

    @TableField("FBUSIEXEQUATUR")
    private String fbusiexequatur;

    @TableField("FBIZREGISTERNO")
    private String fbizregisterno;

    @TableField("FBUSILICENCE")
    private String fbusilicence;

    @TableField("FCITYID")
    private String fcityid;

    @TableField("FCOUNTRYID")
    private String fcountryid;

    @TableField("FFREEZEORGUNIT")
    private String ffreezeorgunit;

    @TableField("FGSPAUTHENTICATION")
    private String fgspauthentication;

    @TableField("FINDUSTRYID")
    private String findustryid;

    @TableField("FINTERNALCOMPANYID")
    private String finternalcompanyid;

    @TableField("FISINTERNALCOMPANY")
    private Long fisinternalcompany;

    @TableField("FPARENTID")
    private String fparentid;

    @TableField("FPROVINCEID")
    private String fprovinceid;

    @TableField("FREGIONID")
    private String fregionid;

    @TableField("FTAXDATAID")
    private String ftaxdataid;

    @TableField("FTAXREGISTERNO")
    private String ftaxregisterno;

    @TableField("FUSEDSTATUS")
    private Long fusedstatus;

    @TableField("FVERSION")
    private Long fversion;

    @TableField("FEFFECTEDSTATUS")
    private Long feffectedstatus;

    @TableField("FMNEMONICCODE")
    private String fmnemoniccode;

    @TableField("FFOREIGNNAME")
    private String fforeignname;

    @TableField("FADMINCUID")
    private String fadmincuid;

    @TableField("FADDRESS")
    private String faddress;

    @TableField("FTAXRATE")
    private BigDecimal ftaxrate;

    @TableField("FISMULTICOPY")
    private Long fismulticopy;

    @TableField("FISCARRIER")
    private Long fiscarrier;

    @TableField("FISOUTER")
    private Long fisouter;

    @TableField("FOLDNUMBER")
    private String foldnumber;

    @TableField("FNAMEPINYIN")
    private String fnamepinyin;

    @TableField("FNAMESHORTPINYIN")
    private String fnameshortpinyin;

    @TableField("FINTERNALPROFITCENTER")
    private String finternalprofitcenter;

    @TableField("FINTERNALSUPPLIERTYPE")
    private Long finternalsuppliertype;

    @TableField("FTAXPAYERTYPE")
    private String ftaxpayertype;

    @TableField("CFKDTEXTFIELD")
    private String cfkdtextfield;

    @TableField("CFSTATUS")
    private String cfstatus;


    @Override
    protected Serializable pkVal() {
        return this.fid;
    }

}
