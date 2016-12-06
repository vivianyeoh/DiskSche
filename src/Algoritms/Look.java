/**
 * @author Yeoh Hui Wen
 * @Date 6 Dec 2016
 */

package Algoritms;

import java.util.ArrayList;
import java.util.Collections;

public class Look extends ScheAlgorithm {

	private int minIndex = 1;
	private int maxIndex = 1;

	public Look(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);
		//get Max and min value
		for (int i = 1; i < getArrangedList().size(); i++) {
			if (getArrangedList().get(i) > getArrangedList().get(maxIndex))
				maxIndex = i;
			if (getArrangedList().get(i) < getArrangedList().get(minIndex))
				minIndex = i;
		}

	}

	public void arrangeList() {
		Collections.sort(reqList);

		int positionOfHeadStart = 0;
		//find the position of headstart 
		for (int i = 0; i < reqList.size(); i++) {
			if (reqList.get(i) >= headStart) {
				positionOfHeadStart = i;
				break;
			}
		}

		//let headstart be the first dot in toBeArranged list
		toBeArranged.add(headStart);

		if (minIndex > maxIndex) {
			// Nearer to end, direction is to end, smallest value after larger
			// value
			//start from first number that is bigger than or equal to headstart
			for (int i = positionOfHeadStart; i < reqList.size(); i++) {
				toBeArranged.add(reqList.get(i));
			}
			
			//follow by one value smaller than headstart
			for (int i = positionOfHeadStart - 1; i > 0; i--) {
				toBeArranged.add(reqList.get(i));
			}
		} else {
			// Nearer to zero, direction is to start, largest value after
			// smallest value
			//start from first number that is smaller than headstart
			for (int i = positionOfHeadStart - 1; i >= 0; i--) {
				toBeArranged.add(reqList.get(i));
			}
			
			//follow by first number that is bigger than or equal to headstart
			for (int i = positionOfHeadStart; i < reqList.size(); i++) {
				toBeArranged.add(reqList.get(i));
			}
		}

	}

}
