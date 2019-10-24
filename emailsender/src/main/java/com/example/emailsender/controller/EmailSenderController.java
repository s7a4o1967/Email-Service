package com.example.emailsender.controller;


import com.example.emailsender.Dto.CredentialDto;
import com.example.emailsender.Dto.EmailResponseDTO;
import com.example.emailsender.service.EmailSenderService;
import com.example.emailsender.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class EmailSenderController {

    @Autowired
    private EmailSenderService emailSenderService;


    @RequestMapping("/email")
    public ResponseEntity<Object> emailSend(@RequestBody CredentialDto credentialDto) {
        EmailResponseDTO emailResponseDTO=new EmailResponseDTO();
        emailResponseDTO.setName(credentialDto.getEmail());

        try{
           emailSenderService.sendEmail(credentialDto);
        }
        catch (MailException e){
          e.printStackTrace();
          emailResponseDTO.setStatus(AppConstants.FAILED);
          return ResponseEntity.status(HttpStatus.ACCEPTED).body(emailResponseDTO);
        }
        emailResponseDTO.setStatus(AppConstants.SUCCESS);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(emailResponseDTO);
    }
}
