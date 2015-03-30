package platform;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Utils {
	public static List<String> scanJarFileForClasses(File file) throws IOException,
			IllegalArgumentException {
		if (file == null || !file.exists()) {
			throw new IllegalArgumentException(
					"Invalid jar-file to scan provided");
		}
		if (file.getName().endsWith(".jar")) {
			List<String> foundClasses = new ArrayList<String>();
			try (JarFile jarFile = new JarFile(file)) {
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry entry = entries.nextElement();
					if (entry.getName().endsWith(".class")) {
						String name = entry.getName();
						name = name.substring(0, name.lastIndexOf(".class"));
						if (name.indexOf("/") != -1)
							name = name.replaceAll("/", ".");
						if (name.indexOf("\\") != -1)
							name = name.replaceAll("\\", ".");

						foundClasses.add(name);
					}
				}
			}
			return foundClasses;
		}
		throw new IllegalArgumentException("No jar-file provided");
	}
	
	public static List<Class<?>> findImplementingClassesInJarFile(File file,
			Class<?> iface, ClassLoader loader) throws Exception {
		List<Class<?>> implementingClasses = new ArrayList<Class<?>>();
		// scan the jar file for all included classes
		for (String classFile : scanJarFileForClasses(file)) {
			Class<?> clazz;
			try {
				// now try to load the class
				if (loader == null)
					clazz = Class.forName(classFile);
				else
					clazz = Class.forName(classFile, true, loader);

				// and check if the class implements the provided interface
				if (iface.isAssignableFrom(clazz) && !clazz.equals(iface)) {
					implementingClasses.add(clazz);

				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return implementingClasses;
	}
}
