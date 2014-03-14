<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <title>Home</title>
    </head>
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
    <div id ="main" ng-controller="loginCtrl">

        <div id="loginform">
            <form class="pure-form" ng-submit="submit()" ng-show="!kirjautuminen" >
                <fieldset class="pure-group">
                    <input type="text" ng-model="login.kayttajanimi" class="pure-input-1-2" placeholder="Käyttäjänimi">
                    <input type="password" ng-model="login.salasana" class="pure-input-1-2" placeholder="Salasana">
                </fieldset>
                <button type="submit" class="pure-button pure-input-1-2 pure-button-primary">Sign in</button>
                <p class="error">{{error}}</p>
            </form>
        </div>

        <div id="userinfo" ng-show="kirjautuminen">
            <a href="/kayttaja/{{kayttaja.id}}">{{kayttaja.kayttajanimi}}</a>
            <a href="#" ng-click="logout()">Kirjaudu ulos</a>
            <a ui-sref="kayttajat">Käyttäjät</a>
            <a ui-sref="judokat">Judokat</a>
            <a ui-sref="tekniikat">Tekniikat</a>
        </div>
        <div ng-show="kirjautuminen != null">
            <div ui-view></div>
        </div>

    </div>

</body>
</html>
