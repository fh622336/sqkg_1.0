package com.kingdee.sqkg.service;

import com.kingdee.sqkg.domain.entity.TBdCustomer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 樊浩
 * @since 2021-09-01
 */
public interface TBdCustomerService extends IService<TBdCustomer> {
   void  addCustomer() throws  Exception;
}
