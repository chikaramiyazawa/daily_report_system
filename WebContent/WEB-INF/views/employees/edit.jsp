<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${employee != null}">
            <h2>id :${employee.id}の従業員情報　編集ページ</h2>
            <p>(パスワードは変更する場合のみ入力してください)</p>
            <form method="POST" action="<c:url value='/employees/update'/>">
                <c:import url="_form.jsp" />
            </form>

            <c:if test= "${sessionScope.login_employee.admin_flag == 1} || ${sessionScope.login_employee.admin_flag == 2}" />
            <p><a href="#" onclick="confirmDestroy();">この従業員情報を削除する</a></p>
            <form method="POST" action="<c:url value='/employees/destroy'/>">
                <input type="hidden" name="_token" value="${_token}"/>
             </form>
            <script>
                function confirmDestroy(){
                if(confirm("本当に削除してよろしいですか？")){
                    document.forms[1].submit();
                  }
                }

              </script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

            <p><a href="<c:url value='/employees/index' />">一覧に戻る</a></p>

    </c:param>
  </c:import>