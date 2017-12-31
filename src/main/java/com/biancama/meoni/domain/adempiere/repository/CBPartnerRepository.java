package com.biancama.meoni.domain.adempiere.repository;


import com.biancama.meoni.domain.adempiere.CBPartner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by massimo.biancalani on 12/06/2017.
 */
@Repository
public class CBPartnerRepository {
    private final static String BP_QUERY = "SELECT bp.c_bpartner_id, bp.name, bp.fiscalid, bp.taxid, loc.address1, loc.postal, loc.city, loc.postal_add " +
        "FROM c_bpartner bp " +
        "  INNER JOIN c_bpartner_location bploc ON (bp.c_bpartner_id = bploc.c_bpartner_id) " +
        "  INNER JOIN c_location loc ON (loc.c_location_id = bploc.c_location_id) " +
        "WHERE bp.c_bpartner_id = ? " +
        "      AND bploc.isactive = 'Y' " +
        "      AND loc.isactive = 'Y' " +
        "      AND bploc.isbillto = 'Y' " +
        "      AND bp.ad_client_id = 1000000 " +
        "LIMIT 1";

    private final JdbcTemplate jdbc;

    public CBPartnerRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public CBPartner findById (Long id) {
        return this.jdbc.queryForObject(
            BP_QUERY,
            (rs, i) -> CBPartner.builder()
                .id(id)
                .name(rs.getString("name"))
                .city(rs.getString("city"))
                .company(rs.getString("fiscalid") == null)
                .fiscalCode(rs.getString("fiscalid"))
                .province(rs.getString("postal_add"))
                .street(rs.getString("address1"))
                .vatCode(rs.getString("taxid"))
                .zipCode(rs.getString("postal"))
                .build(),
            id

        );

    }

}
