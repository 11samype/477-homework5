package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import platform.Loader;

public class ListPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<File> myJars;
	private Loader loader;

	public ListPanel(){
		super();
		this.setBackground(Color.BLUE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		loader = new Loader();
		try {
			myJars = loader.load();
			for (File jar : myJars){
				JButton fileButton = new JButton();
				fileButton.setText(jar.toString());
				fileButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton clicked = (JButton) e.getSource();
						executeJar(clicked);
						
					}
					
				});
				this.add(fileButton);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void executeJar(JButton clicked){
		int index = this.getComponentZOrder(clicked);
		try {
			System.out.println(this.myJars.get(index));
			loader.scanFileForJarFiles(this.myJars.get(index));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
