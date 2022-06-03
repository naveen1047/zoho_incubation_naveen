package com.naveen.jersey_db.orders;

import com.naveen.jersey_db.cart.CartService;
import com.naveen.jersey_db.exception.CustomException;
import com.naveen.jersey_db.product.models.Product;
import com.naveen.jersey_db.product.service.ProductService;
import com.naveen.jersey_db.product.util.DependenciesFactory;
import com.naveen.jersey_db.wallet.WalletService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepo {
    private final ProductService productService;
    private final CartService cartService;
    private final WalletService walletService;

    Connection con;

    public OrderRepo() {
        String url = "jdbc:mysql://localhost:3306/ecommerce";
        String username = "root";
        String password = "";
        productService = DependenciesFactory.getProductService();
        cartService = com.naveen.jersey_db.cart.DependenciesFactory.getCartService();
        walletService = com.naveen.jersey_db.wallet.DependenciesFactory.getWalletService();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


    public List<Orders> getOrders(int id) {
        HashMap<Integer, Orders> hm = new HashMap<>();

        String sql = "SELECT \n" +
                "\tt.id AS t_id, \n" +
                "    p.id AS p_id, \n" +
                "    h.u_id AS u_id, \n" +
                "    p.name AS p_name,\n" +
                "    p.price,\n" +
                "    t.time\n" +
                "FROM history AS h\n" +
                "JOIN transactions AS t\n" +
                "\tON t.id = h.t_id\n" +
                "JOIN transaction_product AS tp\n" +
                " \tON tp.t_id = t.id\n" +
                "JOIN products AS p\n" +
                "\tON tp.p_id = p.id\n" +
                "WHERE h.u_id = ?";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int tID = rs.getInt(1);
                int pID = rs.getInt(2);
                double price = rs.getDouble(5);

                if (hm.containsKey(tID)) {
                    Orders temp = hm.get(tID);
                    List<Product> products = temp.getProducts();
                    products.add(productService.getProduct(pID));
                    temp.setProducts(products);
                    temp.setTotal(hm.get(tID).getTotal() + price);

                    hm.put(tID, temp);
                } else {
                    // first time for Transaction
                    Orders orders1 = new Orders();
                    orders1.setUId(id);
                    orders1.setTId(tID);
                    orders1.setTime(rs.getTime(6));
                    System.out.println(rs.getString(6));
                    orders1.setDateTime(rs.getString(6));
                    orders1.setDate(rs.getDate(6));
                    orders1.setTotal(price);

                    orders1.setProducts(new ArrayList<>());
                    Product p = productService.getProduct(pID);
                    List<Product> products = orders1.getProducts();
                    products.add(p);
                    orders1.setProducts(products);
                    hm.put(tID, orders1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new ArrayList<>(hm.values());
    }

    public void orderNow(int id) {
        // place order and remove cart
        double total = getTotal(id);

        if (total <= walletService.getWallet(id).getBalance()) {

            getCart(id)
                    .stream()
                    .forEach(System.out::println);

            orderNow(getCart(id), id, total);
        } else {
            throw new CustomException("Check you balance");
        }
    }


    private void orderNow(List<Integer> cartIds, int id, double total) {

        try {
            CallableStatement cs = con.prepareCall("{ call order_now(?, ?) } ");

            cs.setInt(1, id);

            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute();

            int tId = cs.getInt(2);

            PreparedStatement ps = con.prepareStatement(getSql(cartIds, tId));

            ps.execute();


        } catch (SQLException e) {
            throw new CustomException(e.getMessage());
        }

        cartService.deleteCart(id);
        walletService.setWallet(id, walletService.getWallet(id).getBalance() - total);
    }

    private String getSql(List<Integer> cartIds, int tID) {
        StringBuilder sb = new StringBuilder();

        sb.append(
                "INSERT INTO  \n" +
                        "\ttransaction_product \n" +
                        "VALUES "
        );


        for (int i = 0; i < cartIds.size(); i++) {
            int cId = cartIds.get(i);

            if (i < cartIds.size() - 1) {
                sb.append(
                        "(" + tID + ", " + cId + "), "
                );
            } else {
                sb.append(
                        "(" + tID + ", " + cId + "); "
                );
            }

        }

        return sb.toString();
    }

    private List<Integer> getCart(int id) {
        return cartService.getCart(id)
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    private double getTotal(int id) {
        return cartService.getCart(id)
                .stream()
                .map(Product::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
