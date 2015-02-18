package org.jrichardsz.apps.htmlimagetobase64.view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import org.jrichardsz.apps.htmlimagetobase64.controller.JSoupBase64Controller;

import com.linet.api.swing.jframe.JFrameUtil;
import com.linet.api.swing.lookandfeel.WindowUtilities;

public class MainView {

	private JFrame frame;
	private JTextField textFieldPathHtml;
	private JButton buttonPathHtml;
	private JTextField u001TextFieldPatternImage;
	private JLabel u001JLabelSrcPattern;
	private JButton u001BtnExecute;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					WindowUtilities.setNativeLookAndFeel();
					
					MainView window = new MainView();
					window.frame.setVisible(true);
					
					//initializing controlers
					//new JSoupBase64Controler(window);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 500,332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldPathHtml = new JTextField();
		textFieldPathHtml.setBounds(10, 11, 398, 20);
		frame.getContentPane().add(textFieldPathHtml);
		textFieldPathHtml.setColumns(10);
		
		buttonPathHtml = new JButton("...");
		buttonPathHtml.setBounds(418, 10, 66, 23);
		frame.getContentPane().add(buttonPathHtml);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 42, 474, 255);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel001 = new JPanel();
		tabbedPane.addTab("Image to B64 with Pattern", null, panel001, null);
		panel001.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel001.setLayout(null);
		
		u001TextFieldPatternImage = new JTextField();
		u001TextFieldPatternImage.setText("img-to-load-\\d+[.]jpg");
		u001TextFieldPatternImage.setBounds(10, 35, 191, 20);
		panel001.add(u001TextFieldPatternImage);
		u001TextFieldPatternImage.setColumns(10);
		
		u001BtnExecute = new JButton("execute");
		u001BtnExecute.setBounds(10, 196, 91, 23);
		panel001.add(u001BtnExecute);
		
		u001JLabelSrcPattern = new JLabel("Sra Pattern of Image");
		u001JLabelSrcPattern.setBounds(10, 11, 191, 14);
		panel001.add(u001JLabelSrcPattern);

		try{
			JFrameUtil.setDefaultIconApp(frame);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTextFieldPathHtml() {
		return textFieldPathHtml;
	}

	public void setTextFieldPathHtml(JTextField textFieldPathHtml) {
		this.textFieldPathHtml = textFieldPathHtml;
	}

	public JButton getButtonPathHtml() {
		return buttonPathHtml;
	}

	public void setButtonPathHtml(JButton buttonPathHtml) {
		this.buttonPathHtml = buttonPathHtml;
	}

	public JTextField getU001TextFieldPatternImage(){
		return u001TextFieldPatternImage;
	}

	public void setU001TextFieldPatternImage(JTextField u001TextFieldPatternImage){
		this.u001TextFieldPatternImage=u001TextFieldPatternImage;
	}

	public JButton getU001BtnExecute(){
		return u001BtnExecute;
	}

	public void setU001BtnExecute(JButton u001BtnExecute){
		this.u001BtnExecute=u001BtnExecute;
	}

	

}
