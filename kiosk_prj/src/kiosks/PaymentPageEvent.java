package kiosks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.coupon.dao.CouponHeldDAO;
import kiosk_prj.coupon.vo.CouponHeldVO;
import kiosks.CompletePayPageDesign;
import kiosks.PaymentPageDesign;
import kiosks.PhoneNumDesign;
import kiosks.dao.OrderMenuDAO;
import kiosks.vo.DetailedOrderVO;
import kiosks.vo.OrderMenuVO;
import kiosks.vo.SummaryOrderVO;

public class PaymentPageEvent extends WindowAdapter implements ActionListener, MouseListener {

	private PaymentPageDesign ppd;
	private JTable orderMenuList;
	private DefaultTableModel dtmOrderMemuList;

	private String phoneNum;
	public PaymentPageEvent(PaymentPageDesign ppd) {
		this.ppd = ppd;

		orderMenuList = ppd.getOrderMenuList();
		dtmOrderMemuList = ppd.getDtmOrderMemuList();

	}// PaymentPageEvent
	
	public String searchOrderNum() {
		String orderNum="";
		OrderMenuDAO omDAO = OrderMenuDAO.getInstance();
		
		try {
			orderNum=omDAO.selectOrderNum();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return orderNum;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ppd.getBtnCheckCoupon()) {
			PhoneNumDesign pnd = new PhoneNumDesign(ppd);
		}
		if (e.getSource() == ppd.getCreditCard()) {
			String orderNum=searchOrderNum();
			// 주문 내역 insert
			insertSummaryOrder("카드", orderNum);
			// 상세 주문 내역 insert
			insertDetailedOrder(orderNum);
			// 쿠폰 사용 내역 update
			updateUsedCoupon();

			phoneNum = ppd.getPhoneNum();
			new CompletePayPageDesign(phoneNum);
			ppd.dispose();
		}
		if (e.getSource() == ppd.getCash()) {
			String orderNum=searchOrderNum();
			// 주문 내역 insert
			insertSummaryOrder("현금", orderNum);
			// 상세 주문 내역 insert
			insertDetailedOrder(orderNum);
			// 쿠폰 사용 내역 update
			updateUsedCoupon();

			phoneNum = ppd.getPhoneNum();
			new CompletePayPageDesign(phoneNum);
			ppd.dispose();
		}
	}// actionPerformed

	/**
	 * 주문 내역 insert
	 * @param orderForm
	 */
	public void insertSummaryOrder(String orderForm,String orderNum) {
		// 주문VO에 값 넣기
		SummaryOrderVO soVO = new SummaryOrderVO();

		// 주문 번호 설정
		soVO.setOrderNumber(orderNum);
		// 개점일 shopOpen
		soVO.setShopOpen(StartPageDesign.shopOpen);

		// 주문시간 orderTime
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			//개점날짜를 가져와서 문자열로 변환. 
			//문자열을 날짜로 파싱해서 date객체로 변환.
			date = dateFormat.parse(soVO.getShopOpen());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 시간을 현재 시간의 시간으로 설정하기 위해 Calendar 객체 생성
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 현재 시간을 Calendar 객체에 설정

		// Calendar.getInstance()를 호출하여 현재 시간으로 설정된 Calendar 객체를 가져옴.
		// 현재 시간으로 시간 필드 업데이트
		calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND));

		// 주문 시간으로 설정할 Timestamp 객체 생성
		Timestamp orderTime = new Timestamp(calendar.getTimeInMillis());
		soVO.setOrderTime(orderTime);
		

		// 결제금액 amount
		int totalPrice = Integer.parseInt(ppd.getTotalPrice().getText());
		soVO.setAmount(totalPrice);

		// 진행현황 progress
		soVO.setProgress("주문완료");

		// 결제방법 orderForm
		soVO.setOrderForm(orderForm);

		// 전화번호 phoneNumber
		phoneNum = PaymentPageDesign.strPhoneNum;
		soVO.setPhoneNumber(phoneNum);

		OrderMenuDAO omDAO = OrderMenuDAO.getInstance();
		try {
			omDAO.insertSummaryOrder(soVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// insertSummaryOrder

	/**
	 *  상세 주문 내역 insert
	 */
	public void insertDetailedOrder(String orderNum) {
		// 상세 주문 VO에 값 넣기
		for (int row = 0; row < ppd.getDtmOrderMemuList().getRowCount(); row++) {
			DetailedOrderVO doVO = new DetailedOrderVO();
			// 주문 번호 설정
			doVO.setOrderNumber(orderNum);
			// 개점일 shopOpen
			doVO.setShopOpen(StartPageDesign.shopOpen);
			// 옵션 열 가져오기
			String optionColumn = ppd.getDtmOrderMemuList().getValueAt(row, 1).toString();
			// ,로 분할
			String[] options = optionColumn.split(",");

			String cupOption = "";
			String tempOption = "";
			int shotOption = 0;

			for (String option : options) {
				if (option.equals("매장컵") || option.equals("텀블러")) {
					cupOption = option;
				} else if (option.equals("HOT") || option.equals("ICE")) {
					tempOption = option;
				} else if (option.equals("1샷 추가(+500원)")) {
					shotOption = 1;
				}
			}
			// 컵 옵션 CUP_SIZE
			doVO.setCupSize(cupOption);
			// 온도 옵션 TEMP
			doVO.setTemp(tempOption);
			// 샷 옵션 SHOT
			doVO.setShot(shotOption);
			
			// 메뉴 이름 옵션 열 가져오기
			String menuName = ppd.getDtmOrderMemuList().getValueAt(row, 0).toString();
			OrderMenuDAO omDAO = OrderMenuDAO.getInstance();
			try {
				// 메뉴이름으로 메뉴 코드, 타입 코드 가져오기
				List<OrderMenuVO> list = omDAO.selectCodes(menuName);
				if (!list.isEmpty()) {
					OrderMenuVO omVO = list.get(0);
					String menuCode = omVO.getMenuCode();
					String typeCode = omVO.getTypeCode();

					// 메뉴코드 MENU_CODE
					doVO.setMenuCode(menuCode);
					// 메뉴 타입 TYPE_CODE
					doVO.setTypeCode(typeCode);

					// 상세 주문 insert
					omDAO.insertDetailedOrder(doVO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch

		} // end for
	}// insertDetailedOrder

	/**
	 * 사용 쿠폰 내역 update
	 */
	public void updateUsedCoupon() {
		CouponHeldVO chVO = new CouponHeldVO();
		chVO.setStatusUse("1");  //"0": 사용가능, "1":사용완료, "2":만료됨
		chVO.setCoupPubCode(PaymentPageDesign.coupPubCode);
		CouponHeldDAO chDAO = CouponHeldDAO.getInstance();
		try {
			int cnt = chDAO.updateCoupHeld(chVO);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ppd, "쿠폰 사용 내역 업데이트에 실패했습니다");
			e.printStackTrace();
		} // catch
	}// updateUsedCoupon

	@Override
	public void windowClosed(WindowEvent e) {
		ppd.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}// class
