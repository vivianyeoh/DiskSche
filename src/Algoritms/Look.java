/**
 * @author Yeoh Hui Wen
 * @Date 6 Dec 2016
 */

package Algoritms;

import java.util.ArrayList;
import java.util.Collections;

public class Look extends ScheAlgorithm {

	public boolean toStart; // direction to 0

	public Look(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);

	}

	public void arrangeList() {
		Collections.sort(reqList);
		toStart = Math.abs(headStart - reqList.get(reqList.size() - 1)) > Math.abs(headStart - reqList.get(0));
		
		// position of headStart and position of max head start is different in
		// a way
		// for example: when we have a headstart of 2, and req number of 2, 2,
		// 3, 4
		// value for positionOfHeadStart is 3 and positionOfMaxHeadStart is 2
		// however, positionOfMaxHeadStart is important to have because
		// when we have a headstart of 8 abd reqNumber of 6, 7, 8, 8, 8,9
		// value for positionOfHeadStart is 9 and positionOfMaxHeadStart is 8
		int positionOfHeadStart = 0;
		int positionOfMaxHeadStart = 0;

		// find the position of headstart
		for (int i = 0; i < reqList.size(); i++) {
			// positionofheadstart is first number bigger than headstart
			if (reqList.get(i) > headStart) {
				positionOfHeadStart = i;
				break;
			}
		}

		for (int i = 0; i < reqList.size(); i++) {
			// positionofheadstart is first number bigger than or equal to
			// headstart
			if (reqList.get(i) >= headStart) {
				positionOfMaxHeadStart = i;
				break;
			}
		}

		// let headstart be the first dot in toBeArranged list
		toBeArranged.add(headStart);
		

		if (toStart) {

			/// Nearer to zero, direction is to start, largest value after
			// smallest value
			// start from first number that is smaller than headstart
			for (int i = positionOfHeadStart - 1; i >= 0; i--) {
				toBeArranged.add(reqList.get(i));
			}

			// follow by first number that is bigger than or equal to headstart
			for (int i = positionOfHeadStart; i < reqList.size(); i++) {
				toBeArranged.add(reqList.get(i));
			}
		} else {
			// Nearer to end, direction is to end, smallest value after larger
			// value
			// start from first number that is bigger than or equal to headstart
			for (int i = positionOfMaxHeadStart; i < reqList.size(); i++) {
				toBeArranged.add(reqList.get(i));
			}

			// follow by one value smaller than headstart
			for (int i = positionOfMaxHeadStart - 1; i >= 0; i--) {
				toBeArranged.add(reqList.get(i));
			}
		}

	}

}
