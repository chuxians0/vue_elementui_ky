package com.example.demo2024zh.dto;

import lombok.Data;

import javax.swing.*;

@Data
public class HealthDTO {
    private ImageIcon id;
    private Long memberId;
    private String date;
    private String url;
    private Long current;
    private Long size;
}
