package platform;

import java.io.IOException;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		Loader loader = new Loader();
		try {
			loader.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame();
		
		Installer installer = new Installer();
		installer.loadFile(frame);
//		try {
//			installer.unInstallJar("test.jar");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
