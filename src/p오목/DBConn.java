package p오목;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DBConn {
   // 싱글톤 생성 시작
   private static DBConn instance = new DBConn();

   public static DBConn getInstance() {
      return instance;
   }

   DBConn() {
   };

   // DB 연결
   public Connection getConnection() {
      Connection conn = null;
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         System.out.println("드라이버 적재 성공");
      } catch (ClassNotFoundException e1) {
         System.out.println("드라이버 적재 실패");
      } // 드라이버 적재

      String url = "jdbc:oracle:thin:@net.yjc.ac.kr:1521:orcl";
      String id = "s1801224";
      String pw = "p1801224";

      try {
         conn = DriverManager.getConnection(url, id, pw);
         System.out.println("데이터베이스 연결 성공.");
      } catch (SQLException e) {
         System.out.println("데이터 베이스 연결에 실패하였습니다..");
      }
      return conn;
   }

   // 유저를 체크함
   public int select(String name) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int num = 0;

      try {
         conn = getConnection();
         String sql = "select * from member where name=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, name);
         rs = pstmt.executeQuery();
         if (rs.next()) {
        	 num = 1;
         }else {
        	 num = 0;
         }
           
      } catch (SQLException e) {
      } finally {
         if (rs != null) {
            try {
               rs.close();
            } catch (SQLException e) {
            }
         }
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
            }
         }
         if (conn != null) {
            try {
               conn.close();
            } catch (SQLException e) {
            }
         }
      }
      return num;
   }
   
   // 회원가입
   public void insertMember(String name) {
      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
            conn = getConnection();
            String sql = "insert into member values(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, 0);
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();
      } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "회원가입에 문제가 있습니다.");
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
            }
         }
         if (conn != null) {
            try {
               conn.close();
            } catch (SQLException e) {
            }
         }
      }
   }

   // 입력된 기록을 DB에서 가져와 프레임에 출력합니다.(쿼리문으로 정렬을 해서)
   public ArrayList selectRank() {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs;
      RankBean rbean;
      ArrayList<RankBean> ra = new ArrayList<RankBean>();
      try {
         conn = getConnection();
         //users테이블에 money를 정렬해서 정렬한 가상의 테이블의 행 번호가 5까지인 것까지 보여준다.
         String sql = "select * from (select * from member order by win DESC) where rownum <= 5";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            rbean = new RankBean();
            rbean.setName(rs.getString("name"));
            rbean.setWin(rs.getInt("win"));
            rbean.setLose(rs.getInt("lose"));
            ra.add(rbean);
         }
      } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "실패");
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
            }
         }
         if (conn != null) {
            try {
               conn.close();
            } catch (SQLException e) {
            }
         }
      }
      return ra;
   }
   //승리 기록
   public void updateWin(String name) {
      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = getConnection();
         String sql = "select win from member where name=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, name);
         ResultSet rs=pstmt.executeQuery();
         int win=0;
         if(rs.next()) {
        	 win = Integer.valueOf(rs.getString(1));
         }
         System.out.println("현재 윈 : "+win);
         win+=1;
         sql = "update member set win=? where name=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, win);
         pstmt.setString(2, name);
         pstmt.executeUpdate();
         JOptionPane.showMessageDialog(null, "기록을 완료하였습니다.");
         System.out.println("기록 완료");
      } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "기록에 실패하였습니다.");
      } finally {
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
            }
         }
         if (conn != null) {
            try {
               conn.close();
            } catch (SQLException e) {
            }
         }
      }
   }
   //패배 기록
   public void updateLose(String name) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;

	      try {
	         conn = getConnection();
	         String sql = "select lose from member where name=?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, name);
	         ResultSet rs=pstmt.executeQuery();
	         int lose=0;
	         if(rs.next()) {
	        	 lose = Integer.valueOf(rs.getString(1));
	         }
	         lose+=1;
	         sql = "update member set lose=? where name=?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, lose);
	         pstmt.setString(2, name);
	         pstmt.executeUpdate();
	         JOptionPane.showMessageDialog(null, "기록을 완료하였습니다.");
	         System.out.println("기록 완료");
	      } catch (SQLException e) {
	         JOptionPane.showMessageDialog(null, "기록에 실패하였습니다.");
	      } finally {
	         if (pstmt != null) {
	            try {
	               pstmt.close();
	            } catch (SQLException e) {
	            }
	         }
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (SQLException e) {
	            }
	         }
	      }
	   }
}