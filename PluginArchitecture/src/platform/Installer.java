package platform;

import gui.Main;

import java.awt.image.ReplicateScaleFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Installer {

	JFileChooser fileChooser;

	public Installer() {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System
				.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Jar File", "jar");
		fileChooser.setFileFilter(filter);
	}

	public void loadFile(JFrame parent) {
		fileChooser.setCurrentDirectory(new File(System
				.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(parent);

		if (result == JFileChooser.APPROVE_OPTION) {
			// user selects a file
			File selectedFile = fileChooser.getSelectedFile();
//			System.out.println("Selected file: "
//					+ selectedFile.getAbsolutePath());

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

//		for (String string : classes) {
//			System.out.println(string);
//		}

		URL url = null;
		try {
			url = selectedFile.toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		URL[] urls = new URL[] { url };
		URLClassLoader loader = new URLClassLoader(urls);

		try {
			List<Class<?>> list = Utils.findImplementingClassesInJarFile(
					selectedFile, IPlugin.class, loader);

			if (!list.isEmpty()) {
				installJar(selectedFile);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void installJar(File selectedFile) throws IOException {

		Path destination = Paths.get("plugins/" + selectedFile.getName());

		Files.copy(selectedFile.toPath(), destination,
					StandardCopyOption.REPLACE_EXISTING);
		
		Scanner myScanner = new Scanner(new File("plugins/plugindir.txt"));
		boolean alreadyInstalled = false;
		while (myScanner.hasNextLine()) {

			String line = myScanner.nextLine();
			
			if (line.equals(selectedFile.getName())) {
				alreadyInstalled = true;
				break;
			}
			
		}
		if (!alreadyInstalled) {
			try (PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("plugins/plugindir.txt", true)));) {
	
				out.println(selectedFile.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		Main.status.printStatus("Installation of " + selectedFile.getName() + " complete");

	}
	
	public void unInstallJar(JFrame parent) throws IOException {
		
		fileChooser.setCurrentDirectory(new File("plugins/"));
		
		int result = fileChooser.showOpenDialog(parent);

		if (result == JFileChooser.APPROVE_OPTION) {
			// user selects a file
			File selectedFile = fileChooser.getSelectedFile();
//			System.out.println("Selected file: "
//					+ selectedFile.getAbsolutePath());

			Path pluginPath = Paths.get("plugins/plugindir.txt");
			Path tempPath = Paths.get("plugins/plugindirtemp.txt");
			
			Files.copy(pluginPath, tempPath, StandardCopyOption.REPLACE_EXISTING);
			
			File pluginDir = new File("plugins/plugindir.txt");
			File pluginDirTemp = new File("plugins/plugindirtemp.txt");
			
			if (pluginDirTemp == null || !pluginDirTemp.exists()) {
				throw new IllegalArgumentException("Invalid file to scan provided");

			} else {
				Scanner myScanner = new Scanner(pluginDirTemp);
				PrintStream out = new PrintStream(pluginDir);

				while (myScanner.hasNextLine()) {

					String line = myScanner.nextLine();
					if (!(line.equals(selectedFile.getName()))) {
						out.println(line);
					}
					
				}
				
				Files.delete(Paths.get("plugins/" + selectedFile.getName()));
				
				removePluginDirWhiteSpace();
			}
			
			
		}
		
		
	}
	
	public static void removePluginDirWhiteSpace() throws IOException {

		Path pluginPath = Paths.get("plugins/plugindir.txt");
		Path tempPath = Paths.get("plugins/plugindirtemp.txt");
		
		Files.copy(pluginPath, tempPath, StandardCopyOption.REPLACE_EXISTING);
		
		File pluginDir = new File("plugins/plugindir.txt");
		File pluginDirTemp = new File("plugins/plugindirtemp.txt");

		Scanner scanner = new Scanner(pluginDirTemp);

		PrintStream out = new PrintStream(pluginDir);

		while(scanner.hasNextLine()){
		    String line = scanner.nextLine();
		    line = line.trim();
		    if(line.length() > 0) {
		        out.println(line);
		    }
		}
		
	}

}
