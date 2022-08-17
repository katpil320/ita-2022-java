INSERT INTO author(id, bio, birth_date, name)
VALUES (1, 'Som zruteny', null, 'Peter Smutny');

INSERT INTO author(id, bio, birth_date, name)
VALUES (2, 'Neexistujem', null, 'Noname');

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

