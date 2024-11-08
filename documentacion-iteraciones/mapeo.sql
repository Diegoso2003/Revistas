create schema revistas;
use revistas;

create table usuario(
	nombre varchar(30) not null,
	contraseña varchar(60) not null,
	rol varchar(15) not null,
	cartera decimal(10,2) not null default 0.00,
	constraint pk_usuario primary key (nombre)
);

create table precio_de_anuncios(
	texto decimal(10,2) not null,
	texto_imagen decimal(10,2) not null,
	video decimal(10,2) not null,
	dia_1 decimal(10,2) not null,
	dia_3 decimal(10,2) not null,
	semana_1 decimal(10,2) not null,
	semana_2 decimal(10,2) not null
);

create table anuncio(
	ID int auto_increment not null,
	nombre_usuario varchar(30) not null,
	precio decimal(10,2) not null,
	fecha_pago date not null,
	estado boolean not null default 1,
	contador int not null default 0,
	texto varchar(50),
	url_video varchar(100),
	tipo_anuncio varchar(20) not null,
	vigencia int not null,
	extension varchar(20),
	media mediumblob,
	constraint pk_anuncio primary key (ID),
	constraint fk_usuario_in_nombre_usuario foreign key (nombre_usuario) references usuario(nombre)
)	auto_increment = 1000;

create table registro_anuncio(
	ID int not null auto_increment,
	ID_anuncio int not null,
	url varchar(150) not null,
	constraint pk_registro_anuncio primary key (ID),
	constraint fk_anuncio_in_ID_anuncio foreign key (ID_anuncio) references anuncio(ID)
);

create table categoria(
	nombre varchar(50) not null,
	constraint pk_categoria primary key (nombre)
);

create table revista(
	ID int auto_increment not null,
	nombre varchar(50) not null,	
	nombre_editor varchar(30) not null,	
	nombre_categoria varchar(50),	
	fecha date not null,	
	bloqueo_comentario boolean not null default 0,
	bloqueo_anuncios boolean not null default 0,
	descripcion varchar(250) not null,	
	precio decimal(10,2) not null default 20.00,		
	precio_bloqueo decimal(10,2) not null default 20.00,	
	bloqueo_suscripcion boolean not null default 0,	
	numero_likes int not null default 0,
	constraint pk_revista primary key(ID),
	constraint fk_usuario_in_nombre_editor foreign key (nombre_editor) references usuario(nombre),
	constraint fk_categoria_in_nombre_categoria foreign key (nombre_categoria) references categoria(nombre)
) auto_increment = 1000;



create table registro_bloqueo_anuncio(
	ID int auto_increment,
	fecha_pago date not null,
	dias_vigentes int not null,
	ID_revista_bloqueo int not null,
	pago decimal(10,2) not null,
	constraint pk_registro_bloqueo_anuncio primary key(ID),
	constraint fk_revista_in_ID_revista_bloqueo foreign key (ID_revista_bloqueo) references revista(ID)
) auto_increment = 1000;

create table pdf(
	ID_pdf int auto_increment,
	ID_revista_principal int not null,
	nombre varchar(50) not null,
	archivo mediumblob not null,
	constraint pk_pdf primary key(ID_pdf),
	constraint fk_revista_in_ID_revista_principal foreign key (ID_revista_principal) references revista(ID)
) auto_increment = 1000;


create table etiqueta(
	nombre varchar(50) not null,
	constraint pk_etiqueta primary key (nombre)
);

create table etiquetador(
	nombre_etiqueta varchar(50) not null,
	ID_revista int not null,
	constraint fk_revista_in_ID_revista foreign key (ID_revista) references revista(ID) ON DELETE CASCADE,
	constraint fk_etiqueta_in_nombre_etiqueta foreign key (nombre_etiqueta) references etiqueta(nombre)
);




