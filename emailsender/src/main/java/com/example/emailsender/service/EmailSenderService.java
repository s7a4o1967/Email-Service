package com.example.emailsender.service;

import com.example.emailsender.Dto.CredentialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.naming.Context;

@Service
public class EmailSenderService {


    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    public void sendEmail(CredentialDto credentialDto) throws MailException{
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(credentialDto.getEmail());
        mail.setFrom(credentialDto.getFrom());
        mail.setSubject(credentialDto.getSubject());
        mail.setText(credentialDto.getText());
        mail.setText("Your Id is "+credentialDto.getEmpId()+"\n Your password is "+credentialDto.getPassword());

//        final Context ctx = new Context(locale);
//        ctx.setVariable("name", credentialDto.getFirstName());
//        ctx.setVariable("subscriptionDate", new );
//        ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML
//
//        final String htmlContent = this.templateEngine.process("html/email-inlineimage.html", ctx);

        javaMailSender.send(mail);
    }

}
