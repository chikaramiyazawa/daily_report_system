<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${follow != null}">
                <h2><c:out value="${follow.followerdname}"/>の日報 <c:out value="${follow.title}"/> </h2>

                <table>
                    <tbody>
                        <tr>
                        <th>フォロワー</th>
                        <td><c:out value="${follow.employee.name}" /></td>
                        </tr>
                        <tr>
                        <th>投稿主</th>
                        <td><c:out value="${follow.followerdname}" /></td>
                        </tr>

                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${follow.report_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${follow.content}" /></pre>
                            </td>
                       </tr>
                       <tr>
                            <th>評価</th>
                            <td>
                                <pre><c:out value="${follow.review}"/></pre>
                            </td>
                       </tr>

                            <tr>
                                <th>登録日時</th>
                                <td>
                                    <fmt:formatDate value="${follow.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                             </tr>
                             <tr>
                                <th>更新日時</th>
                                <td>
                                    <fmt:formatDate value="${follow.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                                </tr>
                             </tbody>
                             </table>

                                <c:if test="${sessionScope.login_employee.id == follow.employee.id}">
                                <p><a href="<c:url value="/follow/edit?id=${follow.id}" />">更新する</a></p>
                                </c:if>

                             </c:when>
                             <c:otherwise>
                                <h2>お探しのデータは見つかりませんでした。</h2>
                             </c:otherwise>
                           </c:choose>

                           <p><a href="<c:url value="/followers/index" />">一覧に戻る</a></p>
                       </c:param>
                       </c:import>
