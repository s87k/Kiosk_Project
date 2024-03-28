package kiosk_prj.settlement;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class SettlementShowDialog extends JDialog {
	
	private SettlementDesign smd;
	
	private JTextArea jtaResult;
	private JButton jbExit;
	
	private String startDay, endDay;
	
	public SettlementShowDialog(SettlementDesign smd, String startDay, String endDay) {
		super(smd,"결과 조회",true);
		this.smd = smd;
		this.startDay = startDay;
		this.endDay = endDay;
		
		//이미지
		ImageIcon imgBackground = new ImageIcon(getClass().getClassLoader().getResource("DialogPeriodResult.png"));
		ImageIcon imgBTconfirm = new ImageIcon(getClass().getClassLoader().getResource("BTconfirm.png"));
	
		//컴포넌트
		JLabel lbBackground = new JLabel(imgBackground);
		JLabel lbSettingDate1 = new JLabel("설정 기간의 매출정보");
		JLabel lbSettingDate2 = new JLabel(startDay + " ~ " + endDay);
		jtaResult = new JTextArea();
		jbExit = new JButton(imgBTconfirm);
		JScrollPane jsp = new JScrollPane(jtaResult);
		
		jtaResult.setEditable(false);
		
		//버튼 테두리,배경 삭제
		jbExit.setBorderPainted(false);
		jbExit.setContentAreaFilled(false);
		
		//텍스트 중앙에 정렬
		lbSettingDate1.setHorizontalAlignment(JLabel.CENTER);
		lbSettingDate2.setHorizontalAlignment(JLabel.CENTER);
		
		//폰트
		lbSettingDate1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lbSettingDate2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jtaResult.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		//배치관리자 해제
		setLayout(null);
		
		//컴포넌트 배치
		jsp.setBounds(20,150,595,290);
		jbExit.setBounds(250,450,150,50);
		lbSettingDate1.setBounds(20,75,595,35);
		lbSettingDate2.setBounds(20,115,595,25);
		lbBackground.setBounds(0,0,650,550);
		
		//컴포넌트 등록
		add(jsp);
		add(jbExit);
		add(lbSettingDate1);
		add(lbSettingDate2);
		add(lbBackground);
		
		SettlementEvent sme = new SettlementEvent(this);
		sme.setSmd(smd);
		jbExit.addActionListener(sme);
		
		setSize(650,550);
		setLocation(smd.getX()+180,smd.getY()+80);
		setResizable(false);//창 크기 변경 불가능
		setVisible(true);
	}//SettlementShowDialog

	public JTextArea getJtaResult() {
		return jtaResult;
	}

	public JButton getJbExit() {
		return jbExit;
	}

	public String getStartDay() {
		return startDay;
	}

	public String getEndDay() {
		return endDay;
	}
	
}//class
