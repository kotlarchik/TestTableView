package kotlarchik.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class ProductSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "saleDate")
    private Date saleDate;

    @Column(name = "quantity")
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public String toString() {
        return "ProductSale{" +
                "id=" + id +
                ", saleDate=" + saleDate +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
