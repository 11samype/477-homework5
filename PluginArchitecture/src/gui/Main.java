package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		JPanel list = new ListPanel();
		JPanel list2 = new ListPanel();
		JPanel list3 = new ListPanel();
		window.setSize(1000, 800);
		window.add(list, BorderLayout.WEST);
		list2.setBackground(Color.CYAN);
		list3.setBackground(Color.GRAY);
		window.add(list2, BorderLayout.CENTER);
		window.add(list3, BorderLayout.SOUTH);
		window.setVisible(true);
	}

}
