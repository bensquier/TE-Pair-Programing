package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) {
		
		File inputFile = getInputFileFromUser();
		int wordCounter = 0;
		int sentenceCounter = 0;
		
		try(Scanner fileScanner = new Scanner(inputFile)){
			while(fileScanner.hasNextLine() ) {
				String line = fileScanner.nextLine();
				String[] wordList = line.trim().split(" ");
				for (String word : wordList) {
					if(word.length() >= 1) {
						wordCounter++;
					}
					if (word.contains("?") || word.contains("!") || word.contains(".")) {
						sentenceCounter++;
					}
				}
			}
			
			System.out.println("Word Count: " + wordCounter);
			System.out.println("Sentence Count: " + sentenceCounter);
			
			} catch (FileNotFoundException e) {
			}
		}
		
		@SuppressWarnings("resource")
		private static File getInputFileFromUser() {
			Scanner userInput = new Scanner(System.in);
			System.out.print("Please enter path to input file >>> ");
			String path = userInput.nextLine();
			
			File inputFile = new File(path);
			if(inputFile.exists() == false) { 
				System.out.println(path+" does not exist");
				System.exit(1); 
			} else if(inputFile.isFile() == false) {
				System.out.println(path+" is not a file");
				System.exit(1);
			}
			return inputFile;
		}

	}


