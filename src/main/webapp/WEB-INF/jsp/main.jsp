<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>FashionLife空间</title>
</head>

<frameset rows="18%,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=request.getContextPath()%>/user/maintop.jsp" name="maintop" scrolling="no" noresize="noresize" id="maintop"/>
    <frameset cols="15%,18%,52%,15%" frameborder="no" border="0" framespacing="0">
        <frame src="<%=request.getContextPath()%>/user/mainempty.jsp" name="leftempty" scrolling="no" noresize="noresize" id="leftempty"/>
        <frame src="<%=request.getContextPath()%>/user/mainleft.jsp" name="mainleft" scrolling="no" noresize="noresize" id="mainleft"/>
        <frame name="maincontent" id="maincontent" target="maincontent" scrolling="yes" noresize="noresize"/>
        <frame src="<%=request.getContextPath()%>/user/mainempty.jsp" name="rightempty" scrolling="no" noresize="noresize" id="rightempty"/>
    </frameset>
</frameset>

</html>
