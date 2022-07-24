package sk.martinliptak.ita.service;

import org.springframework.stereotype.Service;
import sk.martinliptak.ita.model.ProductDto;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {
    private Map<Long, ProductDto> productDtoMap; // Temporary for data storing in tests

    @PostConstruct
    public void init() {
        ProductDto productDto = new ProductDto(
                1L,
                "Kucharina",
                "Kniha pro kuchare",
                43L,
                240L,
                "https://akv-shop.com/wp-content/uploads/kniha-protiprdkava.jpg"
        );
        ProductDto productDto1 = new ProductDto(
                2L,
                "Bozek stavitel",
                "no description",
                120L,
                2L,
                "https://cdn.knihcentrum.cz/6818625_bob-stavitel-kniha-plna-zabavy.jpg"
        );
        ProductDto productDto2 = new ProductDto(
                3L,
                "Programovani v jazyku Java",
                "no description",
                540L,
                20L,
                "https://data.bux.sk/book/064/213/0642136/large-zaciname_programovat_v_jazyku_java.jpg"
        );
        productDtoMap = Stream.of(productDto,productDto1,productDto2)
                .collect(Collectors.toMap(
                        ProductDto::getId,
                        Function.identity()
                ));
    }

    public ProductDto getById(Long id) {
        return productDtoMap.get(id);
    }
    public Collection<ProductDto> getAll() {
        return productDtoMap.values();
    }
}
