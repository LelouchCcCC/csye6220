<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head><title>Post Detail</title></head>
<body>
<h2>${post.title}</h2>
<p>${post.description}</p>
<small>
  分类：${post.category} |
  作者 ID：${post.userId} |
  创建时间：${post.createdAt}
</small>

<h3>Comments</h3>
<!-- 从 level=0 开始，任意深度都会被 comment.tag 处理 -->
<t:comment comments="${comments}" level="0" />

<br/>
<a href="${pageContext.request.contextPath}/feed">返回列表</a>
</body>
</html>
