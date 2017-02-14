package com.amazon;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Amazon wants to implement a new backup system, in which files are stored into data tapes.
 
This new system must follow the following 2 rules:
	1. Never place more than two files on the same tape.
	2. Files cannot be split across multiple tapes.
	
It is guaranteed that all tapes have the same size and that they will always be able to store the largest file.
Every time this process is executed, we already know the size of each file, and the capacity of the tapes. 
Having that in mind, we want to design a system that is able to count how many tapes will be required to store the backup in the most efficient way.
The parameter of your function will be a structure that will contain the file sizes and the capacity of the tapes. 
You must return the minimum amount of tapes required to store the files.

You can write any methods or classes you would like. However, all new classes must be written in this 
file and you cannot change the BackupSystem class name, the Batch interface contract or the getMinimumTapeCount() method signature.

Example:
--------
	Input: Tape Size = 100; Files: 70, 10, 20
	Output: 2
	
*/
public class BackupSystem {

	public interface Batch {
		int[] getFileSizes();

		int getTapeSize();
	}

	public static int getNumTapesRequired(Batch batch) {

		int tapeCounter = 0;
		PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(batch.getFileSizes().length);
		PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(batch.getFileSizes().length,
				new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						return o2 - o1;
					}
				});

		for (int i = 0; i < batch.getFileSizes().length; i++) {
			if (batch.getFileSizes()[i] >= batch.getTapeSize() / 2)
				maxheap.add(batch.getFileSizes()[i]);
			else
				minheap.add(batch.getFileSizes()[i]);
		}
		if (minheap.size() == 0)
			return maxheap.size();

		int maxelesize = maxheap.size();
		boolean bpaired = false;
		Integer minele = minheap.remove();
		for (int i = 0; i < maxelesize; i++) {
			Integer maxele = maxheap.remove();
			if (maxele + minele <= batch.getTapeSize()) {
				bpaired = true;
			}

			if (bpaired) {
				if (minheap.isEmpty())
					minele = batch.getTapeSize();
				else
					minele = minheap.remove();
				bpaired = false;
			}
			tapeCounter++;
		}

		int minremeles = minheap.size();
		if (!bpaired)
			minremeles += 1;

		tapeCounter = tapeCounter + minremeles / 2 + minremeles % 2;

		return tapeCounter;

	}
}
