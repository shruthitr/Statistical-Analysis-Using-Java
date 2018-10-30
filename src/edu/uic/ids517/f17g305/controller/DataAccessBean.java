package edu.uic.ids517.f17g305.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import org.apache.commons.io.FilenameUtils;
import edu.uic.ids517.f17g305.model.FileUploadBean;
import edu.uic.ids517.f17g305.model.LoginBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

@ManagedBean(name = "dataAccessBean")
@SessionScoped
public class DataAccessBean {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private Result result;
	private FileUploadBean fileUploadBean;
	private LoginBean loginBean;
	private InetAddress ipAddress;
	private HttpSession session;
	private String table;
	private String column;
	private String dbSchema;
	private String errorMessage = "";
	private String fileName;
	private String fileContent;
	private String rowsMessage;
	private String operation;
	private String sessionId;
	private ArrayList<String> tableList;
	private ArrayList<String> columnList;
	private ArrayList<Integer> columnsType = new ArrayList<Integer>();
	private ArrayList<String> columnsData = new ArrayList<String>();
	private ArrayList<Double> responseVariableValues = new ArrayList<Double>();
	private ArrayList<Double> predictorVariableValues = new ArrayList<Double>();
	private ArrayList<String> fileList = new ArrayList<String>();
	private ArrayList<String> columnNames = new ArrayList<String>();
	private boolean renderTableList;
	private boolean renderColumnList;
	private boolean renderFileUpload;
	private boolean renderFileUpload2;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public FileUploadBean getFileUploadBean() {
		return fileUploadBean;
	}

