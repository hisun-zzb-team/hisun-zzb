drop table if exists app_sh_pc ;
create table if not exists app_sh_pc (
  id varchar(32) not null,
  pc_mc varchar(255)  ,
  pc_sj datetime  ,
  shlx varchar(1)  ,
  sjlx varchar(1)  ,
  path varchar(128)  ,
  pc_px varchar(1)  ,
  primary key (id));

drop table if exists app_sh_tp ;
create table if not exists app_sh_tp (
  id varchar(32) not null,
  tpq_bh varchar(128)  ,
  tpr_id varchar(32)  ,
  tpr_xm varchar(64)  ,
  tp_sj datetime  ,
  app_sh_pc_id varchar(32) not null,
  is_posted varchar(1) not null default 0,
  primary key (id),

  constraint fk_rm_sh_tp_rm_sh_pc1
  foreign key (app_sh_pc_id)
  references app_sh_pc (id)
  on delete no action
  on update no action);

drop table if exists app_sh_a01 ;
create table if not exists app_sh_a01 (
  id varchar(32) not null,
  app_sh_pc_id varchar(32) not null,
  rmlx varchar(1)  ,
  rmlx_str varchar(128) ,
  xm varchar(20) ,
  xb varchar(10) ,
  mz varchar(20) ,
  jg varchar(20) ,
  csny varchar(24) ,
  cjgzsj varchar(24) ,
  rdsj varchar(24) ,
  whcd varchar(40) ,
  rxjbsj varchar(24) ,
  mztjqk varchar(80) ,
  ywfpjl varchar(10) ,
  xgzdwjzw varchar(128) ,
  ntzpbyj varchar(128) ,
  shyj varchar(10)  ,
  zp_path varchar (128) ,
  a01_px int ,
  primary key (id));

drop table if exists app_sh_tp_sj ;

create table if not exists app_sh_tp_sj (
  id varchar(32) not null,
  tp int not null ,
  app_sh_tp_id varchar(32) not null,
  app_sh_a01_id varchar(32) not null,
  primary key (id));

drop table if exists app_sh_a01_kccl ;

create table if not exists app_sh_a01_kccl (
  id varchar(32) not null,
  path varchar(128) ,
  app_sh_a01_id varchar(32) not null,
  primary key (id));


drop table if exists app_sh_a01_dascqk ;

create table if not exists app_sh_a01_dascqk (
  id varchar(32) not null,
  path varchar(128) ,
  app_sh_a01_id varchar(32) not null,
  primary key (id));



drop table if exists app_sh_a01_grzdsx ;

create table if not exists app_sh_a01_grzdsx (
  id varchar(32) not null,
  path varchar(128) ,
  app_sh_a01_id varchar(32) not null,
  primary key (id));



drop table if exists app_sh_a01_dascqk_tips ;

create table if not exists app_sh_a01_dascqk_tips (
  id varchar(32) not null,
  tip varchar(256) ,
  app_sh_a01_dascqk_id varchar(32) not null,
  primary key (id));



drop table if exists app_sh_a01_gbrmspb ;

create table if not exists app_sh_a01_gbrmspb (
  id varchar(32) not null,
  xm varchar(20) ,
  xb varchar(10) ,
  csny varchar(24) ,
  nl varchar(10) ,
  mz varchar(24) ,
  jg varchar(24) ,
  csd varchar(24) ,
  rdsj varchar(10) ,
  cjgzsj varchar(10) ,
  jkzk varchar(24) ,
  zyjszw varchar(60) ,
  zytc varchar(60) ,
  xl_qrz varchar(24) ,
  xw_qrz varchar(24) ,
  xl_zz varchar(24) ,
  xw_zz varchar(24) ,
  qrz_byyx varchar(128) ,
  zz_byyx varchar(128) ,
  xrzw varchar(128) ,
  nrzw varchar(128) ,
  nmzw varchar(128) ,
  rmly varchar(255) ,
  cbdwyj varchar(255) ,
  spjgyj varchar(255) ,
  xzjgrmyj varchar(255) ,
  path varchar(128) ,
  app_sh_a01_id varchar(32) not null,
  primary key (id));

drop table if exists app_sh_a01_gzjl ;

