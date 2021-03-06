package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.ExchangeRecord;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理积分兑换记录的service接口
 */
public interface IExchangeRecordService {

    /**
     * 封装列表并进行分页操作
     * @param qo 分页条件
     * @return
     */
    PageResult query(QueryObject qo);

    /**
     * 保存积分兑换记录的方式
     * @param entity  包含积分兑换记录的实体
     */
    void save(ExchangeRecord entity);

    /**
     * 导出兑换记录的方法
     * @param response
     */
    void exportExcel(HttpServletResponse response) throws Exception;

}
