package kiosk_prj.trend;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kiosk_prj.adminMain.AdminMainPageDesign;

@SuppressWarnings("serial")
public class TrendDesign extends JDialog{
	private AdminMainPageDesign ampd;
	private JTextArea trendArea;
	private JButton exit;
	
	public TrendDesign(AdminMainPageDesign ampd, String title) {
		super(ampd, "소비 트렌드 분석");
		this.ampd = ampd;
		
		//컴포넌트
		ImageIcon imgBackground = new ImageIcon(getClass().getClassLoader().getResource("DialogTrend.png"));
		ImageIcon imgExit = new ImageIcon(getClass().getClassLoader().getResource("BTexit2.png"));
		JLabel lbBackground = new JLabel(imgBackground);	
		trendArea = new JTextArea("전체 소비자 트렌드 분석");
		trendArea.setEditable(false);
		JScrollPane jsTrend = new JScrollPane(trendArea);
		exit = new JButton(imgExit);
		
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		
		//폰트
		trendArea.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		
		setLayout(null);
		
		jsTrend.setBounds(75, 100, 450, 320);
		exit.setBounds(200, 445, 200, 90);
		lbBackground.setBounds(0,0,600,600);
		
		add(jsTrend);
		add(exit);
		add(lbBackground);
		
		TrendEvent te = new TrendEvent(this);
		exit.addActionListener(te);	
		
		setBounds(ampd.getX()+220, ampd.getY()+80,600,600);
		setVisible(true);
	}

	public AdminMainPageDesign getAmpd() {
		return ampd;
	}

	public void setAmpd(AdminMainPageDesign ampd) {
		this.ampd = ampd;
	}

	public JTextArea getTrendArea() {
		return trendArea;
	}

	public void setTrendArea(JTextArea trendArea) {
		this.trendArea = trendArea;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}
}