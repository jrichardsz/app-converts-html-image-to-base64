package org.jrichardsz.apps.htmlimagetobase64.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jrichardsz.apps.htmlimagetobase64.common.util.ImageUtil;
import org.jrichardsz.apps.htmlimagetobase64.view.EntryView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.linet.api.swing.filechooser.FileChooserUtil;
import com.linet.util.file.FileUtil;

public class JSoupBase64Controller implements ActionListener{

	private EntryView mainView;
	private JTextField textFieldPathHtml;
	private JButton openButton;
	private JButton executeButton;
	private String pathHtml=null;

	public JSoupBase64Controller(EntryView mainView){

		this.mainView=mainView;
		textFieldPathHtml=this.mainView.getTextFieldPathHtml();
		openButton=this.mainView.getOpenButton();
		executeButton=this.mainView.getExecuteButton();

		// add listener
		openButton.addActionListener(this);
		executeButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource().equals(openButton)){
			// get valid path
			getPathForHtmlFile();

		} else if(e.getSource().equals(executeButton)){

			if(pathHtml == null){
				JOptionPane.showMessageDialog(null,"Select a valid path.");
				return;
			}
			
			File input = new File(pathHtml);
			Document doc = null;
			try {
				doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			} catch (IOException e2) {
				e2.printStackTrace();
				return;
			}
			
			Elements img = doc.getElementsByTag("img");

			String folderPath=FileUtil.backLevelsOfPath(pathHtml,1);
			String fileName = FileUtil.getNameFromPathFile(pathHtml);
			
			 for (Element imgElement : img){
				 
				// we back a folder of path
				String srcString = imgElement.attr("src");
				String pathImage=folderPath + File.separator + srcString;
				String formatFile = FileUtil.getFormatFromPathFile(pathImage);
				String base64Image=null;

				try{
					
					base64Image=ImageUtil.encodeToString(pathImage,formatFile);

				}
				catch(Exception e1){
					e1.printStackTrace();
					return;
				}
				 
				imgElement.attr("src", "data:image/"+formatFile+";base64," + base64Image);
				 
			 }

			String pathFinal=null;
			try{
				pathFinal=FileChooserUtil.getFilePathToSave("save final html",fileName+"-ready","html");
			}
			catch(Exception e1){
				e1.printStackTrace();
				return;
			}

			
			BufferedWriter htmlWriter = null;
			
			try{
				htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathFinal), "UTF-8"));
				htmlWriter.write(doc.toString());
			}
			catch(Exception e1){
				e1.printStackTrace();
				return;
			}finally{
				if(htmlWriter!=null){
					try {
						htmlWriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		}

	}

	public void getPathForHtmlFile(){
		try{

			if(textFieldPathHtml.getText() != null && !textFieldPathHtml.getText().equals("")){
				pathHtml=FileChooserUtil.getFilePathToOpen("Select path of html to parse",textFieldPathHtml.getText(),null);
			}
			else{
				pathHtml=FileChooserUtil.getFilePathToOpen("Select path of html to parse");
			}
			textFieldPathHtml.setText(pathHtml);
		}
		catch(Exception exception){
			JOptionPane.showMessageDialog(null,exception.toString());
		}
	}

}
