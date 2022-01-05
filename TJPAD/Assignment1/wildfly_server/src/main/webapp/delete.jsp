<%@ page import="ctrl.CarsController" %>
<%@ page import="domain.Car" %><%--
  Created by IntelliJ IDEA.
  User: Diana
  Date: 1/5/2022
  Time: 1:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! CarsController ctrl;
%>
<%
    ctrl = (CarsController) session.getAttribute("ctrl");
    int carId = Integer.parseInt(request.getParameter("carId"));
    System.out.println("CAR ID:::: " + carId);
    Car car = ctrl.getCarById(carId);
    System.out.println("CAR in delete:: "+ car);
%>
<html>
<head>
    <title>Update car info</title>
</head>
<body>
<form action="ServletDispatcher/" method="POST">
    <input type="hidden" name="action" value="confirmDelete">
    <input type="hidden" name="carId" value="<%=car.getId()%>">
    Numar sasiu: <input readonly type="text" name="code" value="<%=car.getCode()%>">
    Brand: <input readonly type="text" name="brand" value="<%=car.getBrand()%>">
    An Fabricatie: <input readonly type="text" name="year" value="<%=car.getYear()%>">
    Culoare: <input readonly type="text" name="color" value="<%=car.getColor()%>">
    <input type="submit" value="Sterge">
</form>
</body>
</html>
