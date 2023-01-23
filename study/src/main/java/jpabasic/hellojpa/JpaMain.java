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
             * 일대다(컬렉션) 페치조인 후 페이징 사용
             * 관련된 데이터 모두 메모리에 옮기고 페이징
             */
//            String query = "select t From Team t join fetch t.members m";
//
//            List<Team> result = em.createQuery(query, Team.class)
//                    .setFirstResult(0)
//                    .setMaxResults(1)
//                    .getResultList();
//
//            System.out.println("result.size() = " + result.size());
//
//            em.clear();

            // 해결방법 -
            String query2 = "select t From Team t";

            List<Team> result2 = em.createQuery(query2, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();

            System.out.println("result.size() = " + result2.size());

            for (Team team : result2) {
                System.out.println("team = " + team.getName() + " | members = " + team.getMembers().size());
                for (MemberTest member : team.getMembers()) {
                    System.out.println(" -> member = " + member);
                }
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

