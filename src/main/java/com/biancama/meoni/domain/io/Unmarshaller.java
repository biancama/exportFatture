package com.biancama.meoni.domain.io;

import static org.springframework.util.StringUtils.isEmpty;

import com.biancama.meoni.domain.Invoice;
import lombok.AllArgsConstructor;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by massimo on 29/01/2017.
 */
@AllArgsConstructor
public class Unmarshaller {

	private final Invoice invoice;

	private final InputStream mappingFile;


	public String unmarshall() throws IOException {
		if (invoice == null || isEmpty(mappingFile)){
			return null;
		}
		StreamFactory factory = StreamFactory.newInstance();
		// load the mapping file
		factory.load(mappingFile);
		StringWriter stringWriter = new StringWriter();
		BeanWriter out = factory.createWriter("invoice", stringWriter);
		out.write(invoice);
		out.flush();
		out.close();
		return stringWriter.toString();
	};
}
