package sanpham;
public class Brand {
    private int id;
    private String bname;

    public Brand() {
    }

    public Brand(int id, String bname) {
        this.id = id;
        this.bname = bname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    
    
}
