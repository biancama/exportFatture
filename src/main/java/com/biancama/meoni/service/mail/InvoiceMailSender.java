package com.biancama.meoni.service.mail;

import com.biancama.meoni.config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by massimo.biancalani on 20/06/2017.
 */
@Service
public class InvoiceMailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConfig mailConfig;

    public void sendMail(String from, String fileName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(mailConfig.getTo().toArray(new String[0]));
        helper.setCc(mailConfig.getCc().toArray(new String[0]));
        helper.setText("Export Fatture\nSaluti Elena Meoni");
        helper.setSubject("Fatture Meoni: " + from);

        FileSystemResource file = new FileSystemResource(new File(fileName));
        helper.addAttachment("export-fatture-meoni-" + from + ".dat", file);

        mailSender.send(message);
    }
}
