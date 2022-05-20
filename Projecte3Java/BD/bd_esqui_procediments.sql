DROP DATABASE IF EXISTS bd_esqui;
CREATE DATABASE bd_esqui;
USE bd_esqui;

DROP TABLE IF EXISTS client;
CREATE TABLE client(
	dni varchar(9) NOT NULL,
    nom varchar(30) NOT NULL,
    cognom varchar(30) NOT NULL,
    usuari varchar(20) NOT NULL,
    contrasenya varchar(250) NOT NULL,
    sexe char(1) NOT NULL,
    email varchar(50) NOT NULL,
    PRIMARY KEY (dni)
);

DROP TABLE IF EXISTS familia_nombrosa;
CREATE TABLE familia_nombrosa(
	dni varchar(9),
    num_familia_nombrosa varchar(20) NOT NULL,
    data_caducitat date NOT NULL,
    PRIMARY KEY (dni),
    FOREIGN KEY (dni) REFERENCES client(dni)
);

DROP TABLE IF EXISTS federat;
CREATE TABLE federat(
	dni varchar(9),
    nivell int,
    num_federacio varchar(20) NOT NULL,
    data_caducitat date NOT NULL,
    PRIMARY KEY (dni),
    FOREIGN KEY (dni) REFERENCES client(dni)
);

DROP TABLE IF EXISTS monitor;
CREATE TABLE monitor(
	dni varchar(9),
    nom varchar(30),
    cognom varchar(30),
    sexe char(1),
    PRIMARY KEY (dni)
);

DROP TABLE IF EXISTS curs;
CREATE TABLE curs(
	id int NOT NULL AUTO_INCREMENT,
    nom varchar(50) NOT NULL,
    descripcio varchar(50),
    data date,
    monitor varchar(9),
    PRIMARY KEY (id),
    FOREIGN KEY (monitor) REFERENCES monitor(dni)
);

DROP TABLE IF EXISTS individual;
CREATE TABLE individual(
	id int AUTO_INCREMENT,
    preu float,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES curs(id)
);

DROP TABLE IF EXISTS colectiu;
CREATE TABLE colectiu(
	id int,
    max_clients int,
    preu float,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES curs(id)
);

DROP TABLE IF EXISTS competicio;
CREATE TABLE competicio(
	id int,
    nivell int,
    data_fi date,
    preu float,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES curs(id)
);

DROP TABLE IF EXISTS lloga_curs_individual;
CREATE TABLE lloga_curs_individual(
	id int NOT NULL AUTO_INCREMENT,
    id_curs int,
    dni varchar(9),
    data date,					# Data del curs.
    hora_inici time,
    hores int,				# Nº d'hores que es reserven.
    preu_final float,		# Preu tenint en compte les hores llogades i els descomptes aplicats.
    PRIMARY KEY (id),
    FOREIGN KEY (id_curs) REFERENCES curs(id),
    FOREIGN KEY (dni) REFERENCES client(dni)
);

DROP TABLE IF EXISTS lloga_curs_colectiu;
CREATE TABLE lloga_curs_colectiu (
	id int NOT NULL AUTO_INCREMENT,
    id_curs int,
    dni varchar(9),
    data date,
    preu_final float,		# Preu tenint en compte el descompte de familia nombrosa.
    PRIMARY KEY (id),
    FOREIGN KEY (id_curs) REFERENCES curs(id),
    FOREIGN KEY (dni) REFERENCES client(dni)
);

DROP TABLE IF EXISTS lloga_curs_comp;
CREATE TABLE lloga_curs_comp(
	id int NOT NULL AUTO_INCREMENT,
    id_curs int,
    dni varchar(9),
    data date,
    PRIMARY KEY (id),
    FOREIGN KEY (id_curs) REFERENCES curs(id),
    FOREIGN KEY (dni) REFERENCES client(dni)
);

