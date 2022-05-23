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

    
    /** 
     * @return int
     */
    public int getNivell() {
        return nivell;
    }

    
    /** 
     * @param nivell
     */
    public void setNivell(int nivell) {
        this.nivell = nivell;
    }
    
    
    /** 
     * @return String
     */
    public String getSexe() {
        return sexe;
    }

    
    /** 
     * @param sexe
     */
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    
    /** 
     * @return String
     */
    public String getUsuari() {
        return usuari;
    }

    
    /** 
     * @param usuari
     */
    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public Client(String dni, String nom, String cognom) {
        this.dni = dni;
        this.nom = nom;
        this.cognom = cognom;
    }

    
    /** 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    
    /** 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /** 
     * @return String
     */
    public String getFamilia_nombrosa() {
        return familia_nombrosa;
    }

    
    /** 
     * @param familia_nombrosa
     */
    public void setFamilia_nombrosa(String familia_nombrosa) {
        this.familia_nombrosa = familia_nombrosa;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getData_caducitatFN() {
        return data_caducitatFN;
    }

    
    /** 
     * @param data_caducitatFN
     */
    public void setData_caducitatFN(LocalDate data_caducitatFN) {
        this.data_caducitatFN = data_caducitatFN;
    }

    
    /** 
     * @return String
     */
    public String getNumFederat() {
        return numFederat;
    }

    
    /** 
     * @param numFederat
     */
    public void setNumFederat(String numFederat) {
        this.numFederat = numFederat;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getData_caducitatFed() {
        return data_caducitatFed;
    }

    
    /** 
     * @param data_caducitatFed
     */
    public void setData_caducitatFed(LocalDate data_caducitatFed) {
        this.data_caducitatFed = data_caducitatFed;
    }

    
    /** 
     * @return String
     */
    public String getDni() {
        return dni;
    }

    
    /** 
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
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
     * @return String
     */
    public String getCognom() {
        return cognom;
    }

    
    /** 
     * @param cognom
     */
    public void setCognom(String cognom) {
        this.cognom = cognom;
    }
}
