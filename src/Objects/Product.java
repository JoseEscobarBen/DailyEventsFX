
package Objects;

public class Product {
    private int id_product;
    private String name_product;

    public Product(int id_product, String name_product) {
        this.id_product = id_product;
        this.name_product = name_product;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    @Override
    public String toString() {
        return "id_product=" + id_product + ", name_product=" + name_product;
    }
    
    
}
