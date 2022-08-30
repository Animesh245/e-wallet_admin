package com.ewallet.ewallet_admin.controller;

import com.ewallet.ewallet_admin.dto.RequestAdminDto;
import com.ewallet.ewallet_admin.dto.ResponseAdminDto;
import com.ewallet.ewallet_admin.service.IAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class AdminControllerImpl implements IAdminController {

    private final IAdminService iAdminService;

    public AdminControllerImpl(IAdminService iAdminService) {
        this.iAdminService = iAdminService;
    }


    @Override
    public ResponseEntity<String> createAdmin(RequestAdminDto requestAdminDto) throws IOException {
        iAdminService.createAdmin(requestAdminDto);
        return new  ResponseEntity<>("Successfully created admin", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String > updateAdmin(String id, RequestAdminDto requestAdminDto) throws IOException
    {
        iAdminService.updateAdmin(id, requestAdminDto);

        return ResponseEntity.ok("Admin updated");
    }


    @Override
    public ResponseEntity<String > deleteAdmin( String id) throws Exception
    {
        iAdminService.deleteAdmin(id);
        return ResponseEntity.ok("Admin account deleted");
    }

    @Override
    public ResponseEntity<List<ResponseAdminDto>> getAdminList()
    {
        var resAdminDtoList = iAdminService.getAllAdmin();
        return ResponseEntity.ok(resAdminDtoList);
    }

    @Override
    public ResponseEntity<ResponseAdminDto> getAdmin(String id) throws Exception{
        var resAdminDto = iAdminService.getAdmin(id);
        return ResponseEntity.ok(resAdminDto);
    }

}
