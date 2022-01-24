<%@ page import="ctrl.CarsController" %>
<%@ page import="repo.Repository" %>
<%@ page import="domain.Car" %><%--
  Created by IntelliJ IDEA.
  User: Diana
  Date: 12/7/2021
  Time: 6:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! CarsController ctrl;%>
<% if(session.getAttribute("ctrl")==null){
    ctrl = new CarsController(new Repository());
    session.setAttribute("ctrl",ctrl);
}
else{
  ctrl = (CarsController) session.getAttribute("ctrl");
  session.setAttribute("ctrl",ctrl);}
System.out.println("sdlkfjhsaofjasd" + ctrl);
%>
<html>
  <head>
    <title>$Title$</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="js/utils.js"></script>
  </head>
  <body>

  <button id="add" style="margin-bottom: 20px">Adauga masina</button>
  <br/>
  <table>
  <%
    for (Car c : ctrl.getAll()) {
  %>

  <tr style="display: block; margin-bottom: 20px; border: 1px solid grey; border-radius: 3px;">
    <form action="ServletDispatcher/" method="GET">
      <input type="hidden" name="action" value="update">
      <input type="hidden" name="id" value="<%=c.getId()%>">
      <td>
        Numar sasiu: <input type="text" name="code" value="<%=c.getCode()%>" readonly></td>
      <td>An fabricatie: <input type="text" name="year" value="<%=c.getYear()%>" readonly></td>
      <td>Brand: <input type="text" name="brand" value="<%=c.getBrand()%>" readonly></td>
      <td>Culoare: <input type="text" name="color" value="<%=c.getColor()%>" readonly></td>
      <td><input type="submit" name="updateBtn" value="Actualizeaza"></td>
    </form>
    <form action="ServletDispatcher/" method="GET">
      <input type="hidden" name="action" value="delete">
      <input type="hidden" name="id" value="<%=c.getId()%>">
      <td><input type="submit" name="deleteBtn" value="Sterge"></td>
    </form>
  </tr>

  <%
    }
  %>
  </table>
  <script>
    $("#add").on("click", goAdd);
  </script>
  </body>
</html>
