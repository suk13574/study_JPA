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

            MemberTest member = new MemberTest();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            MemberTest singleResult = em.createQuery("select m from MemberTest m where m.username = :username", MemberTest.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());

//            TypedQuery<String> query2 = em.createQuery("select m.username from MemberTest m", String.class);
//            Query query3 = em.createQuery("select m.username, m.age from MemberTest m");

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
