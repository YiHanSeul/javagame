package p오목;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class bPage extends JFrame {

	private DBConn db;
	
	private JTextField bField;
	private JButton SubmitBtn;
	
	private String Bname;
	
	private int num;
	
	
	
	public bPage() {
		setTitle("한슬 오목");

		setResizable(false); // false로 하면 크기 조절 불가능
		setPreferredSize(new Dimension(840,630));
		setSize(840,630);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel;
		panel = new JPanel() {
			Image bg;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				bg = Toolkit.getDefaultToolkit().getImage("img/black.jpg");
				g.drawImage(bg, 0, 0, 825, 595, this);
			}
		};
		panel.setBounds(0, 0, 840, 601);
		getContentPane().add(panel);
		panel.setLayout(null);

		bField = new JTextField();
		bField.setBounds(340, 492, 140, 39);
		panel.add(bField);
		bField.setColumns(10);

		SubmitBtn = new JButton("");
		SubmitBtn.setBounds(493, 477, 89, 55);
		SubmitBtn.setBorderPainted(false);
		SubmitBtn.setContentAreaFilled(false);
		getContentPane().add(SubmitBtn);

		SubmitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Bname = bField.getText();
				
			    db = DBConn.getInstance();
			    db.getConnection();
				num = db.select(Bname);
				if(num == 0) { // 없으면
					db.insertMember(Bname);
					dispose();
					new wPage(Bname);
				}else {
					dispose();
					new wPage(Bname);
				}
			    dispose();
			    new wPage(Bname);
			}
		});

		setVisible(true);
	}
}
