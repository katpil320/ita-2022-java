package sk.martinliptak.ita.model;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long stock;
    private String image;

    public ProductDto(Long id, String name, String description, Long price, Long stock, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getPrice() {
        return price;
    }

    public Long getStock() {
        return stock;
    }

    public String getImage() {
        return image;
    }
}
