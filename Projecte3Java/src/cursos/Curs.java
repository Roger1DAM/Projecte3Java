package cursos;

import java.time.LocalDate;

public abstract class Curs {
    private int id;
    private String nom;
    private LocalDate data;

    
    public Curs(int id, String nom, LocalDate data) {
        this.id = id;
        this.nom = nom;
        this.data = data;
    }

    public Curs() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

}
