package kiosk_prj.controller.memberShipEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kiosk_prj.view.memverShipView.DetailOrderDesign;

public class DetailOrderEvent extends WindowAdapter implements ActionListener {
	private DetailOrderDesign dod;
	
	public DetailOrderEvent(DetailOrderDesign dod) {
		this.dod = dod;
	}//DetailOrderEvent
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == dod.getBack()) {
			dod.dispose();
		}
	}//actionPerformed

}//class
