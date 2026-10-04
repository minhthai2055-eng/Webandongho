package sanpham;

public class cate {
    private int id;
    private String cname;

    public cate(int id, String cname) {
        this.id = id;
        this.cname = cname;
    }

    public cate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    
}
