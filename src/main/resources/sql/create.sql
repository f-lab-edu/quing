create table users
(
    id           bigint auto_increment
        primary key,
    created_at   datetime     null,
    deleted      bit          not null,
    deleted_at   datetime     null,
    updated_at   datetime     null,
    name         varchar(255) null,
    phone_number varchar(255) null
);

create table store
(
    id           bigint auto_increment
        primary key,
    created_at   datetime     null,
    deleted      bit          not null,
    deleted_at   datetime     null,
    updated_at   datetime     null,
    address      varchar(255) null,
    name         varchar(255) null,
    open_hours   varchar(255) null,
    open_status  varchar(255) null,
    page_link    varchar(255) null,
    phone_number varchar(255) null,
    user_id      bigint       null,
    constraint FK7ybuab3162rs43dkcpsmrd0ub
        foreign key (user_id) references users (id)
);

create table menu
(
    id         bigint auto_increment
        primary key,
    created_at datetime     null,
    deleted    bit          not null,
    deleted_at datetime     null,
    updated_at datetime     null,
    image_url  varchar(255) null,
    name       varchar(255) null,
    price      int          null,
    store_id   bigint       null,
    constraint FK4sgenfcmk1jajhgctnkpn5erg
        foreign key (store_id) references store (id)
);

create table rating
(
    id         bigint auto_increment
        primary key,
    created_at datetime null,
    deleted    bit      not null,
    deleted_at datetime null,
    updated_at datetime null,
    rating     float    not null,
    store_id   bigint   null,
    constraint FKl0r7fevnk5phqe53hsms06woh
        foreign key (store_id) references store (id)
);

create table store_manager
(
    id                 bigint auto_increment
        primary key,
    created_at         datetime     null,
    deleted            bit          not null,
    deleted_at         datetime     null,
    updated_at         datetime     null,
    encrypted_password varchar(255) null,
    login_id           varchar(255) null,
    name               varchar(255) null,
    phone_number       varchar(255) null,
    store_id           bigint       null,
    constraint FKribcb3vfdaqpp4rgov3p1fuag
        foreign key (store_id) references store (id)
);

create table waiting
(
    id                   bigint auto_increment
        primary key,
    created_at           datetime null,
    deleted              bit      not null,
    deleted_at           datetime null,
    updated_at           datetime null,
    call_count           int      not null,
    waiting_queue_status int      null,
    store_id             bigint   null,
    user_id              bigint   null,
    constraint FKmhw4grymy4d2pi9sgpxg4pwoh
        foreign key (store_id) references store (id),
    constraint FKmoqjo980w3j2qms54rmd3mnoe
        foreign key (user_id) references users (id)
);

create table review
(
    id         bigint auto_increment
        primary key,
    created_at datetime     null,
    deleted    bit          not null,
    deleted_at datetime     null,
    updated_at datetime     null,
    message    varchar(255) null,
    rating     int          not null,
    user_id    bigint       null,
    waiting_id bigint       null,
    constraint FK6cpw2nlklblpvc7hyt7ko6v3e
        foreign key (user_id) references users (id),
    constraint FKmqutohjr6xu9a9mhh3x53l07g
        foreign key (waiting_id) references waiting (id)
);

create table review_image_urls
(
    review_id  bigint       not null,
    image_urls varchar(255) null,
    constraint FKm8jmy9t0epbnhbboclmot1l2
        foreign key (review_id) references review (id)
);

