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
        <h2>商談Id 検索結果</h2>

        <p><a href="<c:url value='/opportunity/op_code/person/search' />">会社Codeと担当者を検索する</a></p>
        <p><a href="<c:url value='/opportunity/op_id/search'/>">商談Idを検索する</a></p>
        <p><a href="<c:url value='/opportunity/op_date/search'/>">日付検索する</a></p>

        <p>会社Code "${sessionScope.authorization_client.companycode}" 更新可</p>
        <table id="opportunity_list">
            <tbody>
                <tr>
                    <th class="opportunity_op_id">商談Id</th>
                    <th class="opportunity_op_code">会社Code</th>
                    <th class="opportunity_person">担当者</th>
                    <th class="opportunity_changer">更新者</th>
                    <th class="opportunity_date">日付</th>
                    <th class="opportunity">商談</th>
                    <th class="opportunity_action">操作</th>
                </tr>
                    <c:forEach var="opportunity" items="${opportunity}" varStatus="status">
                        <tr class="row${status.count %2}">
                            <td class="opportunity_op_id"><c:out value="${opportunity.op_id}"/></td>
                            <td class="opportunity_op_code"><c:out value="${opportunity.op_code}"/></td>
                            <td class="opportunity_person"><c:out value="${opportunity.person}" /></td>
                            <td class="opportunity_changer"><c:out value="${opportunity.changer}" /></td>
                            <td class="opportunity_date"><fmt:formatDate value='${opportunity.opportunity_date}' pattern='yyyy-MM-dd' /></td>
                            <td class="opportunity"><c:out value = "${opportunity.opportunity}"/></td>
                            <td class="opportunity_action"><a href="<c:url value='/opportunity/show?id=${opportunity.id}' />">詳細を見る</a></td>
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


                </c:param>
            </c:import>