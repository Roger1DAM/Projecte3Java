package cursos;

import java.time.LocalDate;

public class CursColectiu extends Curs {
    private int maxClients;
    private double preu;


    public CursColectiu(int id, String nom, LocalDate data, int maxClients, double preu) {
        super(id, nom, data);
        this.maxClients = maxClients;
        this.preu = preu;
    }


    public int getMaxClients() {
        return maxClients;
    }


    public void setMaxClients(int maxClients) {
        this.maxClients = maxClients;
    }


    public double getPreu() {
        return preu;
    }


    public void setPreu(double preu) {
        this.preu = preu;
    }

    public CursColectiu() {
        
    }
   
}
