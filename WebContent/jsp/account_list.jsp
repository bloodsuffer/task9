<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="/WEB-INF/my_tags.tld" prefix="my" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Accounts list

<table>

	<c:forEach var="bean" items="${accountList}">
					<tr>
						<td><my:clTag client = "${bean.client}" /></td>
						<td>${bean.bank}</td>
						<td>${bean.balance}</td>
						<td>${bean.currency}</td>
						<td><fmt:formatDate value="${bean.openDate}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
						<td><fmt:formatDate value="${bean.closeDate}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
						<td><a href="viewAccountInfo?accountId=${bean.id}">Info</a><</td>
					</tr>

	</c:forEach>		

</table>

</body>
</html>