	public void setFileUploadBean(FileUploadBean fileUploadBean) {
		this.fileUploadBean = fileUploadBean;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(InetAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getDbSchema() {
		return dbSchema;
	}

	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getRowsMessage() {
		return rowsMessage;
	}

	public void setRowsMessage(String rowsMessage) {
		this.rowsMessage = rowsMessage;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public ArrayList<String> getTableList() {
		return tableList;
	}

	public void setTableList(ArrayList<String> tableList) {
		this.tableList = tableList;
	}

	public ArrayList<String> getColumnList() {
		return columnList;
	}

	public void setColumnList(ArrayList<String> columnList) {
		this.columnList = columnList;
	}

	public ArrayList<Integer> getColumnsType() {
		return columnsType;
	}

	public void setColumnsType(ArrayList<Integer> columnsType) {
		this.columnsType = columnsType;
	}

	public ArrayList<String> getColumnsData() {
		return columnsData;
	}

	public void setColumnsData(ArrayList<String> columnsData) {
		this.columnsData = columnsData;
	}

	public ArrayList<Double> getResponseVariableValues() {
		return responseVariableValues;
	}

	public void setResponseVariableValues(ArrayList<Double> responseVariableValues) {
		this.responseVariableValues = responseVariableValues;
	}

	public ArrayList<Double> getPredictorVariableValues() {
		return predictorVariableValues;
	}

	public void setPredictorVariableValues(ArrayList<Double> predictorVariableValues) {
		this.predictorVariableValues = predictorVariableValues;
	}

	public ArrayList<String> getFileList() {
		return fileList;
	}

	public void setFileList(ArrayList<String> fileList) {
		this.fileList = fileList;
	}

	public ArrayList<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(ArrayList<String> columnNames) {
		this.columnNames = columnNames;
	}

	public boolean isRenderTableList() {
		return renderTableList;
	}

	public void setRenderTableList(boolean renderTableList) {
		this.renderTableList = renderTableList;
	}

	public boolean isRenderColumnList() {
		return renderColumnList;
	}

	public void setRenderColumnList(boolean renderColumnList) {
		this.renderColumnList = renderColumnList;
	}

	public boolean isRenderFileUpload() {
		return renderFileUpload;
	}

	public void setRenderFileUpload(boolean renderFileUpload) {
		this.renderFileUpload = renderFileUpload;
	}

	public boolean isRenderFileUpload2() {
		return renderFileUpload2;
	}

	public void setRenderFileUpload2(boolean renderFileUpload2) {
		this.renderFileUpload2 = renderFileUpload2;
	}

	public DataAccessBean() {
		renderColumnList = false;
		renderTableList = false;
		renderFileUpload = false;
		renderFileUpload2 = false;
		dbSchema = "";
		table = "";
		column = "";
		columnList = new ArrayList<>();
		tableList = new ArrayList<>();
		errorMessage = "";
	}

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> m = context.getExternalContext().getSessionMap();
		loginBean = (LoginBean) m.get("loginBean");
		fileUploadBean = (FileUploadBean) m.get("fileUploadBean");
	}

	public String dbConnect() {

		String url = "";
		String jdbcDriver = "";
		errorMessage = "";

		switch (loginBean.getDbType()) {

		case "MYSQL":
			jdbcDriver = "com.mysql.jdbc.Driver";
			url = "jdbc:mysql://" + loginBean.getDbHost();
			break;

		case "DB2":
			jdbcDriver = "com.ibm.db2.jcc.DB2Driver";
			url = "jdbc:db2://" + loginBean.getDbHost();
			break;

		case "ORACLE":
			jdbcDriver = "oracle.jdbc.driver.OracleDriver";
			url = "jdbc:oracle:thin:@" + loginBean.getDbHost();
			break;

		default:
			break;
		}

		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(url, loginBean.getUserName(), loginBean.getPassword());
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (connection != null && statement != null) {
				operation = "login";
				generateFileList();
				captureTransactionLogTable();
				return "success";
			} else {
				return "fail";
			}

		} catch (ClassNotFoundException e) {
			errorMessage = "Either Database Host or Type is incorrect.";
			return "fail";
		} catch (SQLException e) {
			if (e.getSQLState().equals("28000")) {
				errorMessage = "Either username or password is incorrect.";
			} else {
				errorMessage = "Either Host or DB type is incorrect.";
			}
			return "fail";
		} catch (Exception e) {
			errorMessage = "All credentials are incorrect.";
			return "fail";
		}
	}

	public String generateTableList() {

		errorMessage = "";
		String query = "SELECT table_name FROM information_schema.tables where table_schema='" + dbSchema + "';";
		ArrayList<String> tables = new ArrayList<>();
		ResultSet rs;

		try {
			rs = statement.executeQuery(query);

			if (rs != null) {
				while (rs.next()) {
					tables.add(rs.getString("table_name"));
					renderTableList = true;
				}
			}

			if (renderTableList) {
				tableList = tables;
				return "success";
			} else {
				return "fail";
			}
		} catch (SQLException e) {
			errorMessage = e.getMessage();
			renderTableList = false;
			return "fail";
		} catch (Exception e) {
			errorMessage = e.getMessage();
			renderTableList = false;
			return "fail";
		}
	}

	public String generateColumnList() {

		errorMessage = "";
		ArrayList<String> columns = new ArrayList<String>();
		ResultSet rs;
		try {
			String query = "SELECT * FROM " + dbSchema + "." + table + ";";
			rs = statement.executeQuery(query);
			if (rs != null) {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				int columnCount = resultSetMetaData.getColumnCount();
				if (columnCount > 0) {
					for (int i = 1; i <= columnCount; i++) {
						String name = resultSetMetaData.getColumnName(i);
						columns.add(name);
					}
					renderColumnList = true;
					columnList = columns;
					return "success";
				} else {
					renderColumnList = false;
					return "fail";
				}
			} else {
				renderColumnList = false;
				return "fail";
			}
		} catch (SQLException e) {
			errorMessage = e.getMessage();
			renderColumnList = false;
			return "fail";
		} catch (Exception e) {
			errorMessage = e.getMessage();
			renderColumnList = false;
			return "fail";
		}
	}

	public void generateFileList() {

		rowsMessage = "";
		fileContent = "";
		fileList.clear();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String path = facesContext.getExternalContext().getRealPath("/files");
		File directory = new File(path);
		File[] list = directory.listFiles();
		String extension = "";
		if (list != null) {
			for (File file : list) {
				if (file.isFile()) {
					extension = FilenameUtils.getExtension(file.getName());
					if (extension.equalsIgnoreCase("txt")) {
						fileList.add(file.getName());
					}
				}
			}
		}
	}

	public String uploadFile() {

		errorMessage = "";
		String contents = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String path = facesContext.getExternalContext().getRealPath("/files");
		File tempFile = new File(path);
		if (!tempFile.exists()) {
			new File(path).mkdirs();
		}
		File file = null;
		FileOutputStream fileOutputStream = null;
		ResultSetMetaData resultSetMetaData;
		int numberOfColumns = 0;
		String fileName = "";
		String extension = "";
		columnNames.clear();

		try {
			fileName = FilenameUtils.getBaseName(fileUploadBean.getUploadedFile().getName());
			extension = FilenameUtils.getExtension(fileUploadBean.getUploadedFile().getName());

			if (extension.equalsIgnoreCase("txt")) {

				contents = new String(fileUploadBean.getUploadedFile().getBytes());
				file = new File(path + "/" + fileName + "." + extension);
				fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(fileUploadBean.getUploadedFile().getBytes());
				fileOutputStream.close();
				generateFileList();
				String status = displayData(contents);
				if (status.contains("success") && status.contains("select")) {
					resultSetMetaData = resultSet.getMetaData();
					numberOfColumns = resultSetMetaData.getColumnCount();
					result = ResultSupport.toResult(resultSet);
					for (int i = 1; i <= numberOfColumns; i++) {
						columnNames.add(resultSetMetaData.getColumnName(i));
					}
					return "success";
				} else if (status.contains("success") && status.contains("update")) {
					return "success";
				} else {
					return "fail";
				}
			} else {
				errorMessage = "Only .txt format supported.";
				return "fail";
			}
		} catch (IOException e) {
			errorMessage = e.getMessage();
			return "fail";
		} catch (SQLException e) {
			errorMessage = e.getMessage();
			return "fail";
		} catch (Exception e) {
			errorMessage = e.getMessage();
			return "fail";
		}
	}

	public String processFile() {

		rowsMessage = "";
		fileContent = "";

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String path = facesContext.getExternalContext().getRealPath("/files");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "/" + fileName));
			StringBuilder stringBuilder = new StringBuilder();
			String query = "";
			String line = "";
			String status = "";
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append("\n");
			}
			bufferedReader.close();
			query = stringBuilder.toString();
			ResultSetMetaData resultSetMetaData;
			int numberOfColumns = 0;
			columnNames.clear();

			status = displayData(query);
			if (status.contains("success") && status.contains("select")) {
				resultSetMetaData = resultSet.getMetaData();
				result = ResultSupport.toResult(resultSet);
				numberOfColumns = resultSetMetaData.getColumnCount();
				for (int i = 1; i <= numberOfColumns; i++) {
					columnNames.add(resultSetMetaData.getColumnName(i));
				}
				return "success";
			} else if (status.contains("success") && status.contains("update")) {
				return "success";
			} else {
				return "fail";
			}
		} catch (FileNotFoundException e) {
			errorMessage = e.getMessage();
			return "fail";
		} catch (IOException e) {
			errorMessage = e.getMessage();
			return "fail";
		} catch (SQLException e) {
			errorMessage = e.getMessage();
			return "fail";
		} catch (Exception e) {
			errorMessage = e.getMessage();
			return "fail";
		}
	}

	public String displayData(String query) {
		errorMessage = "";
		rowsMessage = "";
		fileContent = "";
		int noOfRows = 0;
		if (connection == null && statement == null) {
			resultSet = null;
			errorMessage = "Connection not established.";
			return "fail";
		} else {
			try {
				if (query.contains("select") || query.contains("Select") || query.contains("SELECT")) {

					resultSet = statement.executeQuery(query);
					if (resultSet != null) {
						renderFileUpload = true;
						renderFileUpload2 = true;
						fileContent = query.toString();
						rowsMessage = "Data displayed below.";
						return "success-select";
					} else {
						errorMessage = "No data returned.";
						return "fail";
					}
				} else {
					noOfRows = statement.executeUpdate(query);
					fileContent = query.toString();
					renderFileUpload = true;
					renderFileUpload2 = false;
					rowsMessage = "(" + noOfRows + ") " + "Rows affected.";
					return "success-update";
				}

			} catch (SQLException e) {
				if (e.getErrorCode() == 1146) {
					errorMessage = "Table does not exist.";
					return "fail";
				} else if (e.getErrorCode() == 1064) {
					errorMessage = "Error in query syntax.";
					return " fail";
				} else if (e.getErrorCode() == 1044) {
					errorMessage = "Access denied.";
					return " fail";
				} else {
					errorMessage = "Error in query.";
					return "fail";
				}
			} catch (Exception e) {
				errorMessage = e.getMessage();
				return "fail";
			}
		}
	}

	public String generateColumnData() {

		ResultSet rs;
		errorMessage = "";
		try {
			columnsData.clear();
			columnsType.clear();
			String query = "SELECT " + column + " FROM " + dbSchema + "." + table + ";";
			PreparedStatement statement = connection.prepareStatement(query);
			rs = statement.executeQuery(query);
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();

			if (rs != null) {

				if (columnCount > 0) {

					while (rs.next()) {
						columnsData.add(rs.getString(1));
					}
					columnsType.add(resultSetMetaData.getColumnType(1));
					renderColumnList = true;
					return "success";
				} else {
					renderColumnList = false;
					return "fail";
				}
			} else {
				renderColumnList = false;
				return "fail";
			}
		} catch (SQLException e) {
			errorMessage = e.getMessage();
			renderColumnList = false;
			return "fail";
		} catch (Exception e) {
			errorMessage = e.getMessage();
			renderColumnList = false;
			return "fail";
		}
	}

	public String generateColumnValues(String predictor, String response) {
		errorMessage = "";
		ResultSet rs;
		predictorVariableValues.clear();
		responseVariableValues.clear();
		ResultSetMetaData resultSetMetaData;

		try {
			String query = "SELECT" + " " + predictor + ", " + response + " " + "FROM " + dbSchema + "." + table + ";";
			rs = statement.executeQuery(query);
			resultSetMetaData = rs.getMetaData();

			if (rs != null) {
				int columnTypeResponse = resultSetMetaData.getColumnType(2);
				int columnTypePredictor = resultSetMetaData.getColumnType(1);

				if (columnTypeResponse == 1 || columnTypeResponse == 12 || columnTypePredictor == 1
						|| columnTypePredictor == 12) {
					setErrorMessage("Please Select Numeric Values");
					return "fail";

				} else {

					while (rs.next()) {
						switch (columnTypePredictor) {
						case 4:
							predictorVariableValues.add((double) rs.getInt(1));
							break;
						case 5:
							predictorVariableValues.add((double) rs.getInt(1));
							break;
						case 6:
							predictorVariableValues.add((double) rs.getFloat(1));
							break;

						default:
							predictorVariableValues.add((double) rs.getDouble(1));
							break;
						}
						switch (columnTypeResponse) {
						case 4:
							responseVariableValues.add((double) rs.getInt(2));
							break;
						case 5:
							responseVariableValues.add((double) rs.getInt(2));
							break;
						case 6:
							responseVariableValues.add((double) rs.getFloat(2));
							break;

						default:
							responseVariableValues.add((double) rs.getDouble(2));
							break;
						}
					}
					return "success";
				}

			} else {
				return "fail";
			}

		} catch (SQLException e) {
			errorMessage = e.getMessage();
			renderColumnList = false;
			return "fail";
		} catch (Exception e) {
			errorMessage = e.getMessage();
			renderColumnList = false;
			return "fail";
		}
	}

	public void captureTransactionLogTable() {
		try {
			ipAddress = InetAddress.getLocalHost();
			session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			sessionId = session.getId();
			String query = "";
			if (operation.equalsIgnoreCase("login")) {
				query = "insert into  f17x321.f17g305_LOG (USERNAME,IPADDRESS,LOGINTIME,SESSIONID)values(" + "'"
						+ loginBean.getUserName() + "'" + "," + "'" + ipAddress + "'" + "," + "'"
						+ LocalDateTime.now().toString() + "'" + "," + "'" + sessionId + "'" + ");";

			} else {
				query = "Update f17x321.f17g305_LOG SET LOGOUTTIME=" + "'" + LocalDateTime.now().toString() + "'"
						+ " where USERNAME='" + loginBean.getUserName() + "'and SESSIONID ='" + sessionId
						+ "' and LOGINTIME is not null  ;";
			}
			statement.executeUpdate(query);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String processLogout() {
		try {
			errorMessage = "";
			operation = "logout";
			captureTransactionLogTable();
			statement.close();
			connection.close();
			errorMessage = "You have successfully logged out!";
			session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.invalidate();
			return "logout";
		} catch (SQLException e) {
			errorMessage = "You have successfully logged out!";
			return "logout";
		} catch (Exception e) {
			errorMessage = "You have successfully logged out!";
			return "logout";
		}
	}

	public void closeConnection() {
		try {
			rowsMessage = "";
			fileContent = "";
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}