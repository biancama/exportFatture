package com.biancama.meoni.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Builder
@Getter
@Setter
public class Invoice {
	private static final long serialVersionUID = 1L;

	private String ditta;
	private String versione;
	private String tarc;

	private String codCliFor;
	private String raso;
	private String ind;
	private String cap;
	private String citta;
	private String prov;
	private String cofi;
	private String piva;
	private String pf;
	private String divide;
	private String paese;
	private String pivaEstero;
	private String cofiEstero;

	private String sesso;
	private String dtnas;
	private String comna;
	private String prvna;
	private String pref;
	private String nteleNum;
	private String faxPref;
	private String faxNum;
	private String cfconto;
	private String cfcodpag;
	private String cfbanca;
	private String cfagenzia;
	private String cfinterm;
	// Dati Fattura
	private String causale;
	private String cauDes;
	private String cauAgg;
	private String cauAgg1;
	private String cauAgg2;
	private Date dataRegistrazione;
	private Date dataDoc;
	private String numDocFor;
	private String ndoc;
	private String serie;
	private String ecPartita;
	private String ecPartitaAnno;
	private String ecCodVal;
	private String ecCambio;
	private String ecDataCambio;
	private String ecTotDocVal;
	private String ecTotIvaVal;
	private String plafond;
	
	// Iva
	private Iva[] ivas;
	//Totale fattura
	private String totFatt;
	//Conti di ricavo/costo
	private String contoRic;
	private String impRic;

	public static class InvoiceBuilder {
		private Iva[] ivas = new Iva[8];
		private String ditta = "MEONI";
		private String tarc = "0";
		private String versione = "3";

		private String codCliFor="00000";
		private String causale= "001";
		private String cauDes="Fatt.di vendita";


	}
}
