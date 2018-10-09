
DROP TABLE  table_ricette_ingredienti;
DROP TABLE  table_ricette;
DROP TABLE table_ingredienti;
DROP TABLE table_dettagli_ingrediente;
DROP TABLE table_sito_fonte;


DELETE FROM  table_ricette_ingredienti;
DELETE FROM   table_ricette;
DELETE FROM  table_ingredienti;
DELETE FROM  table_dettagli_ingrediente;


  CREATE TABLE  table_ricette
   (	
    id NUMBER(14,0) NOT NULL, 
	titolo VARCHAR2(255 BYTE) NOT NULL, 
	image_url VARCHAR2(255 BYTE), 
	link_url VARCHAR2(255 BYTE), 
	descrizione VARCHAR2(2000 BYTE), 
    portata VARCHAR2(255 BYTE), 
	istruzioni VARCHAR2(4000 BYTE), 
	difficolta NUMBER(1,0), 
	tempo_cottura NUMBER(3,0), 
	tempo_preparazione NUMBER(3,0), 
    id_fonte NUMBER(14,0),
    sigla_fonte_fk VARCHAR2(5 BYTE),
   	CONSTRAINT table_ricette_pk PRIMARY KEY (id)
  ) ;


	CREATE TABLE table_ingredienti
   (	
    id NUMBER(14,0) NOT NULL, 
	nome VARCHAR2(255 BYTE) NOT NULL, 
	image_url VARCHAR2(255 BYTE), 
	descrizione VARCHAR2(255 BYTE), 
	dettaglio_ingrediente_fk NUMBER(14,0), 
    CONSTRAINT table_ingredienti_pk PRIMARY KEY (id) 
   );
   
     CREATE TABLE table_dettagli_ingrediente 
   (	
    id NUMBER(38,0) NOT NULL , 
	calorie NUMBER(5,0)  NOT NULL , 
	grassi NUMBER(5,0), 
	carboidrati NUMBER(5,0), 
	proteiene NUMBER(5,0), 
	colesterolo  NUMBER(5,0), 
	sodio  NUMBER(5,0), 
	potassio  NUMBER(5,0), 
	fibra  NUMBER(5,0), 
	zucchero  NUMBER(5,0), 
	 CONSTRAINT table_dettagli_ingrediente_pk PRIMARY KEY (id)
   ) ;
   
   
	CREATE TABLE table_sito_fonte
   (	
   sigla VARCHAR2(20 BYTE) NOT NULL, 
   site_url VARCHAR2(50 BYTE) NOT NULL, 
   site_icon VARCHAR2(50 BYTE),
    CONSTRAINT table_sito_fonte_pk PRIMARY KEY (sigla)
   );

	CREATE TABLE table_ricette_ingredienti
   (	
    id_ricetta NUMBER(14,0) NOT NULL, 
	id_ingrediente NUMBER (14,0)NOT NULL, 
	quantita_estesa VARCHAR2(255 BYTE), 
	unita_di_misura VARCHAR2(225 BYTE), 
	quantita NUMBER(14,0),
    CONSTRAINT table_ric_ing_pk PRIMARY KEY (id_ricetta,id_ingrediente)
   );
   
   
   ALTER TABLE table_ricette
   ADD CONSTRAINT FK_Sigla
   FOREIGN KEY (sigla_fonte_fk) REFERENCES table_sito_fonte(sigla);
   
   ALTER TABLE table_ingredienti
   ADD CONSTRAINT FK_Dettaglio
   FOREIGN KEY (dettaglio_ingrediente_fk) REFERENCES table_dettagli_ingrediente(id);
   
   ALTER TABLE table_ricette_ingredienti
   ADD CONSTRAINT FK_Ricetta
   FOREIGN KEY (id_ricetta) REFERENCES table_ricette(id)
   on DELETE CASCADE;
   
   ALTER TABLE table_ricette_ingredienti
   ADD CONSTRAINT FK_Ingrediente
   FOREIGN KEY (id_ingrediente) REFERENCES table_ingredienti(id);
   
   
   
CREATE  SEQUENCE  "DETTAGLIO_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  "INGREDIENTI_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1521 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE   "RICETTE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 741 CACHE 20 NOORDER  NOCYCLE ;

   
   --------------------------------------------------------
--  DDL for Trigger TRIGGER_PK_INGREDIENTI
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER_PK_INGREDIENTI" 
   before insert on table_ingredienti 
   for each row 
begin  
   if inserting then 
      if :NEW.id is null then 
         select INGREDIENTI_SEQ.nextval into :NEW.id from dual; 
      end if; 
   end if; 
end;
/
ALTER TRIGGER "TRIGGER_PK_INGREDIENTI" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TRIGGER1
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIGGER1" 
BEFORE INSERT ON table_dettagli_ingrediente 
for each row 
begin  
   if inserting then 
      if :NEW.id is null then 
         select DETTAGLIO_SEQ.nextval into :NEW.id from dual; 
      end if; 
   end if; 
end;
/
ALTER TRIGGER "TRIGGER1" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TRIG_RICETTE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TRIG_RICETTE" 
   before insert on table_ricette 
   for each row 
begin  
   if inserting then 
      if :NEW.id is null then 
         select RICETTE_SEQ.nextval into :NEW.id from dual; 
      end if; 
   end if; 
end;
/
ALTER TRIGGER "TRIG_RICETTE" ENABLE;
   
   
   
  INSERT INTO TABLE_SITO_FONTE (SIGLA,SITE_URL,SITE_ICON) VALUES ('GZF','www.giallozzafferano.it','icon.jpeg');
  INSERT INTO TABLE_RICETTE (TITOLO,SIGLA_FONTE_FK) VALUES ('prova','GZF');
  SELECT * FROM TABLE_INGREDIENTI Where NOME='Mascarpone';
   
   
