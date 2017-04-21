package my.apps.db;

import my.apps.web.Product;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CRUDOperations {

    // 1. define connection params to db
    final static String URL = "jdbc:postgresql://54.93.65.5:5432/Auto2_DariusSerdean";
    final static String USERNAME = "fasttrackit_dev";
    final static String PASSWORD = "fasttrackit_dev";

    public static void main(String[] args) {
        System.out.println("Hello database users! We are going to call DB from Java");
        try {
//            demo CRUD operations
//            writeShoppingItem(Product);
            getShoppingItems();
//            getLinksForPerson("darius");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void writeShoppingItem(Product product) throws SQLException, ClassNotFoundException {

        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO shoppingItem1(nume, cantitate, persoana) VALUES (?,?,?)");
        pSt.setString(1, product.getNume());
        pSt.setInt(2, product.getCantitate());
        pSt.setString(3, product.getPersoana());

        // 4. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();
        System.out.println("Inserted " + rowsInserted + " rows.");

        // 5. close the objects
        pSt.close();
        conn.close();
    }

    public static List<Product> getShoppingItems() throws ClassNotFoundException, SQLException {

        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        Statement st = conn.createStatement();

        // 4. execute a query
        ResultSet rs = st.executeQuery("SELECT id, nume, cantitate, persoana FROM shoppingItem1");

        List<Product> products = new ArrayList<>();
        // 5. iterate the result set and print the values
        while (rs.next()) {
            int id = rs.getInt("id");
            String nume = rs.getString("nume");
            int cantitate = rs.getInt("cantitate");
            String persoana = rs.getString("persoana");
            Product product = new Product(id, nume, persoana, cantitate);
            products.add(product);
        }

        // 6. close the objects
        rs.close();
        st.close();
        conn.close();
        return products;

    }

    public static void removeItem(int id) {

        // 1. load the driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2. obtain a connection
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. execute a query
            PreparedStatement pSt = conn.prepareStatement("DELETE from shoppingItem1 where id = ?");
            pSt.setInt(1, id);

            pSt.executeUpdate();

            // 6. close the objects
            pSt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<String> getAllPersons() {

        List<String> persons = new ArrayList<>();
        // 1. load the driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2. obtain a connection
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 3. create a query statement
            Statement st = conn.createStatement();

            // 4. execute a query
            ResultSet rs = st.executeQuery("SELECT DISTINCT persoana FROM shoppingItem1");

            // 5. iterate the result set and print the values
            while (rs.next()) {
                persons.add(rs.getString("persoana").trim());
            }

            // 6. close the objects
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    public static List<Product> getLinksForPerson(String persoana) throws ClassNotFoundException, SQLException {

        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        Statement st = conn.createStatement();

        //create query string
        String query = "SELECT nume, cantitate FROM shoppingitem1 where persoana='" + persoana + "'";

        // 4. execute a query
        ResultSet rs = st.executeQuery(query);

        List<Product> comenzi = new ArrayList<>();
        while (rs.next()) {
            String nume = rs.getString("nume");
            int cantitate = rs.getInt("cantitate");

            Product product = new Product(nume, cantitate, persoana);

            comenzi.add(product);
        }

        // 6. close the objects
        rs.close();
        st.close();
        conn.close();
        return comenzi;
    }


    public static double totalComanda(String person) {
        List<Product> comenzi = new ArrayList<>();

        try {
            comenzi = CRUDOperations.getLinksForPerson(person);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double total = 0;

        for (Product comanda : comenzi) {
            //iterez comenzi
            String urlProdus = comanda.getNume();

            System.out.println("urlProdus: " + urlProdus);

            //obtin pret
            try {
                String pret = Jsoup.connect(urlProdus)
                        .get().getElementById("prodPrice").getElementById("price1").text();

                //inmultesc cu cantitatea
                int cantitate = comanda.getCantitate();

                //pt fiecare comanda ++total;
                total += convertPrice(pret) * cantitate;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //listezi person si total
        System.out.println("Pt" + person + " comanda total = " + total);
        return total;
    }

    private static double convertPrice(String priceStr) {
        priceStr = priceStr.replace("lei", "").replace(".", "").replace(",", ".").trim();
        priceStr = priceStr.substring(0, priceStr.length()-1);
        return Double.parseDouble(priceStr);
    }
}



