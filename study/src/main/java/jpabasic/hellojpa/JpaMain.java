package jpabasic.hellojpa;


import jpabasic.hellojpa.DTO.MemberTestDTO;

import javax.persistence.*;
import java.util.Collection;
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
            member.setUsername("관리자");
            member.changeTeam(team);
//            member.setAge(10);
//            member.setType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();


            /**
             * 상태 필드
             */
            String query1 = "select m.username from MemberTest m";

            List<String> result1 = em.createQuery(query1, String.class)
                    .getResultList();

            System.out.println("상태필드 조회: \n username = " + result1);

            /**
             * 단일 값 연관 경로
             */
            String query2 = "select m.team from MemberTest m";
            List<Team> result2 = em.createQuery(query2, Team.class)
                    .getResultList();
            System.out.println("단일 값 연관 경로: ");

            for (Team team1 : result2) {
                System.out.println("team1 = " + team1.toString());
            }

            /**
             * 컬렉션 값 연관 경로
             */
            String query3 = "select t.members from Team t";
            List<Collection> result3 = em.createQuery(query3, Collection.class).getResultList();
            System.out.println("컬렉션 값 연관 경로: \n members = " + result3);


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

