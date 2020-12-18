<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/op_app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id ="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>"${sessionScope.authorization_client.companyname}"商談一覧</h2>

        <table id="opportunity_list">
            <tbody>
                <tr>
                    <th class="opportnuity_companycode">会社Code</th>
                    <th class="opportunity_client">会社名</th>
                    <th class="opportunity_date">日付</th>
                    <th class="opportunity">商談</th>
                    <th class="opportunity_action">操作</th>
                </tr>
                    <c:forEach var="opportunity" items="${opportunity}" varStatus="status">
                        <tr class="row${status.count %2}">
                            <td class="opportunity.companycode}"><c:out value="${sessionScope.authorization_client.companycode}" /></td>
                            <td class="opportunity_client"><c:out value="${sessionScope.authorization_client.companyname}" /></td>
                            <td class="opportunity_date"><fmt:formatDate value='${opportunity.opportunity_date}' pattern='yyyy-MM-dd' /></td>
                            <td class="opportunity">${opportunity.opportunity}</td>
                            <td class="report_action"><a href="<c:url value='/opportunity/show?id=${opportunity.id}' />">詳細を見る</a></td>
                    </tr>
                    </c:forEach>
                    </tbody>
                    </table>

                    <div id="pagination">
                        (全 ${opportunity_count}　件)<br />
                       <c:forEach var="i" begin="1" end="${((opportunity_count - 1) / 15) + 1}" step="1">
                        <c:choose>
                            <c:when test="${i == page}">
                                <c:out value="${i}" />&nbsp;
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/?page=${i}'/>"><c:out value="${i}"/></a>&nbsp;
                                </c:otherwise>
                                </c:choose>
                                </c:forEach>
                                     </div>

                    <p><a href="<c:url value='/opportunity/new' />">商談を追加する</a></p>
                </c:param>
            </c:import>
