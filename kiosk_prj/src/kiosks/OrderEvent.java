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

		// OrderDesign 실행되면 카드에 메뉴이미지버튼 설정하기.
		for (int i = 0; i < 5; i++) {
			setMenuImg(i + "");
		}

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
		JButton clickedBtn = (JButton) ae.getSource();
		String btnText = clickedBtn.getText();
		if (!btnText.isEmpty()) {
			menuDetail(btnText);
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
	 * OrderDesign이 실행되면 각 카드에 메뉴버튼 설정.
	 * 
	 * @param typeCode
	 */
	private void setMenuImg(String typeCode) {
		OrderMenuDAO omDAO = OrderMenuDAO.getInstance();
		try {
			List<OrderMenuVO> list = omDAO.selectMenuImg(typeCode);

			// 해당하는 카드 패널 가져오기
			JPanel targetPanel = null;
			switch (typeCode) {
			case "0":
				targetPanel = od.getPanel0();
				break;
			case "1":
				targetPanel = od.getPanel1();
				break;
			case "2":
				targetPanel = od.getPanel2();
				break;
			case "3":
				targetPanel = od.getPanel3();
				break;
			case "4":
				targetPanel = od.getPanel4();
				break;
			default:
				// 예외 처리
				break;
			}// end switch

			if (targetPanel != null) {
				// 해당 패널에 메뉴 버튼 추가
				for (OrderMenuVO omVO : list) {

//					String btnName = menu.getMenuCode();
					JButton btnName = new JButton(omVO.getMenuImg());

					// 버튼에 이미지 추가
//					ImageIcon icon = new ImageIcon(menu.getMenuImg());
//					Image image = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
//					menuButton.setIcon(new ImageIcon(image));

					// 버튼 이벤트 등록
					targetPanel.add(btnName);
					btnName.addActionListener(this);

				}
			} // end if

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// setMenuImg

	private void menuDetail(String menuImg) {
		OrderMenuDAO omDAO = OrderMenuDAO.getInstance();
		try {
			List<OrderMenuVO> list = omDAO.selectMenuDetail(menuImg);
			if (!list.isEmpty()) {
				OrderMenuVO omVO;
				omVO = list.get(0);

				OrderDetailDesgin odd = new OrderDetailDesgin(od, "옵션선택");

				odd.getMenuName().setText(omVO.getMenuName());
				odd.getMenuPrice().setText(Integer.toString(omVO.getMenuPrice()));
				odd.getAddMenu().setText(Integer.toString(omVO.getMenuPrice()) + "원 담기");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

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

	/**
	 * 결제 버튼을 누르면 장바구니 정보를 결제창에 보내기
	 */
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

		// 결제창에 주문금액 & 결제금액 보내기
		ppd.getOrderPrice().setText(od.getJtfOrderPrice().getText());
		ppd.getTotalPrice().setText(od.getJtfOrderPrice().getText());
		od.dispose();
	}// sendData

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
