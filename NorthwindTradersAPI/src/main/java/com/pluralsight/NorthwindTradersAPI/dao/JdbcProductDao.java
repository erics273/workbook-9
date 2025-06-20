package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Product> getAll() {
        // Create an empty list to hold the Product objects we will retrieve.
        List<Product> products = new ArrayList<>();

        // This is the SQL SELECT statement we will run.
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM Products";

        // This is a "try-with-resources" block.
        // It ensures that the Connection, Statement, and ResultSet are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Loop through each row in the ResultSet.
            while (rs.next()) {
                // Create a new Product object.
                Product product = new Product();

                // Set the film's ID from the "film_id" column.
                product.setProductId(rs.getInt("ProductID"));

                // Set the products's name from the "ProductName" column.
                product.setProductName(rs.getString("ProductName"));

                // Set the product's Unit Price from the "UnitPrice" column.
                product.setUnitPrice(rs.getDouble("UnitPrice"));

                // Set the product's category id from the "CategoryID" column.
                product.setCategoryId(rs.getInt("CategoryID"));

                // Add the Film object to our list.
                products.add(product);
            }

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        // Return the list of Film objects.
        return products;
    }

    public Product findById(int id) {
        // Create an empty Product.
        Product product = new Product();

        // This is the SQL SELECT statement we will run.
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM Products WHERE ProductID = ?";

        // This is a "try-with-resources" block.
        // It ensures that the Connection, Statement, and ResultSet are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            // Loop through each row in the ResultSet.
            if (rs.next()) {


                // Set the film's ID from the "film_id" column.
                product.setProductId(rs.getInt("ProductID"));

                // Set the products's name from the "ProductName" column.
                product.setProductName(rs.getString("ProductName"));

                // Set the product's Unit Price from the "UnitPrice" column.
                product.setUnitPrice(rs.getDouble("UnitPrice"));

                // Set the product's category id from the "CategoryID" column.
                product.setCategoryId(rs.getInt("CategoryID"));

            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        // Return the list of Film objects.
        return product;
    }

    @Override
    public Product add(Product product) {
        // This is the SQL INSERT statement we will run.
        // We are inserting the Product Name, Category ID, and Unit Price.
        String sql = "INSERT INTO Products (ProductName, CategoryID, UnitPrice) VALUES (?, ?, ?)";

        // This is a "try-with-resources" block.
        // It ensures that the Connection and PreparedStatement are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the first parameter (?) to the products name.
            stmt.setString(1, product.getProductName());

            // Set the second parameter (?) to the products category id.
            stmt.setInt(2, product.getCategoryId());

            // Set the third parameter (?) to the unit price.
            stmt.setDouble(3, product.getUnitPrice());

            // Execute the INSERT statement — this will add the row to the database.
            stmt.executeUpdate();

            // Retrieve the generated film_id
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int newId = keys.getInt(1);
                    product.setProductId(newId); // Set the generated ID on the Film object
                }
            }


        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void updateById(int id, Product product){
        String sql =
                """
                    UPDATE
                        Products
                    SET
                        ProductName = COALESCE(?, ProductName),
                        CategoryID = COALESCE(?, CategoryID),
                        UnitPrice = COALESCE(?, UnitPrice)
                    WHERE
                        ProductID = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductName());

            if (product.getCategoryId() == 0) {
                stmt.setNull(2, Types.INTEGER);
            } else {
                stmt.setInt(2, product.getCategoryId());
            }

            if (product.getUnitPrice() == 0.0) {
                stmt.setNull(3, Types.DOUBLE);
            } else {
                stmt.setDouble(3, product.getUnitPrice());
            }

            stmt.setInt(4, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
