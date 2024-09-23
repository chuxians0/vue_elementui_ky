//package com.example.demo2024zh.controller;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.example.demo2024zh.dto.HealthDTO;
//import com.example.demo2024zh.entity.Health;
//import com.example.demo2024zh.service.IHealthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/health")
//@CrossOrigin(origins = "*")
//public class HealthController {
//
//    @Autowired
//    IHealthService healthService;
//
//    // 查询健康记录列表
//    @GetMapping("/queryHealths")
//
//    public IPage<Health> queryHealths(@RequestParam  Long current,@RequestParam Long size) {
//        return healthService.queryAllHealth(current, size);
//    }
//
//
////     根据条件查询健康记录列表（使用 POST 传递复杂的 DTO）
//@PostMapping("/querybycondition")
//public IPage<Health> queryByCondition(@RequestBody HealthDTO healthDTO) {
//    return healthService.pageListByCondition(healthDTO);
//}
//
//
//    // 插入健康记录（使用 POST 传递健康数据）
//    @PostMapping("/insert")
//    public void insert(@RequestBody Health health) {
//        // 确保 health 对象中没有手动设置 id
//        healthService.insert(health);
//    }
//
//    // 更新健康记录（使用 PUT）
//    @PutMapping("/update")
//    public ResponseEntity<String> update(@RequestBody Health health) {
//        try {
//            healthService.update(health); // 调用 service 层的更新方法
//            return ResponseEntity.ok("Update successful");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed");
//        }
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> delete(@RequestParam Integer id) {
//        try {
//            healthService.delete(id); // 调用 service 层的删除方法
//            return ResponseEntity.ok("Delete successful");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed");
//        }
//    }
//}

package com.example.demo2024zh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo2024zh.dto.HealthDTO;
import com.example.demo2024zh.entity.Health;
import com.example.demo2024zh.service.IHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
@CrossOrigin(origins = "*")
public class HealthController {

    @Autowired
    IHealthService healthService;

    // 查询健康记录列表
    @GetMapping("/queryHealths")
    public IPage<Health> queryHealths(@RequestParam Long current, @RequestParam Long size) {
        return healthService.queryAllHealth(current, size);
    }

    // 根据条件查询健康记录列表（使用 POST 传递复杂的 DTO）
    @PostMapping("/querybycondition")
    public IPage<Health> queryByCondition(@RequestBody HealthDTO healthDTO) {
        return healthService.pageListByCondition(healthDTO);
    }

    // 插入健康记录（使用 POST 传递健康数据）
    @PostMapping("/insert")
    public void insert(@RequestBody Health health) {
        // 确保 health 对象中没有手动设置 id
        healthService.insert(health);
    }

    // 更新健康记录（使用 PUT）
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Health health) {
        try {
            healthService.update(health); // 调用 service 层的更新方法
            return ResponseEntity.ok("Update successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed");
        }
    }

    // 删除健康记录（使用 DELETE）
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Integer id) {
        try {
            healthService.delete(id); // 调用 service 层的删除方法
            return ResponseEntity.ok("Delete successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed");
        }
    }
}
