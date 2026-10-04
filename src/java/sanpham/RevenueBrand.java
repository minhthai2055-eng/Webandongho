package sanpham;
public class RevenueBrand {
    private String brandName;
    private double revenue;
    
    public RevenueBrand() {
    }
    
    public RevenueBrand(String brandName, double revenue) {
        this.brandName = brandName;
        this.revenue = revenue;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
    
    
    
}
