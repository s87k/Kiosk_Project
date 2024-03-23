package kiosk_prj.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kiosk_prj.view.OrderDesign;
import kiosk_prj.view.OrderDetailDesgin;

public class OrderDetailEvent extends WindowAdapter implements ActionListener, ItemListener {

	private OrderDetailDesgin odd;
	private OrderDesign od;
	private Color originalColor;
	private int count, menuPrice, totalPrice;
	private String cup, temp, shot;

	public OrderDetailEvent(OrderDetailDesgin odd, OrderDesign od) {
		this.odd = odd;
		this.od = od;
		originalColor = odd.getStoreCup().getBackground();
		// 라디오버튼의 선택 상태를 감지하는 리스너
		odd.getShotOption().addItemListener(this);
	}// OrderDetailEvent

////////////////////////////////////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		// 메뉴금액
		menuPrice = Integer.parseInt(odd.getMenuPrice().getText());
		// 샷
		shot = "";
		// 수량
		count = Integer.parseInt(odd.getJlCount().getText());

		// 수량 및 가격 업데이트
		updateTotalPrice();

		if (e.getSource() == odd.getStoreCup()) {
			handleCupSelection(odd.getStoreCup());
		} else if (e.getSource() == odd.getPersonalCup()) {
			handleCupSelection(odd.getPersonalCup());
		} else if (e.getSource() == odd.getHot()) {
			handleTemperatureSelection(odd.getHot());
		} else if (e.getSource() == odd.getIce()) {
			handleTemperatureSelection(odd.getIce());
		} else if (e.getSource() == odd.getMenuMinus()) {
			handleCountSelection(odd.getMenuMinus());
		} else if (e.getSource() == odd.getMenuPlus()) {
			handleCountSelection(odd.getMenuPlus());
		} else if (e.getSource() == odd.getAddMenu()) {
			handleAddMenuButton();
		}
	}//actionPerformed

	// 수량 선택 처리
	private void handleCountSelection(Component countComponent) {
		//수량 빼기
		if (countComponent == odd.getMenuMinus()) {
			if (count > 1) {
				count--;
				odd.getJlCount().setText(Integer.toString(count));
				if (odd.getShotOption().isSelected()) {
					totalPrice = (menuPrice * count) + 500;
					odd.getAddMenu().setText(totalPrice + "원 담기");
				} else {
					totalPrice = menuPrice * count;
					odd.getAddMenu().setText(totalPrice + "원 담기");
				}
			}
		// 수량 더하기
		} else if (countComponent == odd.getMenuPlus()) {
			count++;
			odd.getJlCount().setText(Integer.toString(count));
			if (odd.getShotOption().isSelected()) {
				totalPrice = (menuPrice * count) + 500;
				odd.getAddMenu().setText(totalPrice + "원 담기");
			} else {
				totalPrice = menuPrice * count;
				odd.getAddMenu().setText(totalPrice + "원 담기");
			}
		}
	}

	// 컵 선택 처리
	private void handleCupSelection(Component cupComponent) {
		if (cupComponent == odd.getStoreCup()) {
			odd.getStoreCup().setBackground(Color.GRAY);
			odd.getPersonalCup().setBackground(originalColor);
			cup = odd.getStoreCup().getText();
		} else if (cupComponent == odd.getPersonalCup()) {
			odd.getPersonalCup().setBackground(Color.GRAY);
			odd.getStoreCup().setBackground(originalColor);
			cup = odd.getPersonalCup().getText();
		}
	}

	// 온도 선택 처리
	private void handleTemperatureSelection(Component temperatureComponent) {
		if (temperatureComponent == odd.getHot()) {
			odd.getHot().setBackground(Color.GRAY);
			odd.getIce().setBackground(originalColor);
			temp = odd.getHot().getText();
		} else if (temperatureComponent == odd.getIce()) {
			odd.getIce().setBackground(Color.GRAY);
			odd.getHot().setBackground(originalColor);
			temp = odd.getHot().getText();
		}
	}

	// 주문 담기 버튼 처리
	private void handleAddMenuButton() {
		// "개인컵"과 "매장컵", "핫"과 "아이스" 중 하나라도 선택되지 않은 경우 메시지 다이얼로그 출력
		if ((odd.getPersonalCup().getBackground() != Color.GRAY && odd.getStoreCup().getBackground() != Color.GRAY)
				|| (odd.getHot().getBackground() != Color.GRAY && odd.getIce().getBackground() != Color.GRAY)) {
			JOptionPane.showMessageDialog(odd, "주문할 음료의 컵, 온도를 체크해주세요.", "안내", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String menuName = odd.getMenuName().getText();
			StringBuilder option = new StringBuilder();
			option.append(cup).append(", ").append(temp);
			if (odd.getShotOption().isSelected()) {
				shot = "1샷 추가(+500원)";
				option.append(", ").append(shot);
			}
			Object[] rowData = { menuName, option, totalPrice };
			od.getDtmCartList().addRow(rowData);
			odd.dispose();
		}
	}

	// 라디오버튼의 선택 상태 변화를 감지하는 메서드
	@Override
	public void itemStateChanged(ItemEvent e) {
		// 선택 상태에 따라 totalPrice 업데이트
		updateTotalPrice();
	}

	// 수량 및 가격을 기반으로 totalPrice 업데이트
	private void updateTotalPrice() {
		menuPrice = Integer.parseInt(odd.getMenuPrice().getText());
		count = Integer.parseInt(odd.getJlCount().getText());
		if (odd.getShotOption().isSelected()) {
			totalPrice = (menuPrice * count) + 500;
		} else {
			totalPrice = menuPrice * count;
		}
		odd.getAddMenu().setText(totalPrice + "원 담기");
	}

////////////////////////////////////////////////////////////////
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// 메뉴금액
//		menuPrice = Integer.parseInt(odd.getMenuPrice().getText());
//		// 샷추가
//		shot = "";
//		// 수량
//		count = Integer.parseInt(odd.getJlCount().getText());
//
//		if (e.getSource() == odd.getStoreCup()) {
//			System.out.println("매장컵");
//			odd.getStoreCup().setBackground(Color.GRAY);
//			odd.getPersonalCup().setBackground(originalColor);
//			// 매장컵을 선택했으면 옵션에 "매장컵" 추가
//			if (odd.getStoreCup().getBackground().equals(Color.GRAY)) {
//				cup = odd.getStoreCup().getText();
//			}
//		}
//		if (e.getSource() == odd.getPersonalCup()) {
//			System.out.println("개인컵");
//			odd.getPersonalCup().setBackground(Color.GRAY);
//			odd.getStoreCup().setBackground(originalColor);
//			// 개인컵을 선택했으면 옵션에 "개인컵" 추가
//			if (odd.getPersonalCup().getBackground().equals(Color.GRAY)) {
//				cup = odd.getPersonalCup().getText();
//			}
//		}
//		if (e.getSource() == odd.getHot()) {
//			System.out.println("핫");
//			odd.getHot().setBackground(Color.GRAY);
//			odd.getIce().setBackground(originalColor);
//			// 온도 핫을 선택했으면 옵션에 "HOT" 추가
//			if (odd.getHot().getBackground().equals(Color.GRAY)) {
//				temp = odd.getHot().getText();
//			}
//		}
//		if (e.getSource() == odd.getIce()) {
//			System.out.println("아이스");
//			odd.getIce().setBackground(Color.GRAY);
//			odd.getHot().setBackground(originalColor);
//			// 온도 아이스을 선택했으면 옵션에 "ICE" 추가
//			if (odd.getIce().getBackground().equals(Color.GRAY)) {
//				temp = odd.getHot().getText();
//			}
//		}
//		if (odd.getShotOption().isSelected()) {
//			System.out.println("샷추가");
//			shot = odd.getShotOption().getText();
//		}
//		if (e.getSource() == odd.getMenuMinus()) {
//			System.out.println("수량빼기");
//			if (count > 1) {
//				count--;
//				odd.getJlCount().setText(Integer.toString(count));
//				if(odd.getShotOption().isSelected()) {
//					totalPrice = (menuPrice*count) + 500;
//					odd.getAddMenu().setText(totalPrice + "원 담기");
//				}else {
//					totalPrice = menuPrice*count;
//					odd.getAddMenu().setText(totalPrice + "원 담기");
//				}
//			} // end if
//		}
//		if (e.getSource() == odd.getMenuPlus()) {
//			System.out.println("수량더하기");
//			count++;
//			odd.getJlCount().setText(Integer.toString(count));
//			if(odd.getShotOption().isSelected()) {
//				totalPrice = (menuPrice*count) + 500;
//				odd.getAddMenu().setText(totalPrice + "원 담기");
//			}else {
//				totalPrice = menuPrice*count;
//				odd.getAddMenu().setText(totalPrice + "원 담기");
//			}
//		}
//		if (e.getSource() == odd.getAddMenu()) {
//			System.out.println("주문 담기");
//			//"개인컵"과 "매장컵", "핫"과 "아이스" 중 하나라도 선택되지 않은 경우 메시지 다이얼로그 출력
//			if ((odd.getPersonalCup().getBackground() != Color.GRAY && odd.getStoreCup().getBackground() != Color.GRAY)
//					|| (odd.getHot().getBackground() != Color.GRAY && odd.getIce().getBackground() != Color.GRAY)) {
//				JOptionPane.showMessageDialog(odd, "주문할 음료의 컵, 온도를 체크해주세요.", "안내", JOptionPane.INFORMATION_MESSAGE);
//			} else {
//				String menuName = odd.getMenuName().getText();
//				StringBuilder option = new StringBuilder();
//				option.append(cup).append(", ").append(temp);
//				if (odd.getShotOption().isSelected()) {
//					option.append(", ").append(shot);
//				}
//				Object[] rowData = { menuName, option, totalPrice };
//				od.getDtmCartList().addRow(rowData);
//				odd.dispose();
//			} // end if
//		}// end if getAddMenu
//	}
//
//	// 라디오버튼의 선택 상태 변화를 감지하는 메서드
//	@Override
//	public void itemStateChanged(ItemEvent e) {
//		if (e.getStateChange() == ItemEvent.SELECTED) {
//			// 라디오버튼이 선택되었을 때
//			menuPrice = Integer.parseInt(odd.getMenuPrice().getText());
//			count = Integer.parseInt(odd.getJlCount().getText());
//			System.out.println(menuPrice);
//			System.out.println(count);
//			totalPrice = (menuPrice*count) + 500;
//			// 변경된 totalPrice를 반영
//			odd.getAddMenu().setText(totalPrice + "원 담기");
//		} else if (e.getStateChange() == ItemEvent.DESELECTED) {
//			// 라디오버튼이 선택 해제되었을 때
//			totalPrice = menuPrice*count;
//			// 변경된 totalPrice를 반영
//			odd.getAddMenu().setText(totalPrice + "원 담기");
//		}
//	}// itemStateChanged

	@Override
	public void windowClosed(WindowEvent e) {
		odd.dispose();
	}

}
