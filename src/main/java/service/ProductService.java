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
        String sql = "insert into product (id, name, img, price, description, category_id,account_id) values (?, ?, ?, ?, ? , ?, 1);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getImg());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategoryId().getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        String sql = "update product SET name = ? , img = ? , price = ? , description = ?, category_id = ?  WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getImg());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setInt(5, product.getCategoryId().getId());
            preparedStatement.setInt(6, id);
            int rowEffect = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllCategori(int id) {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product WHERE category_id =? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ids = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String img = resultSet.getString("img");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String nameCategory = resultSet.getString("name");
                Category category = new Category(category_id, nameCategory);

                Product p = new Product(ids, name, img, price, description, category);
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        String sql = "select * from category";
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
        String sql = "SELECT p.id, p.name, p.img, p.price, p.description, p.category_id, c.name\n" +
                "FROM product p\n" +
                "JOIN category c ON p.category_id = c.id\n" +
                "WHERE p.id = ?;\n";
        Product p = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String img = resultSet.getString("img");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String nameCategory = resultSet.getString("name");
                Category category = new Category(category_id, nameCategory);
                return new Product(id, name, img, price, description, category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public List<Product> findByName(String name, double price) {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product where LOWER (name) like LOWER (?) or  price <=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setDouble(2, price);
            System.out.println(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("category_id"), resultSet.getString("name"));
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("img"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        category
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(products);
        return products;
    }
}
