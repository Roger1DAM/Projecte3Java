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


    
    /** 
     * @return int
     */
    public int getMaxClients() {
        return maxClients;
    }


    
    /** 
     * @param maxClients
     */
    public void setMaxClients(int maxClients) {
        this.maxClients = maxClients;
    }


    
    /** 
     * @return double
     */
    public double getPreu() {
        return preu;
    }


    
    /** 
     * @param preu
     */
    public void setPreu(double preu) {
        this.preu = preu;
    }

    public CursColectiu() {
        
    }
   
}
