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

//            em.flush();
//            em.clear();

            // ==예시 저장 완료==

            /**
             * 벌크 연산
             */

            //flush 자동 호출
            int resultCount = em.createQuery("update MemberTest m set m.age=20")
                    .executeUpdate();

            System.out.println("resultCount = " + resultCount);

            //DB에는 나이 20, 하지만 영속성 컨텍스트에는 반영되지 않으므로 0으로 나옴
            System.out.println("member1.getAge(): " + member1.getAge());
            System.out.println("member2.getAge(): " + member2.getAge());
            System.out.println("member3.getAge(): " + member3.getAge());
            System.out.println("member4.getAge(): " + member4.getAge());

            /**
             * 벌크 연산 후 영속성 컨텍스트 비우기
             */
            em.clear();

            MemberTest findMember1 = em.find(MemberTest.class, member1.getId());
            System.out.println("findMember1.getAge() : " + findMember1.getAge());

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

