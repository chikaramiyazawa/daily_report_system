<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../layout/op_app.jsp">
     <c:param name="content">

   <c:if test="${hasError}">
    <div id="flush_error">
   商談Idが未入力です。<br />
    </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>

            </div>
        </c:if>

 <h2>商談Id検索画面</h2>
 <form method="POST" action= "<c:url value='/opportunity/op_id/search'/>">
 <label for="op_id">商談Id</label><br />
 <input type="text" name="op_id" value="${opportunity.op_id}"/>
 <br /><br />

 <input type="hidden" name="_token" value="${_token}" />
 <button type="submit">検索</button>
 </form>

 <a href="<c:url value="/opportunity/index" />">商談一覧に戻る</a>
    </c:param>
 </c:import>