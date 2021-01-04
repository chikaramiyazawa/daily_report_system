<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/op_app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>商談Id 一覧</h2>
        <table id="searcher_list">
            <tbody>
               <tr>
                    <th>商談Id</th>
                    <th>使用状況</th>
                </tr>
                <c:forEach var="searcher" items="${searcher}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${searcher.search_id}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${searcher.used == 1}">
                                    使用済み
                                </c:when>
                                <c:otherwise>
                                    <c:out value="未使用" />
                                </c:otherwise>
                            </c:choose>
                            </td>
                            </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><a href="<c:url value='/searchid/new' />">商談Idを作成する</a></p>
    </c:param>
</c:import>