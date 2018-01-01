package com.biancama.meoni.domain.io;

import static org.assertj.core.api.Assertions.assertThat;

import com.biancama.meoni.domain.Invoice;
import com.biancama.meoni.domain.Iva;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

/**
 * Created by massimo on 29/01/2017.
 */
public class UnmarshallerTest {

	@Test
	public void givenInvoiceWithCompanyAsCustomerAndOneTypeOfVATWhenUnMarshalledThenOutputFileIsCorrect() throws IOException {
		Iva iva = Iva.builder()
				.aliq("10")
				.imponib("45570")
				.imposta("4557").build();
		Invoice invoice = Invoice.builder()
				.raso("Salumificio Viani S.r.l.")
				.ind("via Petrarca 14")
				.cap("50013")
				.citta("Campi Bisenzio")
				.prov("FI")
				.piva("00922040522")
				.pf("N")
				.dataRegistrazione(Date.from(LocalDateTime.of(2007, Month.JANUARY, 8, 23, 11).atZone(ZoneId.systemDefault()).toInstant()))
				.dataDoc(Date.from(LocalDateTime.of(2007, Month.JANUARY, 8, 23, 11).atZone(ZoneId.systemDefault()).toInstant()))
				.ndoc("301")
				.totFatt("50127")
				.impRic("50127")
				.causale("001")
				.cauDes("Fatt.di vendita")
				.build();
				invoice.getIvas()[0] = iva;
		Resource resource = new ClassPathResource("invoice01.dat");
		String fileContentExcepted = new String(Files.readAllBytes(Paths.get(resource.getURI())), StandardCharsets.UTF_8);

		Resource invoiceResource = new ClassPathResource("invoice.xml");
		Unmarshaller unmarshaller = new Unmarshaller(invoice, invoiceResource.getInputStream());
		String fileContentActual = unmarshaller.unmarshall();

		assertThat(fileContentExcepted.length()).isEqualTo(7000);
		assertThat(fileContentActual.length()).isEqualTo(7000);
		assertThat(fileContentActual).isEqualTo(fileContentExcepted);
	}

	@Test
	public void givenInvoiceWithPrivateAsCustomerAndTwoTypesOfVATWhenUnMarshalledThenOutputFileIsCorrect() throws IOException {
		Iva iva01 = Iva.builder()
			.aliq("20")
			.imponib("22462")
			.imposta("4492").build();
		Iva iva02 = Iva.builder()
			.aliq("21")
			.imponib("9853")
			.imposta("2069").build();
		Invoice invoice = Invoice.builder()
			.raso("Lanificio Europa s.a.s.")
			.ind("via Montalese 176/c")
			.cap("59100")
			.citta("Prato")
			.prov("PO")
			.cofi("BNCMSM71H21G999S")
			.piva("00922040522")
			.pf("S")
			.dataRegistrazione(Date.from(LocalDateTime.of(2007, Month.JANUARY, 8, 23, 11).atZone(ZoneId.systemDefault()).toInstant()))
			.dataDoc(Date.from(LocalDateTime.of(2007, Month.JANUARY, 8, 23, 11).atZone(ZoneId.systemDefault()).toInstant()))
			.ndoc("103")
			.totFatt("38846")
			.impRic("38846")
			.causale("001")
			.cauDes("Fatt.di vendita")
			.build();
		invoice.getIvas()[0] = iva01;
		invoice.getIvas()[1] = iva02;

		Resource resource = new ClassPathResource("invoice02.dat");
		String fileContentExcepted = new String(Files.readAllBytes(Paths.get(resource.getURI())), StandardCharsets.UTF_8);

		Resource invoiceResource = new ClassPathResource("invoice.xml");
		Unmarshaller unmarshaller = new Unmarshaller(invoice, invoiceResource.getInputStream());
		String fileContentActual = unmarshaller.unmarshall();

		assertThat(fileContentExcepted.length()).isEqualTo(7000);
		assertThat(fileContentActual.length()).isEqualTo(7000);
		assertThat(fileContentActual).isEqualTo(fileContentExcepted);
	}
}
