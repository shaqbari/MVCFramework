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
		bt=new JButton("�Ǵ�");
		
		setLayout(new FlowLayout());
		
		ch.add("A");
		ch.add("B");
		ch.add("AB");
		ch.add("O");
		
		add(ch);
		add(bt);
		
		//1. ��û�� ����.
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
	
	//���⼭�� controller
	public void getResult() {
		//1~2�ܰ� ��û�� �ް� �м��Ѵ�.
		String blood=ch.getSelectedItem();
		
		//3�ܰ� �˸��� ���� ��ü�� �� ��Ų��.
		BloodAdviser adviser=new BloodAdviser();
		String msg=adviser.getAdvice(blood);
		
		//4�ܰ�: ���� �ƴϹǷ� ������ �������� ����
		//���� ������ Ŭ���� Panel, Frame�̶�� ���� �ʿ�
		
		//5�ܰ� ����� �����ش�.
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public static void main(String[] args) {
		new BloodView();
	}
	
}
