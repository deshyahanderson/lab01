import java.util.Objects;

public class Product {

    private String ID= "";
    private String description= "";
    private String name= "";
    private double cost= 0;

    public Product(String ID, String description, String name, double cost) {
        this.ID = ID;
        this.description = description;
        this.name = name;
        this.cost = cost;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String toCSV() {
        return ID + "," + description + "," + name + "," + cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(cost, product.cost) == 0 && Objects.equals(ID, product.ID) && Objects.equals(description, product.description) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, description, name, cost);
    }

}
