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

            //member 저장
            MemberTest member = new MemberTest();
            member.setUsername("member1");
            em.persist(member);

            em.flush();
            em.clear();

            //team 저장
            Team team = new Team();
            team.setName("TeamA");
            team.getMembers().add(member);
            em.persist(team);

            em.flush();
            em.clear();

//            MemberTest findMember = em.find(MemberTest.class, member.getId());
//            List<MemberTest> members = findMember.getTeam().getMembers();


            tx.commit();
        } catch (Exception e){
            tx.rollback();

        } finally {
            em.close();
        }
        emf.close();
    }
}
