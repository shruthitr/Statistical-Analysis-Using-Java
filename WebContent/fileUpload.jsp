<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload SQL script</title>
</head>
<body>
	<h3 align="center">Upload SQL Script</h3>
	<h3 align="center">
		<a href="../faces/menu.jsp">Go back</a>
	</h3>
	<hr />

	<f:view>
		<center>
			<h:form id="logout">
				<h:commandButton value="Logout"
					action="#{dataAccessBean.processLogout}">
				</h:commandButton>
			</h:form>
			<br>
			<h:outputLabel value="#{dataAccessBean.errorMessage}"
				style="color:red;" />
			<h:form id="fileUpload" enctype="multipart/form-data">
				<h:panelGrid columns="3" border="1">
					<h:outputLabel value="Select a file to upload (.txt format ONLY)" />
					<t:inputFileUpload id="fileUpload" label="File to upload"
						storage="default" value="#{fileUploadBean.uploadedFile}" size="60" />
					<h:commandButton id="upload" action="#{dataAccessBean.uploadFile}"
						value="Submit" />
				</h:panelGrid>
			</h:form>
			<br>
			<h:panelGrid columns="2" border="1"
				rendered="#{dataAccessBean.renderFileUpload}">
				<h:outputLabel value="File Content" />
				<h:outputLabel value="#{dataAccessBean.fileContent}" />
				<h:outputLabel value="Result" />
				<h:outputLabel value="#{dataAccessBean.rowsMessage}" />
			</h:panelGrid>
			<hr />
			<h2 align="center">Select a saved file</h2>
			<hr />
			<h:form id="fileSelect">
				<h:panelGrid columns="3" border="1">
					<h:outputLabel value="Select a file to execute" />
					<h:selectOneListbox size="10" styleClass="selectOneListbox_mono"
						value="#{dataAccessBean.fileName}">
						<f:selectItems value="#{dataAccessBean.fileList}" />
					</h:selectOneListbox>
					<h:commandButton id="upload" action="#{dataAccessBean.processFile}"
						value="Submit" />
				</h:panelGrid>
			</h:form>
			<br>
			<t:dataTable value="#{dataAccessBean.result}" var="row" border="1"
				cellspacing="0" cellpadding="1" columnClasses="columnClass1 border"
				headerClass="headerClass" footerClass="footerClass"
				rowClasses="rowClass2" styleClass="dataTableEx" width="900"
				rendered="#{dataAccessBean.renderFileUpload2}">
				<t:columns var="col" value="#{dataAccessBean.columnNames}">
					<f:facet name="header">
						<t:outputText styleClass="ouputHeader" value="#{col}" />
					</f:facet>
					<t:outputText styleClass="outputText" value="#{row[col]}" />
				</t:columns>
			</t:dataTable>
		</center>
	</f:view>
</body>
</html>