package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Polaznik")
public class Polaznik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPolaznik")
    private int id;

    @Column(name = "Ime", nullable = false, length = 100)
    private String ime;

    @Column(name="Prezime", nullable = false, length = 100)
    private String prezime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Upis", joinColumns = @JoinColumn(name = "PolaznikID"), inverseJoinColumns = @JoinColumn(name = "ProgramObrazovanjaID"))
    private List<ProgramObrazovanja> programiObrazovanja = new ArrayList<>();

    public Polaznik() {
    }

    public Polaznik(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public List<ProgramObrazovanja> getProgramiObrazovanja() {
        return programiObrazovanja;
    }

    public void setProgramiObrazovanja(List<ProgramObrazovanja> programiObrazovanja) {
        this.programiObrazovanja = programiObrazovanja;
    }

    public void dodajProgramObrazovanja(ProgramObrazovanja programObrazovanja){
        if (!this.getProgramiObrazovanja().contains(programObrazovanja)) {
            this.getProgramiObrazovanja().add(programObrazovanja);
        }

        if (!programObrazovanja.getPolaznici().contains(this)) {
            programObrazovanja.getPolaznici().add(this);
        }
    }

    public void obrisiProgramObrazovanja(ProgramObrazovanja programObrazovanja){
        if (this.getProgramiObrazovanja().contains(programObrazovanja)) {
            this.getProgramiObrazovanja().remove(programObrazovanja);
        }

        if (programObrazovanja.getPolaznici().contains(this)) {
            programObrazovanja.getPolaznici().remove(this);
        }
    }
}
