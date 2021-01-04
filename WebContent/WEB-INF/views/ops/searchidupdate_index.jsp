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
        <h2>商談Id"${sessionScope.use.search_id}"　照合中</h2>

        <p><a href="<c:url value='/search/narrow' />">商談Idを検索する</a></p>

        <table id="opportunity_list">
            <tbody>
                <tr>
                    <th class="opportunity_faceid">商談Id</th>
                    <th class="opportunity_person">担当者</th>
                    <th class="opportunity_changer">更新者</th>
                    <th class="opportunity_date">日付</th>
                    <th class="opportunity">商談</th>
                    <th class="opportunity_action">操作</th>
                </tr>
                    <c:forEach var="opportunity" items="${opportunity}" varStatus="status">
                        <tr class="row${status.count %2}">
                            <td class="opportunity_faceid"><c:out value="${opportunity.faceid}"/></td>
                            <td class="opportunity_person"><c:out value="${opportunity.person}" /></td>
                            <td class="opportunity_changer"><c:out value="${opportunity.changer}" /></td>
                            <td class="opportunity_date"><fmt:formatDate value='${opportunity.opportunity_date}' pattern='yyyy-MM-dd' /></td>
                            <td class="opportunity">${opportunity.opportunity}</td>
                            <td class="report_action">
                            <c:choose><c:when test= "${sessionScope.use.search_id == opportunity.faceid}">
                            <a href="<c:url value='/opportunity/show?id=${opportunity.id}' />">詳細を見る</a>
                            </c:when>
                            <c:otherwise>
                                    <c:out value="×" />
                                </c:otherwise>
                            </c:choose>
                            </td>
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

                <p><a href="<c:url value='/remove/searchid' />">商談Idの照合を解除する</a></p>
                </c:param>
            </c:import>