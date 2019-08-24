package com.javaclasses;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FilesUtil {

	public static List<String> readTextFileByLines(String fileName){
		List<String> linesList = new ArrayList<String>();
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((sCurrentLine = br.readLine()) != null) {
				linesList.add(sCurrentLine);
			}
 
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			try{
				if (br != null)br.close();
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return linesList;
	}
}
