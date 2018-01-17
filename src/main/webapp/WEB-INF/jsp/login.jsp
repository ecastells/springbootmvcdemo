<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <title>First Web Application</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        .error {
            color: red;
        }
    </style>
</head>

<body>

<div class="container-fluid">
    <form method="post">
        <br/>
        <spring:message code="name" /> <input type="text" name="name" />
        <spring:message code="password" /> <input type="password" name="password" />
        <br/>
        <input type="submit" value="Login" class="btn btn-success" />
    </form>
    <label class="">${errorMessage}</label>
</div>
</body>

</html>