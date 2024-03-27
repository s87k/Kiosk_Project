package kiosks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kiosks.OrderDesign;
import kiosks.StartPageDesign;
import kiosks.vo.SummaryOrderVO;

public class StartPageEvent extends WindowAdapter implements ActionListener {

	private StartPageDesign spd;
	
	public StartPageEvent(StartPageDesign spd) {
		this.spd = spd;
	}//StartPageEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == spd.getStore()) {
			spd.dispose();
			new OrderDesign();
		}//end if
		
		if(e.getSource() == spd.getTakeOut()) {
			spd.dispose();
			new OrderDesign();
		}//end if
		
	}//actionPerformed

	@Override
	public void windowClosed(WindowEvent e) {
		spd.dispose();
	}
	
	

}//class
