package kiosk_prj.settlement;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SettlementSetDaysDialog extends JDialog {
	
	private SettlementDesign smd;
	
	private JLabel jlSetStartDay, jlSetEndDay;
	private JTextField jtfSetStartDay, jtfSetEndDay;
	private JButton jbOk, jbNo;
	
	
	public SettlementSetDaysDialog(SettlementDesign smd) {
		super(smd, "집계기간설정", true);
		this.smd = smd;
		

		//이미지
		ImageIcon imgBackground = new ImageIcon(getClass().getClassLoader().getResource("DialogPeriodSetting.png"));
		ImageIcon imgBTok = new ImageIcon(getClass().getClassLoader().getResource("BTconfirm.png"));
		ImageIcon imgBTno = new ImageIcon(getClass().getClassLoader().getResource("BTcancle.png"));
		
		//컴포넌트
		JLabel lbBackground = new JLabel(imgBackground);
		jlSetStartDay = new JLabel("집계 시작일");
		jlSetEndDay = new JLabel("집계 종료일");
		jtfSetStartDay = new JTextField("2024-");
		jtfSetEndDay = new JTextField("2024-");
		jbOk = new JButton(imgBTok);
		jbNo = new JButton(imgBTno);
		
		JLabel jlNotice = new JLabel();
		String str =
				"<HTML><BODY>"
						+ "입력일은 YYYY-MM-DD의 형식으로 입력해주세요.<br>"
						+ "예시) 2024-03-17"
						+ "</BODY></HTML>";
		jlNotice.setText(str);
		
		//글자수 제한
		
		
		//버튼 테두리,배경 삭제
		jbOk.setBorderPainted(false);
		jbNo.setBorderPainted(false);
		jbOk.setContentAreaFilled(false);
		jbNo.setContentAreaFilled(false);
		
		//폰트
		Font bigFont = new Font("맑은 고딕", Font.BOLD, 30);
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		jtfSetStartDay.setFont(bigFont);
		jtfSetEndDay.setFont(bigFont);
		jlSetStartDay.setFont(font);
		jlSetEndDay.setFont(font);
		jlSetEndDay.setFont(font);
		jlNotice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		//배치관리자 해제
		setLayout(null);
		
		//컴포넌트 배치
		jlSetStartDay.setBounds(80,80,300,40);
		jtfSetStartDay.setBounds(80,120,300,80);
		jlSetEndDay.setBounds(80,200,300,40);
		jtfSetEndDay.setBounds(80,240,300,80);
		jbNo.setBounds(165,420,150,50);
		jbOk.setBounds(335,420,150,50);
		jlNotice.setBounds(81,330,400,70);
		lbBackground.setBounds(0,0,650,550);
		
		//컴포넌트 등록
		add(jlSetStartDay);
		add(jtfSetStartDay);
		add(jlSetEndDay);
		add(jtfSetEndDay);
		add(jbOk);
		add(jbNo);
		add(jlNotice);
		add(lbBackground);
		
		SettlementEvent sme = new SettlementEvent(this);
		sme.setSmd(smd);
		jbOk.addActionListener(sme);
		jbNo.addActionListener(sme);
		jtfSetStartDay.addKeyListener(sme);
		jtfSetEndDay.addKeyListener(sme);
		
		setSize(650,550);
		setLocation(smd.getX()+180,smd.getY()+80);
		setResizable(false);//창 크기 변경 불가능
		setVisible(true);
	}//SettlementSetDaysDialog

	public SettlementDesign getSmd() {
		return smd;
	}

	public JLabel getJlSetStartDay() {
		return jlSetStartDay;
	}

	public JLabel getJlSetEndDay() {
		return jlSetEndDay;
	}

	public JTextField getJtfSetStartDay() {
		return jtfSetStartDay;
	}

	public JTextField getJtfSetEndDay() {
		return jtfSetEndDay;
	}

	public JButton getJbOk() {
		return jbOk;
	}

	public JButton getJbNo() {
		return jbNo;
	}
	
}//class
