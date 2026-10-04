package sanpham;
public class cartItem {
    private int id;
    private int cartId;
    private int productId;
    private int quantity;
    private products product;

    public cartItem() {
    }

    public cartItem(int id, int cartId, int productId, int quantity, products product) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public products getProduct() {
        return product;
    }

    public void setProduct(products product) {
        this.product = product;
    }
    
    public double getTotalPrice() {
        if (product != null) {
            double price = product.getSale_price() > 0 ? product.getSale_price() : product.getPrice();            
            return price * quantity;
        }
        return 0;
    }
}
