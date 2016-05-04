package estudojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import estudojpa.ed.MusicaED;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Iniciando...");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ESTUDO-JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        MusicaED musicaED = new MusicaED();
        musicaED.setNroIntMusica(1L);
        musicaED.setNome("teste 1");
        musicaED.setDuracao(123);

        entityManager.persist(musicaED);

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("OK!");
    }

}
