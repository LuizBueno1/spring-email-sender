package com.ms.email.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDto {

    @NotBlank(message = "ownerRef must be filled")
    private String ownerRef;
    @NotBlank(message = "emailFrom must be filled")
    @Email
    private String emailFrom;
    @NotBlank(message = "emailTo must be filled")
    @Email
    private String emailTo;
    @NotBlank(message = "subject must be filled")
    private String subject;
    @NotBlank(message = "text must be filled")
    private String text;
}
