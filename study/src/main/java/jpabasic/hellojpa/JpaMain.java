package jpabasic.hellojpa;


import jpabasic.hellojpa.DTO.MemberTestDTO;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            MemberTest member = new MemberTest();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();


            List<MemberTestDTO> resultList = em.createQuery(
                    "select new jpabasic.hellojpa.DTO.MemberTestDTO(m.username, m.age) from MemberTest m", MemberTestDTO.class)
                    .getResultList();
            MemberTestDTO memberTestDTO = resultList.get(0);
            System.out.println("username = " + memberTestDTO.getUsername());
            System.out.println("age = " + memberTestDTO.getAge());


            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }
        emf.close();
    }
}

