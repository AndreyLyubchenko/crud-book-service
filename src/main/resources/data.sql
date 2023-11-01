-- DROP TABLE IF EXISTS book;

CREATE TABLE IF NOT EXISTS public.book (
      id SERIAL PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       book_date DATE NOT NULL
);



INSERT INTO public.book (name, book_date)
VALUES
    ('BookName1', '2023-10-30'::DATE),
    ( 'BookName2', '2023-11-01'::DATE)
ON CONFLICT (id) DO NOTHING;


