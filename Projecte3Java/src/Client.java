import java.time.LocalDate;

public class Client {
    private String dni;
    private String nom;
    private String cognom;
    private String usuari;
    private String sexe;
    private String email;
    private int nivell;
    private String familia_nombrosa;
    private LocalDate data_caducitatFN;
    private String numFederat;
    private LocalDate data_caducitatFed;
    

    public Client() {
        
    }

    public int getNivell() {
        return nivell;
    }

    public void setNivell(int nivell) {
        this.nivell = nivell;
    }
    
    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public Client(String dni, String nom, String cognom) {
        this.dni = dni;
        this.nom = nom;
        this.cognom = cognom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamilia_nombrosa() {
        return familia_nombrosa;
    }

    public void setFamilia_nombrosa(String familia_nombrosa) {
        this.familia_nombrosa = familia_nombrosa;
    }

    public LocalDate getData_caducitatFN() {
        return data_caducitatFN;
    }

    public void setData_caducitatFN(LocalDate data_caducitatFN) {
        this.data_caducitatFN = data_caducitatFN;
    }

    public String getNumFederat() {
        return numFederat;
    }

    public void setNumFederat(String numFederat) {
        this.numFederat = numFederat;
    }

    public LocalDate getData_caducitatFed() {
        return data_caducitatFed;
    }

    public void setData_caducitatFed(LocalDate data_caducitatFed) {
        this.data_caducitatFed = data_caducitatFed;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }
}
