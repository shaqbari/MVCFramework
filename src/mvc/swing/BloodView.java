package mvc.swing;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mvc.model.BloodAdviser;

public class BloodView extends JFrame{
	Choice ch;
	JButton bt;
	
	public BloodView() {
		ch=new Choice();		
		bt=new JButton("판단");
		
		setLayout(new FlowLayout());
		
		ch.add("A");
		ch.add("B");
		ch.add("AB");
		ch.add("O");
		
		add(ch);
		add(bt);
		
		//1. 요청이 들어간다.
		bt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getResult();
			}
		});
				
		setSize(200, 150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	//여기서는 controller
	public void getResult() {
		//1~2단계 요청을 받고 분석한다.
		String blood=ch.getSelectedItem();
		
		//3단계 알맞은 로직 객체에 일 시킨다.
		BloodAdviser adviser=new BloodAdviser();
		String msg=adviser.getAdvice(blood);
		
		//4단계: 웹이 아니므로 별도의 페이지가 없다
		//만약 별도이 클래스 Panel, Frame이라면 저장 필요
		
		//5단계 결과를 보여준다.
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public static void main(String[] args) {
		new BloodView();
	}
	
}
