package com.example.GameShopJavaFX.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Genre cannot be blank")
    private String genre;

    private String platform;

    @Column(nullable = false)
    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;

    private String publisher;
    private String developer;

    private LocalDate releaseDate;

    @Positive(message = "Price must be positive")
    private BigDecimal price;

    public Product() {}

    public Product(String title, String genre, String platform, Integer quantity, String publisher, String developer, LocalDate releaseDate , BigDecimal price) {
        if(title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if(genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty");
        }

        if(quantity != null && quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        if(price != null && price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.quantity = quantity;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseDate  = releaseDate ;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public String getPlatform() {
        return platform;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public String getPublisher() {
        return publisher;
    }
    public String getDeveloper() {
        return developer;
    }
    public LocalDate getReleaseDate() {
        return releaseDate ;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", platform='" + platform + '\'' +
                ", publisher='" + publisher + '\'' +
                ", developer='" + developer + '\'' +
                ", releaseDate=" + releaseDate +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
