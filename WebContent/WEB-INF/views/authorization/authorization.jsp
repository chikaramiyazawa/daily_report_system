<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/WEB-INF/views/layout/op_app.jsp">
    <c:param name="content">
        <c:if test="${hasError}">
            <div id="flush_error">
                会社Code、会社名またはパスワードが間違っています。
            </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>

            </div>
        </c:if>
        <h2>取引先認証</h2>
        <form method="POST" action="<c:url value='/authorization' />">
            <label for="companycode">会社Code</label><br />
            <input type="text" name="companycode" value="${companycode}"/>
            <br /><br />

            <label for="companyname">会社名</label><br />
            <input type="text" name="companyname" value="${companyname}"/>
            <br /><br />

            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">認証</button>
        </form>

         <a href="<c:url value='/client/list' />">取引先一覧</a>
    </c:param>
  </c:import>