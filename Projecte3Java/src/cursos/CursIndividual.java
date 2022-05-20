package cursos;

import java.time.LocalDate;

public class CursIndividual extends Curs {
    private int hores;
    private String horaInici;
    private double preuHora;

    
    public int getHores() {
        return hores;
    }

    public void setHores(int hores) {
        this.hores = hores;
    }

    public String getHoraInici() {
        return horaInici;
    }

    public void setHoraInici(String horaInici) {
        this.horaInici = horaInici;
    }

    public CursIndividual(int id, String nom, LocalDate data, double preuHora) {
        super(id, nom, data);
        this.preuHora = preuHora;
    }

    public CursIndividual() {

    }

    public double getPreuHora() {
        return preuHora;
    }

    public void setPreuHora(double preuHora) {
        this.preuHora = preuHora;
    }
    
}
