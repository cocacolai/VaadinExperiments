package com.example.testvaadin;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.data.util.TextFileProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("testvaadin")
public class TestvaadinUI extends UI {
	
	FilesystemContainer docs = new FilesystemContainer(new File("C:/Users/Nicole/Documents/NTU"));
	ComboBox docList = new ComboBox("Documents",docs);
	Label viewer = new Label("Select a document");
	
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = TestvaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final HorizontalSplitPanel layout = new HorizontalSplitPanel();
		//layout.setMargin(true);
		layout.addComponent(docList);
		layout.addComponent(viewer);
		setContent(layout);
		
		docList.addValueChangeListener(new ValueChangeListener(){

			public void valueChange(ValueChangeEvent event) {
				viewer.setPropertyDataSource(new TextFileProperty((File)event.getProperty().getValue()));
				
			}
			
		});
		docList.setImmediate(true);

	}

}