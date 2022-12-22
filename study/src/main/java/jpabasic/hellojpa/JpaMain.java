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

            MovieTest movie = new MovieTest();
            movie.setDirector("감독A");
            movie.setActor("홍길동");
            movie.setName("영화A");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            MovieTest fineMovie = em.find(MovieTest.class, movie.getId());
            System.out.println("fineMovie = " + fineMovie);


            tx.commit();
        } catch (Exception e){
            tx.rollback();

        } finally {
            em.close();
        }
        emf.close();
    }
}
