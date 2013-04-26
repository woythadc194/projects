import java.util.ArrayList;
import java.util.Scanner;

public class Format {

	private static ArrayList<String> paragraph;                                 // Arraylist or String
	private static int limit;                                                   // Longest character length per line

	public static void main(String[] args) {                                    // MAIN METHOD
		paragraph = new ArrayList<String>();                                        // Arraylist of String
		Scanner console = new Scanner(System.in);                                   // Scanner of System.in
		String firstLine = console.nextLine();                                      // Initial line from scanner
		Scanner lineScanner = new Scanner(firstLine);                               // Scanner on that line
		limit = lineScanner.nextInt();                                              // First Int in that line

		while (console.hasNextLine()) {                                             // While there is lines left in scanner
			String newLine = console.nextLine();                                        // newLine gets next line
			if (newLine.length() == 0) {                                                    // if newLine.length == 0
				while(paragraph.size()!=0){                                                 // While paragraph is not empty
					para();                                                                     // Call para method
				}
				System.out.println();                                                       // Print new line
				paragraph = new ArrayList<String>();                                        // Paragraph is renewed
			} else {                                                                    // if newLine.length != 0
				lineScanner = new Scanner(newLine);                                         // Create a scanner on newLine
				while (lineScanner.hasNext()) {                                             // While there is a new line
					String token = lineScanner.next();                                          // take the first token
					paragraph.add(token);                                                       // add it to paragraph
				}
			} 
		}
		if(!console.hasNextLine()){                                                 // if there isnt a next line from scanner
			while(paragraph.size()!=0){                                                 // while paragraph is not empty
				para();                                                                     // call para method
			}
			paragraph = new ArrayList<String>();                                        // paragraph is renewed
		}
			
	}

	public static void para() {                                                 // PARA METHOD
		ArrayList<String> line = new ArrayList<String>();                           // ArrayList of String
		while (getChar(line) < limit) {                                         /**/// while getChar(line) [characters including minimum spaces] returns less than limit
		    int numSpaces = 0;                                                          // initialize numSpaces
			if(paragraph.size()==0){                                                    // if paragraph is empty (method terminates after this if)
			    numSpaces = limit - getChar(line);                                          //number of extra spaces
				makeLine(numSpaces, line);                                                  // call makeLine passing numSpaces and Line
				return;                                                                     // leave method
			}                                                                           // else there is one or more words (tokens)
			line.add(paragraph.get(0));                                                     // add the first word to line arraylist
			String save = paragraph.get(0);                                                 // String save gets the first word in the paragraph
			paragraph.remove(0);                                                            // then remove the first word from the paragraph
			if (getChar(line) >= limit) {                                       /**/        // if getChar(line) [characters including minimum spaces] is >= limit  
				line.remove(line.size()-1);                                                     // remove the word that made the linelength exceed the limit)
				paragraph.add(0, save);                                                         // put that word back to the front of paragraph
				numSpaces = limit - getChar(line);                                              //number of extra spaces
				makeLine(numSpaces, line);                                                      // calls makeLine method with numSpaces and Line
				if(paragraph.size()==1){                                                        // if there is only one word left in the paragraph
					System.out.println(paragraph.get(0));                                           // Print it to terminal by itself with an newline
					paragraph.remove(0);                                                            //remove the word from paragraph
				}
				return;                                                                         //exit the method
			}
	    }                                                                           //loop back to top of while		
	}

	public static void makeLine(int numSpaces, ArrayList<String> line) {        // MAKELINE METHOD [passed in numspaces and line arraylist]
		int index = 0;                                                              // index of first character in line
		for (int i = 0; i < line.size() - 1; i++) {                                 // loop through every index except the last one of line arraylist
			line.set(i, line.get(i) + " ");                                             // add a space to each token (again, except the last one in the list)
		}       
		while (numSpaces != 0) {                                                    // while numspaces isnt 0
			if(line.size()==1)                                                          // if there is only one word in Line
				index=0;                                                                    // set index to 0;
			line.set(index, (line.get(index) + " "));                                   // add a space to the token that is at the current index position in Line
			numSpaces--;                                                                // subtract 1 from numSpaces
			index++;                                                                    // add 1 to index
			if (index == (line.size() - 1)) {                                           // if index becomes equal to the index of the last word in Line
				index = 0;                                                                  // set index back to 0 to add more spaces to the words starting at the beginning of Line
			}
		}
		for (int i = 0; i < line.size(); i++) {                                         // loop through Line
			System.out.print(line.get(i));                                                  // print every token to the terminal
		}
		System.out.println();                                                           // print an indent to terminal
	}

    public static int getChar(ArrayList<String> line) {                         // GETCHAR METHOD
		int sum = 0;                                                                // initialize sum
		if (line.size() == 0) {                                                     // if there are no words in line
			return 0;                                                                   // return 0
		}                                                                           // else there must be at least one word in line
		for (int i = 0; i < line.size(); i++) {                                     // loop through line
			sum += line.get(i).length();                                                // add one to sum for each letter of word                  
		}
		return sum += line.size()-1;                                                // add number of spaces between words (number of total words - 1), then return int
	}
}
