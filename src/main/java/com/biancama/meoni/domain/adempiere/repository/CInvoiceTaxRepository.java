package com.biancama.meoni.domain.adempiere.repository;


import com.biancama.meoni.domain.adempiere.CInvoiceTax;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by massimo.biancalani on 12/06/2017.
 */
@Repository
public class CInvoiceTaxRepository {
    private final static String IT_QUERY = "SELECT it.c_invoice_id, t.rate, it.taxbaseamt * 100 taxbaseamtCents, it.taxamt * 100 taxamtCents\n" +
        "FROM c_invoicetax it\n" +
        "INNER JOIN c_tax t ON (t.c_tax_id = it.c_tax_id)\n" +
        "WHERE it.c_invoice_id = ?\n" +
        "      AND it.ad_client_id = 1000000\n" +
        "      AND it.ad_org_id = 1000000";

    private final JdbcTemplate jdbc;

    public CInvoiceTaxRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public List<CInvoiceTax> findByInvoiceId (Long invoiceId) {
        return this.jdbc.query(
            IT_QUERY,
            (rs, i) -> CInvoiceTax.builder()
                        .cInvoiceId(invoiceId)
                        .rate(rs.getBigDecimal("rate"))
                        .baseAmountCents(rs.getBigDecimal("taxbaseamtCents"))
                        .amountCents(rs.getBigDecimal("taxamtCents"))
                        .build(),
            invoiceId
        );
    }


}
