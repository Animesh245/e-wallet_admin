package com.ewallet.ewallet_admin.service;

import com.common_service.attachment.config.Properties;
import com.common_service.attachment.service.definition.AttachmentService;
import com.common_service.enums.Gender;
import com.common_service.enums.Role;
import com.common_service.enums.Status;
import com.common_service.exceptions.NotFoundException;
import com.ewallet.ewallet_admin.dto.RequestAdminDto;
import com.ewallet.ewallet_admin.dto.ResponseAdminDto;
import com.ewallet.ewallet_admin.entity.Admin;
import com.ewallet.ewallet_admin.repository.AdminRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService
{
    private final AdminRepository adminRepository;
    private final AttachmentService attachmentService;

    public AdminServiceImpl(AdminRepository adminRepository,  AttachmentService attachmentService)
    {
        this.adminRepository = adminRepository;
        this.attachmentService = attachmentService;
    }

    @Override
    public ResponseAdminDto getAdmin(String id)
    {
        var admin = adminRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException("Not Found"));        //Finding the entity from the table

        return entityToDto(admin);
    }

    @Override
    public List<ResponseAdminDto> getAllAdmin()
    {
        List<Admin> adminList = adminRepository.findAll();
        List<ResponseAdminDto> responseAdminDtos = new ArrayList<>();
        var responseAdminDto = new ResponseAdminDto();

        for (Admin admin: adminList)
        {
            responseAdminDto = entityToDto(admin);
            responseAdminDtos.add(responseAdminDto);
        }
        return responseAdminDtos;
    }

    @Override
    public void createAdmin(RequestAdminDto requestAdminDto) throws IOException
    {
        if(adminRepository.findAdminByNidNumber(requestAdminDto.getNidNumber()) == null)
        {
            var admin = dtoToEntity(requestAdminDto);
            var createdAt = LocalDateTime.now();
            admin.setCreatedAt(createdAt);

            adminRepository.save(admin);
        }
    }

    @Override
    public void updateAdmin(String id, RequestAdminDto requestAdminDto) throws IOException
    {
        var admin = adminRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException("Not Found"));

        var admin2 = dtoToEntity(requestAdminDto);
        admin2.setId(admin.getId());
        admin2.setCreatedAt(admin.getCreatedAt());
        admin2.setStatus(admin.getStatus());
        adminRepository.save(admin2);
    }

    @Override
    public void deleteAdmin(String id)
    {
        adminRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Admin dtoToEntity(RequestAdminDto requestAdminDto) throws IOException
    {
        var profileImageUrl = attachmentService.uploadAttachment(requestAdminDto.getProfileImage(), Properties.Attachment_FOLDER);
        var nidPhotoUrl = attachmentService.uploadNidCard(requestAdminDto.getNidCardPhoto(), Properties.NID_CARD_FOLDER);

        var admin = new Admin();
        BeanUtils.copyProperties(requestAdminDto,admin);
        var birthDate = LocalDate.parse(requestAdminDto.getBirthDate());
        admin.setBirthDate(birthDate);
        admin.setProfileImageUrl(profileImageUrl);
        admin.setNidCardPhotoUrl(nidPhotoUrl);
        admin.setStatus(Status.ACTIVE);
        admin.setRole(Role.ROLE_ADMIN);
        admin.setGender(Gender.valueOf(requestAdminDto.getGender()));
        return admin;
    }

    @Override
    public ResponseAdminDto entityToDto(Admin admin)
    {
        var responseAdminDto = new ResponseAdminDto();
        BeanUtils.copyProperties(admin, responseAdminDto);
        responseAdminDto.setId(admin.getId().toString());
        responseAdminDto.setBirthDate(admin.getBirthDate().toString());
        responseAdminDto.setRole(Role.ROLE_ADMIN.toString());
        responseAdminDto.setCreatedAt(admin.getCreatedAt().toString());
        responseAdminDto.setNidCardPhotoUrl(admin.getNidCardPhotoUrl());
        responseAdminDto.setProfileImageUrl(admin.getProfileImageUrl());
        responseAdminDto.setStatus(admin.getStatus().toString());
        responseAdminDto.setGender(admin.getGender().toString());
        return responseAdminDto;
    }
}
