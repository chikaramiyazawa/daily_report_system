<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>日報　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                        <th>氏名</th>
                        <td><c:out value="${report.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${report.report_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${report.content}" /></pre>
                            </td>
                            </tr>
                            <tr>
                                <th>登録日時</th>
                                <td>
                                    <fmt:formatDate value="${report.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                             </tr>
                             <tr>
                                <th>更新日時</th>
                                <td>
                                    <fmt:formatDate value="${report.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                                </tr>
                             </tbody>
                             </table>

                             <c:if test="${sessionScope.login_employee.id == report.employee.id}">
                                <p><a href="<c:url value="/reports/edit?id=${report.id}" />">この日報を編集する</a></p>
                             </c:if>

                             <c:if test="${sessionScope.login_employee.id != report.employee.id}">


                              <h2>${report.employee.name}の日報をフォローする</h2>

                              <form method="POST" action= "<c:url value='/follow/create'/>">


                             <label for="report_date">日付</label><br />
                             <input type="date" name="report_date" value="<fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' />"/>
                             <br /><br />

                             <label for="followerdname">"${report.employee.name}"</label><br />
                             <input type="hidden" name="followerdname" value ="${report.employee.name}" />
                             <br /><br />

                             <label for="name">フォロワー "${sessionScope.login_employee.name}"</label><br />
                             <input type="hidden" name="name" value="${sessionScope.login_employee.name}" />
                             <br /><br />

                             <label for="title">タイトル "${report.title}"</label>
                             <input type="hidden" name="title" value="${report.title}" />
                             <br /><br />

                             <label for="content">内容  "${report.content}"</label><br />
                             <input type="hidden" name ="content" value = "${report.content}"/>
                             <br /><br />

                             <label for="review">評価</label><br />
                             <textarea name="review" rows="10" cols="50">${follow.review}</textarea>
                             <br /><br />

                              <input type="hidden" name="_token" value="${_token}"/>
                              <button type="submit">フォロー</button>

                            </form>
                             </c:if>


                             </c:when>
                             <c:otherwise>
                                <h2>お探しのデータは見つかりませんでした。</h2>
                             </c:otherwise>
                           </c:choose>

                           <p><a href="<c:url value="/reports/index" />">一覧に戻る</a></p>
                       </c:param>
                       </c:import>
