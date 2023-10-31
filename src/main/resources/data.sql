INSERT INTO public.book (name, book_date)
VALUES
    ('BookName1', '2023-10-30'::DATE),
    ( 'BookName1', '2023-10-30'::DATE)
ON CONFLICT (id) DO NOTHING;


