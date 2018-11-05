
DROP TABLE  table_ricette_ingredienti;
DROP TABLE  table_ricette;
DROP TABLE table_ingredienti;
DROP TABLE table_dettagli_ingrediente;
DROP TABLE table_sito_fonte;



CREATE TABLE  table_ricette
   (	
	id numeric(14,0) NOT NULL DEFAULT nextval('ricette_seq'), 
	titolo character varying(255) NOT NULL, 
	image_url character varying(255), 
	link_url character varying(255), 
	descrizione character varying(2000), 
	portata character varying(255), 
	istruzioni character varying(4000), 
	difficolta numeric(1,0), 
	tempo_cottura numeric(3,0), 
	tempo_preparazione numeric(3,0), 
	id_fonte numeric(14,0),
	sigla_fonte_fk character varying(5),
   	CONSTRAINT table_ricette_pk PRIMARY KEY (id)
  ) ;
ALTER SEQUENCE ricette_seq OWNED BY table_ricette.id;

CREATE TABLE table_ingredienti
   (	
	id numeric(14,0) NOT NULL DEFAULT nextval('ingredienti_seq'), 
	nome character varying(255) NOT NULL, 
	image_url character varying(255), 
	descrizione character varying(255), 
	dettaglio_ingrediente_fk numeric(14,0), 
	CONSTRAINT table_ingredienti_pk PRIMARY KEY (id) 
   );
   
ALTER SEQUENCE ingredienti_seq OWNED BY table_ingredienti.id;

     CREATE TABLE table_dettagli_ingrediente 
   (	
	id numeric(38,0) NOT NULL DEFAULT nextval('dettaglio_seq'),  
	calorie numeric(5,0)  NOT NULL , 
	grassi numeric(5,0), 
	carboidrati numeric(5,0), 
	proteiene numeric(5,0), 
	colesterolo  numeric(5,0), 
	sodio  numeric(5,0), 
	potassio  numeric(5,0), 
	fibra  numeric(5,0), 
	zucchero  numeric(5,0), 
	CONSTRAINT table_dettagli_ingrediente_pk PRIMARY KEY (id)
   ) ;
ALTER SEQUENCE dettaglio_seq OWNED BY table_dettagli_ingrediente.id;


   CREATE TABLE table_sito_fonte
   (	
	sigla character varying(20) NOT NULL, 
	site_url character varying(50) NOT NULL, 
	site_icon character varying(50),
	CONSTRAINT table_sito_fonte_pk PRIMARY KEY (sigla)
   );


   CREATE TABLE table_ricette_ingredienti
   (	
	id_ricetta numeric(14,0) NOT NULL, 
	id_ingrediente numeric (14,0)NOT NULL, 
	quantita_estesa character varying(255), 
	unita_di_misura character varying(225), 
	quantita numeric(14,0),
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



CREATE SEQUENCE "dettaglio_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE "dettaglio_seq"
  OWNER TO postgres;
GRANT ALL ON SEQUENCE "dettaglio_seq" TO postgres;




CREATE SEQUENCE "ingredienti_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE "ingredienti_seq"
  OWNER TO postgres;
GRANT ALL ON SEQUENCE "ingredienti_seq" TO postgres;



CREATE SEQUENCE "ricette_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE "ricette_seq"
  OWNER TO postgres;
GRANT ALL ON SEQUENCE "ricette_seq" TO postgres;



  