<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Database Login</title>
</head>
<body>
	<h2 align="center">IDS 517 Project Group Home Page - f17g305</h2>

	<hr />

	<h3 align="center">Assignment (JSF) - Individual Links</h3>
	<table align="center" border="1">
		<tr>
			<th>Group member</th>
			<th>Net ID</th>
		</tr>
		<tr>
			<td>Varun Garg</td>
			<td><a href="../../f17g305_vgarg4/">vgarg4</a></td>
		</tr>

		<tr>
			<td>Sagarika Dutta</td>
			<td><a href="../../f17g305_sdutta24/">sdutta24</a></td>
		</tr>

		<tr>
			<td>Shruthi Tirupattur Ravichandran</td>
			<td><a href="../../f17g305_stirup2/">stirup2</a></td>
		</tr>

	</table>

	<hr />

	<f:view>
		<center>
			<h:outputLabel value="#{dataAccessBean.errorMessage}"
				style="color:red;" />
		</center>
		<h:form id="loginForm">
			<center>
				<h4 align="center">Database Login</h4>
				<h:panelGrid columns="2" border="1">

					<h:outputLabel value="UserName" for="userName" />
					<h:inputText id="userName" value="#{loginBean.userName}" />

					<h:outputLabel value="Password" for="password" />
					<h:inputSecret id="password" value="#{loginBean.password}"></h:inputSecret>

					<h:outputLabel value="Database Host" for="dbHost" />
					<h:selectOneMenu id="dbHost" value="#{loginBean.dbHost}">
						<f:selectItem itemValue="131.193.209.54" itemLabel="Server 54" />
						<f:selectItem itemValue="131.193.209.57" itemLabel="Server 57" />
						<f:selectItem itemValue="131.193.209.68" itemLabel="Server 68" />
						<f:selectItem itemValue="131.193.209.69" itemLabel="Server 69" />
						<f:selectItem itemValue="localhost" itemLabel="localhost" />
					</h:selectOneMenu>

					<h:outputLabel for="dbType" value="Database Type" />
					<h:selectOneMenu id="dbType" value="#{loginBean.dbType}">
						<f:selectItem itemValue="MYSQL" itemLabel="MySQL" />
						<f:selectItem itemValue="DB2" itemLabel="DB2" />
						<f:selectItem itemValue="ORACLE" itemLabel="Oracle" />
					</h:selectOneMenu>
					<h:commandButton value="Login" action="#{dataAccessBean.dbConnect}"></h:commandButton>
				</h:panelGrid>
			</center>
		</h:form>
	</f:view>
</body>
</html>