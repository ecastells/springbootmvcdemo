<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <title>First Web Application</title>
</head>

<body>
    <spring:message code="welcome" /> ${name}!! <a href="/list-todos">Click here</a> to manage your todo's.
</body>

</html>