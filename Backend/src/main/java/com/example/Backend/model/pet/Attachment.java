package com.example.Backend.model.pet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attachment {
    String title;
    String attachmentUrl;
}