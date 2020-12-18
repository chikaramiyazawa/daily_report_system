<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/op_app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>取引先管理</h2>
        <table id="client_list">
            <tbody>
                <tr>
                    <th>会社Code</th>
                    <th>会社名</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="client" items="${client}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${client.companycode}" /></td>
                        <td><c:out value="${client.companyname}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${client.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/client/edit?id=${client.id}' />">登録管理</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${client_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((client_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/client/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/client/new' />">取引先新規登録</a></p>

    </c:param>
</c:import>