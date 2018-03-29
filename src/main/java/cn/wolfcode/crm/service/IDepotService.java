
package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Depot;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;
import java.util.List;

public interface IDepotService {

	 int deleteByPrimaryKey(Long id);

	 int save(Depot depot);

	 Depot selectByPrimaryKey(Long id);

	 List<Depot> selectAll();

	PageResult query(QueryObject qo);

	void changeState(Long id);
}
