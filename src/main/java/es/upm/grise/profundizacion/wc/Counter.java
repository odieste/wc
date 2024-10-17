package es.upm.grise.profundizacion.wc;

import java.io.BufferedReader;
import java.io.IOException;

public class Counter {
	
	int numberCharacters = 0;
	int numberLines = 0;
	int numberWords = 0;
	
	public Counter(BufferedReader br) throws IOException {

	    int character = br.read();
	    boolean insideWord = false;
	    boolean hasCharacters = false;  // Flag to check if we've read any characters

	    while (character != -1) {
	        hasCharacters = true;
	        numberCharacters++;

	        if(character == ' ' || character == '\t' || character == '\n') {
	            if (insideWord) {
	                numberWords++;
	                insideWord = false;
	            }
	        } else {
	            insideWord = true;
	        }

	        if(character == '\n') {
	            numberLines++;
	        }

	        character = br.read();
	    }

	    if (insideWord) {
	        numberWords++;
	    }

	    if (hasCharacters && numberLines == 0) {
	        numberLines = 1;
	    }
	}


	public int getNumberCharacters() {
		return numberCharacters;
	}

	public int getNumberLines() {
		return numberLines;
	}
	
	public int getNumberWords() {
		return numberWords;
	}
	
}
