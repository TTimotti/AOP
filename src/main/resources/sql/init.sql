create table TB_ROLE
(
    ID   INTEGER auto_increment,
    CODE INTEGER               not null
        constraint TB_ROLE_CODE_UQ
            unique,
    NAME CHARACTER VARYING(16) not null,
    constraint TB_ROLE_ID_PK
        primary key (ID)
);

create table TB_USER_MST
(
    ID       INTEGER auto_increment,
    USER_ID  CHARACTER VARYING(16)  not null
        constraint TB_USER_MST_USER_ID_UQ
            unique,
    PASSWORD CHARACTER VARYING(128) not null,
    constraint TB_USER_MST_ID_PK
        primary key (ID)
);

create table TB_USER_INFO
(
    ID      INTEGER auto_increment,
    USER_ID CHARACTER VARYING(16) not null,
    NAME    CHARACTER VARYING(16) not null,
    EMAIL   CHARACTER VARYING(64),
    constraint TB_USER_INFO_ID_PK
        primary key (ID),
    constraint TB_USER_INFO_TB_USER_MST_USER_ID_FK
        foreign key (USER_ID) references TB_USER_MST (USER_ID)
);

create table TB_USER_ROLE
(
    ID        INTEGER auto_increment,
    USER_ID   CHARACTER VARYING(16) not null,
    ROLE_CODE INTEGER,
    constraint TB_USER_ROLE_PK
        primary key (ID),
    constraint TB_USER_ROLE_USER_ID_AND_ROLE_CODE_UQ
        unique (USER_ID, ROLE_CODE),
    constraint TB_USER_ROLE_TB_ROLE_CODE_FK
        foreign key (ROLE_CODE) references TB_ROLE (CODE),
    constraint TB_USER_ROLE_TB_USER_MST_USER_ID_FK
        foreign key (USER_ID) references TB_USER_MST (USER_ID)
);

