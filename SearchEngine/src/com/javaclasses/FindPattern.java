package com.javaclasses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FindPattern {
	public static void main(String args[]) throws IOException {

		String phnpatrn = "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";
		String emailpatrn = "[A-Za-z0-9._%-]+@[gmail]+\\.[A-Za-z]{2,}";
		String postaladdress = "[0-9a-zA-Z]*,*\\s*[0-9a-zA-Z]*,*\\s*[A-Za-z]{1,},*[A-Za-z]{1,}\\s*[Avenue,]+\\s*[a-zA-Z][0-9][a-zA-Z]\\s?[0-9][a-zA-Z][0-9]";
		File Folder = new File("C:\\Users\\DELL\\AC Final Project Work\\SearchEngine\\src\\W3C Web Pages Text");
		File[] files = Folder.listFiles();
		System.out.println("Canadian Phone Numbers Found:");
		fndPhone(files, phnpatrn);
		
				
		System.out.println("\nGmail Addresses Found:");
		EmailAddresses(files, emailpatrn);
				
		System.out.println("\nCanadian Postal Addresses Found:");
		EmailAddresses(files, postaladdress);
	}
	public static void fndPhone(File[] files, String pattern) throws IOException 
	{

		Pattern p = Pattern.compile(pattern);
		Matcher m;
		for (File f : files) {

			Document doc = Jsoup.parse(f, "UTF-8");
			String text = doc.text();
			m = p.matcher(text);
			while (m.find()) {
				System.out.println(m.group());
			}
		}
	}
	

	public static void EmailAddresses(File[] files, String pattern) throws IOException 
	{

		Pattern p = Pattern.compile(pattern);
		Matcher m;
		for (File f : files) {

			Document doc = Jsoup.parse(f, "UTF-8");
			String text = doc.text();
			m = p.matcher(text);
			while (m.find()) {
				System.out.println(m.group());
			}
		}
	}
	
	public static void postaladdress(File[] files, String pattern) throws IOException 
	{

		Pattern p = Pattern.compile(pattern);
		Matcher m;
		for (File f : files) {

			Document doc = Jsoup.parse(f, "UTF-8");
			String text = doc.text();
			m = p.matcher(text);
			while (m.find()) {
				String str = m.group();
				System.out.println(str.trim());
				
			}
		}
	}
	
	  }
