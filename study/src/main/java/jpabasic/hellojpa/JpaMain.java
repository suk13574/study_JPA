package jpabasic.hellojpa;


import jpabasic.hellojpa.DTO.MemberTestDTO;
import jpabasic.study.domain.Member;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            // 예시 저장
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            MemberTest member1 = new MemberTest();
            member1.setUsername("회원1");
            member1.changeTeam(teamA);
            em.persist(member1);

            MemberTest member2 = new MemberTest();
            member2.setUsername("회원2");
            member2.changeTeam(teamA);
            em.persist(member2);

            MemberTest member3 = new MemberTest();
            member3.setUsername("회원3");
            member3.changeTeam(teamB);
            em.persist(member3);

            MemberTest member4 = new MemberTest();
            member4.setUsername("회원4");
            em.persist(member4);

            em.flush();
            em.clear();

            // ==예시 저장 완료==

            /**
             * 네임드 쿼리 사용
             */

            List<MemberTest> resultList = em.createNamedQuery("MemberTest.findByUsername", MemberTest.class)
                    .setParameter("username", "회원1")
                    .getResultList();

            for (MemberTest memberTest : resultList) {
                System.out.println("member = " + memberTest);
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

