package sk.martinliptak.ita.mother;

import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductSimpleDto;

import static sk.martinliptak.ita.mother.AuthorMother.*;
import static sk.martinliptak.ita.mother.GenreMother.*;

public class ProductMother {
    public static Product prepareProduct() {
        Product product = new Product()
                .setName("Začínáme programovat v jazyku Java")
                .setDescription("Tato publikace uvádí čtenáře do světa programování...")
                .setImage("https://img.grada.cz/_t_/media/sprinx.bookimages/130627_350_0_fit.jpg")
                .setPrice(450L)
                .setStock(21L)
                .setAuthor(AuthorMother.prepareAuthor())
                .setGenre(prepareGenre());
        product.setId(1L);
        return product;
    }
    public static Product prepareProduct1() {
        Product product = new Product()
                .setName("Pat a Mat nás baví ")
                .setDescription("Pat a Mat vám ve třinácti nových epizodách plných skvělých gagů opět dokáží, jací jsou to šikulové.")
                .setImage("https://mrtns.eu/tovar/_l/463/l463501.jpg?v=16596936211")
                .setPrice(249L)
                .setStock(1432L)
                .setAuthor(prepareAuthor1())
                .setGenre(prepareGenre1());
        product.setId(2L);
        return product;
    }

    public static ProductDto prepareProductDto() {
        return new ProductDto()
                .setId(1L)
                .setName("Začínáme programovat v jazyku Java")
                .setDescription("Tato publikace uvádí čtenáře do světa programování...")
                .setImage("https://img.grada.cz/_t_/media/sprinx.bookimages/130627_350_0_fit.jpg")
                .setPrice(450L)
                .setStock(21L)
                .setAuthor(prepareAuthorDto())
                .setGenre(prepareGenreDto())
                .setPreview(false);
    }
    public static ProductDto prepareProductDto1() {
        return new ProductDto()
                .setId(2L)
                .setName("Pat a Mat nás baví ")
                .setDescription("Pat a Mat vám ve třinácti nových epizodách plných skvělých gagů opět dokáží, jací jsou to šikulové.")
                .setImage("https://mrtns.eu/tovar/_l/463/l463501.jpg?v=16596936211")
                .setPrice(249L)
                .setStock(1432L)
                .setAuthor(prepareAuthorDto1())
                .setGenre(prepareGenreDto1())
                .setPreview(false);
    }
    public static ProductRequestDto prepareProductRequestDto() {
        return new ProductRequestDto()
                .setName("Začínáme programovat v jazyku Java")
                .setDescription("Tato publikace uvádí čtenáře do světa programování...")
                .setImage("https://img.grada.cz/_t_/media/sprinx.bookimages/130627_350_0_fit.jpg")
                .setPrice(450L)
                .setStock(21L)
                .setAuthorId(1L)
                .setGenreId(1L);
    }
    public static ProductRequestDto prepareProductRequestDto1() {
        return new ProductRequestDto()
                .setName("Pat a Mat nás baví ")
                .setDescription("Pat a Mat vám ve třinácti nových epizodách plných skvělých gagů opět dokáží, jací jsou to šikulové.")
                .setImage("https://mrtns.eu/tovar/_l/463/l463501.jpg?v=16596936211")
                .setPrice(249L)
                .setStock(1432L)
                .setAuthorId(2L)
                .setGenreId(2L);
    }
    public static ProductSimpleDto prepareProductSimpleDto() {
        return new ProductSimpleDto()
                .setId(1L)
                .setName("Začínáme programovat v jazyku Java")
                .setImage("https://img.grada.cz/_t_/media/sprinx.bookimages/130627_350_0_fit.jpg")
                .setPrice(450L);
    }
    public static ProductSimpleDto prepareProductSimpleDto1() {
        return new ProductSimpleDto()
                .setId(2L)
                .setName("Pat a Mat nás baví ")
                .setImage("https://mrtns.eu/tovar/_l/463/l463501.jpg?v=16596936211")
                .setPrice(249L);
    }
}
