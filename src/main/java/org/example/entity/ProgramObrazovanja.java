package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ProgramObrazovanja")
public class ProgramObrazovanja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDProgramObrazovanja")
    private int id;

    @Column(name = "Naziv", nullable = false, length = 50)
    private String naziv;

    @Column(name = "CSVET")
    private int cvset;

    @ManyToMany(mappedBy = "programiObrazovanja", cascade = CascadeType.ALL)
    private List<Polaznik> polaznici = new ArrayList<>();

    public ProgramObrazovanja(String naziv, int cvset) {
        this.naziv = naziv;
        this.cvset = cvset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getCvset() {
        return cvset;
    }

    public void setCvset(int cvset) {
        this.cvset = cvset;
    }

    public List<Polaznik> getPolaznici() {
        return polaznici;
    }

    public void setPolaznici(List<Polaznik> polaznici) {
        this.polaznici = polaznici;
    }


    public void dodajPolaznik(Polaznik polaznik){
        if (!this.getPolaznici().contains(polaznik)) {
            this.getPolaznici().add(polaznik);
        }

        if (!polaznik.getProgramiObrazovanja().contains(this)) {
            polaznik.getProgramiObrazovanja().add(this);
        }
    }

    public void obrisiPolaznik(Polaznik polaznik){
        if (this.getPolaznici().contains(polaznik)) {
            this.getPolaznici().remove(polaznik);
        }

        if (polaznik.getProgramiObrazovanja().contains(this)) {
            polaznik.getProgramiObrazovanja().remove(this);
        }
    }
}
