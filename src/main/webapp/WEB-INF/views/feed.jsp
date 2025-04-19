<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head><title>Feed</title></head>
<body>
<h2>All Posts (第 ${page + 1} 页)</h2>

<c:forEach items="${posts}" var="p">
    <div style="border:1px solid #ccc;margin:8px;padding:8px">
        <h3>
            <a href="${pageContext.request.contextPath}/posts/${p.id}">
                    ${p.title}
            </a>
        </h3>
        <p>${p.description}</p>
        <small>${p.category} | ${p.createdAt}</small>
    </div>
</c:forEach>

<c:if test="${fn:length(posts) == size}">
    <c:url var="nextUrl" value="/feed">
        <c:param name="page" value="${page + 1}" />
        <c:param name="size" value="${size}" />
    </c:url>
    <a href="${pageContext.request.contextPath}${nextUrl}">
        加载更多…
    </a>
</c:if>

<br/><br/>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>
