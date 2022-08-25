package com.ewallet.ewallet_admin.service.definition;

import com.ewallet.ewallet_admin.dto.request.RequestAdminDto;
import com.ewallet.ewallet_admin.dto.response.ResponseAdminDto;
import com.ewallet.ewallet_admin.entity.Admin;

import java.io.IOException;
import java.util.List;

public interface AdminService
{
    ResponseAdminDto getAdmin(String id) throws Exception;

    List<ResponseAdminDto> getAllAdmin();

    void createAdmin(RequestAdminDto requestAdminDto) throws IOException;

    void updateAdmin(String id, RequestAdminDto requestAdminDto) throws IOException;

    void deleteAdmin(String id) throws Exception;

    Admin dtoToEntity(RequestAdminDto requestAdminDto) throws IOException;

    ResponseAdminDto entityToDto(Admin admin);
}
