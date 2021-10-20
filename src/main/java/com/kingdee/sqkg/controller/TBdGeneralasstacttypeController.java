    package com.kingdee.sqkg.controller;


    import com.kingdee.sqkg.domain.entity.Response;
    import com.kingdee.sqkg.service.TBdGeneralasstacttypeService;
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.format.annotation.DateTimeFormat;
    import org.springframework.web.bind.annotation.GetMapping;

    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.*;

    /**
     * <p>
     *  前端控制器
     * </p>
     *
     * @author 樊浩
     * @since 2021-08-18
     */
    @RestController
    @Api(value = "陕汽控股核算项目管理")
    public class TBdGeneralasstacttypeController {
        @Autowired
        private TBdGeneralasstacttypeService generalasstacttypeService;
        @GetMapping("customAccountingItems")
        @ApiOperation(value = "获取自定义核算项目")
        public Response budgetList(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                   @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
                                   @RequestParam  int  actType,
                                   @RequestParam String adminNumber) {
            return generalasstacttypeService.initLoad(startDate, endDate, actType,  adminNumber);
        }
    }
