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
     <label for="opportunity_date">日付</label><br />
     <input type="opportunity_date" name="opportunity_date" value="<fmt:formatDate value='${opportunity.opportunity_date}' pattern='yyyy-MM-dd' />"/>
     <br /><br />
     
     <label for="companycode">会社Code</label><br />
     <c:out value="${sessionScope.authorization_client.companycode}" />
     <br /><br />
     

     <label for="client">会社名</label><br />
     <c:out value="${sessionScope.authorization_client.companyname}" />
     <br /><br />

     <label for="opportunity">商談</label>
     <input type="text" name="opportunity" value="${opportunity.opportunity}" />
     <br /><br />

     <label for="status">商談状況</label><br />
     <textarea name="status" rows="10" cols="50">${opportunity.status}</textarea>
     <br /><br />

     <input type="hidden" name="_token" value="${_token}"/>
     <button type="submit">投稿</button>

