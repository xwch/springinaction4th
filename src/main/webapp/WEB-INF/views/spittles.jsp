<%--
  Created by IntelliJ IDEA.
  User: xwch
  Date: 2017/10/22
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Spitters</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/style.css"/> ">
</head>
<body>

    <%--新增--%>
    <div class="spittleForm">
        <h1>Spit it out...</h1>
        <form name="spittleForm" method="post">
            <input type="hidden" name="latitude">
            <input type="hidden" name="longitude">
            <textarea name="message" cols="80" rows="5"></textarea><br/>
            <input type="submit" value="Add" />
        </form>
    </div>

    <div class="listTitle">
        <h1>Recent Spittles</h1>
        <ul class="spittleList">
            <c:forEach items="${spittleList}" var="spittle">
                <li id="spittle_<c:out value="spittle.id"/> ">
                    <div class="spittleMessage"><c:out value="${spittle.message}"/></div>
                    <div>
                        <span class="spittleTime"><c:out value="${spittle.time}" /></span>
                        <span class="spittleLocation">
                            (<c:out value="${spittle.latitude}"/>,
                            <c:out value="${spittle.longitude}"/>) </span>

                    </div>

                </li>
            </c:forEach>
        </ul>
    </div>

</body>
</html>
