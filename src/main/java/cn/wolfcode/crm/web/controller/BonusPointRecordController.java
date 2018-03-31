package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.BonusPointRecord;
import cn.wolfcode.crm.service.IBonusPointRecordService;
import cn.wolfcode.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("bonusPointRecord")
public class BonusPointRecordController {
    @Autowired
    private IBonusPointRecordService bonusPointRecordService;

    @RequestMapping("selectByMemberId")
    @ResponseBody
    public List<BonusPointRecord> selectByMemberId(Long memberId ) throws Exception {
        return bonusPointRecordService.selectByMemberId(memberId);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(BonusPointRecord entity) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {

            bonusPointRecordService.saveOrUpdate(entity);
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            jsonResult.mark("操作失败:"+e.getMessage());
        }
        return jsonResult;
    }
}
