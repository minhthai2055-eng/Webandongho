package sanpham;

import java.util.List;

public class gioHang {
    private int id;
    private int uID;
    private List<cartItem> items;

    public gioHang() {
    }

    public gioHang(int id, int uID, List<cartItem> items) {
        this.id = id;
        this.uID = uID;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public List<cartItem> getItems() {
        return items;
    }

    public void setItems(List<cartItem> items) {
        this.items = items;
    }

        public double getTotalPrice() {
        double total = 0;
        for (cartItem item : items) {
            double price = item.getProduct().getSale_price() > 0
                    ? item.getProduct().getSale_price()
                    : item.getProduct().getPrice();

            total += price * item.getQuantity();
        }
        return total;
    }
    public int getTotalQuantity() {
        int total = 0;
        if (items != null) {
            for (cartItem item : items) {
                total += item.getQuantity();
            }
        }
        return total;
    }
}
