package sk.martinliptak.ita.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import sk.martinliptak.ita.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


/*
* This class acts like a mother for preparing entities for DB testing
*
* Feel free to add your own repository, prepare() method and also customize cleaning
*
* Please be aware of this:
* - Fetch type EAGER needed for each entity that has relation to other entity
* - Cleaning repositories must be specified in correct order to prevent removing
*   entities with foreign keys or relationships
*/
@Component
@RequiredArgsConstructor
@Slf4j
public class RepositoryEntityCreationManager {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;



    public Author prepareAuthor() {
        Author author = new Author()
                .setName("Samo Chalupka")
                .setBio("Popularny spisovatel")
                .setBirthDate(LocalDate.of(1987,10,4));
        log.debug("Creating author");
        return authorRepository.save(author);
    }

    public Genre prepareGenre() {
        Genre genre = new Genre()
                .setName("Naucne")
                .setDescription("Vela informacii");
        log.debug("Creating Genre");
        return genreRepository.save(genre);
    }

    public Product prepareProduct() {
        Author author = this.prepareAuthor();
        Genre genre = this.prepareGenre();
        Product product = new Product()
                .setName("Programovani v jazyku Java")
                .setDescription("Kniha pro nadsencov, nie nervakov")
                .setPrice(400L)
                .setStock(64L)
                .setImage("http://nejakyobrazok.sk")
                .setAuthor(authorRepository.findById(author.getId()).get())
                .setGenre(genreRepository.findById(genre.getId()).get());
        log.debug("Creating product");
        return productRepository.save(product);
    }

    public Cart prepareCart() {
        Product product = this.prepareProduct();
        Cart cart = new Cart()
                .setProducts(Set.of(product));
        log.debug("Creating cart");
        return cartRepository.save(cart);
    }

    public Order prepareOrder() {
        Cart cart = this.prepareCart();
        Order order = new Order()
                .setStatus(OrderStatus.NEW)
                .setProducts(cart.getProducts());
        log.debug("Creating order");
        return orderRepository.save(order);
    }

    public void clean() {
        for (JpaRepository<?, Long> repository : List.of(this.orderRepository,this.cartRepository, this.productRepository, this.authorRepository, this.genreRepository)) {
            if (!repository.findAll().isEmpty()) {
            repository.deleteAll();
            }
        }
        log.debug("Repositories cleaned");
    }
}
