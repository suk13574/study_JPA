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

            for(int i = 0 ;i < 100 ; i++){
                MemberTest member = new MemberTest();
                member.setUsername("member" + i);
                member.setAge(i);
                em.persist(member);
            }

            em.flush();
            em.clear();

            List<MemberTest> result = em.createQuery("select m from MemberTest m order by m.age desc", MemberTest.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("result.size = " + result.size());
            for (MemberTest member1 : result) {
                System.out.println("member1 = " + member1);
            }


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

