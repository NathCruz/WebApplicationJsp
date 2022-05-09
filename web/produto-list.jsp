<%-- 
    Document   : index
    Created on : 6 de mai. de 2022, 22:19:07
    Author     : nathi
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP CRUD Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #28a745">
                <div>
                    <a href="#" class="navbar-brand">Aplicativo de gerenciamento de Produtos </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Produtos</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4">Gerencie seus produtos</h1>
                <p class="lead">Esse sistema ajuda de forma intuitiva a manter o controle dos produtos de sua loja</p>
            </div>
        </div>
        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">Lista de produtos</h3>
                <hr>
                <div class="container text-left">

                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Adicionar novo produto</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Valor</th>
                            <th>Descrição</th>
                            <th>Desconto</th>
                            <th>Valor após desconto</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!--   for (Todo todo: todos) {  -->
                        <c:forEach var="produto" items="${listProduto}">

                            <tr>
                                <td>
                                    <c:out value="${produto.id}" />
                                </td>
                                <td>
                                    <c:out value="${produto.nome}" />
                                </td>
                                <td>
                                    <c:out value="${produto.valor}" />
                                </td>
                                <td>
                                    <c:out value="${produto.descricao}" />
                                </td>
                                <td>
                                    <c:out value="${produto.desconto}"/>
                                </td>
                                <td><c:out value="${produto.valorPosDesconto}" />
                                </td>
                                <td><a href="edit?id=<c:out value='${produto.id}' />">Editar</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${produto.id}' />">Apagar</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>
    </body>
</html>
