package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCategoryDao implements CategoryDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Category> getAll() {
        // Create an empty list to hold the Category objects we will retrieve.
        List<Category> categories = new ArrayList<>();

        // This is the SQL SELECT statement we will run.
        String sql = "SELECT CategoryID, CategoryName FROM Categories";

        // This is a "try-with-resources" block.
        // It ensures that the Connection, Statement, and ResultSet are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Loop through each row in the ResultSet.
            while (rs.next()) {
                // Create a new Category object.
                Category category = new Category();

                // Set the categories ID from the "CategoryID" column.
                category.setCategoryId(rs.getInt("CategoryID"));

                // Set the categories name from the "CategoryName" column.
                category.setCategoryName(rs.getString("CategoryName"));

                // Add the Film object to our list.\
                categories.add(category);
            }

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        // Return the list of Film objects.
        return categories;
    }

    public Category findById(int id) {
        // Create a category we will create form the category object we find objects.
        Category category = new Category();

        // This is the SQL SELECT statement we will run.
        String sql = "SELECT CategoryID, CategoryName FROM Categories WHERE CategoryID = ?";

        // This is a "try-with-resources" block.
        // It ensures that the Connection, Statement, and ResultSet are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            // Loop through each row in the ResultSet.
            if (rs.next()) {


                // Set the categories ID from the "CategoryID" column.
                category.setCategoryId(rs.getInt("CategoryID"));

                // Set the categories name from the "CategoryName" column.
                category.setCategoryName(rs.getString("CategoryName"));


            }else{
                return null;
            }

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        // Return the list of Film objects.
        return category;
    }

    @Override
    public Category add(Category category) {
        // This is the SQL INSERT statement we will run.
        // We are inserting the CategoryName.
        String sql = "INSERT INTO Categories (CategoryName) VALUES (?)";

        // This is a "try-with-resources" block.
        // It ensures that the Connection and PreparedStatement are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the first parameter (?) to the products name.
            stmt.setString(1, category.getCategoryName());

            // Execute the INSERT statement — this will add the row to the database.
            stmt.executeUpdate();

            // Retrieve the generated film_id
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int newId = keys.getInt(1);
                    category.setCategoryId(newId); // Set the generated ID on the Film object
                }
            }


        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        return category;
    }

}
