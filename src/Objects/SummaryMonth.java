
package Objects;

public final class SummaryMonth {
    private String month;
    private float summary;
    
    public SummaryMonth(){}
    
    public SummaryMonth(int numeroMes, float balance){
        asignarMes(numeroMes);
        this.summary = 46500-balance;
    }

    public void asignarMes(int numeroMes) {
        switch(numeroMes){
            case(1):
                this.month = "Enero";
                break;
            case(2):
                this.month = "Febrero";
                break;
            case(3):
                this.month = "Marzo";
                break;
            case(4):
                this.month = "Abril";
                break;
            case(5):
                this.month = "Mayo";
                break;
            case(6):
                this.month = "Junio";
                break;
            case(7):
                this.month = "Julio";
                break;
            case(8):
                this.month = "Agosto";
                break;
            case(9):
                this.month = "Setiembre";
                break;
            case(10):
                this.month = "Octubre";
                break;
            case(11):
                this.month = "Noviembre";
                break;
            case(12):
                this.month = "Diciembre";
                break;
        }
    }
    
    public int getNumeroMes(){
        int numMes=0;
        switch (month) {
            case "Enero":
                numMes = 1;
                break;
            case "Febrero":
                numMes = 2;
                break;
            case "Marzo":
                numMes = 3;
                break;
            case "Abril":
                numMes = 4;
                break;
            case "Mayo":
                numMes = 5;
                break;
            case "Junio":
                numMes = 6;
                break;
            case "Julio":
                numMes = 7;
                break;
            case "Agosto":
                numMes = 8;
                break;
            case "Septiembre":
                numMes = 9;
                break;
            case "Octubre":
                numMes = 10;
                break;
            case "Noviembre":
                numMes = 11;
                break;
            case "Diciembre":
                numMes = 12;
                break;
            default:
                break;
        }
        return numMes;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public float getSummary() {
        return summary;
    }

    public void setSummary(float summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Mes: " + month + " Balance:" + summary;
    }
    
    
    
}