create table if not exists app_sh_a01_gzjl (
  id varchar(32) not null,
  c_sj varchar(24) ,
  z_sj varchar(24) ,
  jlsm varchar(256) ,
  app_sh_a01_id varchar(32) not null,
  gzjl_px int ,
  primary key (id));


drop table if exists app_sh_a01_jc ;

create table if not exists app_sh_a01_jc (
  id varchar(32) not null,
  nd varchar(24) ,
  jcsm varchar(256) ,
  app_sh_a01_id varchar(32) not null,
  jc_px int ,
  primary key (id));



drop table if exists app_sh_a01_ndkh ;

create table if not exists app_sh_a01_ndkh (
  id varchar(32) not null,
  nd varchar(24) ,
  khjg varchar(60) ,
  app_sh_a01_id varchar(32) not null,
  ndkh_px int ,
  primary key (id));



drop table if exists app_sh_a01_shgx ;

create table if not exists app_sh_a01_shgx (
  id varchar(32) not null,
  cw varchar(24) ,
  xm varchar(24) ,
  nl varchar(10) ,
  zzmm varchar(24) ,
  gzdwjzw varchar(128) ,
  app_sh_a01_id varchar(32) not null,
  shgx_px int ,
  primary key (id));


drop table if exists app_dwjg_tj ;

create table if not exists app_dwjg_tj (
  id varchar(32) not null,
  tj_mc varchar(128)  ,
  tj_json_data text  ,
  tj_px int ,
  tblx varchar(1) not null,
  primary key (id));

drop table if exists app_api_register;
create table app_api_register (
  id varchar(32) not null,
  ip varchar(32) not null,
  port varchar(10) not null,
  context varchar(32) default null,
  uri varchar(128) not null,
  api_code varchar(32) not null,
  request_method varchar(32) not null,
  primary key (id));

drop table if exists app_mc;
create table app_mc (
  id varchar(32) not null,
  mc varchar(255) not null,
  px int(11) not null default '99',
  is_ml varchar(255) not null default 0,
  primary key (id)
);

drop table if exists app_mc_b01;
create table app_mc_b01 (
  id varchar(32) not null,
  b0101 varchar(255) not null,
  px int(11) not null default '99',
  mc_id varchar(32) default null,
  primary key (id),
  constraint app_mc_b01_app_mc_id_fk foreign key (mc_id) references app_mc (id)
);

drop table if exists app_mc_a01;
create table app_mc_a01 (
  id varchar(32) not null,
  xm varchar(10) default null,
  mz varchar(10) default null,
  zw varchar(255) default null,
  csd varchar(40) default null,
  jg varchar(20) default null,
  csny varchar(20) default null,
  cjgzsj varchar(20) default null,
  rdsj varchar(20) default null,
  qrzxlxwjzy varchar(100) default null,
  zzxlxwjzy varchar(100) default null,
  zyjszw varchar(100) default null,
  xrzwsj varchar(20) default null,
  xrzjsj varchar(40) default null,
  b01_id varchar(32) not null,
  a01_px int(11) not null,
  zp_path varchar(128) default null,
  mc_id varchar(32) default null,
  primary key (id),
  constraint app_mc_a01_app_mc_b01_id_fk foreign key (b01_id) references app_mc_b01 (id)
);


drop table if exists app_mc_a01_gbrmspb ;

create table if not exists app_mc_a01_gbrmspb (
  id varchar(32) not null,
  xm varchar(20) ,
  xb varchar(10) ,
  csny varchar(24) ,
  nl varchar(10) ,
  mz varchar(24) ,
  jg varchar(24) ,
  csd varchar(24) ,
  rdsj varchar(10) ,
  cjgzsj varchar(10) ,
  jkzk varchar(24) ,
  zyjszw varchar(60) ,
  zytc varchar(60) ,
  xl_qrz varchar(24) ,
  xw_qrz varchar(24) ,
  xl_zz varchar(24) ,
  xw_zz varchar(24) ,
  qrz_byyx varchar(128) ,
  zz_byyx varchar(128) ,
  xrzw varchar(128) ,
  nrzw varchar(128) ,
  nmzw varchar(128) ,
  rmly varchar(255) ,
  cbdwyj varchar(255) ,
  spjgyj varchar(255) ,
  xzjgrmyj varchar(255) ,
  path varchar(128) ,
  app_mc_a01_id varchar(32) not null,
  primary key (id));


