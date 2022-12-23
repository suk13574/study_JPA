package jpabasic.hellojpa;

import jpabasic.study.domain.Member;
import org.hibernate.Hibernate;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            MemberTest member1 = new MemberTest();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            MemberTest ref1 = em.getReference(MemberTest.class, member1.getId());
            System.out.println("ref1.getClass() = " + ref1.getClass());

            Hibernate.initialize(ref1);

            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(ref1));


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
