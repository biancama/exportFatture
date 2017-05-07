create table c_bpartner
(
  c_bpartner_id numeric(10) not null,
  ad_client_id numeric(10) not null,
  ad_org_id numeric(10) not null,
  isactive char default 'Y'::bpchar not null
    constraint c_bpartner_isactive_check
    check (isactive = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])),
  created timestamp default now() not null,
  createdby numeric(10) not null,
  updated timestamp default now() not null,
  updatedby numeric(10) not null,
  value varchar(40) not null,
  name varchar(60) not null,
  name2 varchar(60),
  description varchar(255),
  issummary char default 'N'::bpchar not null
    constraint c_bpartner_issummary_check
    check (issummary = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])),
  c_bp_group_id numeric(10) not null,
  isonetime char default 'N'::bpchar not null
    constraint c_bpartner_isonetime_check
    check (isonetime = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])),
  isprospect char default 'Y'::bpchar not null
    constraint c_bpartner_isprospect_check
    check (isprospect = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])),
  isvendor char default 'N'::bpchar not null
    constraint c_bpartner_isvendor_check
    check (isvendor = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])),
  iscustomer char default 'Y'::bpchar not null
    constraint c_bpartner_iscustomer_check
    check (iscustomer = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])),
  isemployee char default 'N'::bpchar not null
    constraint c_bpartner_isemployee_check
    check (isemployee = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])),
  issalesrep char default 'N'::bpchar not null
    constraint c_bpartner_issalesrep_check
    check (issalesrep = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])),
  referenceno varchar(40),
  duns char(11),
  url varchar(120),
  ad_language varchar(6),
  taxid varchar(20),
  istaxexempt char default 'N'::bpchar
    constraint c_bpartner_istaxexempt_check
    check (istaxexempt = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])),
  c_invoiceschedule_id numeric(10),
  rating char,
  salesvolume numeric(10),
  numberemployees numeric(10),
  naics char(6),
  firstsale timestamp,
  acqusitioncost numeric default 0,
  potentiallifetimevalue numeric default 0,
  actuallifetimevalue numeric default 0,
  shareofcustomer numeric(10),
  paymentrule char,
  so_creditlimit numeric default 0,
  so_creditused numeric default 0,
  c_paymentterm_id numeric(10),
  m_pricelist_id numeric(10),
  m_discountschema_id numeric(10),
  c_dunning_id numeric(10),
  isdiscountprinted char default 'Y'::bpchar
    constraint c_bpartner_isdiscountprinted_check
    check (isdiscountprinted = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])),
  so_description varchar(255),
  poreference varchar(20),
  paymentrulepo char,
  po_pricelist_id numeric(10),
  po_discountschema_id numeric(10),
  po_paymentterm_id numeric(10),
  documentcopies numeric(10),
  c_greeting_id numeric(10),
  invoicerule char,
  deliveryrule char,
  freightcostrule char,
  deliveryviarule char,
  salesrep_id numeric(10),
  sendemail char default 'N'::bpchar not null,
  bpartner_parent_id numeric(10),
  invoice_printformat_id numeric(10),
  socreditstatus char default 'O'::bpchar,
  shelflifeminpct numeric(10),
  ad_orgbp_id numeric(10),
  flatdiscount numeric,
  totalopenbalance numeric,
  dunninggrace date,
  c_taxgroup_id numeric(10),
  fiscalid varchar(20),
  logo_id numeric(10) default NULL::numeric,
  ispotaxexempt char default 'N'::bpchar not null
    constraint c_bpartner_ispotaxexempt_check
    check (ispotaxexempt = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])),
  ismanufacturer char default 'N'::bpchar
    constraint c_bpartner_ismanufacturer_check
    check (ismanufacturer = ANY (ARRAY['Y'::bpchar, 'N'::bpchar]))
)
;

create unique index c_bpartner_value
  on c_bpartner (ad_client_id, value)
;

create index c_bpartner_name
  on c_bpartner (name)
;

create index c_bpartner_bporg
  on c_bpartner (ad_orgbp_id)
;