drop table if exists app_mc_a01_gzjl ;

create table if not exists app_mc_a01_gzjl (
  id varchar(32) not null,
  c_sj varchar(24) ,
  z_sj varchar(24) ,
  jlsm varchar(256) ,
  app_mc_a01_id varchar(32) not null,
  gzjl_px int ,
  primary key (id));

drop table if exists app_sh_pc_atts ;
create table app_sh_pc_atts
(
  id varchar(32) primary key not null,
  file_name varchar(128) not null,
  file_path varchar(255) not null,
  sh_pc_id varchar(32) not null,
  constraint app_sh_pc_atts_app_sh_pc_id_fk foreign key (sh_pc_id) references app_sh_pc (id)
);

drop table if exists app_bset_fl;
create table app_bset_fl (
  id varchar(32) not null,
  fl varchar(32) not null,
  parent_id varchar(32) default null,
  px int(11) default null,
  is_hidden int(11) default null,
  primary key (id)
);


drop table if exists app_bset_b01;
create table app_bset_b01 (
  id varchar(32) not null,
  b0101 varchar(255) not null,
  parent_id varchar(32) default null,
  px int(11) not null default '99',
  query_code varchar(32) default null,
  primary key (id)
) ;

drop table if exists app_bset_fl_2_b01;
create table app_bset_fl_2_b01 (
  id varchar(32) default null,
  fl_id varchar(32) default null,
  b01_id varchar(32) default null,
  px int(11) default null
) ;

drop table if exists app_aset_a01;
create table app_aset_a01 (
  id varchar(32) not null,
  xm varchar(10) default null,
  xb varchar(10) default null,
  mz varchar(10) default null,
  zw varchar(255) default null,
  csd varchar(40) default null,
  jg varchar(20) default null,
  csny varchar(20) default null,
  cjgzsj varchar(20) default null,
  rdsj varchar(20) default null,
  qrzxl varchar(200) default null,
  zyjszw varchar(200) default null,
  xrzwsj varchar(200) default null,
  xrzjsj varchar(200) default null,
  zp_path varchar(128) default null,
  zzxl varchar(100) default null,
  qrzxw varchar(100) default null,
  zzxw varchar(100) default null,
  nl varchar(10) default null,
  jkzk varchar(24) default null,
  zytc varchar(60) default null,
  qrz_byyx varchar(128) default null,
  zz_byyx varchar(128) default null,
  xrzw varchar(128) default null,
  nrzw varchar(128) default null,
  nmzw varchar(128) default null,
  file2img_path varchar(255) default null,
  qrz_zy varchar(100) default null,
  zz_zy varchar(100) default null,
  xrzj varchar(100) default null,
  dp varchar(100) default null,
  primary key (id)
) ;

drop table if exists app_aset_a02;
create table app_aset_a02 (
  id varchar(32) not null,
  a01_id varchar(32) not null,
  b01_id varchar(32) not null,
  zwmc varchar(120) not null,
  px int(11) not null,
  jtl_px int(11) not null,
  rzsj varchar(20) default null,
  primary key (id),
  constraint app_gbcx_a02_app_gbcx_a01_id_fk foreign key (a01_id) references app_aset_a01 (id),
  constraint app_gbcx_a02_app_gbcx_b01_id_fk foreign key (b01_id) references app_bset_b01 (id)
);

drop table if exists app_zscx_zs;
create table app_zscx_zs (
  id varchar(32) not null,
  b01_id varchar(32) not null,
  zwmc varchar(120) not null,
  xp int(11) not null,
  sp int(11) not null,
  cqb int(11) not null,
  primary key (id),
  constraint app_zscx_zs_app_bset_b01_id_fk foreign key (b01_id) references app_bset_b01 (id)
);


drop table if exists app_zscx_zs_a01;
create table app_zscx_zs_a01 (
  id varchar(32) not null,
  zs_id varchar(32) not null,
  a01_id varchar(32) default null,
  primary key (id),
  constraint app_zscx_a01_app_zscx_zs_id_fk foreign key (zs_id) references app_zscx_zs (id),
  constraint app_zscx_zs_a01_app_aset_a01_id_fk foreign key (a01_id) references app_aset_a01 (id)
);