package com.gduranti.jsftests.fileupload;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

public class FileInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String obs;
	private UploadedFile file;

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
