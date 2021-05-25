create sequence "MS_USUARIO"."SECUENCIA_CLIENTE" start 1 increment 1
create sequence "MS_USUARIO"."SECUENCIA_EMPLEADO" start 1 increment 1
create sequence "MS_USUARIO"."SECUENCIA_OBRA" start 1 increment 1
create sequence "MS_USUARIO"."SECUENCIA_TIPO_OBRA" start 1 increment 1
create sequence "MS_USUARIO"."SECUENCIA_TIPO_USUARIO" start 1 increment 1
create sequence "MS_USUARIO"."SECUENCIA_USUARIO" start 1 increment 1

    create table "MS_USUARIO"."CLIENTE" (
       "ID" int8 not null,
        "CUIT" varchar(255),
        "EMAIL" varchar(255),
        "FECHA_BAJA" timestamp,
        "HABILITADO_ONLINE" boolean,
        "MAXIMO_CUENTA_CORRIENTE" numeric(19, 2),
        "RAZON_SOCIAL" varchar(255),
        "ID_USUARIO" int8,
        primary key ("ID")
    )

    create table "MS_USUARIO"."EMPLEADO" (
       "ID" int8 not null,
        "EMAIL" varchar(255),
        "NOMBRE" varchar(255),
        "ID_USUARIO" int8,
        primary key ("ID")
    )

    create table "MS_USUARIO"."OBRA" (
       "ID" int8 not null,
        "DESCRIPCION" varchar(255),
        "DIRECCION" varchar(255),
        "LATITUD" numeric(19, 2),
        "LONGITUD" numeric(19, 2),
        "SUPERFICIE" numeric(19, 2),
        "ID_CLIENTE" int8,
        "ID_TIPO_OBRA" int8,
        primary key ("ID")
    )

    create table "MS_USUARIO"."TIPO_OBRA" (
       "ID" int8 not null,
        "TIPO" varchar(255),
        primary key ("ID")
    )

    create table "MS_USUARIO"."TIPO_USUARIO" (
       "ID" int8 not null,
        "TIPO" varchar(255),
        primary key ("ID")
    )

    create table "MS_USUARIO"."USUARIO" (
       "ID" int8 not null,
        "CLAVE" varchar(255),
        "NOMBRE" varchar(255),
        "ID_TIPO_USUARIO" int8,
        primary key ("ID")
    )

    alter table if exists "MS_USUARIO"."CLIENTE" 
       add constraint "FK_CLIENTE_ID_USUARIO_TO_USUARIO_ID" 
       foreign key ("ID_USUARIO") 
       references "MS_USUARIO"."USUARIO"

    alter table if exists "MS_USUARIO"."EMPLEADO" 
       add constraint "FK_EMPLEADO_ID_USUARIO_TO_USUARIO_ID" 
       foreign key ("ID_USUARIO") 
       references "MS_USUARIO"."USUARIO"

    alter table if exists "MS_USUARIO"."OBRA" 
       add constraint "FK_OBRA_ID_CLIENTE_TO_CLIENTE_ID" 
       foreign key ("ID_CLIENTE") 
       references "MS_USUARIO"."CLIENTE"

    alter table if exists "MS_USUARIO"."OBRA" 
       add constraint "FK_OBRA_ID_TIPO_OBRA_TO_TIPO_OBRA_ID" 
       foreign key ("ID_TIPO_OBRA") 
       references "MS_USUARIO"."TIPO_OBRA"

    alter table if exists "MS_USUARIO"."USUARIO" 
       add constraint "FK_USUARIO_ID_TIPO_USUARIO_TO_TIPO_USUARIO_ID" 
       foreign key ("ID_TIPO_USUARIO") 
       references "MS_USUARIO"."TIPO_USUARIO"
create sequence "MS_USUARIO"."SECUENCIA_CLIENTE" start 1 increment 1
create sequence "MS_USUARIO"."SECUENCIA_EMPLEADO" start 1 increment 1
create sequence "MS_USUARIO"."SECUENCIA_OBRA" start 1 increment 1
create sequence "MS_USUARIO"."SECUENCIA_TIPO_OBRA" start 1 increment 1
create sequence "MS_USUARIO"."SECUENCIA_TIPO_USUARIO" start 1 increment 1
create sequence "MS_USUARIO"."SECUENCIA_USUARIO" start 1 increment 50

    create table "MS_USUARIO"."CLIENTE" (
       "ID" int8 not null,
        "CUIT" varchar(255),
        "EMAIL" varchar(255),
        "FECHA_BAJA" timestamp,
        "HABILITADO_ONLINE" boolean,
        "MAXIMO_CUENTA_CORRIENTE" numeric(19, 2),
        "RAZON_SOCIAL" varchar(255),
        "ID_USUARIO" int8,
        primary key ("ID")
    )

    create table "MS_USUARIO"."EMPLEADO" (
       "ID" int8 not null,
        "EMAIL" varchar(255),
        "NOMBRE" varchar(255),
        "ID_USUARIO" int8,
        primary key ("ID")
    )

    create table "MS_USUARIO"."OBRA" (
       "ID" int8 not null,
        "DESCRIPCION" varchar(255),
        "DIRECCION" varchar(255),
        "LATITUD" numeric(19, 2),
        "LONGITUD" numeric(19, 2),
        "SUPERFICIE" numeric(19, 2),
        "ID_CLIENTE" int8,
        "ID_TIPO_OBRA" int8,
        primary key ("ID")
    )

    create table "MS_USUARIO"."TIPO_OBRA" (
       "ID" int8 not null,
        "TIPO" varchar(255),
        primary key ("ID")
    )

    create table "MS_USUARIO"."TIPO_USUARIO" (
       "ID" int8 not null,
        "TIPO" varchar(255),
        primary key ("ID")
    )

    create table "MS_USUARIO"."USUARIO" (
       "ID" int8 not null,
        "CLAVE" varchar(255),
        "NOMBRE" varchar(255),
        "ID_TIPO_USUARIO" int8,
        primary key ("ID")
    )

    alter table if exists "MS_USUARIO"."CLIENTE" 
       add constraint "FK_CLIENTE_ID_USUARIO_TO_USUARIO_ID" 
       foreign key ("ID_USUARIO") 
       references "MS_USUARIO"."USUARIO"

    alter table if exists "MS_USUARIO"."EMPLEADO" 
       add constraint "FK_EMPLEADO_ID_USUARIO_TO_USUARIO_ID" 
       foreign key ("ID_USUARIO") 
       references "MS_USUARIO"."USUARIO"

    alter table if exists "MS_USUARIO"."OBRA" 
       add constraint "FK_OBRA_ID_CLIENTE_TO_CLIENTE_ID" 
       foreign key ("ID_CLIENTE") 
       references "MS_USUARIO"."CLIENTE"

    alter table if exists "MS_USUARIO"."OBRA" 
       add constraint "FK_OBRA_ID_TIPO_OBRA_TO_TIPO_OBRA_ID" 
       foreign key ("ID_TIPO_OBRA") 
       references "MS_USUARIO"."TIPO_OBRA"

    alter table if exists "MS_USUARIO"."USUARIO" 
       add constraint "FK_USUARIO_ID_TIPO_USUARIO_TIPO_USUARIO_ID\" 
       foreign key ("ID_TIPO_USUARIO") 
       references "MS_USUARIO"."TIPO_USUARIO"
