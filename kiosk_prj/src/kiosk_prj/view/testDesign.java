package kiosk_prj.view;

import javax.swing.*;

public class testDesign {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater를 사용하여 GUI를 생성하는 스레드에 이벤트 디스패치 스레드를 사용합니다.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // MainFrame 객체 생성 후 화면에 표시
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
}

// 메인 프레임 클래스
class MainFrame extends JFrame {
    private Dialog1 dialog1; // 다이얼로그1 객체

    // 생성자
    public MainFrame() {
        setTitle("Main Frame"); // 프레임 제목 설정
        setSize(300, 200); // 프레임 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 동작 설정

        dialog1 = new Dialog1(this); // 다이얼로그1 객체 생성
    }

    // 다이얼로그1에 값 설정 메서드
    public void setValueA(int value) {
        dialog1.setValueA(value);
    }

    // 다이얼로그1에서 값 가져오는 메서드
    public int getValueA() {
        return dialog1.getValueA();
    }
}

// 다이얼로그1 클래스
class Dialog1 extends JDialog {
    private Dialog2 dialog2; // 다이얼로그2 객체
    private int valueA; // 값A

    // 생성자
    public Dialog1(MainFrame parent) {
        super(parent, "Dialog 1", true); // 부모 프레임에 모달 다이얼로그로 설정
        setSize(200, 150); // 다이얼로그 크기 설정
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // 레이아웃 설정

        // 다이얼로그2 열기 버튼 생성
        JButton openDialog2Button = new JButton("Open Dialog 2");
        openDialog2Button.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
        openDialog2Button.addActionListener(e -> {
            setVisible(false); // 현재 다이얼로그 숨기기
            dialog2 = new Dialog2(this); // 다이얼로그2 객체 생성
            dialog2.setVisible(true); // 다이얼로그2 화면에 표시
        });

        // 버튼을 다이얼로그에 추가
        add(openDialog2Button);
    }

    // 값A 설정 메서드
    public void setValueA(int value) {
        valueA = value;
    }

    // 값A 가져오는 메서드
    public int getValueA() {
        return valueA;
    }

    // 값A 업데이트 메서드
    public void updateValueA(int newValue) {
        valueA = newValue; // 새로운 값으로 업데이트
        setVisible(true); // 다이얼로그1 화면에 표시
    }
}

// 다이얼로그2 클래스
class Dialog2 extends JDialog {
    private Dialog1 parentDialog; // 부모 다이얼로그

    // 생성자
    public Dialog2(Dialog1 parent) {
        super(parent, "Dialog 2", true); // 부모 다이얼로그에 모달 다이얼로그로 설정
        parentDialog = parent; // 부모 다이얼로그 설정
        setSize(200, 150); // 다이얼로그 크기 설정
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // 레이아웃 설정

        // 값B를 입력받는 텍스트 필드 생성
        JTextField valueBField = new JTextField(10);
        valueBField.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬

        // 계산 버튼 생성
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
        calculateButton.addActionListener(e -> {
            int valueB = Integer.parseInt(valueBField.getText()); // 입력된 값B 가져오기
            int newValue = parentDialog.getValueA() + valueB; // 값A와 값B 더하기
            parentDialog.updateValueA(newValue); // 값A 업데이트
            dispose(); // 다이얼로그2 닫기
        });

        // 다이얼로그에 텍스트 필드와 버튼 추가
        add(new JLabel("Enter Value B:"));
        add(valueBField);
        add(calculateButton);
    }
}

