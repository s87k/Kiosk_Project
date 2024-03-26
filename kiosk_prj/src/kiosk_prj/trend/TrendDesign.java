package kiosk_prj.trend;

import javax.swing.JButton;
import javax.swing.JDialog;
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
		trendArea = new JTextArea("전체 소비자 트렌드 분석");
		JScrollPane jsTrend = new JScrollPane(trendArea);
		exit = new JButton("나가기");
		
		
		setLayout(null);
		
		jsTrend.setBounds(50, 50, 400, 400);
		exit.setBounds(200, 450, 100, 100);
		
		add(jsTrend);
		add(exit);
		
		TrendEvent te = new TrendEvent(this);
	
		exit.addActionListener(te);	
		
		setBounds(getX(), getY(), 600, 600);
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
