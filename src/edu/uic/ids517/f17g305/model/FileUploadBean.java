package edu.uic.ids517.f17g305.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.myfaces.custom.fileupload.UploadedFile;

@ManagedBean(name = "fileUploadBean")
@SessionScoped
public class FileUploadBean {

	private UploadedFile uploadedFile;

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
}
