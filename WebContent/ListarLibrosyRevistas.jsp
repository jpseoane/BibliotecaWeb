<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Aplicacion Biblioteca</title>
</head>
<body>
	<center>
		<h1>Administración de libros</h1>
        <h2>
        	<a href="nuevo">Agregar nuevo Libro</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="listar">Listar libros</a>
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de libros</h2></caption>
            <tr>
                <th>ID</th>
                <th>Titulo</th>
                <th>Autor</th>
                <th>Precio</th>
                <th>Acciones</th>
            </tr>
            <c:forEach var="libro" items="${listaLibros}">
                <tr>
                    <td><c:out value="${libro.id}" /></td>
                    <td><c:out value="${libro.titulo}" /></td>
                    <td><c:out value="${libro.autor}" /></td>
                    <td><c:out value="${libro.precio}" /></td>
                    <td>
                    	<a href="editar?id=<c:out value='${libro.id}' />">Editar</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="borrar?id=<c:out value='${libro.id}' />">Borrar</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Revistas</h2></caption>
            <tr>
                <th>ID</th>
                <th>Editorial</th>
                <th>Es Comic</th>
                <th>SuperHeroe</th>                
            </tr>
            <c:forEach var="revista" items="${listaRevistas}">
                <tr>
                    <td><c:out value="${revista.id}" /></td>
                    <td><c:out value="${revista.editorial}" /></td>
                    <td><c:out value="${revista.escomic}" /></td>
                    <td><c:out value="${revista.superheroe}" /></td>
                    <td>
                    	<a href="editar?id=<c:out value='${revista.id}' />">Editar</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="borrar?id=<c:out value='${revista.id}' />">Borrar</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
