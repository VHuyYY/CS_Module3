package service;

import configuration.ConnectDataBase;
import entity.Category;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService<Product> {
    private Connection connection;

    public ProductService() {
        this.connection = ConnectDataBase.getConnection();
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public void delete(int id) {
        String sql = "delete from product where id = ?;";
        try {
          PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Product product) {

    }

    public List<Category> getAllCategori() {
        List<Category> categories = new ArrayList<>();
        String sql = "select * from Category";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public List<Product> getAll() {
        String sql = "select product.*, c.name as nameCategory from product join category c on c.id = product.category_id";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String img = resultSet.getString("img");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String nameCategory = resultSet.getString("nameCategory");
                Category category = new Category(category_id, nameCategory);
                Product p = new Product(id, name, img, price, description, category);
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Product findById(int id) {
        return null;
    }
}