package jpabasic.hellojpa;

import javax.persistence.*;

@Entity
public class MemberProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberTest member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}

