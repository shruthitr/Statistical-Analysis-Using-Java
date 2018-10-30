<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
</head>
<body>
	<h3 align="center">Select an operation to perform</h3>
	<h3 align="center">or</h3>
	<h3 align="center">
		<a href="../faces/fileUpload.jsp">Upload SQL Script</a>
	</h3>
	<hr />
	<f:view>
		<center>
			<h:outputLabel value="#{dataAccessBean.errorMessage}"
				style="color:red;" />
			<br>
			<h:outputLabel value="#{statisticsBean.errorMessage}"
				style="color:red;" />
			<br>

			<h:form id="logout">
				<h:commandButton value="Logout"
					action="#{dataAccessBean.processLogout}">
				</h:commandButton>

				<br>
				<br>
			</h:form>
		</center>
		<center>
			<h:form id="schemaForm">
				<h:panelGrid columns="3" border="1">
					<h:outputLabel value="Schema Name" for="schemaName" />
					<h:selectOneMenu id="schemaName" value="#{dataAccessBean.dbSchema}">
						<f:selectItem itemValue="world" itemLabel="World" />
						<f:selectItem itemValue="f17x321" itemLabel="f17x321" />
					</h:selectOneMenu>
					<h:commandButton value="Generate Table List"
						action="#{dataAccessBean.generateTableList}">
					</h:commandButton>
				</h:panelGrid>
			</h:form>
			<br>
			<h:form id="tableForm">
				<h:panelGrid columns="3" border="1"
					rendered="#{dataAccessBean.renderTableList}">
					<h:outputLabel value="Table Name" for="tableName"
						rendered="#{dataAccessBean.renderTableList}" />
					<h:selectOneMenu id="tableName" value="#{dataAccessBean.table}"
						rendered="#{dataAccessBean.renderTableList}">
						<f:selectItems value="#{dataAccessBean.tableList}" />
					</h:selectOneMenu>
					<h:commandButton value="Generate Column List"
						rendered="#{dataAccessBean.renderTableList}"
						action="#{dataAccessBean.generateColumnList}">
					</h:commandButton>
				</h:panelGrid>
			</h:form>
			<br>
			<h:form id="columnForm">
				<h:panelGrid columns="4" border="1"
					rendered="#{dataAccessBean.renderColumnList}">
					<h:outputLabel value="Column Name" for="columnName"
						rendered="#{dataAccessBean.renderColumnList}" />
					<h:selectOneMenu id="columnName" value="#{dataAccessBean.column}"
						rendered="#{dataAccessBean.renderColumnList}">
						<f:selectItems value="#{dataAccessBean.columnList}" />
					</h:selectOneMenu>
					<h:selectOneMenu id="operation" value="#{statisticsBean.operation}"
						rendered="#{dataAccessBean.renderColumnList}">
						<f:selectItem itemValue="excelColumns"
							itemLabel="Export column list to Excel" />
						<f:selectItem itemValue="descriptiveStatistics"
							itemLabel="Generate Descriptive Statistics" />
						<f:selectItem itemValue="excelStats"
							itemLabel="Export Descriptive Statistics to Excel" />
						<f:selectItem itemValue="scatterPlot"
							itemLabel="Generate Scatter Plot" />
						<f:selectItem itemValue="regression"
							itemLabel="Regression Analysis" />
					</h:selectOneMenu>
					<h:commandButton value="Select operation"
						rendered="#{dataAccessBean.renderColumnList}"
						action="#{statisticsBean.runOperation}">
					</h:commandButton>
				</h:panelGrid>
			</h:form>

			<br>

			<h:panelGrid columns="11" border="1"
				rendered="#{statisticsBean.renderColumnList}">

				<h:outputLabel value="Mean" />
				<h:outputLabel value="Minimum Value" />
				<h:outputLabel value="Maximum Value" />
				<h:outputLabel value="Median" />
				<h:outputLabel value="Variance" />
				<h:outputLabel value="Standard Deviation" />
				<h:outputLabel value="Range" />
				<h:outputLabel value="Quartile 1" />
				<h:outputLabel value="Quartile 3" />
				<h:outputLabel value="Inter-quartile Range" />
				<h:outputLabel value="Coefficient of Variation" />

				<h:outputLabel
					value="#{statisticsBean.descriptiveStatisticsBean.mean}" />
				<h:outputLabel
					value="#{statisticsBean.descriptiveStatisticsBean.minValue}" />
				<h:outputLabel
					value="#{statisticsBean.descriptiveStatisticsBean.maxValue}" />
				<h:outputLabel
					value="#{statisticsBean.descriptiveStatisticsBean.median}" />
				<h:outputLabel
					value="#{statisticsBean.descriptiveStatisticsBean.variance}" />
				<h:outputLabel
					value="#{statisticsBean.descriptiveStatisticsBean.standardDeviation}" />
				<h:outputLabel
					value="#{statisticsBean.descriptiveStatisticsBean.range}" />
				<h:outputLabel
					value="#{statisticsBean.descriptiveStatisticsBean.quartile1}" />
				<h:outputLabel
					value="#{statisticsBean.descriptiveStatisticsBean.quartile3}" />
				<h:outputLabel
					value="#{statisticsBean.descriptiveStatisticsBean.iqr}" />
				<h:outputLabel
					value="#{statisticsBean.descriptiveStatisticsBean.coeffOfVariation}" />

			</h:panelGrid>

			<br>

			<t:dataTable id="columnDataList" var="item"
				value="#{statisticsBean.columnsData}"
				rendered="#{statisticsBean.renderColumnList}" border="1"
				cellspacing="0" cellpadding="1" columnClasses="columnClass1 border"
				headerClass="headerClass" footerClass="footerClass"
				rowClasses="rowClass2" styleClass="dataTableEx" width="200">
				<h:column>
					<f:facet name="header">
						<h:outputText styleClass="outputHeader" value="Column Data" />
					</f:facet>
					<t:outputText styleClass="outputText" value="#{item}" />
				</h:column>
			</t:dataTable>

			<h:form id="statisticsForm">
				<h:panelGrid columns="5" border="1"
					rendered="#{statisticsBean.renderStatisticsColumns}">
					<h:outputLabel value="Response Variable" for="responseVariable"
						rendered="#{statisticsBean.renderStatisticsColumns}" />
					<h:selectOneMenu id="responseVariable"
						value="#{statisticsBean.responseVariable}"
						rendered="#{statisticsBean.renderStatisticsColumns}">

						<f:selectItems value="#{dataAccessBean.columnList}" />
					</h:selectOneMenu>
					<h:outputLabel value="Predictor Variable" for="predictorVariable"
						rendered="#{statisticsBean.renderStatisticsColumns}" />
					<h:selectOneMenu id="predictorVariable"
						value="#{statisticsBean.predictorVariable}"
						rendered="#{statisticsBean.renderStatisticsColumns}">
						<f:selectItems value="#{dataAccessBean.columnList}" />
					</h:selectOneMenu>

					<h:commandButton type="submit" value="Generate Scatter Plot"
						action="#{statisticsBean.scatterCallPlot}"
						rendered="#{statisticsBean.renderStatisticsColumns}">
					</h:commandButton>

				</h:panelGrid>

			</h:form>
			<h:form id="plotForm">
				<h:graphicImage id="displayScatterPlot"
					value="#{statisticsBean.chartPath}" height="600" width="600"
					rendered="#{statisticsBean.renderScatterPlot}" />
			</h:form>


			<h:form id="regression">
				<h:panelGrid columns="5" border="1"
					rendered="#{statisticsBean.renderRegression}">
					<h:outputLabel value="Response Variable" for="responseVariable"
						rendered="#{statisticsBean.renderRegression}" />
					<h:selectOneMenu id="responseVariable"
						value="#{statisticsBean.responseValue}"
						rendered="#{statisticsBean.renderRegression}">

						<f:selectItems value="#{dataAccessBean.columnList}" />
					</h:selectOneMenu>
					<h:outputLabel value="Predictor Variable" for="predictorVariable"
						rendered="#{statisticsBean.renderRegression}" />
					<h:selectOneMenu id="predictorVariable"
						value="#{statisticsBean.predictorValue}"
						rendered="#{statisticsBean.renderRegression}">
						<f:selectItems value="#{dataAccessBean.columnList}" />
					</h:selectOneMenu>
					<h:commandButton type="submit" value="Generate Regression Report"
						action="#{statisticsBean.calculateRegressionVariables}"
						rendered="#{statisticsBean.renderRegression}">
					</h:commandButton>
				</h:panelGrid>

			</h:form>
			<br>

			<h:outputLabel value="Regression Model:"
				rendered="#{statisticsBean.renderRegressionTable}" />
			<br>
			<h:panelGrid columns="5" border="1"
				rendered="#{statisticsBean.renderRegressionTable}">

				<h:outputLabel value="Predictor" />
				<h:outputLabel value="Co-efficient" />
				<h:outputLabel value="Standard Error Co-effcient" />
				<h:outputLabel value="T-Statistic" />
				<h:outputLabel value="p-Value" />


				<h:outputLabel value="Constant" />
				<h:outputLabel value="#{statisticsBean.regressionBean.intercept}" />
				<h:outputLabel
					value="#{statisticsBean.regressionBean.interceptStandardError}" />
				<h:outputLabel value="#{statisticsBean.regressionBean.tValue}" />
				<h:outputLabel value="#{statisticsBean.regressionBean.pValue}" />

				<h:outputLabel value="#{statisticsBean.regressionBean.predictor}" />
				<h:outputLabel value="#{statisticsBean.regressionBean.slope}" />
				<h:outputLabel
					value="#{statisticsBean.regressionBean.slopeStandardError}" />
				<h:outputLabel
					value="#{statisticsBean.regressionBean.tStatisticPredictor}" />
				<h:outputLabel
					value="#{statisticsBean.regressionBean.pValuePredictor}" />
			</h:panelGrid>
			<br>

			<h:panelGrid columns="3" border="1"
				rendered="#{statisticsBean.renderRegressionTable}">

				<h:outputLabel value="Standard Error" />
				<h:outputLabel value="R-Square(Co-efficient of Determination)" />
				<h:outputLabel
					value="Adjusted R-Square(Co-efficient of Determination)" />

				<h:outputLabel
					value="#{statisticsBean.regressionBean.standardErrorModel}" />
				<h:outputLabel value="#{statisticsBean.regressionBean.rSquareValue}" />
				<h:outputLabel
					value="#{statisticsBean.regressionBean.adjustedRSquare}" />
			</h:panelGrid>
			<br>


			<h:outputLabel value="Anova Analysis:"
				rendered="#{statisticsBean.renderRegressionTable}" />

			<br>
			<h:panelGrid columns="6" border="1"
				rendered="#{statisticsBean.renderRegressionTable}">

				<h:outputLabel value="Source" />
				<h:outputLabel value="Degree of Freedom(DF)" />
				<h:outputLabel value="Sum of Squares" />
				<h:outputLabel value="Mean of Squares" />
				<h:outputLabel value="F-Statistic" />
				<h:outputLabel value="p-Value" />

				<h:outputLabel value="Regression" />
				<h:outputLabel value="#{statisticsBean.regressionBean.dfPredictor}" />
				<h:outputLabel
					value="#{statisticsBean.regressionBean.sumSquareRegression}" />
				<h:outputLabel value="#{statisticsBean.regressionBean.meanSquare}" />
				<h:outputLabel value="#{statisticsBean.regressionBean.fValue}" />

				<h:outputLabel value="#{statisticsBean.regressionBean.pValue}" />


				<h:outputLabel value="Residual Error" />
				<h:outputLabel
					value="#{statisticsBean.regressionBean.dfResidualError}" />
				<h:outputLabel
					value="#{statisticsBean.regressionBean.sumSquareError}" />
				<h:outputLabel
					value="#{statisticsBean.regressionBean.meanSquareError}" />
				<h:outputLabel value=" " />

				<h:outputLabel value=" " />

				<h:outputLabel value="Total" />
				<h:outputLabel value="#{statisticsBean.regressionBean.dfTotal}" />
				<h:outputLabel value=" " />
				<h:outputLabel value=" " />
				<h:outputLabel value=" " />

				<h:outputLabel value=" " />

			</h:panelGrid>
			<br>

			<h:outputLabel value="Regression Equation"
				rendered="#{statisticsBean.renderRegressionTable}" />
			<br>
			<h:outputLabel
				value="#{statisticsBean.regressionBean.regressionEquation}"
				rendered="#{statisticsBean.renderRegressionTable}" />
			<t:dataTable id="regressionDataList" var="item2"
				value="#{statisticsBean.predictorResponseBeanList}"
				rendered="#{statisticsBean.renderRegressionTable}" border="1"
				cellspacing="0" cellpadding="1" columnClasses="columnClass1 border"
				headerClass="headerClass" footerClass="footerClass"
				rowClasses="rowClass2" styleClass="dataTableEx" width="200">
				<h:column>
					<f:facet name="header">
						<h:outputText styleClass="outputHeader" value="Predictor" />
					</f:facet>
					<t:outputText styleClass="outputText" value="#{item2.predictor}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText styleClass="outputHeader" value="Response" />
					</f:facet>
					<t:outputText styleClass="outputText" value="#{item2.response}" />
				</h:column>
			</t:dataTable>
		</center>
	</f:view>
</body>
</html>