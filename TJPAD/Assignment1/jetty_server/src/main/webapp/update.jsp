<%@ page import="ctrl.CarsController" %>
<%@ page import="repo.Repository" %>
<%@ page import="domain.Car" %><%--
  Created by IntelliJ IDEA.
  User: Diana
  Date: 1/4/2022
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! CarsController ctrl;
%>
<%
//    System.out.println("ctrl" + ctrl);
    ctrl = (CarsController) session.getAttribute("ctrl");
    int carId = Integer.parseInt(request.getParameter("carId"));
    Car car = ctrl.getCarById(carId);
%>
<html>
<head>
    <title>Update car info</title>
</head>
<body>
    <form action="ServletDispatcher/" method="POST">
    <input type="hidden" name="action" value="confirmUpdate">
    <input type="hidden" name="carId" value="<%=car.getId()%>">
    Numar sasiu: <input readonly type="text" name="code" value="<%=car.getCode()%>">
    Brand: <input readonly type="text" name="brand" value="<%=car.getBrand()%>">
    An Fabricatie: <input readonly type="text" name="year" value="<%=car.getYear()%>">
    Culoare: <input type="text" name="color" value="<%=car.getColor()%>">
    <input type="submit" value="Modifica">
    </form>
</body>
</html>
