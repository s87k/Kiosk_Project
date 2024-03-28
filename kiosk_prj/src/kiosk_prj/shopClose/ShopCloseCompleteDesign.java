package kiosk_prj.shopClose;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ShopCloseCompleteDesign extends JDialog implements ActionListener {
	
	private JButton jbExit;
	
	public ShopCloseCompleteDesign(ShopCloseDesign scd, String openDate) {
		
		//이미지
		ImageIcon imgBackground = new ImageIcon(getClass().getClassLoader().getResource("DialogComplete.png"));
		ImageIcon BTexit2 = new ImageIcon(getClass().getClassLoader().getResource("BTexit2.png"));
		
		//컴포넌트
		JLabel lbBackground = new JLabel(imgBackground);
		JLabel jlcompleteText1 = new JLabel("마감 되었습니다.");
		JLabel jlcompleteText2 = new JLabel
				("<HTML><BODY><CENTER><font color=\"#461228\">마감일자 : " + openDate + "</font></CENTER></BODY></HTML>");
		jbExit = new JButton(BTexit2);
		jbExit.setBorderPainted(false);
		
		//텍스트 중앙에 정렬
		jlcompleteText1.setHorizontalAlignment(JLabel.CENTER);
		jlcompleteText2.setHorizontalAlignment(JLabel.CENTER);
		
		//폰트
		jlcompleteText1.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jlcompleteText2.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		
		//배치관리자 해제
		setLayout(null);
		
		//컴포넌트 배치
		jlcompleteText1.setBounds(70,115,505,90);
		jlcompleteText2.setBounds(70,185,505,90);
		jbExit.setBounds(220,340,200,90);
		lbBackground.setBounds(0,0,650,550);
		
		//컴포넌트 등록
		add(jlcompleteText1);
		add(jlcompleteText2);
		add(jbExit);
		add(lbBackground);
		
		//이벤트 등록
		jbExit.addActionListener(this);
		
		setAlwaysOnTop(true);//창 제일 위로!!!!!!!!!!!
		setSize(650,550);
		setLocation(scd.getX()+180,scd.getY()+80);
		setResizable(false);//창 크기 변경 불가능
		setVisible(true);
	}//ShopOpenCompleteDesign

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}//actionPerformed
	
	
}//complete