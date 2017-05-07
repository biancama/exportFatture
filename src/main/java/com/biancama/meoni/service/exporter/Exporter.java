package com.biancama.meoni.service.exporter;

import static org.springframework.util.CollectionUtils.isEmpty;

import com.biancama.meoni.domain.Invoice;
import com.biancama.meoni.domain.io.Unmarshaller;
import com.biancama.meoni.service.dto.AdempiereToIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by massimo.biancalani on 20/06/2017.
 */
@Service
public class Exporter {

    @Autowired
    private AdempiereToIO adempiereToIO;

    public String extractFile(String from, String to) throws IOException {

        List<Invoice> invoices = adempiereToIO.convertBetweenDate(from, to);
        if(isEmpty(invoices)) {
            return null;
        }
        File temp = File.createTempFile("invoices-" + from, ".dat");
        temp.setWritable(true);
        Resource invoiceResource = new ClassPathResource("invoice.xml");
        try(  PrintWriter out = new PrintWriter( temp.getAbsolutePath() )  ){
            for (Invoice invoice : invoices) {
                Unmarshaller unmarshaller = new Unmarshaller(invoice, invoiceResource.getInputStream());
                    out.print( unmarshaller.unmarshall());
            }
        }
        temp.setWritable(false);
        return temp.getAbsolutePath();
    }
}
