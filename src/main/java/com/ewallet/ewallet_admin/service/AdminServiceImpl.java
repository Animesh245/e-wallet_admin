package com.ewallet.ewallet_admin.service;

import com.common_service.attachment.config.Properties;
import com.common_service.attachment.service.definition.IAttachmentService;
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
public class AdminServiceImpl implements IAdminService
{
    private final AdminRepository adminRepository;
    private final IAttachmentService iAttachmentService;

    public AdminServiceImpl(AdminRepository adminRepository,  IAttachmentService iAttachmentService) {
        this.adminRepository = adminRepository;
        this.iAttachmentService = iAttachmentService;
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
        var responseAdminDtos = new ArrayList<ResponseAdminDto>();
        var responseAdminDto = new ResponseAdminDto();

        for (Admin admin: adminList)
        {
            responseAdminDto = entityToDto(admin);
            responseAdminDtos.add(responseAdminDto);
        }
        return responseAdminDtos;
    }

    @Override
    public void createAdmin(RequestAdminDto requestAdminDto) throws IOException {
        var admin = dtoToEntity(requestAdminDto);
        var createdAt = LocalDateTime.now();
        admin.setCreatedAt(createdAt);

        adminRepository.save(admin);
    }

    @Override
    public void updateAdmin(String id, RequestAdminDto requestAdminDto) throws IOException {
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
    public Admin dtoToEntity(RequestAdminDto requestAdminDto) throws IOException {
//        var nidCard = nidCardService.getNidCard(UUID.fromString(requestAdminDto.getNidNumber()));
        var profileImage = iAttachmentService.uploadAttachment(requestAdminDto.getProfileImage(), Properties.Attachment_FOLDER).toString();


        var admin = new Admin();
        BeanUtils.copyProperties(requestAdminDto,admin);
        var birthDate = LocalDate.parse(requestAdminDto.getBirthDate());
        admin.setBirthDate(birthDate);
//        admin.setNidNumber(nidCard);
        admin.setProfileImageUrl(profileImage);
        admin.setStatus(Status.ACTIVE);
        admin.setRole(Role.ROLE_ADMIN);
        admin.setGender(Gender.valueOf(requestAdminDto.getGender()));
        return admin;
    }

    @Override
    public ResponseAdminDto entityToDto(Admin admin)
    {
        var responseAdminDtoAdminDto = new ResponseAdminDto();
        BeanUtils.copyProperties(admin, responseAdminDtoAdminDto);
        responseAdminDtoAdminDto.setId(admin.getId().toString());
        responseAdminDtoAdminDto.setBirthDate(admin.getBirthDate().toString());
        responseAdminDtoAdminDto.setRole(Role.ROLE_ADMIN.toString());
        responseAdminDtoAdminDto.setCreatedAt(admin.getCreatedAt().toString());
//        responseAdminDtoAdminDto.setNidNumber(admin.getNidNumber().getNidNumber());
        responseAdminDtoAdminDto.setProfileImageUrl(admin.getProfileImageUrl());
        responseAdminDtoAdminDto.setStatus(admin.getStatus().toString());
        responseAdminDtoAdminDto.setGender(admin.getGender().toString());
        return responseAdminDtoAdminDto;
    }
}
