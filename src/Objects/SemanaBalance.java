package Objects;

public final class SemanaBalance {
    private String semana;
    private float balance;
    
    public SemanaBalance(){}
    
    public SemanaBalance(int numeroSemana, float semanaMonto){
        asignarSemana(numeroSemana);
        this.balance = 10500-semanaMonto;
    }   

    private void asignarSemana(int numeroSemana) {
        switch(numeroSemana){
            case 1:
                this.semana = "Semana 1";
                break;
            case 2:
                this.semana = "Semana 2";
                break;
            case 3:
                this.semana = "Semana 3";
                break;
            case 4:
                this.semana = "Semana 4";
                break;
            case 5:
                this.semana = "Semana 5";
                break;                
        }
    }
    
    public int getNumeroSemana(){
        int numSemana = 0;
        switch (semana) {
            case "Semana 1":
                numSemana = 1;
                break;
            case "Semana 2":
                numSemana = 2;
                break;
            case "Semana 3":
                numSemana = 3;
                break;
            case "Semana 4":
                numSemana = 4;
                break;
            case "Semana 5":
                numSemana = 5;
                break;
            default:
                break;
        }
        return numSemana;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Semana: " + semana + " Balance: " + balance;
    }   
    
}
