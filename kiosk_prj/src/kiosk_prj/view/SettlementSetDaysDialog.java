package kiosk_prj.view;

import javax.swing.JDialog;

public class SettlementSetDaysDialog extends JDialog {
	
	private SettlementDesign smd;
	
	public SettlementSetDaysDialog(SettlementDesign smd) {
		
		
		
		
		
		
		
		
		
		
		
		setSize(650,550);
		setLocation(smd.getX()+180,smd.getY()+80);
		setResizable(false);//창 크기 변경 불가능
		setVisible(true);
	}//SettlementSetDaysDialog
	
}//class
