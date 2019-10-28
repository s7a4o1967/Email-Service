package com.example.emailsender.service;

import com.example.emailsender.Dto.CredentialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class EmailSenderService {


    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    public void sendEmail(CredentialDto credentialDto) throws MailException, MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(credentialDto.getEmail());
        helper.setFrom(credentialDto.getFrom());
        helper.setSubject(credentialDto.getSubject());
        STGroupFile stGroupFile=new STGroupFile("C:\\Users\\MT1080\\Downloads\\EmailService\\Email-Service\\emailsender\\src\\main\\resources\\email.stg",'$','$');
        ST st=stGroupFile.getInstanceOf("Emailtemplate").add("name",credentialDto.getFirstName()).add("empId",credentialDto.getEmpId()).add("password",credentialDto.getPassword());
        String s=st.render();

        message.setContent(s,"text/html");

        javaMailSender.send(message);
    }

}
