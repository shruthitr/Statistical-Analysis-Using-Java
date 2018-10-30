package edu.uic.ids517.f17g305.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.OutputStream;
import java.util.ArrayList;

@ManagedBean(name = "fileDownloadBean")
@SessionScoped
public class FileDownloadBean {

	private String errorMessage = "";

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String downloadFile(String[] columnNames, ArrayList<String> result, String name) {

		errorMessage = "";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		FileOutputStream fileOutputStream = null;
		FileInputStream fileInputStream = null;
		String path = externalContext.getRealPath("/files");
		File tempFile = new File(path);
		if (!tempFile.exists()) {
			new File(path).mkdirs();
		}
		String fileName = path + "/" + name;
		File file = new File(fileName);
		StringBuffer stringBuffer = new StringBuffer();
		try {
			fileOutputStream = new FileOutputStream(fileName);
			for (int i = 0; i < columnNames.length; i++) {
				stringBuffer.append(columnNames[i] + ",");
			}
			stringBuffer.append("\n");
			fileOutputStream.write(stringBuffer.toString().getBytes());
			stringBuffer = new StringBuffer();
			for (int i = 0; i < result.size(); i++) {
				stringBuffer.append(result.get(i));
				stringBuffer.append(",");
			}
			fileOutputStream.write(stringBuffer.toString().getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
			String mimeType = externalContext.getMimeType(fileName);
			byte b;
			externalContext.responseReset();
			externalContext.setResponseContentType(mimeType);
			externalContext.setResponseContentLength((int) file.length());
			externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			fileInputStream = new FileInputStream(file);
			OutputStream output = externalContext.getResponseOutputStream();
			while (true) {
				b = (byte) fileInputStream.read();
				if (b < 0)
					break;
				output.write(b);
			}
			facesContext.responseComplete();
			return "success";
		} catch (IOException e) {
			errorMessage = e.getMessage();
			return "fail";
		} catch (Exception e) {
			errorMessage = e.getMessage();
			return "fail";
		}
	}
}
