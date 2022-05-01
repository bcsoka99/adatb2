create table UTASOK
(
    ID              NUMBER(10) generated as identity
        primary key,
    NEV             VARCHAR2(255 char),
    SZULETESI_DATUM TIMESTAMP(6),
    GYEREK          NUMBER(1)
)
/

create table LEGITARSASAG
(
    NEV VARCHAR2(255 char) not null
        primary key
)
/

create table BIZTOSITOK
(
    ID  NUMBER(10) generated as identity
        primary key,
    NEV VARCHAR2(255 char)
)
/

create table SZALLODAK
(
    ID        NUMBER(10) generated as identity
        primary key,
    AR        NUMBER(10),
    KATEGORIA VARCHAR2(255 char),
    NEV       VARCHAR2(255 char),
    TELEPULES VARCHAR2(255 char)
)
/

create table REPULOJARATOK
(
    ID              NUMBER(10) generated as identity
        primary key,
    ERKEZES         TIMESTAMP(6),
    HONNAN          VARCHAR2(255 char),
    HOVA            VARCHAR2(255 char),
    INDULAS         TIMESTAMP(6),
    LEGITARSASAG_ID VARCHAR2(255 char)
        constraint FKSE6O9XFOQC3S4YRBHGSNB1MWU
            references LEGITARSASAG,
    ETKEZES         NUMBER(1)
)
/

create table REPULOK
(
    ID               NUMBER(10) generated as identity
        primary key,
    FEROHELY         NUMBER(10),
    TIPUS            VARCHAR2(255 char),
    REPULOJARATOK_ID NUMBER(10)
        constraint FKMNFWSWJ23LA92PGC22OLPBPY5
            references REPULOJARATOK
)
/

create table BIZTOSITASOK
(
    ID            NUMBER(10) generated as identity
        primary key,
    DIJ           NUMBER(10),
    NEV           VARCHAR2(255 char),
    BIZTOSITOK_ID NUMBER(10)
        constraint FK37BVO775S8P6DLPOH3KADWF30
            references BIZTOSITOK,
    UTASOK_ID     NUMBER(10)
        constraint FKBHWNM4JXGV5761MWDEIQHHYPT
            references UTASOK
)
/

create table JEGY
(
    ID               NUMBER(10) generated as identity
        primary key,
    AR               NUMBER(10),
    DATUM            TIMESTAMP(6),
    REPULOJARATOK_ID NUMBER(10)
        constraint FKHNNN9Q9TV0BWQXOSMMHPWULBX
            references REPULOJARATOK,
    UTASOK_ID        NUMBER(10)
        constraint FK2NK217SMQOA58Q3B5RB88WJR1
            references UTASOK,
    FOGLALT          NUMBER(1),
    SOR              NUMBER(10),
    SZEK             NUMBER(10)
)
/

create table SZOBAFOGLALAS
(
    ID           NUMBER(10) generated as identity
        primary key,
    EJSZAKAK     NUMBER(10),
    SZALLODAK_ID NUMBER(10)
        constraint FKH6L8BPVSWRYXR183WPD1EDR3J
            references SZALLODAK,
    UTASOK_ID    NUMBER(10)
        constraint FKREXGLYKY6JLSJAHN3GAATNCW0
            references UTASOK
)
/

