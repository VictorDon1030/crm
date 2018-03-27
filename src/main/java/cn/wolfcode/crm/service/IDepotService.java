// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IDepotService.java

package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Depot;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;
import java.util.List;

public interface IDepotService {

	 int deleteByPrimaryKey(Long id);

	 int save(Depot depot);

	 Depot selectByPrimaryKey(Long id);

	 List selectAll();

	PageResult query(QueryObject qo);

	void changeState(Long id);
}
