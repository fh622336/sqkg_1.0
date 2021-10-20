package com.kingdee.sqkg.domain.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Custom {
    private  String name;
    private  String number;
    @JSONField(name = "DESC20")
    private  String dESC20;
    //国家
    @JSONField(name = "DESC12")
    private  String dESC12;
    @JSONField(name = "DESC15")
    private  String dESC15;
    @JSONField(name = "DESC16")
    private  String dESC16;
    @JSONField(name = "DESC17")
    private  String dESC17;
    @JSONField(name = "DESC4")
    private  String dESC4;
    @JSONField(name = "DESC6")
    private  String dESC6;
    @JSONField(name = "DESC11")
    private  String dESC11;
    //开户账号
    @JSONField(name = "DESC23")
    private  String dESC23;
    //开户名称
    @JSONField(name = "DESC21")
    private  String dESC21;
    //开户行
    @JSONField(name = "DESC22")
    private  String dESC22;

}
