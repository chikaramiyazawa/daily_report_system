<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/op_app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>取引先 会社一覧</h2>
        <table id="client_list">
            <tbody>
                <tr>
                    <th>会社Code</th>
                    <th>会社名</th>

                </tr>
                <c:forEach var="client" items="${client}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${client.companycode}" /></td>
                        <td><c:out value="${client.companyname}" /></td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><a href="<c:url value='/search/list'/>">商談Idを見る</a></p>


    </c:param>
</c:import>