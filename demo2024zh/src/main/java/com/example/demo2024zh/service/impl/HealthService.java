package com.example.demo2024zh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo2024zh.dto.HealthDTO;
import com.example.demo2024zh.service.IHealthService;
import com.example.demo2024zh.dao.HealthDAO;
import com.example.demo2024zh.entity.Health;
import org.springframework.stereotype.Service;

@Service
public class HealthService extends ServiceImpl<HealthDAO, Health> implements IHealthService {

    @Override
    public IPage<Health> queryAllHealth(Long current, Long size) {
        Page<Health> page = new Page<>(current, size);
        QueryWrapper<Health> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Health> pageListByCondition(HealthDTO condition) {
        Page<Health> page = new Page<>(condition.getCurrent(), condition.getSize());
        QueryWrapper<Health> queryWrapper = new QueryWrapper<>();

        if (condition.getMemberId() != null && condition.getMemberId()>0) {
            queryWrapper.eq("memberID", condition.getMemberId());
        }

        return this.baseMapper.selectPage(page, queryWrapper);
    }


    @Override
    public Integer insert(Health health) {
//        try {
//            // 插入逻辑
//        } catch (IllegalArgumentException e) {
//            System.out.println("Argument type mismatch: " + e.getMessage());
//            throw e;  // 重新抛出以便在控制器层捕获
//        }
        return this.baseMapper.insert(health);
    }

    @Override
    public Integer update(Health health) {
        return this.baseMapper.updateById(health);
    }

    @Override
    public Integer delete(Integer id) {
        return this.baseMapper.deleteById(id);
    }
}
