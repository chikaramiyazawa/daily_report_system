<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../layout/app.jsp">
     <c:param name="content">

   <c:if test="${hasError}">
    <div id="flush_error">
   日付が未入力です。<br />
    </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>

            </div>
        </c:if>

 <h2>日付検索画面</h2>
 <form method="POST" action= "<c:url value='/reports/date/search'/>">
 <label for="report_date">日付</label><br />
  <input type="date" name="report_date" value="${report.report_date}" />
  <br /><br />

 <input type="hidden" name="_token" value="${_token}" />
 <button type="submit">検索</button>
 </form>

 <p><a href="<c:url value="/reports/date/before" />">日付前日検索する</a></p>

 <p><a href="<c:url value="/reports/date/after" />">日付後日検索する</a></p>

 <p><a href="<c:url value="/reports/index" />">商談一覧に戻る</a></p>
    </c:param>
 </c:import>