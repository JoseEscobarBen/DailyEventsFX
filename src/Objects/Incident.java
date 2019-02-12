
package Objects;

public class Incident {
    private String name_product;
    private float cost_incident;
    
    public Incident(){}

    public Incident(String name_product, float cost_incident) {
        this.name_product = name_product;
        this.cost_incident = cost_incident;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public float getCost_incident() {
        return cost_incident;
    }

    public void setCost_incident(float cost_incident) {
        this.cost_incident = cost_incident;
    }

    @Override
    public String toString() {
        return "Descripcion: " + name_product + " Monto: " + cost_incident;
    }
            
    
}
