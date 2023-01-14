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

//            Team team = new Team();
//            team.setName("team1");
//            em.persist(team);

            MemberTest member = new MemberTest();
            member.setUsername("관리자");
//            member.setAge(10);
//            member.changeTeam(team);
//            member.setType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select size(t.members) from Team t";

            List<Integer> result = em.createQuery(query, Integer.class)
                    .getResultList();
            for (Integer s : result) {
                System.out.println("s = " + s);
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

