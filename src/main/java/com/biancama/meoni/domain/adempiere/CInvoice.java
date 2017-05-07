package com.biancama.meoni.domain.adempiere;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by massimo.biancalani on 20/06/2017.
 */
@Builder
@Getter
@Setter
public class CInvoice {
    private Long id;
    private Long cBPartnerId;
    private String docNumber;
    private Date docDate;
    private BigDecimal totalCents;

}
