<%-- 
    Document   : produto-form
    Created on : 7 de mai. de 2022, 00:15:53
    Author     : nathi
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aplicativo de gerenciamento de Produtos</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #28a745">
                <div>
                    <a href="index" class="navbar-brand">Aplicativo de gerenciamento de Produtos </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Produtos</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${produto != null}">
                        <form action="update" method="post">
                        </c:if>
                        <c:if test="${produto == null}">
                            <form action="insert" method="post">
                            </c:if>
                            <caption>
                                <h2>
                                    <c:if test="${produto != null}">
                                        Editar produto
                                    </c:if>
                                    <c:if test="${produto == null}">
                                        Adicionar um novo produto
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${produto != null}">
                                <input type="hidden" name="id" value="<c:out value='${produto.id}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>Nome do produto</label> <input type="text" value="<c:out value='${produto.nome}' />" class="form-control" name="nome" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Valor do produto</label> <input type="number" step=".01" placeholder="0.00" value="<c:out value='${produto.valor}' />" class="form-control" name="valor">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Descrição do produto</label> <input type="text" value="<c:out value='${produto.descricao}' />" class="form-control" name="descricao">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Desconto do produto</label> <input type="number" placeholder="0.0" value="<c:out value='${produto.desconto}' />" class="form-control" name="desconto">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Valor Após Desconto</label> <input id="mySelect" type="number" disabled value="<c:out value='${produto.valorPosDesconto}'/>" class="form-control" name="valorPosDesconto">11
                            </fieldset>

                            <button type="submit" class="btn btn-success">Salvar</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
