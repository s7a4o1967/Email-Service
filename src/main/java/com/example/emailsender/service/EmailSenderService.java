package com.example.emailsender.service;

import com.example.emailsender.Dto.CredentialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailSenderService {

    private JavaMailSender javaMailSender;

    private TemplateEngine templateEngine;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender,TemplateEngine templateEngine){
        this.javaMailSender=javaMailSender;
        this.templateEngine=templateEngine;
    }

    public void sendEmail(CredentialDto credentialDto) throws MailException, MessagingException {

        MimeMessage mail=javaMailSender.createMimeMessage();
       // SimpleMailMessage mail=new SimpleMailMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mail);
        helper.setTo(credentialDto.getEmail());
        helper.setFrom(credentialDto.getFrom());
        helper.setSubject(credentialDto.getSubject());


        Context context=new Context();
        context.setVariable("name",credentialDto.getFirstName());
        context.setVariable("empid",credentialDto.getEmpId() );
        context.setVariable("password",credentialDto.getPassword());

         String htmlContent = this.templateEngine.process("template.html", context);
        mail.setText(htmlContent);
        mail.setContent(htmlContent,"text/html");
        javaMailSender.send(mail);
    }

}
