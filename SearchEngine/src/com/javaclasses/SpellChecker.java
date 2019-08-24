package com.javaclasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class SpellChecker {

	public static int editDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
	 
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];
	 
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
	 
		return dp[len1][len2];
	}
	public static void SpellCheck(String s) throws IOException {
	String word = s; 
	int leng = word.length();
	//String str2 = "C:\\Study_Mac\\Information_Retrieval_Systems\\Project\\engmix.txt";
	String str2 = "C:\\Users\\DELL\\Final Project\\searchengine\\searchengine\\src\\com\\javaclasses\\words.txt";
	BufferedReader  reader1 = null;
	 try {
         reader1  = new BufferedReader(new FileReader(str2));
         String file;
         System.out.print("Did you mean: ");
         while ((file = reader1 .readLine()) != null) {
            int dist = editDistance(word,file);
            //if((leng == file.length()) && (dist == 1 || dist == 2))
            int count = 0;
            if((leng == file.length()) && (dist == 1 ))
            {
            	System.out.println(file + " ");
            	
            	count++;
              	}
                       {
            /*if ((leng == file.length()) && (dist == 2)&&(count==0))
            {
            	System.out.println(file + " ");
            }*/
            }
         }
         
	 }
         finally {
	                reader1.close();
	            }
	
	}	
	public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        String input;
        System.out.println("\n-------Enter a word: ");
        while (true) {
            
            input = scan.nextLine();
            if (input.equals("")) {
                break;
            }
            
                SpellCheck(input);
            
        }
    }

}

