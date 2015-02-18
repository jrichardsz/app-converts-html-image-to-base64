package org.jrichardsz.apps.htmlimagetobase64.view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.jrichardsz.apps.htmlimagetobase64.controller.JSoupBase64Controller;

import com.linet.api.swing.lookandfeel.WindowUtilities;

public class EntryView {

	private JFrame frmImgHtmlTo;
	private JTextField textFieldPathHtml;
	private JButton executeButton;
	private JButton openButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowUtilities.setNativeLookAndFeel();
					EntryView window = new EntryView();
					window.frmImgHtmlTo.setVisible(true);
					//initializing controlers
					new JSoupBase64Controller(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EntryView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmImgHtmlTo = new JFrame();
		frmImgHtmlTo.setTitle("Img Html to Base 64");
		frmImgHtmlTo.setBounds(100, 100, 450, 116);
		frmImgHtmlTo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImgHtmlTo.getContentPane().setLayout(null);
		
		textFieldPathHtml = new JTextField();
		textFieldPathHtml.setBounds(10, 11, 338, 20);
		frmImgHtmlTo.getContentPane().add(textFieldPathHtml);
		textFieldPathHtml.setColumns(10);
		
		openButton = new JButton("Open");
		openButton.setBounds(363, 10, 69, 23);
		frmImgHtmlTo.getContentPane().add(openButton);
		
		executeButton = new JButton("Execute");
		executeButton.setBounds(170, 53, 91, 23);
		frmImgHtmlTo.getContentPane().add(executeButton);
	}
	
	
	public JButton getExecuteButton() {
		return executeButton;
	}
	public JButton getOpenButton() {
		return openButton;
	}
	public JTextField getTextFieldPathHtml() {
		return textFieldPathHtml;
	}
}
