package kiosks;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

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
		trendCheck();
		for (int i = 0; i < 5; i++) {
			setMenuImg(i + "");
		}

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
		String btnText = clickedBtn.getName();
		if (btnText != null && (btnText.endsWith(".jpg") || btnText.endsWith(".png"))) {
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

	public void trendCheck() {
		OrderMenuDAO omDAO = OrderMenuDAO.getInstance();
		try {
			List<OrderMenuVO> list = omDAO.selectTop3Img();

			// 각 메뉴 이름의 발생 횟수를 저장하기 위한 Map
			Map<String, Integer> menuCountMap = new HashMap<>();

			// 각 메뉴 이름의 발생 횟수 계산
			for (OrderMenuVO oVO : list) {
				String menuImg = oVO.getMenuImg();
				menuCountMap.put(menuImg, menuCountMap.getOrDefault(menuImg, 0) + 1);
			}

			// 발생 횟수에 따라 정렬
			List<Map.Entry<String, Integer>> sortedMenuCountList = new ArrayList<>(menuCountMap.entrySet());
			sortedMenuCountList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

			// 상위 3개의 메뉴 이미지 버튼 생성
			int count = 0;
			for (Map.Entry<String, Integer> entry : sortedMenuCountList) {
				if (count < 3) {
					JButton btnName = new JButton();

					// 버튼에 이미지 추가
					ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(entry.getKey()));
					Image image = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
					btnName.setIcon(new ImageIcon(image));
					btnName.setName(entry.getKey());

					// 버튼 이벤트 등록
					od.getPanel0().add(btnName);
					btnName.addActionListener(this);

					count++;
				} else {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
					JButton btnName = new JButton();

					// 버튼에 이미지 추가
					ImageIcon icon = null;
					String imagePath = omVO.getMenuImg();
					if(getClass().getClassLoader().getResource(imagePath) != null) {
						icon = new ImageIcon(getClass().getClassLoader().getResource(imagePath));
					}else {
						//이미지가 없으면 버튼에 경로를 추가
						btnName.setText(imagePath);
					}
					
					btnName.setIcon(icon);
					btnName.setName(omVO.getMenuImg());

					// 버튼 이벤트 등록
					btnName.addActionListener(this);
					targetPanel.add(btnName);
				}
			}
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

				// 이미지 경로 변경
				ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(menuImg));
				Image image = icon.getImage();
				Image scaledImage = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
				ImageIcon scaledIcon = new ImageIcon(scaledImage);
				odd.getMenuImg().setIcon(scaledIcon);

				// 메뉴명, 메뉴 가격 설정
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
		PaymentPageDesign.amount = Integer.parseInt(ppd.getTotalPrice().getText());
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
