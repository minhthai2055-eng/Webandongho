package sanpham;
public class products {
    private int id;
    private String name;
    private double price;
    private String image;
    private int brand;
    private String description;
    private double sale_price;
    private int is_new;
    private int is_hot;

    public products() {
    }

    public products(int id, String name, double price, String image, int brand, String description, double sale_price, int is_new, int is_hot) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.brand = brand;
        this.description = description;
        this.sale_price = sale_price;
        this.is_new = is_new;
        this.is_hot = is_hot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    public int getIs_new() {
        return is_new;
    }

    public void setIs_new(int is_new) {
        this.is_new = is_new;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
    }

    @Override
    public String toString() {
        return "products{" + "id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", brand=" + brand + ", description=" + description + ", sale_price=" + sale_price + ", is_new=" + is_new + ", is_hot=" + is_hot + '}';
    }

    
    
    
}
