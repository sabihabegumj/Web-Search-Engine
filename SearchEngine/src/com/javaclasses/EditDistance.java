package com.javaclasses;
import java.util.ArrayList;
import java.util.Scanner;

public class EditDistance {

    private Dictionary dict;
    final static String filePath = "C:\\Users\\DELL\\Final Project\\searchengine\\searchengine\\src\\com\\javaclasses\\words.txt";
    final static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public EditDistance() {
        dict = new Dictionary();
        dict.build(filePath);

    }

    void run() {
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        String input;
       
        while (true) 
        {	 System.out.print("\nEnter a Word: ");
        input = scan.nextLine();
            if (input.equals("")) {
                break;
            }
            if (dict.contains(input)) {
                System.out.println("\n" + input + " is spelled correctly");
            } else {
                System.out.print("\n" +input+" is not spelled correctly, ");
                System.out.println(printSuggestions(input));
            }
        }
    }

    public String printSuggestions(String input) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> print = makeSuggestions(input);
        if (print.size() == 0) {
            return "and I have no idea what word you could mean.\n";
        }
        sb.append("perhaps you meant:");
        for (String s : print) {
            sb.append("\n  " + s);
        }
        return sb.toString();
    }

    private ArrayList<String> makeSuggestions(String input) {
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.addAll(charAppended(input));
        toReturn.addAll(charMissing(input));
        toReturn.addAll(charsSwapped(input));
        return toReturn;
    }

    private ArrayList<String> charAppended(String input) { 
        ArrayList<String> toReturn = new ArrayList<>();
        for (char c : alphabet) {
            String atFront = c + input;
            String atBack = input + c;
            if (dict.contains(atFront)) {
                toReturn.add(atFront);
            }
            if (dict.contains(atBack)) {
                toReturn.add(atBack);
            }
        }
        return toReturn;
    }

    private ArrayList<String> charMissing(String input) {   
        ArrayList<String> toReturn = new ArrayList<>();

        int len = input.length() - 1;
        //try removing char from the front
        if (dict.contains(input.substring(1))) {
            toReturn.add(input.substring(1));
        }
        for (int i = 1; i < len; i++) {
            //try removing each char between (not including) the first and last
            String working = input.substring(0, i);
            working = working.concat(input.substring((i + 1), input.length()));
            if (dict.contains(working)) {
                toReturn.add(working);
            }
        }
        if (dict.contains(input.substring(0, len))) {
            toReturn.add(input.substring(0, len));
        }
        return toReturn;
    }

    private ArrayList<String> charsSwapped(String input) {   
        ArrayList<String> toReturn = new ArrayList<>();

        for (int i = 0; i < input.length() - 1; i++) {
            String working = input.substring(0, i);// System.out.println("    0:" + working);
            working = working + input.charAt(i + 1);  //System.out.println("    1:" + working);
            working = working + input.charAt(i); //System.out.println("    2:" + working);
            working = working.concat(input.substring((i + 2)));//System.out.println("    FIN:" + working); 
            if (dict.contains(working)) {
                toReturn.add(working);
            }
        }
        return toReturn;
    }

    public static void main(String[] args) {
        EditDistance sc = new EditDistance();
        sc.run();
    }

}