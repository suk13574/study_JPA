package jpabasic.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //team 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            //member 저장
            MemberTest member = new MemberTest();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            MemberTest findMember = em.find(MemberTest.class, member.getId());
            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);


            tx.commit();
        } catch (Exception e){
            tx.rollback();

        } finally {
            em.close();
        }
        emf.close();
    }
}
