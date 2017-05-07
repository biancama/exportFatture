package com.biancama.meoni.domain.adempiere;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by massimo.biancalani on 20/06/2017.
 */
@Builder
@Getter
@Setter
public class CInvoiceTax {
    private Long cInvoiceId;
    private BigDecimal rate;
    private BigDecimal baseAmountCents;
    private BigDecimal amountCents;
}
