package kiosk_prj.controller.memberShipEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kiosk_prj.view.memverShipView.ConsumePatternDesign;

public class ConsumePatternEvent extends WindowAdapter implements ActionListener{
	private ConsumePatternDesign cspd;
	public ConsumePatternEvent(ConsumePatternDesign cspd) {
		this.cspd = cspd;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cspd.getBack()) {
			cspd.dispose();
		}
	}

}
