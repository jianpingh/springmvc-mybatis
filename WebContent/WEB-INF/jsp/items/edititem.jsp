<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>修改商品信息</title>
</head>
<body>
	修改商品信息
	<!-- 显示错误信息 -->
	<c:if test="${errors!=null }">
		<c:forEach items="${errors}" var="error">
	${ error.defaultMessage}
	</c:forEach>
	</c:if>
	<form id="itemForm"
		action="${pageContext.request.contextPath }/editItemSubmit.action">
		<input type="hidden" name="id" value="${item.id }" />
		<table width="100%" border="1px">
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="name" value="${item.name}" /></td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td><input type="text" name="price" value="${item.price}" /></td>
			</tr>
			<tr>
				<td>商品创建时间</td>
				<td><input type="text" name="createtime"
					value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" /></td>
			</tr>
			<tr>
				<td>商品</td>
				<td><input type="text" name="detail" value="${item.detail}" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>