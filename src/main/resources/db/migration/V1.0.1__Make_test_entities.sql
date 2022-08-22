INSERT INTO author(id, bio, birth_date, name)
VALUES (1, 'Som zruteny', current_date, 'Peter Smutny');

INSERT INTO author(id, bio, birth_date, name)
VALUES (2, 'Super info o mne', current_date, 'Jozef Suprovy');

INSERT INTO genre(id, description, name)
VALUES (1, 'Super trapne vtipy', 'Komedia');

INSERT INTO product(id,
                    description,
                    image,
                    name,
                    price,
                    stock,
                    author_id,
                    genre_id)
VALUES (1,
        'Kniha pre smutne okamziky',
        'nejaky image',
        'Dennik smiesneho trpaslika',
        12,
        423,
        1,
        1);

INSERT INTO product(id,
                    description,
                    image,
                    name,
                    price,
                    stock,
                    author_id,
                    genre_id)
VALUES (2,
        'Nic pro slabe povahy',
        'nejaky image 2',
        'Programovanie v jazyku Java',
        42,
        24,
        1,
        1);

