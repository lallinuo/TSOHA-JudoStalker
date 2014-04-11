<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <title>Home</title>
    </head>
<link href='http://fonts.googleapis.com/css?family=Exo:500' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" type="text/css" /> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pure-min.css" type="text/css" /> 
    <script src= "${pageContext.request.contextPath}/static/js/angular.min.js"></script>
    <script src= "${pageContext.request.contextPath}/static/js/angular-ui-router.min.js"></script>
    <script src= "${pageContext.request.contextPath}/static/js/angular-resource.min.js"></script>
    <script src= "${pageContext.request.contextPath}/static/js/code.js"></script>
    <script src= "${pageContext.request.contextPath}/static/js/router.js"></script>
    <script src= "${pageContext.request.contextPath}/static/js/factorys.js"></script>

    <meta charset="UTF-8"/>



    <body ng-app="judoStalker">
        <div ui-view="nav"></div>
        <div ui-view="content"></div>
    </body>
</html>
