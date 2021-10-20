package com.kingdee.sqkg.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_BD_SUPPLIERCOMPANYBANK")
public class TBdSuppliercompanybank extends Model<TBdSuppliercompanybank> {

    private static final long serialVersionUID = 1L;

    @TableId("FID")
    private String fid;

    @TableField("FBANK")
    private String fbank;

    @TableField("FBANKACCOUNT")
    private String fbankaccount;

    @TableField("FBANKADDRESS")
    private String fbankaddress;

    @TableField("FSUPPLIERCOMPANYINFOID")
    private String fsuppliercompanyinfoid;

    @TableField("FSEQ")
    private Long fseq;

    @TableField("FBANKENGNAME")
    private String fbankengname;

    @TableField("FBENEFICIARY")
    private String fbeneficiary;

    @TableField("FBENEFICIARYENGNAME")
    private String fbeneficiaryengname;

    @TableField("FBENEFICIARYBANKCOUNTRYID")
    private String fbeneficiarybankcountryid;

    @TableField("FBENEFICIARYBANKCOUNTRYISO")
    private String fbeneficiarybankcountryiso;

    @TableField("FBENEFICIARYBANKCODE")
    private String fbeneficiarybankcode;

    @TableField("FBENEFICIARYADDR")
    private String fbeneficiaryaddr;

    @TableField("FBENEFICIARYBANKSWIFT")
    private String fbeneficiarybankswift;

    @TableField("FSETTLEMENTPARAM")
    private String fsettlementparam;

    @TableField("FAGENCYBANKACCOUNT")
    private String fagencybankaccount;

    @TableField("FAGENCYBANKNAME")
    private String fagencybankname;

    @TableField("FAGENCYBANKCOUNTRYID")
    private String fagencybankcountryid;

    @TableField("FAGENCYBANKCOUNTRYISO")
    private String fagencybankcountryiso;

    @TableField("FAGENCYBANKSWIFT")
    private String fagencybankswift;

    @TableField("FAGENCYBANKADDR")
    private String fagencybankaddr;

    @TableField("FBANKID")
    private String fbankid;

    @TableField("FISDEFAULT")
    private Long fisdefault;


    @Override
    protected Serializable pkVal() {
        return this.fid;
    }

}
