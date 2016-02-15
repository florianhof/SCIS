alter table COMMUNE_HIST drop constraint FK1FC2C1C114F3303F;
alter table GROUND_OBJECT drop constraint FK9CE0D0F71C858424;
alter table GROUND_OBJECT drop constraint FK9CE0D0F7C48099D9;
alter table GROUND_OBJECT drop constraint FK9CE0D0F781156767;
alter table GROUND_OBJECT drop constraint FK9CE0D0F7A242484A;
alter table GROUND_OBJECT_HIST drop constraint FK9FAC16A1406DC56;
alter table KARSTOLOGIST_HIST drop constraint FK9DE65AE914F3303F;
alter table KARST_OBJECT drop constraint FKA2B95CA16F2BBA46;
alter table KARST_OBJECT_DOCUMENT drop constraint FKCBA63AF9C3BC0924;
alter table KARST_OBJECT_DOCUMENT drop constraint FKCBA63AF9E2C96CF3;
alter table KARST_OBJECT_DOCUMENT_HIST drop constraint FK337148A814F3303F;
alter table KARST_OBJECT_HIST drop constraint FK3ED8F00014F3303F;
alter table PRIVACY_HIST drop constraint FK665D419914F3303F;
alter table REVISION_CHANGE drop constraint FK6966E09433925F9B;
alter table SPELEO_OBJECT drop constraint FK9462E5301C858424;
alter table SPELEO_OBJECT_HIST drop constraint FKF5E968D11406DC56;
drop table COMMUNE cascade;
drop table COMMUNE_HIST cascade;
drop table GROUND_OBJECT cascade;
drop table GROUND_OBJECT_HIST cascade;
drop table IMAGES cascade;
drop table KARSTOLOGIST cascade;
drop table KARSTOLOGIST_HIST cascade;
drop table KARST_OBJECT cascade;
drop table KARST_OBJECT_DOCUMENT cascade;
drop table KARST_OBJECT_DOCUMENT_HIST cascade;
drop table KARST_OBJECT_HIST cascade;
drop table PRIVACY cascade;
drop table PRIVACY_HIST cascade;
drop table REVISION cascade;
drop table REVISION_CHANGE cascade;
drop table SPELEO_OBJECT cascade;
drop table SPELEO_OBJECT_HIST cascade;
drop sequence hibernate_sequence;
create table COMMUNE (ID int8 not null, DELETED bool not null, BARON_NR varchar(6) not null, CANTON varchar(2) not null, DISTRICT varchar(50) not null, FSO_NR int4 not null, NAME varchar(50) not null, primary key (ID), unique (FSO_NR));
create table COMMUNE_HIST (ID int8 not null, REV int4 not null, REVTYPE int2, DELETED bool, BARON_NR varchar(6), CANTON varchar(2), DISTRICT varchar(50), FSO_NR int4, NAME varchar(50), primary key (ID, REV));
create table GROUND_OBJECT (CANTON_BARON varchar(2), CAVE_BARON_NR int4, COMMUNE_BARON_NR int4, COORD_ALTITUDE int4 check ((COORD_ALTITUDE>=100 and COORD_ALTITUDE<=4999)), COORD_EAST int4 check ((COORD_EAST>=480000 and COORD_EAST<=850000) or (COORD_EAST>=2480000 and COORD_EAST<=2850000)), COORD_NORTH int4 check ((COORD_NORTH>=70000 and COORD_NORTH<=310000) or (COORD_NORTH>=1070000 and COORD_NORTH<=1310000)), INVENTORY_NR int4 unique, LOCATION_ACCURACY varchar(2), TYPE varchar(1), ID int8 not null, COMMUNE_ID int8, PRIVACY_ID int8 unique, SPELEO_OBJECT_ID int8, primary key (ID), unique (CANTON_BARON, COMMUNE_BARON_NR, CAVE_BARON_NR), unique (PRIVACY_ID), unique (INVENTORY_NR));
create table GROUND_OBJECT_HIST (ID int8 not null, REV int4 not null, CANTON_BARON varchar(2), CAVE_BARON_NR int4, COMMUNE_BARON_NR int4, COORD_ALTITUDE int4, COORD_EAST int4, COORD_NORTH int4, INVENTORY_NR int4, LOCATION_ACCURACY varchar(2), TYPE varchar(1), COMMUNE_ID int8, PRIVACY_ID int8, SPELEO_OBJECT_ID int8, primary key (ID, REV));
create table IMAGES (ID varchar(255) not null, GALLERY varchar(255), IMAGE bytea, primary key (ID));
create table KARSTOLOGIST (ID int8 not null, DELETED bool not null, CLUB varchar(50), COMMENT varchar(255), FIRSTNAME varchar(50), INITIALS varchar(10) not null, LASTNAME varchar(50), primary key (ID));
create table KARSTOLOGIST_HIST (ID int8 not null, REV int4 not null, REVTYPE int2, DELETED bool, CLUB varchar(50), COMMENT varchar(255), FIRSTNAME varchar(50), INITIALS varchar(10), LASTNAME varchar(50), primary key (ID, REV));
create table KARST_OBJECT (ID int8 not null, DELETED bool not null, COMMENT varchar(50000), CREATED_DATE date, DATA_HISTORY varchar(500), LAST_MODIF_DATE timestamp, LITERATURE varchar(500), NAME varchar(100) not null, VERIFIED bool not null, MANAGER_ID int8, primary key (ID));
create table KARST_OBJECT_DOCUMENT (ID int8 not null, REMARKS varchar(255), ROLLED_MAP bool, SUSPENSION_FOLDER bool, TRANSMISSION_DATE date, CONTACT_ID int8, OBJECT_ID int8, primary key (ID));
create table KARST_OBJECT_DOCUMENT_HIST (ID int8 not null, REV int4 not null, REVTYPE int2, REMARKS varchar(255), ROLLED_MAP bool, SUSPENSION_FOLDER bool, TRANSMISSION_DATE date, CONTACT_ID int8, OBJECT_ID int8, primary key (ID, REV));
create table KARST_OBJECT_HIST (ID int8 not null, REV int4 not null, REVTYPE int2, DELETED bool, COMMENT varchar(50000), CREATED_DATE date, DATA_HISTORY varchar(500), LITERATURE varchar(500), NAME varchar(100), VERIFIED bool, MANAGER_ID int8, primary key (ID, REV));
create table PRIVACY (ID int8 not null, END_DATE date, PROTECTOR varchar(50), REASON varchar(255) not null, START_DATE date not null, primary key (ID));
create table PRIVACY_HIST (ID int8 not null, REV int4 not null, REVTYPE int2, END_DATE date, PROTECTOR varchar(50), REASON varchar(255), START_DATE date, primary key (ID, REV));
create table REVISION (REV_ID int4 not null, MODIF_DATE timestamp not null, USERNAME varchar(30), primary key (REV_ID));
create table REVISION_CHANGE (ID int8 not null, ACTION int4 not null, ENTITY_CLASS_NAME varchar(255) not null, ENTITY_ID int8, ENTITY_NAME varchar(255) not null, REVISION_ID int4 not null, primary key (ID));
create table SPELEO_OBJECT (DEPTH int4 check (DEPTH>=0), DEPTH_AND_ELEVATION int4 check (DEPTH_AND_ELEVATION>=0), DOCUMENTATION_STATE varchar(2), ELEVATION int4 check (ELEVATION>=0), LENGTH int4 check (LENGTH>=0), SYSTEM_NR int4, TYPE varchar(1), ID int8 not null, primary key (ID), unique (SYSTEM_NR));
create table SPELEO_OBJECT_HIST (ID int8 not null, REV int4 not null, DEPTH int4, DEPTH_AND_ELEVATION int4, DOCUMENTATION_STATE varchar(2), ELEVATION int4, LENGTH int4, SYSTEM_NR int4, TYPE varchar(1), primary key (ID, REV));
alter table COMMUNE_HIST add constraint FK1FC2C1C114F3303F foreign key (REV) references REVISION;
alter table GROUND_OBJECT add constraint FK9CE0D0F71C858424 foreign key (ID) references KARST_OBJECT;
alter table GROUND_OBJECT add constraint FK9CE0D0F7C48099D9 foreign key (COMMUNE_ID) references COMMUNE;
alter table GROUND_OBJECT add constraint FK9CE0D0F781156767 foreign key (PRIVACY_ID) references PRIVACY;
alter table GROUND_OBJECT add constraint FK9CE0D0F7A242484A foreign key (SPELEO_OBJECT_ID) references SPELEO_OBJECT;
alter table GROUND_OBJECT_HIST add constraint FK9FAC16A1406DC56 foreign key (ID, REV) references KARST_OBJECT_HIST;
alter table KARSTOLOGIST_HIST add constraint FK9DE65AE914F3303F foreign key (REV) references REVISION;
alter table KARST_OBJECT add constraint FKA2B95CA16F2BBA46 foreign key (MANAGER_ID) references KARSTOLOGIST;
alter table KARST_OBJECT_DOCUMENT add constraint FKCBA63AF9C3BC0924 foreign key (OBJECT_ID) references KARST_OBJECT;
alter table KARST_OBJECT_DOCUMENT add constraint FKCBA63AF9E2C96CF3 foreign key (CONTACT_ID) references KARSTOLOGIST;
alter table KARST_OBJECT_DOCUMENT_HIST add constraint FK337148A814F3303F foreign key (REV) references REVISION;
alter table KARST_OBJECT_HIST add constraint FK3ED8F00014F3303F foreign key (REV) references REVISION;
alter table PRIVACY_HIST add constraint FK665D419914F3303F foreign key (REV) references REVISION;
alter table REVISION_CHANGE add constraint FK6966E09433925F9B foreign key (REVISION_ID) references REVISION;
alter table SPELEO_OBJECT add constraint FK9462E5301C858424 foreign key (ID) references KARST_OBJECT;
alter table SPELEO_OBJECT_HIST add constraint FKF5E968D11406DC56 foreign key (ID, REV) references KARST_OBJECT_HIST;
create sequence hibernate_sequence;
