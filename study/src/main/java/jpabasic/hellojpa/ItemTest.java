package jpabasic.hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemTest {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
