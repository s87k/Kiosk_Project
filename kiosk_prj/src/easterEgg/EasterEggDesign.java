package easterEgg;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kiosk_prj.adminMain.AdminMainPageDesign;

@SuppressWarnings("serial")
public class EasterEggDesign extends JDialog{
	private AdminMainPageDesign ampd;
	private JTextArea easterEggArea;
	private JButton exit;
	
	public EasterEggDesign(AdminMainPageDesign ampd, String title) {
		super(ampd, "이스터에그~~~");
		this.ampd = ampd;
		
		
		easterEggArea = new JTextArea("이스터에그");
		JScrollPane jsEasterEgg = new JScrollPane(easterEggArea);
		exit = new JButton("나가쇼 ㅋㅋ");
		
		setLayout(null);
		
		jsEasterEgg.setBounds(50, 50, 400, 400);
		exit.setBounds(200, 450, 100, 100);
		
		add(jsEasterEgg);
		add(exit);
		
		EasterEggEvent eee = new EasterEggEvent(this);
		exit.addActionListener(eee);
		
		setVisible(true);
		setBounds(getX(), getY(), 600, 600);
	}

	public JTextArea getEasterEggArea() {
		return easterEggArea;
	}

	public void setEasterEggArea(JTextArea easterEggArea) {
		this.easterEggArea = easterEggArea;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

}
