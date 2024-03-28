package kiosk_prj.shopOpen;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ShopOpenConfirmDesign extends JDialog {
	
	private JLabel mainJlOpenDate;
	private String openDate;
	
	private JButton jbOk, jbNo;
	private JLabel jlConfirmText;
	
	
	public ShopOpenConfirmDesign() {
	}//public ShopOpenConfirmDesign
	
	public ShopOpenConfirmDesign(ShopOpenDesign sod, String openDate) {
		super(sod, "개점", true);
		this.openDate = openDate;
		
		//이미지
		ImageIcon imgBackground = new ImageIcon(getClass().getClassLoader().getResource("DialogConfirm.png"));
		ImageIcon imgOK = new ImageIcon(getClass().getClassLoader().getResource("BTok.png"));
		ImageIcon imgNO = new ImageIcon(getClass().getClassLoader().getResource("BTno.png"));
		//컴포넌트
		JLabel lbBackground = new JLabel(imgBackground);
		jbOk = new JButton(imgOK);
		jbNo = new JButton(imgNO);
		jlConfirmText = new JLabel
				("<HTML><BODY><CENTER><font color=\"#461228\">개점 설정일 : "
					+ openDate
					+ "</font<br>해당 일로 개점일을 설정하시겠습니까?</CENTER></BODY></HTML>");
		jbOk.setBorderPainted(false);
		jbNo.setBorderPainted(false);
		
		//폰트
		jlConfirmText.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		
		//텍스트 중앙에 정렬
		jlConfirmText.setHorizontalAlignment(JLabel.RIGHT);
		
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
		ShopOpenEvent soe = new ShopOpenEvent(sod, this);
		jbOk.addActionListener(soe);
		jbNo.addActionListener(soe);
		
		soe.setSocd(this); //이벤트 클래스를 같이 쓰기위한 세터. 인스턴스를 넣어줘야함ㅠㅠ
		
		setSize(650,550);
		setLocation(sod.getX()+180,sod.getY()+80);
		setResizable(false);//창 크기 변경 불가능
		setVisible(true);
	}//ShopOpenConfirmDesign

	public JLabel getMainJlOpenDate() {
		return mainJlOpenDate;
	}

	public String getOpenDate() {
		return openDate;
	}

	public JButton getJbOk() {
		return jbOk;
	}

	public JButton getJbNo() {
		return jbNo;
	}

	public JLabel getJlConfirmText() {
		return jlConfirmText;
	}
	
}//class
