<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css"></link>
</head>
<body>
<div id="maintop">
    <div id="mainlogo">
        <img id="logo" src="${pageContext.request.contextPath}/resources/img/img_logo.png">
    </div>
    <div id="mainnav">
        <div id="navigation">
            <div>
                <a href="${pageContext.request.contextPath}/main/dynamic" target="maincontent">动态</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/diary/diaryList" target="maincontent">日志</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/photo/photoList" target="maincontent">相册</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/main/friend" target="maincontent">好友</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
