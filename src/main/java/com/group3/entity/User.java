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
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String salt;
    private String email;
    private String phoneNumber;
    private Integer status;
    private Integer binding;
    private Integer credits;
    private LocalDate createTime;
    private LocalDate lastLoginTime;
}
