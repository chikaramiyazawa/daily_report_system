<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/WEB-INF/views/layout/op_app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${opportunity != null}">

                <h2><c:out value="${sessionScope.authorization_client.companyname}"/>の商談　詳細ページ  </h2>

                <table>
                    <tbody>
                        <tr>
                        <th>会社Code</th>
                        <td><c:out value="${sessionScope.authorization_client.companycode}" /></td>
                        </tr>
                        <tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${opportunity.opportunity_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>担当者</th>
                            <td>
                                <pre><c:out value="${opportunity.person}" /></pre>
                            </td>
                       </tr>
                       <tr>
                            <th>更新者</th>
                            <td>
                                <pre><c:out value="${opportunity.changer}" /></pre>
                            </td>
                       </tr>
                       <tr>
                            <th>場所</th>
                            <td>
                                <pre><c:out value="${opportunity.location}"/></pre>
                            </td>
                       </tr>
                        <tr>
                            <th>商談</th>
                            <td>
                                <pre><c:out value="${opportunity.opportunity}" /></pre>
                            </td>
                       </tr>
                       <tr>
                            <th>商談状況</th>
                            <td>
                                <pre><c:out value="${opportunity.status}"/></pre>
                            </td>
                       </tr>

                            <tr>
                                <th>登録日時</th>
                                <td>
                                    <fmt:formatDate value="${opportunity.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                             </tr>
                             <tr>
                                <th>更新日時</th>
                                <td>
                                    <fmt:formatDate value="${opportunity.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                                </tr>
                             </tbody>
                             </table>


                                <p><a href="<c:url value="/opportunity/edit?id=${opportunity.id}" />">この商談を更新する</a></p>

                                </c:when>
                             <c:otherwise>
                                <h2>お探しのデータは見つかりませんでした。</h2>
                             </c:otherwise>
                           </c:choose>


                           <p><a href="<c:url value="/opportunity/index" />">商談一覧に戻る</a></p>
                       </c:param>
                       </c:import>
