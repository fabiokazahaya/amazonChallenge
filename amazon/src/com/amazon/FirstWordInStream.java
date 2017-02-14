package com.amazon;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/*
Find the first word in a stream which is not repeated in the rest of the stream.
Please note that you are being provided a stream as a source for the characters.
The stream is guaranteed to eventually terminate (i.e. return false from a call to the hasNext() method), though it could be very long.
You will access this stream through the provided interface methods.
A call to hasNext() will return whether the stream contains any more characters to process.
A call to next() will return the next character to be processed in the stream.
It is not possible to restart the stream. 

You can write any methods or classes you would like. However, all new classes must be written in this 
file and you cannot change the FirstWordInStream class name, the Stream interface contract or the firstWord() method signature.

Example:
--------
	Input: "The angry dog was red. And the cat was also angry."
	Output: "dog"
In this example, the word "dog" is the first word in the stream which is not repeated in the rest of the stream. 

	
*/
public class FirstWordInStream {
	
	public interface Stream {
		char next();
		boolean hasNext();
	}

	public static String firstWord(final Stream input) {
		char c;
		StringBuilder sb = new StringBuilder();
		String word;
		Set<String> linkedHashSet = new LinkedHashSet<String>();
		Set<String> duplicateWords = new HashSet<String>();

		while (input.hasNext()) {
			c = input.next();
			switch (c) {
			case ' ':
			case '.':
				word = sb.toString().toLowerCase();
				if (linkedHashSet.contains(word)) {
					linkedHashSet.remove(word);
					duplicateWords.add(word);
				} else if (!duplicateWords.contains(word)) {
					linkedHashSet.add(word);
				}

				sb.setLength(0);
				break;
			default:
				sb.append(c);
			}
		}

		if (linkedHashSet.isEmpty()) {
			return "";
		} else {
			return linkedHashSet.iterator().next();
		}
	}
}
