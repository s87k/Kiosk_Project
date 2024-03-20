package kiosk_prj.view.memverShipView;

import javax.swing.JButton;
import javax.swing.JDialog;

import kiosk_prj.controller.memberShipEvent.DetailMemberEvent;

@SuppressWarnings("serial")
public class DetailMemberDesign extends JDialog {
	private JButton jOrderList, jCouponList, jConsumeTrend;
	
	public DetailMemberDesign(JDialog MemberShipDesign, String title) {
		super(MemberShipDesign, "회원 상세");
		
		//컴포넌트
		jOrderList = new JButton("주문 내역");
		jCouponList = new JButton("보유 쿠폰");
		jConsumeTrend = new JButton("소비 패턴");
		
		//배치 관리자 해제
		setLayout(null);
		
		//컴포넌트 배치
		jOrderList.setBounds(50, 250, 100, 100);
		jCouponList.setBounds(200, 250, 100, 100);
		jConsumeTrend.setBounds(350, 250, 100, 100);
		
		//컴포넌트 추가
		add(jOrderList);
		add(jCouponList);
		add(jConsumeTrend);
		
		//ActionListener 추가
		DetailMemberEvent dme = new DetailMemberEvent(this);
		jOrderList.addActionListener(dme);
		jCouponList.addActionListener(dme);
		jConsumeTrend.addActionListener(dme);
		
		setVisible(true);
		setBounds(MemberShipDesign.getX()+350, MemberShipDesign.getY(), 574,648);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public JButton getjOrderList() {
		return jOrderList;
	}

	public void setjOrderList(JButton jOrderList) {
		this.jOrderList = jOrderList;
	}

	public JButton getjCouponList() {
		return jCouponList;
	}

	public void setjCouponList(JButton jCouponList) {
		this.jCouponList = jCouponList;
	}

	public JButton getjConsumeTrend() {
		return jConsumeTrend;
	}

	public void setjConsumeTrend(JButton jConsumeTrend) {
		this.jConsumeTrend = jConsumeTrend;
	}
}
