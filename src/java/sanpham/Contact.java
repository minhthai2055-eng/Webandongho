package sanpham;
public class Contact {
    private String fullname,email,phone,message;

    public Contact() {
    }

    public Contact(String fullname, String email, String phone, String message) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
