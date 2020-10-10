package p오목;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class wPage extends JFrame {
	
	private DBConn db;
	
	private JTextField wField;

	private String Bname;
	private String Wname;

	private int num;
	
	public wPage(String name) {
		
		this.Bname = name;
		
		setTitle("한슬 오목");
		setResizable(false); //false로 하면 크기 조절 불가능
		setPreferredSize(new Dimension(840,630));
		setSize(840,630);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		 JPanel panel;
	     panel = new JPanel() {
	        Image bg;
	           protected void paintComponent(Graphics g) {
	              super.paintComponent(g);
	              bg = Toolkit.getDefaultToolkit().getImage("img/white.jpg");               
	              g.drawImage(bg, 0,0,825,595, this);                         
	           }
	     };
	     panel.setBounds(0, 0, 840, 601);
	     getContentPane().add(panel);
	     panel.setLayout(null);
	     
	     wField = new JTextField();
	     wField.setBounds(340, 492, 140, 39);
	     panel.add(wField);
	     wField.setColumns(10);
		
		JButton SubmitBtn = new JButton("");
		SubmitBtn.setBounds(493, 477, 89, 55);
		SubmitBtn.setBorderPainted(false);
		SubmitBtn.setContentAreaFilled(false);
		getContentPane().add(SubmitBtn);
		
		SubmitBtn.addActionListener(new ActionListener() {
			
			private String id1;

			@Override
			public void actionPerformed(ActionEvent e) {
				Wname = wField.getText();
				
				db = DBConn.getInstance();
				db.getConnection();
				num = db.select(Wname);
				if(num == 0) { // 없으면
					db.insertMember(Wname);
					dispose();
					new gameStart(Bname, Wname);
				}else {
					dispose();
					new gameStart(Bname, Wname);
				}
				dispose();
				
			}
		});dispose();
		
		setVisible(true);
	}
}

