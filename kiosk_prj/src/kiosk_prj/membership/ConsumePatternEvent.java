package kiosk_prj.membership;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

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
