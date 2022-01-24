<%@ page import="domain.Car" %>
<%@ page import="ctrl.CarsController" %><%--
  Created by IntelliJ IDEA.
  User: Diana
  Date: 1/4/2022
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%! CarsController ctrl;
%>
<%
    ctrl = (CarsController) session.getAttribute("ctrl");
    System.out.println("-------- "  + ctrl.getAll().size());
//    Car car = ctrl.getCarById(carId);
//    System.out.println("===" + car);
%>
<html>
<head>
    <title>Adauga masina</title>
</head>
<body>
<form action="ServletDispatcher/" method="POST">
    <input type="hidden" name="action" value="confirmAdd">
    <input type="hidden" name="id" value="-1">
    Numar sasiu: <input type="text" name="code">
    Brand: <input type="text" name="brand">
    An Fabricatie: <input type="text" name="year">
    Culoare: <input type="text" name="color">
    <input type="submit" value="Adauga">
</form>
</body>
</html>

