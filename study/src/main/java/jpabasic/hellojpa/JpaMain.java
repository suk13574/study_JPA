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
            member.setUsername("관리자");
            member.setAge(10);
            member.changeTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            //case 문 사용해보기
//            String query =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금' " +
//                            " when m.age >= 60 then '경로요금' " +
//                            " else '일반요금' END " +
//                            "from MemberTest m ";


            // coalesce 사용해보기
//            String query = "select coalesce(m.username, '이름 없는 회원') from MemberTest m";

            String query = "select NULLIF(m.username, '관리자') from MemberTest m";

            List<String> result = em.createQuery(query, String.class)
                    .getResultList();
            for (String s : result) {
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

