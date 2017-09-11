package com.biancama.meoni.service.dto;

import com.biancama.meoni.domain.Invoice;
import com.biancama.meoni.domain.Iva;
import com.biancama.meoni.domain.adempiere.CBPartner;
import com.biancama.meoni.domain.adempiere.CInvoice;
import com.biancama.meoni.domain.adempiere.CInvoiceTax;
import com.biancama.meoni.domain.adempiere.repository.CBPartnerRepository;
import com.biancama.meoni.domain.adempiere.repository.CInvoiceRepository;
import com.biancama.meoni.domain.adempiere.repository.CInvoiceTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by massimo on 30/01/2017.
 */
@Service
public class AdempiereToIO {

	@Autowired
	private CBPartnerRepository cbPartnerRepository;

	@Autowired
	private CInvoiceRepository cInvoiceRepository;

	@Autowired
	private CInvoiceTaxRepository cInvoiceTaxRepository;

	public List<Invoice> convertBetweenDate(String from, String to) {
		List<Invoice> result = new ArrayList<>();
		List<Long> cInvoiceIds = fetchAllInvoiceId(from, to);
		for (Long invoiceId : cInvoiceIds) {
			CInvoice cInvoice = cInvoiceRepository.findById(invoiceId);
			CBPartner cbPartner = cbPartnerRepository.findById(cInvoice.getCBPartnerId());
			List<CInvoiceTax> cInvoiceTaxes = cInvoiceTaxRepository.findByInvoiceId(invoiceId);
			result.add(toInvoice(cInvoice, cbPartner, cInvoiceTaxes));
		}
		return result;
	}

	private Invoice toInvoice(CInvoice cInvoice, CBPartner cbPartner, List<CInvoiceTax> cInvoiceTaxes) {
		Invoice invoice = Invoice.builder()
			.raso(cbPartner.getName())
			.ind(cbPartner.getStreet())
			.cap(cbPartner.getZipCode())
			.citta(cbPartner.getCity())
			.prov(cbPartner.getProvince())
			.piva(cbPartner.getVatCode())
			.cofi(cbPartner.getFiscalCode())
			.pf(cbPartner.isCompany() ? "N" : "S")
			.dataRegistrazione(cInvoice.getDocDate())
			.dataDoc(cInvoice.getDocDate())
			.ndoc(cInvoice.getDocNumber())
			.totFatt(cInvoice.getTotalCents().setScale(0).toString())
			.impRic(cInvoice.getTotalCents().setScale(0).toString())
			.build();
		int index = 0;
		for (CInvoiceTax cInvoiceTax : cInvoiceTaxes) {
			Iva iva = Iva.builder()
				.aliq(cInvoiceTax.getRate().setScale(0).toString())
				.imponib(cInvoiceTax.getBaseAmountCents().setScale(0).toString())
				.imposta(cInvoiceTax.getAmountCents().setScale(0).toString())
				.build();
			invoice.getIvas()[index++] = iva;
		}
		return invoice;
	}

	private List<Long> fetchAllInvoiceId(String from, String to) {
		return cInvoiceRepository.findByDate(from, to);
	}
}
