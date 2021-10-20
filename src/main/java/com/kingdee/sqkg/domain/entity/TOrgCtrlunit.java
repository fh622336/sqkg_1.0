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
 * @since 2021-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_ORG_CTRLUNIT")
public class TOrgCtrlunit extends Model<TOrgCtrlunit> {

    private static final long serialVersionUID = 1L;

    @TableId("FID")
    private String fid;

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

    @TableField("FISGROUPING")
    private Long fisgrouping;

    @TableField("FEFFECTDATE")
    private LocalDateTime feffectdate;

    @TableField("FINVALIDDATE")
    private LocalDateTime finvaliddate;

    @TableField("FISFREEZE")
    private Long fisfreeze;

    @TableField("FISCOMPANYORGUNIT")
    private Long fiscompanyorgunit;

    @TableField("FISADMINORGUNIT")
    private Long fisadminorgunit;

    @TableField("FISSALEORGUNIT")
    private Long fissaleorgunit;

    @TableField("FISPURCHASEORGUNIT")
    private Long fispurchaseorgunit;

    @TableField("FISSTORAGEORGUNIT")
    private Long fisstorageorgunit;

    @TableField("FISPROFITORGUNIT")
    private Long fisprofitorgunit;

    @TableField("FISCOSTORGUNIT")
    private Long fiscostorgunit;

    @TableField("FISCU")
    private Long fiscu;

    @TableField("FISUNION")
    private Long fisunion;

    @TableField("FISHRORGUNIT")
    private Long fishrorgunit;

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

    @TableField("FISLEAF")
    private Long fisleaf;

    @TableField("FLEVEL")
    private Long flevel;

    @TableField("FLONGNUMBER")
    private String flongnumber;

    @TableField("FPARENTID")
    private String fparentid;

    @TableField("FISSTART")
    private Long fisstart;

    @TableField("FISOUSEALUP")
    private Long fisousealup;

    @TableField("FDISPLAYNAME_L1")
    private String fdisplaynameL1;

    @TableField("FDISPLAYNAME_L2")
    private String fdisplaynameL2;

    @TableField("FDISPLAYNAME_L3")
    private String fdisplaynameL3;

    @TableField("FVERSIONNUMBER")
    private String fversionnumber;

    @TableField("FCODE")
    private String fcode;

    @TableField("FISTRANSPORTORGUNIT")
    private Long fistransportorgunit;

    @TableField("FISQUALITYORGUNIT")
    private Long fisqualityorgunit;

    @TableField("FORGTYPESTR")
    private String forgtypestr;


    @Override
    protected Serializable pkVal() {
        return this.fid;
    }

}
