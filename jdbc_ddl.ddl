-- Potrebne tabele i ograničenja

CREATE TABLE artikal (
    id_ar     INTEGER NOT NULL,
    naziv_art VARCHAR2(30) NOT NULL,
    opis      VARCHAR2(50),
    kateg     VARCHAR2(30) NOT NULL,
    CONSTRAINT artikal_pk PRIMARY KEY (id_ar),
    CONSTRAINT artikal_kateg_ck CHECK (kateg IN ('Garnitura', 'Pojedinacna'))
);

CREATE TABLE zica (
    id_z        INTEGER NOT NULL,
    datum_proiz DATE NOT NULL,
    duz_z       INTEGER NOT NULL,
    naziv_z     VARCHAR2(30) NOT NULL,
    CONSTRAINT zica_pk PRIMARY KEY (id_z)
);

CREATE TABLE je_izradjen (
    zica_id_z        INTEGER NOT NULL,
    materijal_id_mat INTEGER NOT NULL,
    CONSTRAINT je_izradjen_pk PRIMARY KEY (zica_id_z, materijal_id_mat),
    CONSTRAINT je_izradjen_zica_fk FOREIGN KEY (zica_id_z)
        REFERENCES zica (id_z)
    -- Napomena: materijal_id_mat ostaje kao ID bez veze ka tabeli materijal
);

CREATE TABLE nalog_za_izradu (
    id_nal     INTEGER NOT NULL,
    datum_kre  DATE NOT NULL,
    datum_zav  DATE,
    status_nal VARCHAR2(30) NOT NULL,
    CONSTRAINT nalog_za_izradu_pk PRIMARY KEY (id_nal),
    CONSTRAINT nalog_za_izradu_status_ck CHECK (status_nal IN ('Na_cekanju', 'Obustavljen', 'U_toku_izrade', 'Zavrsen'))
);

CREATE TABLE narudzbina (
    id_nar            INTEGER NOT NULL,
    datum_nar         DATE NOT NULL,
    status            VARCHAR2(30) NOT NULL,
    kupac_id_kup      INTEGER NOT NULL,
    narudzbina_id_nar INTEGER,
    CONSTRAINT narudzbina_pk PRIMARY KEY (id_nar),
    CONSTRAINT narudzbina_status_ck CHECK (status IN ('Na cekanju', 'Poslata', 'U proizvodnji'))
);

CREATE TABLE sadrzi (
    narudzbina_id_nar      INTEGER NOT NULL,
    artikal_id_ar          INTEGER NOT NULL,
    nalog_za_izradu_id_nal INTEGER,
    CONSTRAINT sadrzi_pk PRIMARY KEY (narudzbina_id_nar, artikal_id_ar),
    CONSTRAINT sadrzi_narudzbina_fk FOREIGN KEY (narudzbina_id_nar)
        REFERENCES narudzbina (id_nar),
    CONSTRAINT sadrzi_artikal_fk FOREIGN KEY (artikal_id_ar)
        REFERENCES artikal (id_ar),
    CONSTRAINT sadrzi_nalog_fk FOREIGN KEY (nalog_za_izradu_id_nal)
        REFERENCES nalog_za_izradu (id_nal)
);


