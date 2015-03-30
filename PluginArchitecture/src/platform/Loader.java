package platform;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Loader {

	public void load() throws Exception {
		// URL[] classLoaderUrls = new URL[]{new URL("../plugins/test.jar")};
		//
		//
		File file = new File("plugins/plugindir.txt");
		// URL url = file.toURI().toURL();
		// URL[] urls = new URL[]{url};
		// ClassLoader cl = new URLClassLoader(urls);
		//
		// Class<?> helloClass = cl.getClass();
		// //Class<? extends IPlugin> pluginClass =
		// helloClass.asSubclass(IPlugin.class);
		// IPlugin plugin = (IPlugin) helloClass.newInstance();
		// plugin.execute();
		// plugin.status();

		scanFileForJarFiles(file);

		// scanJarFileForClasses();

	}

	public void scanFileForJarFiles(File file) throws IOException {
		if (file == null || !file.exists()) {
			throw new IllegalArgumentException("Invalid file to scan provided");

		} else {
			Scanner myScanner = new Scanner(file);

			while (myScanner.hasNextLine()) {

				String line = myScanner.nextLine();

				// System.out.println(line);
				File jarFile = new File("plugins/" + line);
				// System.out.println(jarFile.toString()+": "+jarFile.exists());
				List<String> classes = Utils.scanJarFileForClasses(jarFile);

				URL[] urls = new URL[] { jarFile.toURI().toURL() };
				URLClassLoader loader = new URLClassLoader(urls);

				try {
					List<Class<?>> list = Utils.findImplementingClassesInJarFile(
							jarFile, IPlugin.class, loader);
					for (Class<?> class1 : list) {
						IPlugin plugin = (IPlugin) class1.newInstance();
						plugin.execute();
						plugin.status();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
