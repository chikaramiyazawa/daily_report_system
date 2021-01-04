<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${errors != null}">
    <div id="flush_error">
    入力内容にエラーがあります。<br />
    <c:forEach var="error" items="${errors}">
        ・<c:out value="${error}" /><br />
    </c:forEach>

    </div>
 </c:if>

 <label for="search_id">商談Id</label><br />
 <input type="hidden" name="search_id" value="${sessionScope.use.search_id}" />
 <c:out value="${sessionScope.use.search_id}" />
 <br /><br />

 <input type="hidden" name="_reset" value="${_reset}" />
 <button type="submit">中断</button>