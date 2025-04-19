<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Login</title></head>
<body>
<c:if test="${param.error != null}">
    <p style="color:red">Invalid email or password</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/api/auth/login">
    Email:    <input type="text" name="email"/><br/>
    Password: <input type="password" name="password"/><br/>
    <button type="submit">Login</button>
</form>

<a href="${pageContext.request.contextPath}/register">Sign up</a>
</body>
</html>
