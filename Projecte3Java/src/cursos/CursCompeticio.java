package cursos;

import java.time.LocalDate;

public class CursCompeticio extends Curs {
    private int nivell;
    private LocalDate data_fi;
    private double preu;


    public CursCompeticio(int id, String nom, LocalDate data, int nivell, LocalDate data_fi, double preu) {
        super(id, nom, data);
        this.nivell = nivell;
        this.data_fi = data_fi;
        this.preu = preu;
    }

    public CursCompeticio() {

    }

    public int getNivell() {
        return nivell;
    }

    public void setNivell(int nivell) {
        this.nivell = nivell;
    }

    public LocalDate getData_fi() {
        return data_fi;
    }

    public void setData_fi(LocalDate data_fi) {
        this.data_fi = data_fi;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }
    
}
