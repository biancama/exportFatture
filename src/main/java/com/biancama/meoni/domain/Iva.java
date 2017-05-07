package com.biancama.meoni.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by massimo on 29/01/2017.
 */
@Builder
@Getter
@Setter
public class Iva {
	// Dati Iva
	private String imponib;
	private String aliq;
	private String aliqAgricola;
	private String iva11;
	private String imposta;
}
