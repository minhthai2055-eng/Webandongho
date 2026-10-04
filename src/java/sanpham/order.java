package sanpham;

import java.sql.Timestamp;
import java.util.List;

public class order {
    private int id;
    private int userId;
    private double totalPrice;
    private String status;
    private Timestamp createdAt;
    private String receiverName;
    private String phone;
    private String address;
    private String city;
    private String note;
    private List<orderItem> items;
    
    public order() {
    }

    public order(int id, int userId, double totalPrice, String status, Timestamp createdAt, String receiverName, String phone, String address, String city, String note, List<orderItem> items) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.receiverName = receiverName;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.note = note;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<orderItem> getItems() {
        return items;
    }

    public void setItems(List<orderItem> items) {
        this.items = items;
    }

    

    

    
    
}
