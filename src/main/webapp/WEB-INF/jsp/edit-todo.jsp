<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    <div class="row-fluid">
        <div class="col-md-6">

            <form:form method="post" action="/edit-todo-save" modelAttribute="todo">
                <div>
                    <form:hidden path="id" />
                </div>
                <div>
                    <form:hidden path="user" />
                </div>
                <div class="form-group">
                    <form:label path="desc"><spring:message code="todo.desc" /></form:label>
                    <form:textarea path="desc" cols="60" class="form-control"/>
                    <form:errors path="desc" cssClass="error" />
                </div>
                <div class="form-group">
                    <form:label path="targetDate"><spring:message code="todo.targetDate" /></form:label>
                    <form:input path="targetDate" type="date" class="form-control"/>
                    <form:errors path="targetDate" cssClass="error" />
                </div>

                <div class="form-group">
                    <form:label path="done"><spring:message code="todo.done" /></form:label>
                    <form:checkbox path="done" class="checkbox" />
                    <form:errors path="done" />
                </div>
                <div class="form-group">
                    <input type="submit" value="Save" class="btn btn-success"/>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>