package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            /**
             * member 생성
             * Member member = new Member();
             * member.setId(1L);
             * member.setName("HelloA");
             *
             * em.persist(member); //DB에 저장
             */

            /**
             * member 수정
             * Member findMember = em.find(Member.class, 1L);
             * findMember.setName("HelloJPA");
             *
             * em.persist(findMember);
             */

            /** member 삭제
             * Member findMember = em.find(Member.class, 1L);
             * em.remove(findMember);
             */

            /** member 전체 조회
             * List<Member> result = em.createQuery("select m from Member as m", Member.class)
             *         .getResultList();
             * for (Member member : result) {
             *     System.out.println("member.getName() = " + member.getName());
             * }
             */

            /** 같은 트랜잭션 안 동일성 보장
             * Member findMember1 = em.find(Member.class, 100L);
             * Member findMember2 = em.find(Member.class, 100L);
             * System.out.println("result: "+(findMember1 == findMember2));
             */

            //영속상태
            Member member = em.find(Member.class, 1L);
            member.setName("AAA"); //이름 변경

            //준영속 상태- JPA에서 관리X
            em.detach(member);

            tx.commit();
        } catch (Exception e){
            tx.rollback();

        } finally {
            em.close();
        }
        emf.close();
    }
}
