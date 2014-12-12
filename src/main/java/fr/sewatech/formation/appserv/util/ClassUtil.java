package fr.sewatech.formation.appserv.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;

public final class ClassUtil {
	
	private static final Logger logger = Logger.getLogger(ClassUtil.class);

	public static File getFile(Class<?> clazz) {
		String name = clazz.getName();
		name = name.replace('.', '/');
		name += ".class";
		
		URL location = clazz.getClassLoader().getResource(name);	
		
		return new File(location.getFile());
	}
	
	public static File getLibrary(Class<?> clazz) {
		String name = clazz.getName();
		name = name.replace('.', '/');
		name += ".class";
		
		ClassLoader classLoader = clazz.getClassLoader();
		logger.debug(classLoader);
		
		URL location = classLoader.getResource(name);
		String locationText = location.getFile();
		String libLocation = locationText.substring(0, locationText.indexOf(name)-1);
		
		return new File(libLocation);
	}

}
