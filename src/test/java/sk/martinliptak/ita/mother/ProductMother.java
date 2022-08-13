package sk.martinliptak.ita.mother;

import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.model.CreateProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;

public class ProductMother {
    public static Product prepareProduct() {
        return new Product()
                .setId(1L)
                .setName("Začínáme programovat v jazyku Java")
                .setDescription("Tato publikace uvádí čtenáře do světa programování...")
                .setImage("https://img.grada.cz/_t_/media/sprinx.bookimages/130627_350_0_fit.jpg")
                .setPrice(450L)
                .setStock(21L);
    }
    public static Product prepareProduct2() {
        return new Product()
                .setId(2L)
                .setName("Pat a Mat nás baví ")
                .setDescription("Pat a Mat vám ve třinácti nových epizodách plných skvělých gagů opět dokáží, jací jsou to šikulové.")
                .setImage("https://mrtns.eu/tovar/_l/463/l463501.jpg?v=16596936211")
                .setPrice(249L)
                .setStock(1432L);
    }
    public static ProductDto prepareProductDto() {
        return new ProductDto()
                .setId(1L)
                .setName("Začínáme programovat v jazyku Java")
                .setDescription("Tato publikace uvádí čtenáře do světa programování...")
                .setImage("https://img.grada.cz/_t_/media/sprinx.bookimages/130627_350_0_fit.jpg")
                .setPrice(450L)
                .setStock(21L);
    }
    public static CreateProductRequestDto prepareCreateProductDto() {
        return new CreateProductRequestDto()
                .setName("Začínáme programovat v jazyku Java")
                .setDescription("Tato publikace uvádí čtenáře do světa programování...")
                .setImage("https://img.grada.cz/_t_/media/sprinx.bookimages/130627_350_0_fit.jpg")
                .setPrice(450L)
                .setStock(21L);
    }
    public static CreateProductRequestDto prepareCreateProductDto2() {
        return new CreateProductRequestDto()
                .setName("Pat a Mat nás baví ")
                .setDescription("Pat a Mat vám ve třinácti nových epizodách plných skvělých gagů opět dokáží, jací jsou to šikulové.")
                .setImage("https://mrtns.eu/tovar/_l/463/l463501.jpg?v=16596936211")
                .setPrice(249L)
                .setStock(1432L);
    }
}
