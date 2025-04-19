<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Register</title></head>
<body>
<form method="post" action="${pageContext.request.contextPath}/api/auth/register">
    Username: <input type="text" name="username"/><br/>
    Email:    <input type="text" name="email"/><br/>
    Password: <input type="password" name="password"/><br/>
    <button type="submit">Sign up</button>
</form>
<a href="${pageContext.request.contextPath}/login">Back to login</a>
</body>
</html>
