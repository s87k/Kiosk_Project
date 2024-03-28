package easterEgg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class EasterEggEvent extends WindowAdapter implements ActionListener{
	private EasterEggDesign eed;
	
	public EasterEggEvent(EasterEggDesign eed) {
		this.eed = eed;
		try {
			lolEasterEggLol();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == eed.getExit()) {
			eed.dispose();
		}
		
	}
	
	public void lolEasterEggLol() throws InterruptedException {
		eed.getEasterEggArea().setText("사랑합니다.\n");
		eed.getEasterEggArea().append("언젠가 한번쯤은 돌아봐주겠죠.\n");;
		eed.getEasterEggArea().append("한 없이 뒤에서 기다리면\n");
		eed.getEasterEggArea().append("------------------------\n");
		eed.getEasterEggArea().append("좋아합니다.\n");
		eed.getEasterEggArea().append("아아아아 참으려 해봤지만~~~\n");
		eed.getEasterEggArea().append("더는 안되겠어요 오오오~~~\n");
		eed.getEasterEggArea().append("이제야 아아아아아\n");
		eed.getEasterEggArea().append("말할 수 있겠어요\n");
		eed.getEasterEggArea().append("사랑하고 싶어요~~ 그댈~~");
		
	}
}
