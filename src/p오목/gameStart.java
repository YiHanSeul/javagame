package p오목;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



import java.awt.Font;

public class gameStart extends JFrame {
//	gameStartGUI GUI=new gameStartGUI();
	private String Bname, Wname;
	private JLabel bL, wL;
	public MapSize size= new MapSize();
	public Map map = new Map(size);
	private final int STONE_SIZE=28; //돌 사이즈
//	public gameStart(MapSize m,Map map) {
//		// TODO Auto-generated constructor stub
//		setBackground(new Color(206,167,61)); //배경색 지정
//		map = new Map(size);
//		gameStartGUI d = new gameStartGUI(size, map);
//		addMouseListener(new MouseEventHandler(map, size, d, this));
//		size=m;
//		System.out.println(m);
//		MapSize size = new MapSize();
//		setLayout(null);
//		this.map=map;
//	}
	public gameStart(String Bname, String Wname) {

		this.Bname = Bname;
		this.Wname = Wname;
		
		map = new Map(size);
		gameStartGUI d = new gameStartGUI(size, map);
		addMouseListener(new MouseEventHandler(map, size, d, this, Bname,Wname));
		
		
		setTitle("한슬 오목");
		Image Back;
		setResizable(true); // false로 하면 크기 조절 불가능
		setPreferredSize(new Dimension(840, 630));
		setSize(840, 630);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel;
		panel = new JPanel() {
			Image bg;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				bg = Toolkit.getDefaultToolkit().getImage("img/gameStart1.jpg");
				g.drawImage(bg, 0, 0, 825, 595, this);
				
					
				for(int i=1;i<=10;i++){
					//가로 줄 그리기
					g.drawLine(325-(i*30), i*30, 275+(i*30), i*30); //가로줄
					g.drawLine(25+(i*30), i*30+300,575-(i*30),i*30+300); //가로줄
					g.drawLine((i*30), 323-(i*30), (i*30) , 270+(30*i)); //세로줄
					g.drawLine(270+(i*30), (i*30), 270+(i*30) , 600-(30*i)); //세로줄
					
						}
						

				
							drawStone(g);
			}
			
			public void drawStone(Graphics arg0) {
				for(int y=0;y<size.getSize();y++){
					//System.out.println("ddd22");
					for(int x=0;x<size.getSize();x++){
//						System.out.println("ddd33");
						//배열의 정보가 흑일경우 흑돌, 백일경우 백돌을 그림
						if(map.getXY(y, x)==map.getBlack()) {
							drawBlack(arg0,x,y);
//							System.out.println("ddd");
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
				//System.out.println("블랙");
			}
			public void drawWhite(Graphics arg0,int x,int y){
				//그려질 색을 화이트로 바꿈
				arg0.setColor(Color.WHITE);
				arg0.fillOval(x*size.getCell()+15, y*size.getCell()+15, STONE_SIZE, STONE_SIZE);
				//System.out.println("white");
			}
		};

		panel.setBounds(0, 0, 840, 840 / 12 * 9);
		getContentPane().add(panel);
		panel.setBounds(0, 0, 840, 630);
		getContentPane().add(panel);
		panel.setLayout(null);

		bL = new JLabel(Bname);
		bL.setForeground(Color.WHITE);
		bL.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		bL.setBounds(646, 195, 70, 20);
		panel.add(bL);

		wL = new JLabel(Wname);
		wL.setForeground(Color.black);
		wL.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		wL.setBounds(646, 542, 70, 20);
		panel.add(wL);

		setVisible(true);
		

	}
	public void showPopUp(String message) {
		JOptionPane.showMessageDialog(this, message, "", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
		
	}
}



	
		
	


