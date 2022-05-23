package cursos;

import java.time.LocalDate;

public class CursIndividual extends Curs {
    private int hores;
    private String horaInici;
    private double preuHora;

    
    
    /** 
     * @return int
     */
    public int getHores() {
        return hores;
    }

    
    /** 
     * @param hores
     */
    public void setHores(int hores) {
        this.hores = hores;
    }

    
    /** 
     * @return String
     */
    public String getHoraInici() {
        return horaInici;
    }

    
    /** 
     * @param horaInici
     */
    public void setHoraInici(String horaInici) {
        this.horaInici = horaInici;
    }

    public CursIndividual(int id, String nom, LocalDate data, double preuHora) {
        super(id, nom, data);
        this.preuHora = preuHora;
    }

    public CursIndividual() {

    }

    
    /** 
     * @return double
     */
    public double getPreuHora() {
        return preuHora;
    }

    
    /** 
     * @param preuHora
     */
    public void setPreuHora(double preuHora) {
        this.preuHora = preuHora;
    }
    
}
