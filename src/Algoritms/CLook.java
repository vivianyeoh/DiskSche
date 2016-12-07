/**
 * @author Yeoh Hui Wen
 * @Date 6 Dec 2016
 */

package Algoritms;

import java.util.ArrayList;
import java.util.Collections;

public class CLook extends ScheAlgorithm {

	public boolean toStart; // direction to 0

	public CLook(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);

	}

	public void arrangeList() {
		Collections.sort(reqList);

		//position of headStart and position of max head start is different in a way
		//for example: when we have a headstart of 2, and req number of 2, 2, 3, 4
		//value for positionOfHeadStart is 3 and positionOfMaxHeadStart is 2
		//however, positionOfMaxHeadStart is important to have because
		//when we have a headstart of 8 abd reqNumber of 6, 7, 8, 8, 8,9
		//value for positionOfHeadStart is 9 and positionOfMaxHeadStart is 8
		int positionOfHeadStart = 0;
		int positionOfMaxHeadStart=0;

		
		// find the position of headstart
		for (int i = 0; i < reqList.size(); i++) {
			// positionofheadstart is first number bigger than headstart
			if (reqList.get(i) > headStart) {
				positionOfHeadStart = i;
				break;
			}
		}
		
		for (int i = 0; i < reqList.size(); i++) {
			// positionofheadstart is first number bigger than or equal to headstart
			if (reqList.get(i) >= headStart) {
				positionOfMaxHeadStart = i;
				break;
			}
		}
		
		
		System.out.println("positionOfHeadStart: "+positionOfHeadStart);
		
		// let headstart be the first dot in toBeArranged list
		toBeArranged.add(headStart);
		toStart = Math.abs(headStart - reqList.get(reqList.size() - 1)) > Math.abs(headStart - reqList.get(0));

		if (toStart) {

			// Nearer to zero, direction is to start, largest value after
			// smallest value
			// start from first number that is smaller than headstart
			for (int i = positionOfHeadStart-1; i >= 0; i--) {
				System.out.println(" Nearer to zero i: "+i+" reqList.get(i)"+reqList.get(i));
				toBeArranged.add(reqList.get(i));
			}
			// follow by biggest number
			for (int i = reqList.size() - 1; i >=positionOfHeadStart; i--) {
				System.out.println(" Nearer to zero i: "+i+" reqList.get(i)"+reqList.get(i));
				toBeArranged.add(reqList.get(i));
			}
		} else {
			// Nearer to end, direction is to end, smallest value after larger
			// value
			// start from first number that is bigger than or equal to headstart
			for (int i = positionOfMaxHeadStart; i < reqList.size(); i++) {
				toBeArranged.add(reqList.get(i));
			}
			// follow by 0 and following number
			for (int i = 0; i < positionOfMaxHeadStart; i++) {
				toBeArranged.add(reqList.get(i));
			}
		}

	}

	@Override
	public int getTtlHeadMovement() {
		int total = 0;
		// not comparing headstart
		int maxIndex=1, minIndex=1;

		// get Max and min value
		

		// direction to start
		// Nearer to zero, direction is to start, largest value after smallest
		// value
		// skip the addition between maxIndex and minIndex because huge jump
		// doesn't count as a head movement in CLook
		if (toStart) {
			for (int i = 1; i < getArrangedList().size(); i++) {
				if (getArrangedList().get(i) <= getArrangedList().get(minIndex)) {
					minIndex = i;
				}
			}
			maxIndex = minIndex + 1;
			System.out.println("maxIndex" + maxIndex);
			System.out.println("minIndex" + minIndex);
			for (int i = 0; i < minIndex; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
				System.out.println(" direction to start i: " + i + " " + getArrangedList().get(i) + " - "
						+ getArrangedList().get(i + 1) + " = "
						+ Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1)));
			}
			for (int i = maxIndex; i < getArrangedList().size() - 1; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
				System.out.println(" direction to start i: " + i + " " + getArrangedList().get(i) + " - "
						+ getArrangedList().get(i + 1) + " = "
						+ Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1)));
			}
		} else {
			// direction to end
			// Nearer to end, direction is to end, smallest value after larger
			// value
			for (int i = 1; i < getArrangedList().size(); i++) {
				if (getArrangedList().get(i) >= getArrangedList().get(maxIndex)) {
					maxIndex = i;
				}
			}
			minIndex = maxIndex + 1;
			System.out.println("maxIndex" + maxIndex);
			System.out.println("minIndex" + minIndex);
			for (int i = 0; i < maxIndex; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
				System.out.println(" direction to end i: " + i + " " + getArrangedList().get(i) + " - "
						+ getArrangedList().get(i + 1) + " = "
						+ Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1)));
			}
			for (int i = minIndex; i < getArrangedList().size() - 1; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
				System.out.println(" direction to end i: " + i + " " + getArrangedList().get(i) + " - "
						+ getArrangedList().get(i + 1) + " = "
						+ Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1)));
			}
		}

		return total;
	}

}
