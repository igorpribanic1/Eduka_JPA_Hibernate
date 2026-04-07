package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entity.Polaznik;
import org.example.entity.ProgramObrazovanja;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("mojPersistanceUnit");
            EntityManager em = emf.createEntityManager()) {

            System.out.println("START");
            EntityTransaction tx = em.getTransaction();

            tx.begin();

            Polaznik polaznik1 = new Polaznik("Ivan", "Ivić");
            Polaznik polaznik2 = new Polaznik("Pero", "Perić");
            Polaznik polaznik3 = new Polaznik("Jure", "Jurić");

            ProgramObrazovanja programObrazovanja1 = new ProgramObrazovanja("PO1", 10);
            ProgramObrazovanja programObrazovanja2 = new ProgramObrazovanja("PO2", 20);
            ProgramObrazovanja programObrazovanja3 = new ProgramObrazovanja("PO3", 30);

            polaznik1.dodajProgramObrazovanja(programObrazovanja1);
            polaznik2.dodajProgramObrazovanja(programObrazovanja2);
            polaznik3.dodajProgramObrazovanja(programObrazovanja3);

            em.persist(polaznik1);
            em.persist(polaznik2);
            em.persist(polaznik3);
            em.persist(programObrazovanja1);
            em.persist(programObrazovanja2);
            em.persist(programObrazovanja3);

            tx.commit();

            System.out.println("END");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}