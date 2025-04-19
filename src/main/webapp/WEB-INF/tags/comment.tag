<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="comments" type="java.util.List" required="true" %>
<%@ attribute name="level"    type="java.lang.Integer" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${comments}" var="cmt">
    <div style="
            margin-left: ${level * 20}px;
            border:1px solid #ddd;
            padding:5px;
            margin:5px;">
        <strong>User ${cmt.userId}：</strong> ${cmt.content}<br/>
        <small>${cmt.createdAt}</small>
    </div>
    <!-- 如果有子评论，递归调用自己 -->
    <c:if test="${not empty cmt.replies}">
        <t:comment
                comments="${cmt.replies}"
                level="${level + 1}" />
    </c:if>
</c:forEach>
