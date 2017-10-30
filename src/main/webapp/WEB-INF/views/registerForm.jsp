<%--
  Created by IntelliJ IDEA.
  User: xwch
  Date: 2017/10/22
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>注册页面</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/static/css/style.css"/>">
</head>
<body>
<h1>Register</h1>
<%--
<form method="post">
    First Name:<input type="text" name="firstName" /><br/>
    Last  Name:<input type="text" name="lastName" /><br/>
    Eamil:<input type="email" name="email" /><br/>
    UserName:<input type="text" name="username" /><br/>
    PassWord:<input type="password" name="password" /><br/>
    <input type="submit" value="Register" />
</form>--%>

<sf:form commandName="spitter" method="post">
    First Name:<sf:input path="firstName"/>
    <sf:errors path="firstName" cssClass="error"/><br/>

    Last  Name:<sf:input path="lastName"/>
    <sf:errors path="lastName" cssClass="error"/><br/>

    Eamil:<sf:input path="email"/>
    <sf:errors path="email" cssClass="error"/><br/>

    UserName:<sf:input path="username"/>
    <sf:errors path="username" cssClass="error"/><br/>

    PassWord:<sf:input path="password"/>
    <sf:errors path="password" cssClass="error"/><br/>

    <input type="submit" value="Register" />
</sf:form>

</body>
</html>
