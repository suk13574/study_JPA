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

            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            MemberTest member = new MemberTest();
            member.setUsername("member1");
            member.setAge(10);
            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m from MemberTest m left join m.team t";
            List<MemberTest> result = em.createQuery(query, MemberTest.class)
                    .getResultList();


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

