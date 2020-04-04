package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private Integer id;
    private String mId;
    private String name;
    private String author;
    private String src;
    private String img;
    private int count;
    private String type;
    private LocalDateTime updateTime;
}
