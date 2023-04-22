CREATE TABLE IF NOT EXISTS public.category
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.flyway_schema_history
(
    installed_rank integer NOT NULL,
    version character varying(50) COLLATE pg_catalog."default",
    description character varying(200) COLLATE pg_catalog."default" NOT NULL,
    type character varying(20) COLLATE pg_catalog."default" NOT NULL,
    script character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    checksum integer,
    installed_by character varying(100) COLLATE pg_catalog."default" NOT NULL,
    installed_on timestamp without time zone NOT NULL DEFAULT now(),
    execution_time integer NOT NULL,
    success boolean NOT NULL,
    CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank)
    );

CREATE TABLE IF NOT EXISTS public.menu
(
    id bigint NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    photo character varying(255) COLLATE pg_catalog."default",
    "position" character varying(255) COLLATE pg_catalog."default",
    price double precision,
    restaurant_menu_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT menu_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.menu_category
(
    menu_id bigint NOT NULL,
    category_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT menu_category_pkey PRIMARY KEY (menu_id, category_id)
    );

CREATE TABLE IF NOT EXISTS public."menu"
(
    id bigint NOT NULL,
    status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    total_price character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_order_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT order_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.order_history
(
    order_id bigint NOT NULL,
    menu_id bigint NOT NULL,
    CONSTRAINT order_history_pkey PRIMARY KEY (order_id, menu_id)
    );

CREATE TABLE IF NOT EXISTS public.payment_data
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    cvv character varying(255) COLLATE pg_catalog."default" NOT NULL,
    card character varying(255) COLLATE pg_catalog."default" NOT NULL,
    expiration_date date NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_payment_info_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT payment_data_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.restaurant
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    company_description character varying(255) COLLATE pg_catalog."default",
    company_logo character varying(255) COLLATE pg_catalog."default",
    company_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT restaurant_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.roles
(
    id bigint NOT NULL,
    role character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT roles_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.sitting_tables
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "number" integer NOT NULL,
    qr_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    restaurant_tables_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT sitting_tables_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public."user"
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL,
    mail character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_restaurant_id character varying(255) COLLATE pg_catalog."default",
    user_role_id bigint NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)
    );

ALTER TABLE IF EXISTS public.menu
    ADD CONSTRAINT fkqrme7h1lt0fvm1ws83v8nvpps FOREIGN KEY (restaurant_menu_id)
    REFERENCES public.restaurant (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.menu_category
    ADD CONSTRAINT fk1b2ncxm5f7gx23cmcjwkix2w6 FOREIGN KEY (category_id)
    REFERENCES public.category (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.menu_category
    ADD CONSTRAINT fkcibuohcu46dgr77uxbjpd4qks FOREIGN KEY (menu_id)
    REFERENCES public.menu (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public."menu"
    ADD CONSTRAINT fkawcrdlcl8gfr6686hgnsx56sn FOREIGN KEY (user_order_id)
    REFERENCES public."user" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.order_history
    ADD CONSTRAINT fk6el0upxftdcwcp1ihndl7dmlq FOREIGN KEY (order_id)
    REFERENCES public."menu" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.order_history
    ADD CONSTRAINT fk7y0eavtssvnd45bgyomxb89ob FOREIGN KEY (menu_id)
    REFERENCES public.menu (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.payment_data
    ADD CONSTRAINT fkgvhxfesqlm3l9w5aeg2ikgvyk FOREIGN KEY (user_payment_info_id)
    REFERENCES public."user" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.sitting_tables
    ADD CONSTRAINT fktn9ksetjm73xguwe5f7ccjem4 FOREIGN KEY (restaurant_tables_id)
    REFERENCES public.restaurant (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public."user"
    ADD CONSTRAINT fkioesfd8rf40llf7xm9ejtsmqf FOREIGN KEY (user_restaurant_id)
    REFERENCES public.restaurant (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public."user"
    ADD CONSTRAINT fks7n93gypwww456pxhm3d8i9ev FOREIGN KEY (user_role_id)
    REFERENCES public.roles (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION;