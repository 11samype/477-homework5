package platform;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Installer {
	
	JFileChooser fileChooser;
	
	public Installer() {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Jar File", "jar");
		fileChooser.setFileFilter(filter);
	}
	
	public void loadFile(JFrame parent) {
		int result = fileChooser.showOpenDialog(parent);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			// user selects a file
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			
			checkFile(selectedFile);
		}
		
		
	}

	private void checkFile(File selectedFile) {
		List<String> classes = new ArrayList<String>();
		
		try {
			classes = Utils.scanJarFileForClasses(selectedFile);
		} catch (IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String string : classes) {
			System.out.println(string);
		}
		
	}
	
}
