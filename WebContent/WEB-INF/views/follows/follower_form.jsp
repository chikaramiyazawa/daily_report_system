<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            .<c:out value="${error}" /><br />
        </c:forEach>

     </div>
</c:if>
                            <label for="report_date">日付</label><br />
                            <input type="date" name="report_date" value="<fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' />"/>
                            <br /><br />

                             <label for="followerdname">"${follow.followerdname}"</label><br />
                             <input type="hidden" name="followerdname" value ="${follow.followerdname}" />
                             <br /><br />

                             <label for="name">フォロワー "${sessionScope.login_employee.name}"</label><br />
                             <input type="hidden" name="name" value="${sessionScope.login_employee.name}" />
                             <br /><br />

                             <label for="title">タイトル "${report.title}"</label>
                             <input type="hidden" name="title" value="${report.title}" />
                             <br /><br />

                             <label for="content">内容  "${follow.content}"</label><br />
                             <input type="hidden" name ="content" value = "${follow.content}"/>
                             <br /><br />

                             <label for="review">評価</label><br />
                             <textarea name="review" rows="10" cols="50">${follow.review}</textarea>
                             <br /><br />

                              <input type="hidden" name="_token" value="${_token}"/>
                              <button type="submit">更新</button>
