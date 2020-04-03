package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author wl_sun
 * @description TODO
 * @create Date
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Music {
    private String id;
    private String name;
    private String author;
    private String src;
    private String img;
    private int count;
    private String type;
    private LocalDate updateTime;
}
