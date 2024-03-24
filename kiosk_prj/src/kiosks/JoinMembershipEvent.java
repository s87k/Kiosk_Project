package kiosks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kiosks.JoinMembershipDesign;
import kiosks.PaymentPageDesign;

public class JoinMembershipEvent extends WindowAdapter implements ActionListener{

	private JoinMembershipDesign jmd;
	
	public JoinMembershipEvent(JoinMembershipDesign jmd) {
		this.jmd = jmd;
	}//JoinMembershipEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jmd.getCancel()) {
			new PaymentPageDesign();
			jmd.dispose();
		}
		if(e.getSource() == jmd.getJoin()) {
			new PaymentPageDesign();
			jmd.dispose();
		}
	}//actionPerformed

	@Override
	public void windowClosed(WindowEvent e) {
		jmd.dispose();
	}

}//class
