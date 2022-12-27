package jpabasic.hellojpa;

import javax.persistence.*;

@Entity
public class OrderTest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int orderAmount;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
