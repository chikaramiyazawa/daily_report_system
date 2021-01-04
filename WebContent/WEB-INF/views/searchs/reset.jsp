<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../layout/op_app.jsp">
    <c:param name="content">
        <h2>商談Id使用中止</h2>

        <form method="POST" action= "<c:url value='/search/reset'/>">
            <c:import url="reset_form.jsp"/>
        </form>


        </c:param>
</c:import>