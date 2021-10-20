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
 * @since 2021-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_BD_DATABASEDASSIGN")
public class TBdDatabasedassign extends Model<TBdDatabasedassign> {

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

    @TableField("FDATABASEDID")
    private String fdatabasedid;

    @TableField("FASSIGNCUID")
    private String fassigncuid;

    @TableField("FBOSOBJECTTYPE")
    private String fbosobjecttype;

    @TableField("FSTATUS")
    private Long fstatus;


    @Override
    protected Serializable pkVal() {
        return this.fid;
    }

}
