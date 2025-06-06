package logica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("militarPU");
        EntityManager em = emf.createEntityManager();

        Militar militar = new Militar();
        militar.setNomeCompleto("João Silva");
        militar.setNomeGuerra("Silva");
        militar.setPatente(Patente.CAP);
        militar.setSaram("123456");
        militar.setIdentidadeMilitar("ID789");
        militar.setSecao("Infantaria");
        militar.setTurma("2010");
        militar.setSituacao(Situacao.EF);

        em.getTransaction().begin();
        em.persist(militar);
        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Militar persistido com sucesso!");
    }
}
