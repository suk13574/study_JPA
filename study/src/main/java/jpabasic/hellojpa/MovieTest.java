package jpabasic.hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("MOVIE")
public class MovieTest extends ItemTest{
    private String director;
    private String actor;
}
