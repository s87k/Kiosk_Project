package kiosks;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import kiosks.OrderDesign;
import kiosks.OrderDetailDesgin;
import kiosks.PaymentPageDesign;
import kiosks.dao.OrderMenuDAO;
import kiosks.vo.OrderMenuVO;

public class OrderEvent extends WindowAdapter implements ActionListener, MouseListener, TableModelListener {

	private OrderDesign od;

	private CardLayout cardLayout;

	private JTable cartList;
	private DefaultTableModel dtmCartList;
	private String name, price, orderPrice;

	public OrderEvent(OrderDesign od) {
		this.od = od;

		cartList = od.getCartList();
		dtmCartList = od.getDtmCartList();
		cardLayout = od.getCardLayout();
	}// OrderEvent

	@Override
	public void actionPerformed(ActionEvent ae) {
		// 카드 이동
		if (ae.getSource() == od.getBest()) {
			cardLayout.show(od.getCardPanel(), "0");
		} else if (ae.getSource() == od.getCoffee()) {
			cardLayout.show(od.getCardPanel(), "1");
		} else if (ae.getSource() == od.getNonCoffee()) {
			cardLayout.show(od.getCardPanel(), "2");
		} else if (ae.getSource() == od.getTea()) {
			cardLayout.show(od.getCardPanel(), "3");
		} else if (ae.getSource() == od.getSmoothie()) {
			cardLayout.show(od.getCardPanel(), "4");
		}

		// 메뉴 선택
		if (ae.getSource() == od.getEspresso()) {
			menuDetail("에스프레소", "1000");
		}
		if ((ae.getSource() == od.getAmericano()) || (ae.getSource() == od.getBestAmericano())) {
			menuDetail("아메리카노", "1200");
		}
		if ((ae.getSource() == od.getCafeLatte()) || (ae.getSource() == od.getBestCafeLatte())) {
			menuDetail("카페라떼", "1500");
		}
		if ((ae.getSource() == od.getVanillaLatte()) || (ae.getSource() == od.getBestVanillaLatte())) {
			menuDetail("바닐라라떼", "2000");
		}
		if (ae.getSource() == od.getChocolateLatte()) {
			System.out.println("초고라떼");
		}
		if (ae.getSource() == od.getToffeeNutLatte()) {
			System.out.println("토피넛라떼");
		}
		if (ae.getSource() == od.getGreenTeaLatte()) {
			System.out.println("녹차라떼");
		}
		if (ae.getSource() == od.getDalgonaLatte()) {
			System.out.println("달고나라떼");
		}
		if (ae.getSource() == od.getPeachTea()) {
			System.out.println("복숭아 티");
		}
		if (ae.getSource() == od.getLemonTea()) {
			System.out.println("레몬 티");
		}
		if (ae.getSource() == od.getGrapefruitTea()) {
			System.out.println("자몽 티");
		}
		if (ae.getSource() == od.getCamomileTea()) {
			System.out.println("캐모마일 티");
		}
		if (ae.getSource() == od.getYogurtSmoothie()) {
			System.out.println("요거트 스무디");
		}
		if (ae.getSource() == od.getMilkShake()) {
			System.out.println("딸기 스무디");
		}
		if (ae.getSource() == od.getStrowberrySmoothie()) {
			System.out.println("밀크쉐이크");
		}
		if (ae.getSource() == od.getJavachipFrappe()) {
			System.out.println("자바칩 프라페");
		}

		// 주문 삭제 & 결제
		if (ae.getSource() == od.getCancel()) {
			delete();
		}
		if (ae.getSource() == od.getPay()) {
			sendData();
		}
	}// actionPerformed

	/**
	 * 선택한 메뉴의 이름, 가격을 옵션 선택창에 설정
	 * 
	 * @param name
	 * @param price
	 * @param orderPrice
	 */
	private void menuDetail(String name, String price) {
		OrderDetailDesgin odd = new OrderDetailDesgin(od, "옵션선택");
		odd.getMenuName().setText(name);
		odd.getMenuPrice().setText(price);
		odd.getAddMenu().setText(price + "원 담기");
	}

	/**
	 * 장바구니 행을 선택하고 취소 버튼을 누르면 행 삭제
	 */
	private void delete() {
		int select = od.getCartList().getSelectedRow();
		if (select != -1) {
			od.getDtmCartList().removeRow(select);
		} // end if
	}// delete

	/**
	 * 장바구니 테이블에 변화가 있을 때마다 주문금액 변경
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		if (e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.DELETE) {
			updateOrderPrice();
		}
	}// tableChanged

	/**
	 * 장바구니 테이블의 가격을 다 더해서 주문금액에 반영
	 */
	private void updateOrderPrice() {
		int totalOrderPrice = 0;
		for (int row = 0; row < od.getDtmCartList().getRowCount(); row++) {
			totalOrderPrice += Integer.parseInt(od.getDtmCartList().getValueAt(row, 2).toString());
		}
		od.getJtfOrderPrice().setText(Integer.toString(totalOrderPrice));
	}// updateOrderPrice

	private void sendData() {
		PaymentPageDesign ppd = new PaymentPageDesign();
		// 결제창에 장바구니 데이터 보내기
		int rowCount = od.getDtmCartList().getRowCount();
		for (int row = 0; row < rowCount; row++) {
			Object[] rowData = new Object[od.getDtmCartList().getColumnCount()];
			for (int col = 0; col < od.getDtmCartList().getColumnCount(); col++) {
				rowData[col] = od.getDtmCartList().getValueAt(row, col);
			}
			ppd.getDtmOrderMemuList().addRow(rowData);
		} // end for

		// 결제창에 결제금액 보내기
		ppd.getOrderPrice().setText(od.getJtfOrderPrice().getText());
		od.dispose();
	}// sendData

	private void setMenuImg(String typeCode) {
		OrderMenuDAO omDAO = OrderMenuDAO.getInstance();
		try {
			List<OrderMenuVO> list = omDAO.selectMenuImg(typeCode);

			// 해당하는 카드 패널 가져오기
			JPanel targetPanel = null;
			switch (typeCode) {
			case "0":
				targetPanel = od.getPanel1();
				break;
			case "1":
				targetPanel = od.getPanel2();
				break;
			case "2":
				targetPanel = od.getPanel3();
				break;
			case "3":
				targetPanel = od.getPanel4();
				break;
			case "4":
				targetPanel = od.getPanel5();
				break;
			default:
				// 예외 처리
				break;
			}//end switch

			if (targetPanel != null) {
				// 해당 패널에 메뉴 버튼 추가
//				for (OrderMenuVO menu : list) {
//					JButton menuButton = new JButton(menu.getName());
//					// 버튼에 이미지 추가
//					ImageIcon icon = new ImageIcon(menu.getImagePath());
//					Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//					menuButton.setIcon(new ImageIcon(image));
//					// 버튼 이벤트 등록
//					menuButton.addActionListener(this);
//					targetPanel.add(menuButton);
//				}
			}//end if

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// setMenuImg

	@Override
	public void windowClosed(WindowEvent e) {
		od.dispose();
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
