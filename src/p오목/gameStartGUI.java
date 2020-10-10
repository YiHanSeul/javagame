package p오목;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JPanel;


public class gameStartGUI extends JPanel {

public gameStartGUI(MapSize m, Map map) {
	
		// TODO Auto-generated constructor stub
	setBackground(new Color(206,167,61)); //배경색 지정
	size=m;
	setLayout(null);
	this.map=map;
	
		Runnable thread = () -> {
			
		};
	}


	//	new gameStart("오목");
//	private String id1, id2;
//	private JLabel bL, wL;
	public MapSize size;
	public Map map;
	private final int STONE_SIZE=28; //돌 사이즈
	
	
	JPanel panel = new JPanel() {
		Image bg;
		Image rect;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			setVisible(true);
		}
		public void drawStone(Graphics arg0) {
			for (int y = 0; y < size.getSize(); y++) {/* System.out.println("ddd22"); */
				for(int x=0;x<size.getSize();x++){
//					System.out.println("ddd33");
					//배열의 정보가 흑일경우 흑돌, 백일경우 백돌을 그림
					if(map.getXY(y, x)==map.getBlack()) {
						drawBlack(arg0,x,y);
//						System.out.println("ddd");
					}
					else if(map.getXY(y, x)==map.getWhite())
						drawWhite(arg0, x, y);
				}
			}
		}
		public void drawBlack(Graphics arg0,int x,int y){
			//그려질 색을 블랙으로 바꿈
			arg0.setColor(Color.BLACK);
			arg0.fillOval((x+1)*size.getCell()-15, (y)*size.getCell()+15, STONE_SIZE, STONE_SIZE);
			System.out.println("블랙");
		}
		public void drawWhite(Graphics arg0,int x,int y){
			//그려질 색을 화이트로 바꿈
			arg0.setColor(Color.WHITE);
			arg0.fillOval(x*size.getCell()+15, y*size.getCell()+15, STONE_SIZE, STONE_SIZE);
			System.out.println("white");
		}
		
	};
	
}
