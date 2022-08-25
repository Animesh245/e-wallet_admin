package com.ewallet.ewallet_admin.service.definition;

import com.ewallet.ewallet_admin.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface AttachmentService
{
    Attachment getAttachment(UUID id);

    Attachment uploadAttachment(MultipartFile file, String path) throws IOException;
}
