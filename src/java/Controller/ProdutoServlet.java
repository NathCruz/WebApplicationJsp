/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author nathi
 */

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Produto;
import DAO.ProdutoDAO;
import javax.servlet.annotation.WebServlet;

@WebServlet("/")
public class ProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProdutoDAO produtoDAO;
    
    public void init() {
        produtoDAO = new ProdutoDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
        
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertProduto(request, response);
                    break;
                case "/delete":
                    deleteProduto(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateProduto(request, response);
                    break;
                default:
                    listProduto(request, response);
                    break;
            }
            } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProduto(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List<Produto> listProduto = produtoDAO.selectAllProdutos();
        request.setAttribute("listProduto", listProduto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("produto-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("produto-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto existingProduto = produtoDAO.selectProduto(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("produto-form.jsp");
        request.setAttribute("produto", existingProduto);
        dispatcher.forward(request, response);

    }
    
    private void insertProduto(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String nome = request.getParameter("nome");
        Double valor = Double.parseDouble(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");
        Double desconto = Double.parseDouble(request.getParameter("desconto"));
        Produto newProduto = new Produto(nome, valor, descricao, desconto);
        produtoDAO.insertProduto(newProduto);
        response.sendRedirect("list");
    }

    private void updateProduto(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        Double valor = Double.parseDouble(request.getParameter("valor"));
        String descricao = request.getParameter("descricao");
        Double desconto = Double.parseDouble(request.getParameter("desconto"));
        
        Produto book = new Produto(id, nome, valor, descricao, desconto);
        produtoDAO.updateProduto(book);
        response.sendRedirect("list");
    }

    private void deleteProduto(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        produtoDAO.deleteProduto(id);
        response.sendRedirect("list");

    }
}
