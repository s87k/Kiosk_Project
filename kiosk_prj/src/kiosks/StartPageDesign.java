package kiosks;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import kiosk_prj.adminMain.AdminMainPageDesign;
import kiosks.StartPageEvent;
import kiosks.vo.SummaryOrderVO;

@SuppressWarnings("serial")
public class StartPageDesign extends JFrame{

	private JButton store, takeOut;
	private JLabel logo;
	
	public StartPageDesign() {
		design();
	}//StartPageDesign
	public StartPageDesign(AdminMainPageDesign ampd) {
		design();
		SummaryOrderVO soVO = new SummaryOrderVO();
		soVO.setShopOpen(ampd.getJlOpenDate().getText());
	}//StartPageDesign
	
	private void design() {
		store = new JButton("매장");
		takeOut = new JButton("포장");
		
		//logo 추가
		logo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("login_logo.png")));
        
		setLayout(null);
		
		add(store);
		add(takeOut);
		add(logo);
		
		store.setBounds(100, 480, 150, 100);
		takeOut.setBounds(330, 480, 150, 100);
		logo.setBounds(140, 110, 300, 300);
		
		StartPageEvent spe = new StartPageEvent(this);
		store.addActionListener(spe);
		takeOut.addActionListener(spe);
		
		addWindowListener(spe);
		
		setSize(600,800);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}//design

	public static void main(String[] args) {
		new StartPageDesign();
	}//main

	public JButton getStore() {
		return store;
	}

	public JButton getTakeOut() {
		return takeOut;
	}

}//class
