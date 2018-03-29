package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.mapper.MemberMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IMemberService;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Member entity) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        entity.setOnUser(employee);
        return memberMapper.insert(entity);
    }

    @Override
    public Member selectByPrimaryKey(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Member> selectAll() {
        return memberMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Member entity) {
        return memberMapper.updateByPrimaryKey(entity);
    }
    @Override
    public PageResult query(QueryObject qo) {
        int total = memberMapper.queryCount(qo);
        List<Member> data = memberMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }

    @Override
    public void changeState(Long id) {
        memberMapper.changeState(id);
    }

    @Override
    public void updatePasswordById(Member member) {
        Md5Hash md5Hash = new Md5Hash(member.getPassword(), member.getName(), 2);
        /*if(entity.getPassword()!= null) {
                Md5Hash md5Hash = new Md5Hash(entity.getPassword(), entity.getUsername(), 2);
                entity.setPassword(md5Hash.toString());
            }*/
        member.setPassword(md5Hash.toString());
        memberMapper.updatePasswordById(member);
    }

}
