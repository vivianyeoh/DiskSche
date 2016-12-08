/**
 * @author Nurtahirah binti Abdul Rahman
 * @Date 6 Dec 2016
 */

package Algoritms;

import java.util.ArrayList;
import java.util.Collections;

public class Scan extends ScheAlgorithm {

	private int minIndex = 0;
	private int maxIndex = 1;
	
	public Scan(ArrayList<Integer> reqList, int headStart, int maxValue) {
		super(reqList, headStart, maxValue);
			//get Max and min value
		for (int i = 1; i < getArrangedList().size(); i++) {
			if (getArrangedList().get(i) > getArrangedList().get(maxIndex))
				maxIndex = i;
			if (getArrangedList().get(i) < getArrangedList().get(minIndex))
				minIndex = i;
		}
	}

	public void arrangeList() {

		toBeArranged.add(headStart);
		reqList.add(0);
		int totalDistance = 0;
		int[] distance = new int[reqList.size() + 1];
		Collections.sort(reqList);

		// assume that the reqList initially goes from left to right
		int positionOfHeadStart = 0;
		// search for start request
		for (int i = 0; i < reqList.size(); i++) {
			if (reqList.get(i) >= headStart) {
				positionOfHeadStart = i;
				break;
			}
		}
		
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
		
		//calculating the total head movement
				int iterator = 1;
				totalDistance += Math.abs(headStart - reqList.get(positionOfHeadStart));

				for (int i = positionOfHeadStart; i < reqList.size() - 1; i++, iterator++) {

					totalDistance += Math.abs(reqList.get(i + 1) - reqList.get(i));
					distance[iterator] = reqList.get(i);
				}
				// to add the last request

				distance[iterator] = reqList.get(reqList.size() - 1);
				iterator++;

				totalDistance += Math.abs(reqList.get(reqList.size() - 1) - reqList.get(positionOfHeadStart - 1));
				for (int i = positionOfHeadStart - 1; i > 0; i--, iterator++) {

					totalDistance += Math.abs(reqList.get(i - 1) - reqList.get(i));
					distance[iterator] = reqList.get(i);
				}
				distance[iterator] = reqList.get(0);


		distance[0] = totalDistance;
	}

}
