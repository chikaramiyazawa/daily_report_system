<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../layout/app.jsp">
     <c:param name="content">

   <c:if test="${hasError}">
    <div id="flush_error">
   氏名が未入力です。<br />
    </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>

            </div>
        </c:if>

 <h2>氏名検索画面</h2>
 <form method="POST" action= "<c:url value='/reports/employee/search'/>">
 <label for="name">氏名</label><br />
 <input type="text" name="name" value="${search_employee}"/>
 <br /><br />

 <input type="hidden" name="_token" value="${_token}" />
 <button type="submit">検索</button>
 </form>

 <a href="<c:url value="/reports/index" />">日報一覧に戻る</a>
    </c:param>
 </c:import>