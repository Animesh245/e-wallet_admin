package com.ewallet.ewallet_admin.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


@Getter
@Setter
public class RequestAdminDto
{
    private String fullName;
    private String userName;
    private String birthDate;
    private String address;
    private String nidNumber;
    private String phoneNumber;
    private String email;
    private String password;
    private String gender;
    private CommonsMultipartFile profileImage;
}
