package com.kingdee.sqkg.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AccountView {
    private   String   FNumber;
    private   String   FName;
    private   String   FIsGFreeze;
    private   String  FGAA ;
    private   String   FIsCFreeze;
    private   String   FHelpCode;
    private   String   FCompanyID;
    private   String   FDC;
    private   String   FIsChangeCurrency;
    private   String   FIsBizChangeCurrency;
    private   String  FLongName ;
    private   String   FIsCashEquivalent;
    private   String   FIsQty;
    private   String   FMeasureUnitGroupID;
    private   String  FMeasureUnitID ;
    private   String   FAC;
    private   String   FPLType;
    private   String  FControl ;
    private   String   FCAA;
    private   String   FAcctCurrency;
    private   String   FIsBank;
    private   String  FAccountTypeID ;
    private   String  FIsCash ;
    private   String   FhasUserProperty;
    private   String   FIsAllowCA;
    private   String   FParentAAID;
    private   String   FIsUpperAllowCA;
    private   String   FIsSelfFreeze;
    private   String   FIsParentFreeze;
    private   String  FIsOutDailyAccount ;
    private   String   FACNotice;
    private   String  FBw ;
    private   String   FBorrowerMainCashFlowItemID;
    private   String  FBorrowerAttCashFlowItemID ;
    private   String   FLenderMainCashFlowItemID;
    private   String   FLenderAttCashFlowItemID;
    private   String   FIsControl;
    private   String   FAccrualDirection;
    private   String  FIsProfitCenter ;
    private   String   FCategory;
    private   String   FControlUnitID;
    private   String   FParentID;
    private   String   PARENTCODE;

}
