package com.ewallet.ewallet_admin;

import com.common_service.attachment.service.definition.IAttachmentService;
import com.common_service.attachment.service.implementation.AttachmentServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EwalletAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(EwalletAdminApplication.class, args);
    }

    @Bean
    public IAttachmentService attachmentService()
    {
        return new AttachmentServiceImpl();
    }

}
