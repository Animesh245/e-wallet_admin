package com.ewallet.ewallet_admin.controller;

import com.ewallet.ewallet_admin.dto.RequestAdminDto;
import com.ewallet.ewallet_admin.dto.ResponseAdminDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("${http://localhost:4200}")
@RequestMapping("/api/v1/admin")
public interface AdminController
{
    @PostMapping("/")
    ResponseEntity<String> createAdmin(@ModelAttribute RequestAdminDto requestAdminDto) throws IOException;

    @PutMapping("/{id}")
    ResponseEntity<?> updateAdmin(@PathVariable("id") String id, @ModelAttribute RequestAdminDto requestAdminDto) throws IOException;

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAdmin(@PathVariable("id") String id) throws Exception;

    @GetMapping("/")
    ResponseEntity<List<ResponseAdminDto>> getAdminList();

    @GetMapping("/{id}")
    ResponseEntity<ResponseAdminDto> getAdmin(@PathVariable("id") String id) throws Exception;
}
