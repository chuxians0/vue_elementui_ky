package com.example.demo2024zh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo2024zh.dto.HealthDTO;
import com.example.demo2024zh.entity.Health;

public interface IHealthService extends IService<Health>{

    public IPage<Health> queryAllHealth(Long current, Long size);
    IPage<Health> pageListByCondition(HealthDTO condition);

    Integer insert(Health health);
    Integer update(Health health);

    Integer delete(Integer id);
}
