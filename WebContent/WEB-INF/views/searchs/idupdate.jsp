<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../layout/op_app.jsp">
<c:param name="content">
<c:if test="${errors != null}">
    <div id="flush_error">
    使用されてない商談Idを使用しています。<br />
    <c:forEach var="error" items="${errors}">
        <c:out value="${error}" /><br />
    </c:forEach>

    </div>
 </c:if>

<h2>商談Id照合画面</h2>
<form method="POST" action="<c:url value='/search/idupdate' />">
 <label for="search_id">商談Id</label><br />
 <input type="text" name="search_id" value="${search_id}" />
 <br /><br />

 <input type="hidden" name="_token" value="${_token}" />
 <button type="submit">照合</button>
 </form>
 </c:param>
 </c:import>