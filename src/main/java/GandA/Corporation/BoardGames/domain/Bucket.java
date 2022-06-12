package GandA.Corporation.BoardGames.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "buckets")
public class Bucket {
    private static final String SEQ_NAME = "bucket_seq";

    @Id
    @Column(name = "bucket_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name ="user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "buckets_products",
    joinColumns = @JoinColumn(name = "bucket_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
