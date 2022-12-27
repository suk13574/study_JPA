package jpabasic.hellojpa;


import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{


            /** JPQL
             *
             * List<MemberTest> result = em.createQuery(
             *          "select m From MemberTest m where m.username like '%kim%'",
             *          MemberTest.class
             *  ).getResultList();
             *
             */

            /** Criteria
             *
             *     //Criteria 사용 준비
             *     CriteriaBuilder cb = em.getCriteriaBuilder();
             *     CriteriaQuery<MemberTest> query = cb.createQuery(MemberTest.class);
             *
             *     Root<MemberTest> m = query.from(MemberTest.class);
             *
             *     CriteriaQuery<MemberTest> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
             *     List<MemberTest> resultList = em.createQuery(cq).getResultList();
             *
             */

            /** Native Query
             *
             * em.createNativeQuery("select * from MemberTest", MemberTest.class)
             *          .getResultList();
             *
             */

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
