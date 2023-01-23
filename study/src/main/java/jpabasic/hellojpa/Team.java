package jpabasic.hellojpa;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Team {

     @Id @GeneratedValue
     private Long id;
     private String name;

     @BatchSize(size = 100)
     @OneToMany(mappedBy = "team")
     private List<MemberTest> members = new ArrayList<>();

     @Override
     public String toString() {
          return "Team{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  '}';
     }
}
