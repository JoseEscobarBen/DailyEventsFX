
package Objects;

public class SummaryDay{
    private String day;
    private float summary;
    
    public SummaryDay(){}
    
    public SummaryDay( int numeroDia, float diaMonto){
        establecerDia(numeroDia);
        this.summary = 1500-diaMonto;
    }
    
    private void establecerDia(int numeroDia){
        this.day = "Día "+numeroDia; 
    }
    
    public int getNumDia(){
        int numDia=0;
        for(int i = 1; i<=31; i++){
            if(day.equals("Día "+i))
                numDia = i; 
        }
        return numDia;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public float getSummary() {
        return summary;
    }

    public void setSummary(float summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Dia: " + day + " Balance: " + summary;
    }
    
    
}