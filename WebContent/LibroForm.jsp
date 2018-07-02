<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Aplicacion Biblioteca</title>
</head>
<body>
	<center>
		<h1>Administración de libros</h1>
        <h2>
        	<a href="nuevo">Agregar nuevo libro</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="listar">Listar todos los libros</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${libro != null}">
			<form action="actualizar" method="post">
                </c:if>
                <c:if test="${libro == null}">
                        <form action="insertar" method="post">
                </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${libro != null}">
            			Editar Libro
            		</c:if>
            		<c:if test="${libro == null}">
            			Agregar nuevo libro
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${libro != null}">
        			<input type="hidden" name="id" value="<c:out value='${libro.id}'/>"                              
        		</c:if>            
            <tr>
                <th>Titulo: </th>
                <td>
                	<input type="text" name="titulo" size="45" placeholder="Ingrese el titulo"
                			value="<c:out value='${libro.titulo}' />"
                	required />
                </td>
            </tr>
            <tr>
                <th>Autor: </th>
                <td>
                	<input type="text" name="autor" size="45"  placeholder="Ingrese el autor"
                			value="<c:out value='${libro.autor}' />"
                	required />
                </td>
            </tr>
            <tr>
                <th>Precio: </th>
                <td>
                    
                	<input type="number" size="5" name="precio" placeholder="Ingrese el precio"
                			value="<c:out value='${libro.precio}' />"
                	required />
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Guardar" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
