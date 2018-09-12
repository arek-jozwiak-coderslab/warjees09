<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- Declaration of page, url and size tag attributes --%>

<%-- The page attribute of type org.springframework.data.domain.Page --%>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page" %>

<%-- The url path before the page request parameter  (url?p=1) --%>
<%@ attribute name="url" required="true" %>

<%--Number of page numbers to display at once --%>
<%@ attribute name="size" required="false" %>

<%-- Declaration of the default size value --%>
<c:set var="size" value="${empty size ? 10 : size}"/>

<%-- half_size_floor = floor(size/2)  is used to display the current page in the middle --%>
<c:set var="N" value="${size/2}"/>
<c:set var="half_size_floor">
    <fmt:formatNumber value="${N-(1-(N%1))%1}" type="number" pattern="#"/>
</c:set>

<%-- current variable stands for the current page number  --%>
<c:set var="current" value="${page.number+1}"/>

<c:set var="startPage" value="${current < half_size_floor + 1 ? 1 : current - half_size_floor }"/>
<c:set var="startPage"
       value="${current > page.totalPages - half_size_floor ? page.totalPages - size + 1 : startPage }"/>
<c:set var="endPage" value="${startPage+size-1}"/>
<c:set var="endPage" value="${endPage > page.totalPages ? page.totalPages : endPage}"/>

<%--less pages then the size of the block --%>
<c:set var="startPage" value="${page.totalPages < size ? 1 : startPage}"/>
<c:set var="endPage" value="${page.totalPages < size ? page.totalPages : endPage}"/>

<%-- PAGE NAVIGATION LINKS --%>
<div>

    <%-- Previous link --%>
    <c:if test="${current > half_size_floor + 1 and page.totalPages > size}">
        <a href="${url}?p=${current-1}">&lt;prev</a>
    </c:if>

    <%-- Numerated page links --%>
    <c:forEach var="pageNumber" begin="${startPage}" end="${endPage}">
        <c:choose>
            <c:when test="${current != pageNumber}">
                <a href="${url}?p=${pageNumber}"><c:out value="${pageNumber}"/></a>
            </c:when>
            <c:otherwise>
                <a class="current" href="${url}?p=${pageNumber}"><c:out value="${pageNumber}"/></a>
            </c:otherwise>
        </c:choose>
        <c:if test="${pageNumber != endPage}">|</c:if>
    </c:forEach>

    <%-- Next link --%>
    <c:if test="${current < page.totalPages - half_size_floor + (1-size%2) and page.totalPages > size}">
        <a href="${url}?p=${current+1}">next&gt;</a>
    </c:if>
</div>