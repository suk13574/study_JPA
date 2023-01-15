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

            // 기존 join 사용해보기
            String query1 = "select m From MemberTest m";
            List<MemberTest> result = em.createQuery(query1, MemberTest.class)
                    .getResultList();

            result.stream()
                    .map(m -> Optional.ofNullable(m.getTeam()))
                    .filter(r -> r.isPresent())
                    .map(r -> r.get())
                    .forEach(System.out::println);

            System.out.println("==========================");

            // fetch join 사용해보기
            String query2 = "select m from MemberTest m join fetch m.team";

            List<MemberTest> result2 = em.createQuery(query2, MemberTest.class).getResultList();

            result2.stream()
                    .map(m -> Optional.ofNullable(m.getTeam()))
                    .filter(r -> r.isPresent())
                    .map(r -> r.get())
                    .forEach(System.out::println);

            System.out.println("==========================");

            // 컬렉션 페치 조인 사용해보기
            String query3 = "select t from Team t join fetch t.members";

            List<Team> result3 = em.createQuery(query3, Team.class).getResultList();

            result3.stream()
                    .forEach(x -> System.out.println(x.getName() + " | members.size = " + x.getMembers().size()));


            System.out.println("==========================");

            // DISTINCT로 중복 제거하기
            String query4 = "select distinct t from Team t join fetch t.members";

            List<Team> result4 = em.createQuery(query4, Team.class).getResultList();

            System.out.println("쿼리에 DISTINCT 추가: " + result4.size());




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

