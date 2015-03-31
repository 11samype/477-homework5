package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneLayout;

import platform.Loader;

public class StatusPanel extends JScrollPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel statuses;

	public StatusPanel(){
		super();
		this.setBackground(Color.GRAY);
		ScrollPaneLayout layout = new ScrollPaneLayout();
		layout.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		layout.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		this.setLayout(layout);
		this.statuses = new JPanel();
		this.statuses.setPreferredSize(new Dimension(990,200));
		this.setPreferredSize(new Dimension(200,200));
		JViewport viewport = this.getViewport();
		statuses.setLayout(new BoxLayout(statuses, BoxLayout.Y_AXIS));
		viewport.add(statuses);
	}
	
	public void printStatus(String status){
		statuses.add(new JLabel(status));
		viewport.updateUI();
	}
}
