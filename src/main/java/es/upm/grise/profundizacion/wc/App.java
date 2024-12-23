package es.upm.grise.profundizacion.wc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {

	public static void main(String[] args) {
    	if(args.length == 0) {
    		System.out.println("Usage: wc [-clw file]");
    		return;
    	}
    	if(args.length != 2) {
    		System.out.println("Wrong arguments!");
    		return;
    	}
		BufferedReader br = null;
    	String fileName = args[1];

		try{
			br = new BufferedReader(new FileReader(fileName));
		} catch(Exception e) {
    		System.out.println("Cannot find file: " + fileName);
    		return;
		}
    	Counter counter = null;
		try {
			counter = new Counter(br);
		} catch (IOException e) {
    		System.out.println("Error reading file: " + fileName);
    		return;
		}
		String commands = args[0];
		if(commands.charAt(0) != '-') {
    		System.out.println("The commands do not start with -");
    		return;
		}
		String result = "";
		commands = commands.substring(1);
		for(char c : commands.toCharArray()) {
			switch(c) {
			case 'c': result += "\t" + Integer.toString(counter.getNumberCharacters()); break;
			case 'l': result += "\t" + Integer.toString(counter.getNumberLines()); break;
			case 'w': result += "\t" + Integer.toString(counter.getNumberWords()); break;
			default:
	    		System.out.println("Unrecognized command: " + c);
	    		return;
			}
		}
		System.out.println(result + "\t" + fileName);
	}
}
