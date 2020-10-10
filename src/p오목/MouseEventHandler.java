package p오목;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class MouseEventHandler extends MouseAdapter {
	private Map map;
	private MapSize ms;
	private gameStartGUI d;
	private gameStart main;
	String Bname,Wname;
	
	public MouseEventHandler(Map m, MapSize ms,gameStartGUI d, gameStart main, String Bname, String Wname) {
		map =m;
		this.ms=ms;
		this.d = d;
		this.main =main;
		this.Bname = Bname;
		this.Wname = Wname;
		System.out.println("흑돌 : "+this.Bname+"/ 백돌 : "+this.Wname);
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		
		int x= (int) Math.round(arg0.getX() / (double)ms.getCell())-1;
		int y= (int) Math.round(arg0.getY() /  (double)ms.getCell())-2;
		if(x<0|| x>19|| y<0||y>19||(x<9 && y==0)||(x<8 && y==1)||(x<7 && y==2)||(x<6 && y==3)||(x<5 && y==4)||(x<4 && y==5)||(x<3 && y==6)||(x<2 && y==7)||(x<1 && y==8)//왼위
				||(x<1 && y==10)||(x<2 && y==11)||(x<3 && y==12)||(x<4 && y==13)||(x<5 && y==14)||(x<6 && y==15)||(x<7 && y==16)||(x<8 && y==17)||(x<9 && y==18)||(x<10 && y==19)//왼아
				||(x>9 && y==0)||(x>10 && y==1)||(x>11 && y==2)||(x>12 && y==3)||(x>13 && y==4)||(x>14 && y==5)||(x>15 && y==6)||(x>16 && y==7)||(x>17 && y==8)||(x>18 && y==9)//오위
				||(x>17 && y==10)||(x>16 && y==11)||(x>15 && y==12)||(x>14 && y==13)||(x>13 && y==14)||(x>12 && y==15)||(x>11 && y==16)||(x>10 && y==17)||(x>9 && y==18)||(x>8 && y==19)) {
			return;
		}
			if(map.getXY(y, x)==map.getBlack()
					|| map.getXY(y, x)==map.getWhite()) {//이미놓여진경우 return
				return;
			}
			System.out.println(x+ " "+y); //눌린 좌표값 확인
			map.setMap(y, x);//입력된 좌표를 배열에 정보표시
			map.changeCheck();
			
			main.repaint();//맵을 새롭게 다시그림
			
			if(map.winCheck(x, y)) {//승리확인을 하고 승리한쪽이 나올 경우 팝업창을 띄움
				DBConn db = DBConn.getInstance();
				if(map.getCheck()==true) {
					System.out.println("백돌이 승리했데 : "+Wname);
					db.updateWin(Wname);
					db.updateLose(Bname);
					main.showPopUp("백돌이 승리하였습니다.");
				}else {
					db.updateWin(Bname);
					db.updateLose(Wname);
					main.showPopUp("흑돌이 승리합");
				}
			}
			
			
	}
	
}
