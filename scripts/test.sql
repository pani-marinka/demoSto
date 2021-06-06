CREATE TABLE public.product

(
    productid character varying COLLATE pg_catalog."default" NOT NULL,
    productname character varying COLLATE pg_catalog."default",
    quantity integer  NOT NULL,
    price    numeric NOT NULL CHECK (price > 0),
    CONSTRAINT product_pkey PRIMARY KEY (productid)
)

    TABLESPACE pg_default;

ALTER TABLE public.product
    OWNER to postgres;


CREATE TABLE public.order

(
    orderid integer  NOT NULL,
    ordername character varying COLLATE pg_catalog."default",
    idproduct character varying COLLATE pg_catalog."default" NOT NULL,
    quantity integer  NOT NULL,
    price  numeric NOT NULL CHECK (price > 0),
    orderdate  TIMESTAMP  NOT null,
    CONSTRAINT order_pkey PRIMARY KEY (orderid)
)

    TABLESPACE pg_default;

ALTER TABLE public.order
    OWNER to postgres;



drop table public.product cascade


select ordername from public.order