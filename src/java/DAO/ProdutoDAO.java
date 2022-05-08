/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author nathi
 */
import Model.Produto;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/dbequals?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_PRODUTOS_SQL = "INSERT INTO produtos" + "  (nome, valor, descricao, desconto, valorPosDesconto) VALUES "
            + " (?, ?, ?, ?, ?);";

    private static final String SELECT_PRODUTO_BY_ID = "select id,nome,valor,descricao,desconto,valorPosDesconto from produtos where id =?";
    private static final String SELECT_ALL_PRODUTOS = "select * from produtos";
    private static final String DELETE_PRODUTOS_SQL = "delete from produtos where id = ?;";
    private static final String UPDATE_PRODUTOS_SQL = "update produtos set nome = ?,valor= ?, descricao= ?, desconto= ?, valorPosDesconto= ? where id= ?;";

    public ProdutoDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        } catch (SQLException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertProduto(Produto produto) throws SQLException {
        System.out.println(INSERT_PRODUTOS_SQL);
        // try-with-resource statement will auto close the connection.
        try (
            Connection connection = getConnection();  
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUTOS_SQL)) {
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setDouble(2, produto.getValor());
            preparedStatement.setString(3, produto.getDescricao());
            preparedStatement.setDouble(4, produto.getDesconto());
            preparedStatement.setDouble(5, produto.getValorPosDesconto());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Produto selectProduto(int id) {
        Produto produto = null;
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUTO_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String nome = rs.getString("nome");
                Double valor = rs.getDouble("valor");
                String descricao = rs.getString("descricao");
                Double desconto = rs.getDouble("desconto");
                Double valorPosDesconto = rs.getDouble("valorPosDesconto");
                produto = new Produto(id, nome, valor, descricao, desconto);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return produto;
    }

    public List< Produto> selectAllProdutos() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List< Produto> produtos = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUTOS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Double valor = rs.getDouble("valor");
                String descricao = rs.getString("descricao");
                Double desconto = rs.getDouble("desconto");
                Double valorPosDesconto = rs.getDouble("valorPosDesconto");
                produtos.add(new Produto(id, nome, valor, descricao, desconto, valorPosDesconto));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return produtos;
    }

    public boolean deleteProduto(int id) throws SQLException {
        boolean rowDeleted;
        try ( Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(DELETE_PRODUTOS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateProduto(Produto produto) throws SQLException {
        boolean rowUpdated;
        try ( Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUTOS_SQL);) {
            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getValor());
            statement.setString(3, produto.getDescricao());
            statement.setDouble(4, produto.getDesconto());
            statement.setDouble(5, produto.getValorPosDesconto());
            statement.setInt(6, produto.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }

        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
