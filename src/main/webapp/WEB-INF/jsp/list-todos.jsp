<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <title>First Web Application</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-7">
            <spring:message code="todo.message.list" />
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>id</th>
                    <th>user</th>
                    <th>desc</th>
                    <th>targetDate</th>
                    <th>isDone</th>
                    <th>country</th>
                    <th>edit</th>
                    <th>delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${todos}" var="todo" varStatus="status">
                    <tr>
                        <td>${todo.id}</td>
                        <td>${todo.user}</td>
                        <td>${todo.desc}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.done}</td>
                        <td>${todo.country.name}</td>
                        <td><a href="<spring:url value="/update-todo/${todo.id}" />" class="btn btn-info btn-xs"><i class="glyphicon glyphicon-check"></i> <spring:message code="todo.edit" /></a></td>
                        <td><a href="<spring:url value="/delete-todo/${todo.id}" />" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-trash"></i><spring:message code="todo.delete" /></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <BR/>
            <spring:message code="your.name" /> ${name}
            <BR/>
            <a href="<spring:url value="/create-todo" />" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i><spring:message code="todo.create" /></a>
        </div>
    </div>
</div>
</body>

</html>