<html>

<head>
    <title>SPA REST</title>
    <script src="assets/script/lib/jquery.js"></script>
    <script src="assets/script/lib/handlebars.js"></script>
    <script src="assets/script/script.js"></script>
</head>

<body>
<button onclick="getUsers()">Get users</button>
<div class="users"></div>
</body>

<script id="user" type="text/x-handlerbars-template">
    <tr>
        <td>{{id}}</td>
        <td>{{firstName}}</td>
        <td>{{lastName}}</td>
        <td><button onclick="deleteUser({{id}})">Delete user</button></td>
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
        <tr>
            <td>{{id}}</td>
            <td>{{firstName}}</td>
            <td>{{lastName}}</td>
            <td><button onclick="deleteUser({{id}})">Delete user</button></td>
        </tr>
        {{/this}}
    </table>

    First name:<br>
    <input class="firstname" type="text" name="firstname"><br>
    Last name:<br>
    <input class="lastname" type="text" name="lastname"><br>
    <button onclick="addUser()">Add user</button>
</script>

</html>