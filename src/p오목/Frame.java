package p오목;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame {
   
	private JButton LoginBtn, RankBtn;
	
   public Frame() {
      setTitle("한슬 오목");
      
      setResizable(false); //false로 하면 크기 조절 불가능
      setPreferredSize(new Dimension(840,630));
      setSize(840,630);
      setLocationRelativeTo(null);
      
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      getContentPane().setLayout(null);
      setVisible(true);
      
      JPanel panel;
      panel = new JPanel() {
         Image bg;
            protected void paintComponent(Graphics g) {
               super.paintComponent(g);
               bg = Toolkit.getDefaultToolkit().getImage("img/back.jpg");               
               g.drawImage(bg, 0,0,825,595, this);                         
            }
      };
      panel.setBounds(0, 0, 840, 630);
      getContentPane().add(panel);
      panel.setLayout(null);
      
      RankBtn = new JButton("");
      RankBtn.setBounds(535, 370, 200, 50);
      RankBtn.setBorderPainted(false);
      RankBtn.setContentAreaFilled(false);
      panel.add(RankBtn);
      
      RankBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new Rank_Frame();
		}
	});
      
      LoginBtn = new JButton(" ");
      LoginBtn.setBounds(535, 460, 200, 50);
      LoginBtn.setBorderPainted(false);
      LoginBtn.setContentAreaFilled(false);
      panel.add(LoginBtn);
      
      LoginBtn.addActionListener(new ActionListener() {
          
          @Override
          public void actionPerformed(ActionEvent e) {
             dispose();
             new bPage();
             
          }
       });
    
      
      
     
      /*
       * ImageIcon logo1; logo1 = new ImageIcon("img/오목표지미완.jpg"); panel = new
       * JPanel() { public void paintComponents(Graphics g) {
       * g.drawImage(logo1.getImage(), 0,0,700,640, null); super.paintComponents(g); }
       * }; setContentPane(panel); JLabel a = new JLabel(logo1); add(a);
       */
   
   }

}
