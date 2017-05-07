package com.biancama.meoni.domain.adempiere.repository;


import com.biancama.meoni.domain.adempiere.CInvoice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by massimo.biancalani on 12/06/2017.
 */
@Repository
public class CInvoiceRepository {
    private final static String I_QUERY = "SELECT i.c_invoice_id, i.c_bpartner_id, i.documentno, i.dateinvoiced, i.grandtotal * 100 totalCents\n" +
        "FROM c_invoice i\n" +
        "WHERE i.c_invoice_id = ?\n" +
        "      AND i.ad_client_id = 1000000\n" +
        "      AND i.ad_org_id = 1000000";

    private final static String I_FETCH_ALL_QUERY = "SELECT i.c_invoice_id\n" +
        "FROM c_invoice i\n" +
        "WHERE i.dateinvoiced >= to_date(?, 'DD-MM-YYYY') AND i.dateinvoiced < to_date(?, 'DD-MM-YYYY')\n" +
        "AND i.ad_client_id = 1000000\n" +
        "AND i.ad_org_id = 1000000";

    private final JdbcTemplate jdbc;

    public CInvoiceRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public CInvoice findById (Long id) {
        return this.jdbc.queryForObject(
            I_QUERY,
            (rs, i) -> CInvoice.builder()
                .id(id)
                .cBPartnerId(rs.getLong("c_bpartner_id"))
                .docDate(rs.getDate("dateinvoiced"))
                .docNumber(rs.getString("documentno"))
                .totalCents(rs.getBigDecimal("totalCents"))
                .build(),
            id
        );
    }

    public List<Long> findByDate (String from, String till) {
        return this.jdbc.query(I_FETCH_ALL_QUERY,
            (rs, i) -> rs.getLong(1),
            from, till);
    }

}
