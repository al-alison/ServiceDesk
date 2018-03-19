
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Lista de Chamados</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    
</head>

<body>
    <!-- Barra superior com os menus de navegação -->
    <c:import url="Menu.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
       <div class="container">
		<div class="row centered mt mb">
			<div class="col-lg-8 col-lg-offset-2">
            <div class="cadastro">
            <h1>Chamados</h1>		
            <table class ="table table-condensed" border ="1">
                <TR>
                    <td>Descrição</td>
                    <td>Status</td>
                    <td>Data de abertura</td>
                    <td>Data de fechamento</td>
                </tr>
               <c:forEach var="chamado" items="${chamados}">
            	<tr style="font-size: 10">
                	<td>${chamado.descricao}</td>
                	<td>${chamado.status}</td>
                	<td>${chamado.dt_abertura}</td>
                	<td>${chamado.dt_fechamento}</td>
            	</tr>
            </c:forEach>
            </table>
            </div>
			</div>

		</div>
	</div>
</div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>