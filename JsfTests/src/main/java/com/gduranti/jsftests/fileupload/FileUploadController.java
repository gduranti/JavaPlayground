package com.gduranti.jsftests.fileupload;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.event.FileUploadEvent;
import java.io.Serializable;

@Named
@ViewAccessScoped
public class FileUploadController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<FileInfoDTO> files;

	@PostConstruct
	public void init() {
		files = new ArrayList<>();
	}

	public void handleFileUpload(FileUploadEvent event) {
		FileInfoDTO item = new FileInfoDTO();
		item.setFile(event.getFile());
		files.add(item);
		addMessage("Succesful", event.getFile().getFileName() + " was uploaded.");
	}

	public List<FileInfoDTO> getFiles() {
		return files;
	}

	public void remove(FileInfoDTO itemTeste) {
		files.remove(itemTeste);
		addMessage("Succesful", itemTeste.getFile().getFileName() + " was removed. Actual list size is " + files.size());
	}

	private void addMessage(String sumary, String detail) {
		FacesMessage message = new FacesMessage(sumary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
