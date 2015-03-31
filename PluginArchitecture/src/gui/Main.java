package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		ListPanel list = new ListPanel();
		ExecPanel exec = new ExecPanel();
		StatusPanel status = new StatusPanel();
		window.setSize(1000, 800);
		status.printStatus("Main GUI loaded.");
		window.add(list, BorderLayout.WEST);
		window.add(exec, BorderLayout.CENTER);
		window.add(status, BorderLayout.SOUTH);
		window.setVisible(true);
	}

}
