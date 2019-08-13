package com.adame.utileries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class FileWriter {  
	    public static void saveStringIntoFile(String filePath, String contentToSave)
				throws FileNotFoundException, IOException {
			File f = new File(filePath);
			FileUtils.writeStringToFile(f, contentToSave, "UTF-8");
		}
}
