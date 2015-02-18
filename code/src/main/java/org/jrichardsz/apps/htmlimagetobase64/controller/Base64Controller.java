package org.jrichardsz.apps.htmlimagetobase64.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jrichardsz.apps.htmlimagetobase64.common.util.ImageUtil;
import org.jrichardsz.apps.htmlimagetobase64.view.MainView;

import com.linet.api.swing.filechooser.FileChooserUtil;
import com.linet.util.file.FileUtil;

public class Base64Controller implements ActionListener{

	private MainView mainView;
	private JTextField textFieldPathHtml;
	private JButton buttonPathHtml;
	private JTextField textFieldPatternImage;
	private JButton btnExecute;
	private String pathHtml=null;

	public Base64Controller(MainView mainView){

		this.mainView=mainView;
		textFieldPathHtml=this.mainView.getTextFieldPathHtml();
		buttonPathHtml=this.mainView.getButtonPathHtml();
		textFieldPatternImage=this.mainView.getU001TextFieldPatternImage();
		btnExecute=this.mainView.getU001BtnExecute();

		// add listener
		buttonPathHtml.addActionListener(this);
		btnExecute.addActionListener(this);
	}

	public MainView getMainView(){
		return mainView;
	}

	public void setMainView(MainView mainView){
		this.mainView=mainView;
	}

	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource().equals(buttonPathHtml)){

			// get valid path

			getPathForHtmlFile();

		}
		
		else if(e.getSource().equals(btnExecute)){

			if(pathHtml == null){
				JOptionPane.showMessageDialog(null,"Select a valid path.");
				return;
			}

			// parsing html file
			ArrayList<String> htmlFile=null;
			try{
				htmlFile=(ArrayList<String>) FileUtil.readFileAsStringCollection(pathHtml);
			}
			catch(Exception exception){
				exception.printStackTrace();
				return;
			}

			// we back a folder of path
			String folderPath=FileUtil.backLevelsOfPath(pathHtml,1);
			String fileName = FileUtil.getOnlyPathFromPathFile(pathHtml);
			
			// img-to-load-\d+[.]jpg
			// data:image/png;base64
			Pattern pattern=Pattern.compile(textFieldPatternImage.getText());

			ArrayList<String> finalHtml=new ArrayList<String>();

			for(String line : htmlFile){

				Matcher matcher=pattern.matcher(line);

				if(matcher.find()){
					String strFound=matcher.group();

					String pathImage=folderPath + File.separator + strFound;
					String formatFile = FileUtil.getFormatFromPathFile(pathImage);
					String base64Image=null;

					try{
						
						base64Image=ImageUtil.encodeToString(pathImage,formatFile);

					}
					catch(Exception e1){
						e1.printStackTrace();
						return;
					}

					finalHtml.add(line.replace(strFound,"data:image/"+formatFile+";base64," + base64Image));

				}
				else{
					finalHtml.add(line);
				}
			}

			String pathFinal=null;
			try{
				pathFinal=FileChooserUtil.getFilePathToSave("save final html",fileName+"-ready","html");
			}
			catch(Exception e1){
				e1.printStackTrace();
				return;
			}

			try{
				FileUtil.writeFileFromStringCollection(pathFinal,finalHtml);
			}
			catch(Exception e1){
				e1.printStackTrace();
				return;
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
