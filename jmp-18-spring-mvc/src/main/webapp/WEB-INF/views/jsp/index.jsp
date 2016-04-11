<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<title>Users</title>

Language : <a href="?language=en">English</a>|<a href="?language=ru">Русский</a><br>
Current locale: ${pageContext.response.locale}<br>

<button onclick="getUsers()"><spring:message code="users.get" text="Get Users"/></button>
<div class="users"></div>

<spring:url value="/resources/core/js/script.js" var="script"/>
<spring:url value="/resources/core/js/lib/jquery.js" var="jQuery"/>
<spring:url value="/resources/core/js/lib/handlebars.js" var="handlebars"/>

<script src="${jQuery}"></script>
<script src="${handlebars}"></script>
<script src="${script}"></script>

<script id="user" type="text/x-handlerbars-template">
    <tr class="user_{{id}}">
        <td>{{id}}</td>
        <td class="firstname">{{firstName}}</td>
        <td class="lastname">{{lastName}}</td>
        <td>
            <button onclick="deleteUser({{id}})">Delete user</button>
        </td>
    </tr>
</script>

<script id="users" type="text/x-handlerbars-template">
    <table border="1" id="user-table">
        <tr>
            <th>Id</th>
            <th>First name</th>
            <th>Last name</th>
        </tr>
        {{#this}}
        <tr class="user_{{id}}">
            <td>{{id}}</td>
            <td class="firstname">{{firstName}}</td>
            <td class="lastname">{{lastName}}</td>
            <td>
                <button onclick="deleteUser({{id}})">Delete user</button>
            </td>
        </tr>
        {{/this}}
    </table>

    First name:<br>
    <input id="firstname" type="text" name="firstname"><br>
    Last name:<br>
    <input id="lastname" type="text" name="lastname"><br>
    <button onclick="addUser()">Add user</button>

    <br>
    User id:<br>
    <input id="id" type="text" name="id"><br>
    First name:<br>
    <input id="firstname_edit" type="text" name="firstname"><br>
    Last name:<br>
    <input id="lastname_edit" type="text" name="lastname"><br>
    <button onclick="updateUser()">Edit user</button>
    <br>
    <div class="error"></div>
</script>

</body>
</html>