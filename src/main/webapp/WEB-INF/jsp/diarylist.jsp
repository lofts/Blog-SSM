<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/14
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/diarylist.css">
</head>

<body>
<div id="diarymain">
    <div id="headdiv">
        <a id="write" href="${pageContext.request.contextPath}/diary/writeDiary">写日志</a>
    </div>
    <hr class="headline">
    <div>
        <ul>
            <c:forEach items="${diarylist}" var="diary">
                <li>
                    <div>
                        <a href="${pageContext.request.contextPath}/diary/queryDiary?id=${diary.id}">${diary.title}</a>
                        <span class="diarytype">${diary.type}</span><span class="diarydate">${diary.createdate}</span>
                    </div>
                </li>
                <hr class="listline">
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
