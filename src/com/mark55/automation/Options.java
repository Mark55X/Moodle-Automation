package com.mark55.automation;

import javax.swing.JPanel;

import javax.swing.JTextField;


import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class Options extends JPanel{
	
	private static final long serialVersionUID = -6031685273075342908L;
	private JTextField mailField;
	private JTextField pwdField;
	private JLabel lblTitle;
	private JLabel lblPwd;
	private JLabel lblMail;
	private JButton button;
	private JRadioButton radioFirefox;
	private JRadioButton radioChrome;
	private JRadioButton radioEdge;
	private JRadioButton radioSafari;
	private JRadioButton radioOpera;
	private ButtonGroup radioBrowser;
			
	public Options() {
		setLayout(null);
		
		//Get env variable value
		Map<String,String> env = System.getenv();
		String userEnv = env.get("MOODLE_USER");
		String pwdEnv = env.get("MOODLE_PWD");
		
		mailField = new JTextField();
		mailField.setBounds(158, 54, 111, 25);
		if(userEnv!=null) mailField.setText(userEnv);
		add(mailField);
		mailField.setColumns(10);
		
		pwdField = new JTextField();
		pwdField.setColumns(10);
		pwdField.setBounds(158, 90, 111, 25);
		if(pwdEnv!=null) pwdField.setText(pwdEnv);
		add(pwdField);
		
		lblMail = new JLabel("Mail (senza dominio)");
		lblMail.setBounds(33, 51, 136, 31);
		add(lblMail);
		
		lblPwd = new JLabel("Password");
		lblPwd.setBounds(43, 87, 81, 31);
		add(lblPwd);
		
		lblTitle = new JLabel("MOODLE AUTOMATION");
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblTitle.setBounds(89, 11, 227, 32);
		add(lblTitle);
		
		button = new JButton("AVVIA");
		button.addActionListener(e -> {
			String mail = mailField.getText();
			String pwd = pwdField.getText();
			//Controllo O.S
			String os = System.getProperty("os.name");
			if(os.contains("Windows")) {
				try {
					ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", String.format("setx MOODLE_USER %s",mail));
					builder.start();
					builder = new ProcessBuilder("cmd.exe", "/c", String.format("setx MOODLE_PWD %s",pwd));
					builder.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else {
				//UNIX LIKE OS
				
			}
			Automation automation = new Automation(radioBrowser.getSelection().getActionCommand());
			automation.activate(mail, pwd);
		});
		button.setBounds(147, 217, 111, 31);
		add(button);
		
		radioBrowser = new ButtonGroup();
		radioFirefox = new JRadioButton("Firefox");
		radioFirefox.setBounds(175, 144, 109, 23);
		radioFirefox.setActionCommand("firefox");
		add(radioFirefox);
		
		radioChrome = new JRadioButton("Chrome");
		radioChrome.setBounds(60, 144, 109, 23);
		radioChrome.setSelected(true);
		radioChrome.setActionCommand("chrome");
		add(radioChrome);
		
		radioEdge = new JRadioButton("Edge");
		radioEdge.setBounds(281, 144, 109, 23);
		radioEdge.setActionCommand("edge");
		add(radioEdge);
		
		radioOpera = new JRadioButton("Opera");
		radioOpera.setBounds(226, 177, 109, 23);
		radioOpera.setActionCommand("opera");
		add(radioOpera);
		
		radioSafari = new JRadioButton("Safari");
		radioSafari.setBounds(104, 177, 109, 23);
		radioSafari.setActionCommand("safari");
		add(radioSafari);
		
		radioBrowser.add(radioFirefox);
		radioBrowser.add(radioChrome);
		radioBrowser.add(radioSafari);
		radioBrowser.add(radioEdge);
		radioBrowser.add(radioOpera);
	
	}
}
