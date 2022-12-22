package jpabasic.hellojpa;

import javax.persistence.Entity;

@Entity
public class BookTest extends ItemTest{

    private String author;
    private String isbn;
}
