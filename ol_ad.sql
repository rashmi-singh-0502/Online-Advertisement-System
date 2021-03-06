PGDMP         7                x            postgres    13.1    13.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    13442    postgres    DATABASE     d   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_India.1252';
    DROP DATABASE postgres;
                postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3012                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            �           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    2            �            1259    16411 	   advertise    TABLE     �   CREATE TABLE public.advertise (
    ad_id numeric NOT NULL,
    title "char",
    category "char",
    description "char",
    price numeric,
    status "char"
);
    DROP TABLE public.advertise;
       public         heap    postgres    false            �            1259    16419    category    TABLE     �   CREATE TABLE public.category (
    category_id numeric NOT NULL,
    name character varying,
    description character varying
);
    DROP TABLE public.category;
       public         heap    postgres    false            �            1259    16427    message    TABLE     �   CREATE TABLE public.message (
    message_id numeric NOT NULL,
    message character varying,
    sender_username character varying
);
    DROP TABLE public.message;
       public         heap    postgres    false            �            1259    16403    user    TABLE     �   CREATE TABLE public."user" (
    userid numeric NOT NULL,
    username character varying,
    name character varying,
    address character varying,
    emailid character varying,
    contact_number numeric
);
    DROP TABLE public."user";
       public         heap    postgres    false            �          0    16411 	   advertise 
   TABLE DATA           W   COPY public.advertise (ad_id, title, category, description, price, status) FROM stdin;
    public          postgres    false    202   �       �          0    16419    category 
   TABLE DATA           B   COPY public.category (category_id, name, description) FROM stdin;
    public          postgres    false    203   �       �          0    16427    message 
   TABLE DATA           G   COPY public.message (message_id, message, sender_username) FROM stdin;
    public          postgres    false    204          �          0    16403    user 
   TABLE DATA           Z   COPY public."user" (userid, username, name, address, emailid, contact_number) FROM stdin;
    public          postgres    false    201   )       4           2606    16418    advertise Advertise_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.advertise
    ADD CONSTRAINT "Advertise_pkey" PRIMARY KEY (ad_id);
 D   ALTER TABLE ONLY public.advertise DROP CONSTRAINT "Advertise_pkey";
       public            postgres    false    202            6           2606    16426    category category_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_id);
 @   ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
       public            postgres    false    203            8           2606    16434    message message_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (message_id);
 >   ALTER TABLE ONLY public.message DROP CONSTRAINT message_pkey;
       public            postgres    false    204            2           2606    16410    user user_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (userid);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    201            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     