package com.biancama.meoni.domain.adempiere;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by massimo.biancalani on 11/06/2017.
 */
@Builder
@Getter
@Setter
public class CBPartner {
    private Long id;
    private String name;
    private String street;
    private String zipCode;
    private String city;
    private String province;
    private String fiscalCode;
    private String vatCode;
    private boolean company;

    public static class CBPartnerBuilder {
        private boolean company = true;
    }
}
