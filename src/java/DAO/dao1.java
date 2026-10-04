package DAO;
import Db.connect;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sanpham.Brand;
import sanpham.Contact;
import sanpham.RevenueBrand;
import sanpham.User;
import sanpham.cartItem;
import sanpham.cate;
import sanpham.gioHang;
import sanpham.order;
import sanpham.orderItem;
import sanpham.products;
public class dao1 {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<products> getAll(){
        List<products> list = new ArrayList<>();
        String query = "SELECT * FROM `products` ";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                 list.add(new products(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("image_url"),
                                rs.getInt("brand_id"),
                                rs.getString("description"),
                                rs.getDouble("sale_price"),
                                rs.getInt("is_new"),
                                rs.getInt("is_hot")
                            ));
            }
        } catch (Exception ex) {
            Logger.getLogger(dao1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<cate> getCategory(){
        List<cate> list = new ArrayList<>();
        String query = "SELECT * FROM `categories` ";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new cate(rs.getInt(1),rs.getString(2)));
            }
        } catch (Exception ex) {
            Logger.getLogger(dao1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<products> getLast() {
        List<products> list = new ArrayList<>();
        String query = "SELECT p.* FROM products p "
                 + "JOIN product_categories pc ON p.id = pc.product_id "
                 + "WHERE pc.category_id = 3 LIMIT 9";

        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                 list.add(new products(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("image_url"),
                                rs.getInt("brand_id"),
                                rs.getString("description"),
                                rs.getDouble("sale_price"),
                                rs.getInt("is_new"),
                                rs.getInt("is_hot")
                            ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }
    public List<Brand> getAllBrand() {
        List<Brand> list = new ArrayList<>();
        String query = "SELECT * FROM `brands`";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Brand(rs.getInt(1),rs.getString(2)));
            }
        } catch (Exception ex) {
            Logger.getLogger(dao1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<products> getPbyID(String id){
        List<products> list = new ArrayList<>();
        String query = "SELECT p.* FROM products p " +
                   "JOIN product_categories pc ON p.id = pc.product_id " +
                   "WHERE pc.category_id = ?";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new products(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("image_url"),
                                rs.getInt("brand_id"),
                                rs.getString("description"),
                                rs.getDouble("sale_price"),
                                rs.getInt("is_new"),
                                rs.getInt("is_hot")
                            ));
            }
        } catch (Exception ex) {
            Logger.getLogger(dao1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<products> getBID(String id){
        List<products> list = new ArrayList<>();
        String query = "SELECT * FROM `products` where brand_id = ?";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                 list.add(new products(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("image_url"),
                                rs.getInt("brand_id"),
                                rs.getString("description"),
                                rs.getDouble("sale_price"),
                                rs.getInt("is_new"),
                                rs.getInt("is_hot")
                            ));
            }
        } catch (Exception ex) {
            Logger.getLogger(dao1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public cate getCategoryById(String id){
        String query = "SELECT * FROM categories WHERE id = ?";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                return new cate(rs.getInt(1), rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Brand getBById(String id){
        String query = "SELECT * FROM brands WHERE id = ?";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                return new Brand(rs.getInt(1), rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public products getPById(String id){
        String query = "SELECT * FROM products WHERE id = ?";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                return new products(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("image_url"),
                                rs.getInt("brand_id"),
                                rs.getString("description"),
                                rs.getDouble("sale_price"),
                                rs.getInt("is_new"),
                                rs.getInt("is_hot")
                            );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<products> getProductBySort(String type) {
        List<products> list = new ArrayList<>();
        // Mặc định sắp xếp theo ID nếu không có yêu cầu đặc biệt
        String query = "SELECT * FROM products";

        if ("asc".equals(type)) {
            query += """
                    ORDER BY 
                    CASE 
                        WHEN sale_price > 0 THEN sale_price
                        ELSE price
                    END ASC
                """;
        } else if ("desc".equals(type)) {
            query += """
                    ORDER BY 
                    CASE 
                        WHEN sale_price > 0 THEN sale_price
                        ELSE price
                    END DESC
                """;
        }

        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
               list.add(new products(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("image_url"),
                                rs.getInt("brand_id"),
                                rs.getString("description"),
                                rs.getDouble("sale_price"),
                                rs.getInt("is_new"),
                                rs.getInt("is_hot")
                            ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<products> getProduct(String cID, String type) {
        List<products> list = new ArrayList<>();
        String query = "SELECT p.* FROM products p " +
                       "JOIN product_categories pc ON p.id = pc.product_id " +
                       "WHERE 1=1 ";

        // 2. Lọc theo Category ID
        if (cID != null && !cID.isEmpty()) {
            query += " AND pc.category_id = ? ";
        }

        // 3. Sắp xếp theo giá
        if ("asc".equals(type)) {
            query += """
                        ORDER BY 
                        CASE 
                            WHEN sale_price > 0 THEN sale_price
                            ELSE price
                        END ASC
                    """;
        } else if ("desc".equals(type)) {
                query += """
                        ORDER BY 
                        CASE 
                            WHEN sale_price > 0 THEN sale_price
                            ELSE price
                        END DESC
                    """;
        }

        try {
            // Sử dụng class kết nối của bạn (connect hoặc DBContext)
             con = new connect().getConnection(); 
             ps = con.prepareStatement(query);

            if (cID != null && !cID.isEmpty()) {
                ps.setString(1, cID);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new products(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("image_url"),
                                rs.getInt("brand_id"),
                                rs.getString("description"),
                                rs.getDouble("sale_price"),
                                rs.getInt("is_new"),
                                rs.getInt("is_hot")
                            ));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<products> getProductByBrand(String bID, String type) {
        List<products> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE 1=1 ";
        if (bID != null && !bID.isEmpty()) {
            query += " AND brand_id = ? ";
        }
        if ("asc".equals(type)) {
            query += """
                        ORDER BY 
                        CASE 
                            WHEN sale_price > 0 THEN sale_price
                            ELSE price
                        END ASC
                    """;
        } else if ("desc".equals(type)) {
            query += """
                        ORDER BY 
                        CASE 
                            WHEN sale_price > 0 THEN sale_price
                            ELSE price
                        END DESC
                    """;
        }

        try {
             con = new connect().getConnection(); 
             ps = con.prepareStatement(query);

            if (bID != null && !bID.isEmpty()) {
                ps.setString(1, bID);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new products(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("image_url"),
                                rs.getInt("brand_id"),
                                rs.getString("description"),
                                rs.getDouble("sale_price"),
                                rs.getInt("is_new"),
                                rs.getInt("is_hot")
                            ));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<products> getHotProduct(){
        List<products> list = new ArrayList<>();
        String query = "SELECT DISTINCT p.* FROM products p " +
                   "JOIN product_categories pc ON p.id = pc.product_id " +
                   "WHERE pc.category_id = 4 ";

        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                list.add(new products(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("image_url"),
                    rs.getInt("brand_id"),
                    rs.getString("description"),
                    rs.getDouble("sale_price"),
                    rs.getInt("is_new"),
                    rs.getInt("is_hot")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<products> getSaleProduct(){
        List<products> list = new ArrayList<>();
        String query =  "SELECT DISTINCT p.* FROM products p " +
                   "JOIN product_categories pc ON p.id = pc.product_id " +
                   "WHERE pc.category_id = 5 ";

        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                list.add(new products(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("image_url"),
                    rs.getInt("brand_id"),
                    rs.getString("description"),
                    rs.getDouble("sale_price"),
                    rs.getInt("is_new"),
                    rs.getInt("is_hot")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<products> searchByName(String ts , String type){
        List<products> list = new ArrayList<>();
        String query = """
                        SELECT DISTINCT p.*
                                   FROM products p
                                   JOIN brands b 
                                       ON p.brand_id = b.id
                                   LEFT JOIN product_categories pc 
                                       ON p.id = pc.product_id
                                   LEFT JOIN categories c 
                                       ON pc.category_id = c.id
                                   WHERE p.name LIKE ?
                                      OR b.name LIKE ?
                                      OR c.name LIKE ?
                        """;
        if(type != null){
            if(type.equals("asc")){
                 query += """
                                ORDER BY
                                CASE
                                    WHEN sale_price > 0 THEN sale_price
                                    ELSE price
                                END ASC
                            """;
            } else if(type.equals("desc")){
                query += """
                            ORDER BY
                            CASE
                                WHEN sale_price > 0 THEN sale_price
                                ELSE price
                            END DESC
                        """;
            }
        }
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + ts + "%");
            ps.setString(2, "%" + ts + "%");
            ps.setString(3, "%" + ts + "%");
            rs = ps.executeQuery();
            while(rs.next()){
                 list.add(new products(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("image_url"),
                                rs.getInt("brand_id"),
                                rs.getString("description"),
                                rs.getDouble("sale_price"),
                                rs.getInt("is_new"),
                                rs.getInt("is_hot")
                            ));
            }
        } catch (Exception ex) {
            Logger.getLogger(dao1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public User login(String u , String p){
        String sql = "SELECT * FROM users WHERE (username = ? OR phone = ?) AND password = MD5(?) LIMIT 1";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,u);
            ps.setString(2,u);
            ps.setString(3,p);
            rs = ps.executeQuery();
            while(rs.next()){
                 return new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
            }
        } catch (Exception ex) {
            Logger.getLogger(dao1.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    public User checkU(String u){
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,u);
            rs = ps.executeQuery();
            while(rs.next()){
                 return new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
            }
        } catch (Exception ex) {
            Logger.getLogger(dao1.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    public void insertUser( String fname , String username, String password , String email , String phone , String address ,int role) {
        String query = "INSERT INTO users (fullname, username, password, email, phone, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, fname);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setInt(7, role); 
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getTotalProduct() {
        String sql = "SELECT COUNT(*) FROM products";
        try (Connection con = new connect().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTotalValue() {
        String sql = "SELECT SUM(CASE WHEN sale_price > 0 THEN sale_price ELSE price END) FROM products";
    
        try (Connection con = new connect().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<products> getProductByPage(int offset, int pageSize) {
        List<products> list = new ArrayList<>();
        String sql = "SELECT * FROM products LIMIT ?, ?";

        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new products(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("image_url"),
                                rs.getInt("brand_id"),
                                rs.getString("description"),
                                rs.getDouble("sale_price"),
                                rs.getInt("is_new"),
                                rs.getInt("is_hot")
                            ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String MD5(String text) {
    try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(text.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public gioHang getCartByUserId(int userId) {
        gioHang cart = null;
        try {
            String sql = "SELECT * FROM carts WHERE user_id = ?";
            con = new connect().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cart = new gioHang();
                cart.setId(rs.getInt("id"));
                cart.setuID(userId);

                String sqlItems = """
                    SELECT ci.*, p.name, p.price, p.sale_price ,p.image_url
                    FROM cart_items ci
                    JOIN products p ON ci.product_id = p.id
                    WHERE ci.cart_id = ?
                """;

                PreparedStatement ps2 = con.prepareStatement(sqlItems);
                ps2.setInt(1, cart.getId());
                ResultSet rs2 = ps2.executeQuery();

                List<cartItem> items = new ArrayList<>();

                while (rs2.next()) {
                    cartItem item = new cartItem();
                    item.setId(rs2.getInt("id"));
                    item.setCartId(rs2.getInt("cart_id"));
                    item.setProductId(rs2.getInt("product_id"));
                    item.setQuantity(rs2.getInt("quantity"));

                    products p = new products();
                    p.setId(rs2.getInt("product_id"));
                    p.setName(rs2.getString("name"));
                    p.setPrice(rs2.getDouble("price"));
                    p.setSale_price(rs2.getDouble("sale_price"));
                    p.setImage(rs2.getString("image_url"));

                    item.setProduct(p);
                    items.add(item);
                }

                cart.setItems(items);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cart;
    }
    
    public int getCartIdByUserId(int userId) {
        try {
            String sql = "SELECT id FROM carts WHERE user_id = ?";
            con = new connect().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public int createCart(int userId) {
        try {
            String sql = "INSERT INTO carts(user_id) VALUES(?)";
            con = new connect().getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public void addToCart(int userId, int productId) {
        try {
            int cartId = getCartIdByUserId(userId);

            if (cartId == -1) {
                cartId = createCart(userId);
            }

            String sql = """
                INSERT INTO cart_items(cart_id, product_id, quantity)
                VALUES (?, ?, 1)
                ON DUPLICATE KEY UPDATE quantity = quantity + 1
            """;
            con = new connect().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateQuantity(int cartId, int productId, int quantity) {
        try {
            String sql = "UPDATE cart_items SET quantity = ? WHERE cart_id = ? AND product_id = ?";
            con = new connect().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, cartId);
            ps.setInt(3, productId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removeItem(int cartId, int productId) {
        try {
            String sql = "DELETE FROM cart_items WHERE cart_id = ? AND product_id = ?";
            con = new connect().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clearCart(int cartId) {
        try {
            String sql = "DELETE FROM cart_items WHERE cart_id = ?";
            con = new connect().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void Contact(String fullname, String email,String phone, String message) {
        try {
            String sql = "INSERT INTO contacts(fullname,email,phone,message) VALUES(?,?,?,?)";

            con = new connect().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, message);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void checkout(int userId,
        String receiverName,
        String phone,
        String address,
        String city,
        String note) {

    try {
        gioHang cart = getCartByUserId(userId);
        if (cart == null || cart.getItems().isEmpty()) return;

        double total = 0;
        for (cartItem item : cart.getItems()) {
            total += item.getTotalPrice();
        }

        String sqlOrder = """
            INSERT INTO orders
            (user_id,total_price,receiver_name,phone,address,city,note)
            VALUES (?,?,?,?,?,?,?)
        """;

        con = new connect().getConnection();
        ps = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, userId);
        ps.setDouble(2, total);
        ps.setString(3, receiverName);
        ps.setString(4, phone);
        ps.setString(5, address);
        ps.setString(6, city);
        ps.setString(7, note);

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int orderId = 0;

        if (rs.next()) orderId = rs.getInt(1);

        String sqlItem = """
            INSERT INTO order_items(order_id, product_id, quantity, price)
            VALUES (?, ?, ?, ?)
        """;

        PreparedStatement ps2 = con.prepareStatement(sqlItem);

        for (cartItem item : cart.getItems()) {
            ps2.setInt(1, orderId);
            ps2.setInt(2, item.getProductId());
            ps2.setInt(3, item.getQuantity());

            double price =
                    item.getProduct().getSale_price() > 0
                    ? item.getProduct().getSale_price()
                    : item.getProduct().getPrice();

            ps2.setDouble(4, price);

            ps2.executeUpdate();
        }

        clearCart(cart.getId());

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public List<order> getOrdersByUser(int userId) {
        List<order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY created_at DESC";

        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                order o = new order();
                o.setId(rs.getInt("id"));
                o.setUserId(rs.getInt("user_id"));
                o.setTotalPrice(rs.getDouble("total_price"));
                o.setStatus(rs.getString("status"));
                o.setCreatedAt(rs.getTimestamp("created_at"));
                o.setReceiverName(rs.getString("receiver_name"));
                o.setPhone(rs.getString("phone"));
                o.setAddress(rs.getString("address"));
                o.setCity(rs.getString("city"));
                o.setNote(rs.getString("note"));
                List<orderItem> items = getOrderItems(o.getId());
                o.setItems(items);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<orderItem> getOrderItems(int orderId) {
        List<orderItem> list = new ArrayList<>();

        String sql = """
            SELECT oi.*, p.name, p.image_url
            FROM order_items oi
            JOIN products p ON oi.product_id = p.id
            WHERE oi.order_id = ?
        """;

        try {
            PreparedStatement ps2 = con.prepareStatement(sql);
            ps2.setInt(1, orderId);

            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                orderItem item = new orderItem();

                item.setProductId(rs2.getInt("product_id"));
                item.setQuantity(rs2.getInt("quantity"));
                item.setPrice(rs2.getDouble("price"));

                products p = new products();
                p.setName(rs2.getString("name"));
                p.setImage(rs2.getString("image_url"));

                item.setProduct(p);

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public void updateCartQuantity(int userId, int productId, int quantity) {
        String sql = """
            UPDATE cart_items ci
            JOIN carts c ON ci.cart_id = c.id
            SET ci.quantity = ?
            WHERE c.user_id = ? AND ci.product_id = ?
        """;

        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.setInt(3, productId);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateProfile(int id,String fullname,String phone,String email,String address) {
        String sql = "UPDATE users SET fullname=?, phone=?, email=?, address=? WHERE id=? ";
         try {
            con = new connect().getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, fullname);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setInt(5, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean changePassword(int userId, String oldPass, String newPass) {
        String checkSql = "SELECT password FROM users WHERE id = ?";
        String updateSql = "UPDATE users SET password = ? WHERE id = ?";

        try {
            con = new connect().getConnection();

            PreparedStatement ps1 = con.prepareStatement(checkSql);
            ps1.setInt(1, userId);

            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {

                String currentPassword = rs.getString("password");

                // so sánh MD5
                if (!currentPassword.equals(MD5(oldPass))) {
                    return false;
                }

                PreparedStatement ps2 = con.prepareStatement(updateSql);

                ps2.setString(1,MD5(newPass));
                ps2.setInt(2, userId);

                return ps2.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public void addProduct(
        String name,
        double price,
        String image,
        int brandId,
        String des,
        double salePrice,
        String[] categoryIds
) {

    try {

        con = new connect().getConnection();

        // thêm product
        String sql = "INSERT INTO products "
                + "(name, price, image_url, brand_id, description, sale_price) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setString(3, image);
        ps.setInt(4, brandId);
        ps.setString(5, des);

        if (salePrice <= 0) {
            ps.setNull(6, java.sql.Types.DECIMAL);
        } else {
            ps.setDouble(6, salePrice);
        }

        ps.executeUpdate();

        // lấy product id vừa thêm
        rs = ps.getGeneratedKeys();

        int productId = 0;

        if (rs.next()) {
            productId = rs.getInt(1);
        }

        // thêm category trung gian
        if (categoryIds != null) {

            String sqlCate = "INSERT INTO product_categories(product_id, category_id) VALUES (?, ?)";

            PreparedStatement psCate = con.prepareStatement(sqlCate);

            for (String cateId : categoryIds) {

                psCate.setInt(1, productId);
                psCate.setInt(2, Integer.parseInt(cateId));
                psCate.executeUpdate();
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void deleteProduct(int id) {

        String sql = "DELETE FROM products WHERE id = ?";

        try {
            con = new connect().getConnection();

            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProductCategory(int productId) {

        String sql = "DELETE FROM product_categories WHERE product_id = ?";

        try {
            con = new connect().getConnection();

            ps = con.prepareStatement(sql);

            ps.setInt(1, productId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update product
    public void updateProduct(int id,String name,double price,String image, int brandId,String des,double salePrice,int isNew,int isHot,int isSale) {
        String sql = """
               UPDATE products
               SET name = ?,
                   price = ?,
                   image_url = ?,
                   brand_id = ?,
                   description = ?,
                   sale_price = ?,
                   is_new = ?,
                   is_hot = ?,
                   is_sale = ?
               WHERE id = ?
           """;             
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, image);
            ps.setInt(4, brandId);
            ps.setString(5, des);
            ps.setDouble(6, salePrice);
            ps.setInt(7, isNew);
            ps.setInt(8, isHot);
            ps.setInt(9, isSale);
            ps.setInt(10, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void insertProductCategory(int productId, int categoryId) {

        String sql = "INSERT INTO product_categories(product_id, category_id) VALUES (?, ?)";

        try {

            con = new connect().getConnection();

            ps = con.prepareStatement(sql);

            ps.setInt(1, productId);
            ps.setInt(2, categoryId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // lấy list category của product
    public List<Integer> getCategoryIdsByProductId(int productId) {

        List<Integer> list = new ArrayList<>();

        String sql = "SELECT category_id FROM product_categories WHERE product_id = ? ";
        try {

            con = new connect().getConnection();

            ps = con.prepareStatement(sql);

            ps.setInt(1, productId);

            rs = ps.executeQuery();

            while (rs.next()) {

                list.add(rs.getInt("category_id"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public double getRevenue() {

    String sql = """
                 SELECT SUM(total_price)
                 FROM orders
                 """;

    try (Connection con = new connect().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getDouble(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}

public int getTotalOrders() {

    String sql = "SELECT COUNT(*) FROM orders";

    try (Connection con = new connect().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}

public int getTotalSold() {

    String sql = """
                 SELECT SUM(quantity)
                 FROM order_items
                 """;

    try (Connection con = new connect().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}
    public double getTotalRevenue() {

    String sql = """
                 SELECT SUM(oi.quantity * oi.price) AS totalRevenue
                 FROM orders o
                 JOIN order_items oi
                 ON o.id = oi.order_id
                 """;

    try {

        con = new connect().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

        if (rs.next()) {

            return rs.getDouble("totalRevenue");

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return 0;
}

    public List<RevenueBrand> getRevenueByBrand() {

        List<RevenueBrand> list = new ArrayList<>();

        String sql = """
            SELECT b.name AS brandName,
                    SUM(oi.quantity * oi.price) AS revenue
             FROM order_items oi
             JOIN products p ON oi.product_id = p.id
             JOIN brands b ON p.brand_id = b.id
             GROUP BY b.name
             ORDER BY revenue DESC
        """;
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RevenueBrand(rs.getString("brandName"), rs.getDouble("revenue"))); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public String getTopBrand() {

        String sql = """
             SELECT b.name
                    FROM order_items oi
                    JOIN products p ON oi.product_id = p.id
                    JOIN brands b ON p.brand_id = b.id
                    GROUP BY b.name
                    ORDER BY SUM(oi.quantity * oi.price) DESC
                    LIMIT 1
        """;
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public List<Contact> getAllFeedback() {
        List<Contact> list = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        try {
            con = new connect().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Contact(
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("message")
                ));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean checkProductName(String name) {
        String sql = "SELECT 1 FROM products WHERE LOWER(name) = LOWER(?)";
        try {
             con = new connect().getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);

            ResultSet rs = st.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
        public static void main(String[] args) {

            dao1 dao = new dao1();

            List<products> list = dao.getAll();

            for (products p : list) {

                System.out.println("ID: " + p.getId());
                System.out.println("Tên: " + p.getName());
                System.out.println("Giá: " + p.getPrice());
                System.out.println("Ảnh: " + p.getImage());
                System.out.println("Brand ID: " + p.getBrand());
                System.out.println("Sale Price: " + p.getSale_price());

                System.out.println("---------------------------");
            }
        }
}
