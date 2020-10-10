package p오목;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Rank_Frame extends JFrame {
   private JButton ExitBtn; // 나가기 버튼
   private ArrayList<RankBean> ra = new ArrayList<RankBean>();
   private JLabel rank; // 순위를 넣을 라벨


   public Rank_Frame() {
      setDefaultCloseOperation(DISPOSE_ON_CLOSE); //순위표의 x표시를 눌러도 모든 프레임이 꺼지지 않습니다.

      setSize(700, 500);
      setLocationRelativeTo(null); // 화면 정중앙 코드
      getContentPane().setLayout(null);
      
      JPanel panel;
      panel =new JPanel() {
    	  Image bg;

		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			bg=Toolkit.getDefaultToolkit().getImage("img/rank.jpg");
			g.drawImage(bg, 0,0,700,500,this);
		}
    	  
      };
      panel.setBounds(0, 0, 700, 500);
      getContentPane().add(panel);
      panel.setLayout(null);
      
      Image bg;
      
    
			
      JPanel panel_1 = new JPanel();
      panel_1.setBounds(244, 76, 213, 152);
      panel.add(panel_1);
      
      DBConn db = DBConn.getInstance();
      ra = db.selectRank(); // ArrayList에 DB에서 가져온 정보를 저장합니다.
      //저장한 정보들을 Label에 넣어서 출력합니다.
      for (int i = 0; i < ra.size(); i++) {
          rank = new JLabel(
                i + 1 + "등   " + ra.get(i).getName() + "        승 : " + String.valueOf(ra.get(i).getWin())
                +"        패 : " + String.valueOf(ra.get(i).getLose()));
          panel_1.add(rank);
          
          JButton exitButton = new JButton("나가기");
          exitButton.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
          exitButton.setBounds(300, 420, 97, 23);
          exitButton.setBorderPainted(false);
          exitButton.setContentAreaFilled(false);
          panel.add(exitButton);
          setVisible(true);
          
          exitButton.addActionListener(new ActionListener() {
        	  
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
         
      }
   }
}