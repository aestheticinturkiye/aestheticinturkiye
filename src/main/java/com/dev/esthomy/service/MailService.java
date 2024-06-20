package com.dev.esthomy.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    private String emailTemplateCreator(String name, String template) {
        String emailTemplate = template.replace("[Name]", name);
        return emailTemplate;
    }

    public void sendEmail(String email, String clientName, File file, String emailType) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);


        helper.setFrom("esthomy@noreply.com","Esthomy");
        helper.setTo(email);

        if(file != null){
            helper.addAttachment(
                    file.getName(), file);
        }

        helper.setSubject("Subject: Receipt of Your Request for a Cosmetic Surgery Proposal\n\n");
        helper.setText(emailTemplateCreator(clientName,emailType) ,true);

        mailSender.send(message);
    }
}
