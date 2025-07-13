package com.ms.email.controllers;

import com.ms.email.dtos.EmailDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestController
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

    @GetMapping("/emails")
    public ResponseEntity<Page<EmailModel>> getEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(emailService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/emails/all")
    public ResponseEntity<List<EmailModel>> getAllEmails() {
        return new ResponseEntity<>(emailService.listAllNonPageable(), HttpStatus.OK);
    }
    
    
    @GetMapping("/emails/{emailId}")
    public ResponseEntity<Object> getEmailById(@PathVariable UUID emailId) {
        Optional<EmailModel> emailModelOptional = emailService.findByEmailId(emailId);

        if(!emailModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
        }
    }
    
}
