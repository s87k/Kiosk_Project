package kiosk_prj.shopClose;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ShopCloseConfirmDesign extends JDialog{
	
	private ShopCloseDesign scd;
	
	private JButton jbOk, jbNo;
	private JLabel jlConfirmText;
	
	public ShopCloseConfirmDesign() {
	}//ShopCloseConfirmDesign
	
	public ShopCloseConfirmDesign(ShopCloseDesign scd, String openDate) {
		super(scd, "개점", true);
		this.scd = scd;
		
		//이미지
		ImageIcon imgBackground = new ImageIcon(getClass().getClassLoader().getResource("DialogConfirm.png"));
		ImageIcon imgOK = new ImageIcon(getClass().getClassLoader().getResource("BTok.png"));
		ImageIcon imgNO = new ImageIcon(getClass().getClassLoader().getResource("BTno.png"));

		//컴포넌트
		JLabel lbBackground = new JLabel(imgBackground);
		jbOk = new JButton(imgOK);
		jbNo = new JButton(imgNO);
		jlConfirmText = new JLabel("<HTML><BODY><CENTER><font color=\"#461228\">" + openDate
				+ "</font>일자로 마감을 하시겠습니까?</CENTER></BODY></HTML>");
		
		//버튼 테두리 삭제
		jbOk.setBorderPainted(false);
		jbNo.setBorderPainted(false);
		
		//폰트
		jlConfirmText.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		
		//텍스트 중앙에 정렬
		jlConfirmText.setHorizontalAlignment(JLabel.CENTER);
		
		//배치관리자 해제
		setLayout(null);
		
		//컴포넌트 배치
		jlConfirmText.setBounds(50,110,550,200);
		jbNo.setBounds(90,340,200,90);
		jbOk.setBounds(360,340,200,90);
		lbBackground.setBounds(0,0,650,550);
		
		//컴포넌트 등록
		add(jlConfirmText);
		add(jbOk);
		add(jbNo);
		add(lbBackground);
		
		//이벤트 등록
		ShopCloseEvent sce = new ShopCloseEvent(scd, this);
		jbOk.addActionListener(sce);
		jbNo.addActionListener(sce);
		
		
		sce.setSccd(this); //이벤트 클래스를 같이 쓰기위한 세터. 인스턴스를 넣어줘야함ㅠㅠ
		setSize(650,550);
		setLocation(scd.getX()+180,scd.getY()+80);
		setResizable(false);//창 크기 변경 불가능
		setVisible(true);
	}//ShopOpenConfirmDesign

	public JButton getJbOk() {
		return jbOk;
	}

	public JButton getJbNo() {
		return jbNo;
	}
	
}//class
