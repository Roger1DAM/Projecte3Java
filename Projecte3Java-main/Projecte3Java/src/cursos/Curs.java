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

    
    /** 
     * @return int
     */
    public int getId() {
        return id;
    }

    
    /** 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * @return String
     */
    public String getNom() {
        return nom;
    }

    
    /** 
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getData() {
        return data;
    }

    
    /** 
     * @param data
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

}