DROP TABLE IF EXISTS producte;
CREATE TABLE producte(
	id int NOT NULL AUTO_INCREMENT,
    nom varchar(20),
    marca varchar(15),
    preu float(10),
    num_usos int,
    estat boolean,
    imatge varchar(20),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS esquis;
CREATE TABLE esquis(
	id int,
    talla int,
    PRIMARY KEY(id),
    FOREIGN KEY (id) REFERENCES producte(id)
);

DROP TABLE IF EXISTS botes;
CREATE TABLE botes(
	id int,
    talla int,
    PRIMARY KEY(id),
    FOREIGN KEY (id) REFERENCES producte(id)
);

DROP TABLE IF EXISTS pals;
CREATE TABLE pals(
	id int,
    mida int,
    PRIMARY KEY(id),
    FOREIGN KEY (id) REFERENCES producte(id)
);

DROP TABLE IF EXISTS lloga_prod;
CREATE TABLE lloga_prod(
	id int NOT NULL AUTO_INCREMENT,
    id_prod int ,
    dni varchar(9),
    data date,
    preu float(10),
    PRIMARY KEY(id),
    FOREIGN KEY (id_prod) REFERENCES producte(id),
    FOREIGN KEY (dni) REFERENCES client(dni)
);

DROP TABLE IF EXISTS kit;
CREATE TABLE kit(
	id int NOT NULL AUTO_INCREMENT,
    cops_llogat int,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS format_per;
CREATE TABLE format_per(
	id_kit int,
    id_producte int,
    PRIMARY KEY(id_kit, id_producte),
    FOREIGN KEY(id_kit) REFERENCES kit(id),
    FOREIGN KEY(id_producte) REFERENCES producte(id)
);

DROP TABLE IF EXISTS lloga_kit;
CREATE TABLE lloga_kit(
	id int NOT NULL,
    id_kit int,
    dni varchar(9),
    data date,
    preu float(10),
    PRIMARY KEY(id),
    FOREIGN KEY (id_kit) REFERENCES kit(id),
    FOREIGN KEY (dni) REFERENCES client(dni)
);


################################# INSERTS
INSERT INTO `client` VALUES('1234567L', 'Roger', 'Marin', 'rmarin', md5('1234'), 'H', 'rmarin@gmail.com');

INSERT INTO monitor VALUES('2121212P','Lluc','Perez','H'),
	('3232323T', 'Laia', 'Puig', 'D');

INSERT INTO curs VALUES(default, 'Introducció a l\'esqui', 'Curs d\'introducció a l\'esquí per a principiants', '2022-04-22', '2121212P'),
	(default, 'Curs d\'esquí de nivell intermig', 'Curs d\'esquí de nivell intermig', '2022-04-26', '3232323T'),
	(default, 'Curs per a esquiadors experts', 'Curs per als esquiadors més experimentats', '2022-04-26', '3232323T'),
	(default, 'Fonaments bàsics de l\'esqui', 'Curs per a aprendre els fonaments bàsics de l\esquí', '2022-04-29', '2121212P');

INSERT INTO colectiu VALUES(1, 20, 20),
	(2, 15, 25);
    
INSERT INTO competicio VALUES(3, 2, '2022-04-28', 30);

INSERT INTO familia_nombrosa VALUES('1234567L', '11111111', '2024-08-10');

INSERT INTO federat VALUES('1234567L', 2, 22222222, '2024-08-10');

INSERT INTO producte VALUES(default, 'Botes LX-100', 'Fischer', 79.99, 0, true, 'IMG/1.jpg'),
	(default, 'Botes Pro Max', 'Fischer', 99.99, 0, true, 'IMG/2.jpg'),
    (default, 'Botes Alltrack Pro', 'Rossignol', 100, 0, true, 'IMG/3.jpg'),
    (default, 'Esquis Salomon 500', 'Salomon', 149.99, 0, true, 'IMG/4.jpg'),
    (default, 'Esquis Rookie 90', 'Wedze', 74.99, 0, true, 'IMG/5.jpg'),
    (default, 'Esquis FR 100', ' Wedze', 99.99, 0, true, 'IMG/6.jpg'),
    (default, 'Pals FR950', 'Wedze', 19.99, 0, true, 'IMG/7.jpg'),
    (default, 'Pals Freeride', 'Inovik', 24.99, 0, true, 'IMG/8.jpg'),
    (default, 'Pals XC Pole', 'Inovik', 20, 0, true, 'IMG/9.jpg');

INSERT INTO botes VALUES(1, 25),
	(2, 28),
    (3, 27);

INSERT INTO esquis VALUES(4, 160),
	(5, 155),
    (6, 163);

INSERT INTO pals VALUES(7, 120),
	(8, 130),
    (9, 125);







################################# CONSULTES
#### CONSULTA PER LLISTAR PRODUCTES AMB LA CATEGORIA DEL PRODUCTE.
SELECT * FROM(
SELECT a.*, "Botes" AS tipus
FROM producte a, botes b
WHERE a.id = b.id
UNION
SELECT a2.*, "Esquis" AS tipus
FROM producte a2, esquis b2
WHERE a2.id = b2.id
UNION
SELECT a3.*, "Pals" AS tipus
FROM producte a3, pals b3
WHERE a3.id = b3.id) producte;

#### COMPROVAR SI EXISTEIX KIT.
DELIMITER //
CREATE PROCEDURE comprovarExisteixKit(IN _esquis int, IN _botes int, IN _pals int)
BEGIN
	DECLARE _id_kit int;
    
    IF EXISTS(select count(*), id_kit
		from format_per
		where id_producte= _esquis OR
		id_producte= _botes OR
		id_producte= _pals
		group by id_kit
		HAVING count(*) = 3) AND
        (SELECT count(*) 
		FROM producte 
		WHERE id IN (_esquis, _botes, _pals) AND
		num_usos < 10) = 3 THEN
		CALL sumarUsos(_esquis, _botes, _pals);
    ELSE
        CALL crearKit(_esquis, _botes, _pals);
	END IF;
		
	
END //

call comprovarExisteixKit(1, 2, 3);

#### SUMAR 1 ÚS ALS PRODUCTES LLOGATS.
DELIMITER //
CREATE PROCEDURE sumarUsos(IN _esquis int, _botes int, _pals int)
BEGIN
	UPDATE producte SET num_usos = num_usos + 1 WHERE id IN (_esquis, _botes, _pals);
END //

call sumarUsos(1, 2, 3);


#### CREAR KIT SI NO EXISTEIX.
DELIMITER //
CREATE PROCEDURE crearKit(IN _esquis int, IN _botes int, IN _pals int)
BEGIN
	DECLARE _id_kit int;
	INSERT INTO kit VALUES(default, 1);
    SELECT id INTO _id_kit
    FROM kit
    order by id desc 
    limit 1;
    INSERT INTO format_per VALUES(_id_kit, _esquis),
		(_id_kit, _botes),
        (_id_kit, _pals);
END //

#### Llogar curs individual
DELIMITER //
CREATE PROCEDURE llogarCursIndividual(IN _dni varchar(9), _data date, _preu float, _horaInici time, _numHores int, _preuFinal float)
BEGIN
	DECLARE _idcurs int;
	INSERT INTO individual VALUES(default, _preu);
    SET _idcurs = (SELECT MAX(id) FROM individual);
    
    INSERT INTO lloga_curs_individual VALUES(default, _idcurs, _dni, _data, _horaInici, _numHores, _preuFinal);
    
END //
   
call llogarCursIndividual('1234567L', '2022-05-20', 20, '12:00:00', 1, 16);
call llogarCursIndividual('1234567L', '2022-5-4', 20, '18:00:00', 2, 12);

#### Llogar curs
DELIMITER //
CREATE PROCEDURE llogarCursos(IN _dni varchar(9), _idcurs int)
BEGIN
	IF EXISTS(SELECT * FROM colectiu WHERE id = _idcurs) THEN
    #Si el curs és col·lectiu es farà el procediment dels cursos col·lectius.
		CALL llogarCursColectiu(_dni, _idcurs);
    ELSEIF EXISTS (SELECT * FROM competicio WHERE id = _idcurs) THEN
		CALL llogarCursCompeticio(_dni, _idcurs);
    END IF;
END //

#### Llogar curs col·lectiu
DELIMITER //
CREATE PROCEDURE llogarCursColectiu(IN _dni varchar(9), _idcurs int)
BEGIN
DECLARE descompteFN float;
DECLARE _preu float;
DECLARE data_curs date;
SET _preu = (SELECT preu FROM colectiu WHERE id = _idcurs);
SET data_curs = (SELECT data FROM curs WHERE id = _idcurs);
	IF NOT EXISTS (SELECT * FROM lloga_curs_colectiu WHERE dni = _dni AND id_curs = _idcurs) THEN
		IF EXISTS(SELECT * FROM familia_nombrosa WHERE dni = _dni AND data_caducitat > curdate()) THEN
			SET descompteFN = 0.6;
			INSERT INTO lloga_curs_colectiu VALUES(default, _idcurs, _dni, data_curs, (_preu*descompteFN));
			SELECT "";
		ELSE
			INSERT INTO lloga_curs_colectiu VALUES(default, _idcurs, _dni, data_curs, _preu);
			SELECT "";
		END IF;
	END IF;
END //

DELIMITER //
CREATE PROCEDURE llogarCursCompeticio(IN _dni varchar(9), _idcurs int)
BEGIN
DECLARE _nivellCurs int;
DECLARE _dataCurs date;
SET _dataCurs = (SELECT data FROM curs WHERE id = _idcurs);
SET _nivellCurs = (SELECT nivell FROM competicio WHERE id = _idcurs);
	IF NOT EXISTS (SELECT * FROM lloga_curs_comp WHERE dni = _dni AND id_curs = _idcurs) THEN
		IF EXISTS (SELECT * FROM federat WHERE dni = _dni AND nivell >= _nivellCurs AND data_caducitat > curdate()) THEN
			INSERT INTO lloga_curs_comp VALUES(default, _idcurs, _dni, _dataCurs);
			SELECT "";
		END IF;
    END IF;
END //


SET GLOBAL log_bin_trust_function_creators = 1;
## Calcular preu final curs individual
DELIMITER //
CREATE FUNCTION calcularPreuFinal(_preu int, _hores int, _dni varchar(30)) RETURNS DOUBLE
BEGIN
	IF EXISTS (SELECT * FROM familia_nombrosa WHERE dni = _dni) THEN
		RETURN ((_preu * _hores) * 0.60);
	ELSEIF (_hores IN (1,2)) THEN
		RETURN ((_preu * _hores) * 0.80);
	ELSEIF (_hores = 3) THEN
		RETURN ((_preu * _hores) * 0.70);
	ELSEIF (_hores = 6) THEN
		RETURN ((_preu * _hores) * 0.50);
	END IF;
    RETURN 0;
END //



SELECT calcularPreuFinal(20,6,'1234567L');



SELECT a.*, b.num_familia_nombrosa, b.data_caducitat AS data_fn , c.num_federacio, c.data_caducitat AS data_fed, c.nivell  FROM client a LEFT JOIN familia_nombrosa b ON a.dni = b.dni LEFT JOIN federat c ON a.dni = c.dni








