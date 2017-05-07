package com.biancama.meoni;

import com.biancama.meoni.service.exporter.Exporter;
import com.biancama.meoni.service.mail.InvoiceMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by massimo.biancalani on 20/06/2017.
 */
@Component
public class CommandLiner implements CommandLineRunner {

    @Autowired
    private Exporter exporter;

    @Autowired
    private InvoiceMailSender mailSender;

    @Override
    public void run(String... args) throws Exception {
        String from = args[0];
        String to = args[1];
        String exportFile = exporter.extractFile(from, to);
        if (exportFile != null) {
            System.out.println("File exported: " + exportFile);
            mailSender.sendMail(from, exportFile);
            Path fileToDeletePath = Paths.get(exportFile);
            Files.delete(fileToDeletePath);
        }

    }

}
