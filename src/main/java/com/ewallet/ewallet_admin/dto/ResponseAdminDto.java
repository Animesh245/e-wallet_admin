package com.ewallet.ewallet_admin.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponseAdminDto
{
    private String id;
    private String fullName;
    private String userName;
    private String birthDate;
    private String role;
    private String address;
    private String nidNumber;
    private String phoneNumber;
    private String email;
    private String password;
    private String gender;
    private String createdAt;
    private String profileImageUrl;
    private String status;
}
