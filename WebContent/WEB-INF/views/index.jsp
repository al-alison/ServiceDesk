<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Chamados - Login</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Barra superior com os menus de navegação -->
    <c:import url="Menu.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
    	<br><br>
        <div class="jumbotron">
            <h1> Sistema de Chamados - Login </h1>
             <form action="tela_principal" method="post">
            	<div class="row">
            	<div class="form-group col-md-4">
            		<label for="username">Nome de Usuário:</label>
            		<input type="text" name="username" class="form-control" id="usename">
            		<br>
            		<label for="password">Senha:</label>
            		<input type="password" name="password" class="form-control" id="password">
            	</div>
            	</div>
 				<div id="actions" class="row">
            		<div class="col-md-12">
            			<button type="submit" class="btn btn-primary" >Realizar login</button>
                	</div>
            	</div>
        	</form>
        	<br>
            <span class="glyphicon glyphicon-copyright-mark" aria-hidden="true"></span> USJT - 2018
        </div>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>