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


            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }


            tx.commit();
        } catch (Exception e){
            tx.rollback();

        } finally {
            em.close();
        }
        emf.close();
    }
}
