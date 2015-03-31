package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import platform.Installer;

public class Main {
	
	public static JPanel exec;
	public static StatusPanel status;
	
	private static Installer installer;
	
	public static ListPanel list;
	
	static JFrame window;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		window = new JFrame();
		list = new ListPanel();
		
		installer = new Installer();
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem installItem = new JMenuItem("Install Jar Plugin");
		JMenuItem uninstallItem = new JMenuItem("Uninstall Jar Plugin");
		
		installItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				executeInstall();
				
			}
		});
		
		uninstallItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				executeUninstall();
				
			}
		});
		
		fileMenu.add(installItem);
		fileMenu.add(uninstallItem);
		
		exec = new ExecPanel();
		status = new StatusPanel();
		
		window.setJMenuBar(menuBar);
		window.setSize(1000, 800);
		status.printStatus("Main GUI loaded.");
		window.add(list, BorderLayout.WEST);
		window.add(exec, BorderLayout.CENTER);
		window.add(status, BorderLayout.SOUTH);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void executeInstall() {
		
		JFrame frame = new JFrame();
		
		installer.loadFile(frame);
		
		list.loadList();
		list.updateUI();

	}
	
	public static void executeUninstall() {
		
		JFrame frame = new JFrame();
		
		try {
			installer.unInstallJar(frame);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.loadList();
		list.updateUI();
		
	}
	
	public static void swapExecPanel(JPanel newPanel) {
		window.remove(exec);
		window.add(newPanel, BorderLayout.CENTER);
		window.update(window.getGraphics());
		window.setVisible(true);
		status.printStatus("New Plugin Loaded");
		
//		for (Component component : newPanel.getComponents()) {
//			status.printStatus(component.getClass().toString());
//		}
		
	}

}
