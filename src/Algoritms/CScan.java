package Algoritms;

import java.util.ArrayList;
import java.util.Collections;

public class CScan extends ScheAlgorithm {
	private int minIndex = 0;
	private int maxIndex = 1;

	public CScan(ArrayList<Integer> reqList, int headStart, int maxValue) {
		super(reqList, headStart,maxValue);
	}

	public void arrangeList() {
		Collections.sort(reqList);
		int positionOfReqlist = 0;
		for (int i = 0; i < reqList.size(); i++) {
			if (reqList.get(i) >= headStart) {
				positionOfReqlist = i;
				break;
			}
		}
		
		toBeArranged.add(headStart);
		
		
		if (headStart != (maxValue/2)) {
			// Nearer to end, direction is to end, smallest value after larger
			// value
			for (int i = positionOfReqlist; i < reqList.size(); i++) {
				toBeArranged.add(reqList.get(i));
			}
			toBeArranged.add(maxValue);
			System.out.println(maxValue);
			toBeArranged.add(0);
			for (int i = 0; i < positionOfReqlist; i++) {
				toBeArranged.add(reqList.get(i));
			}
		} else {
			// Nearer to zero, direction is to start, largest value after
			// smallest value
			for (int i = positionOfReqlist - 1; i >= 0; i--) {
				toBeArranged.add(reqList.get(i));
			}
			System.out.println("to yhe lft");
			toBeArranged.add(0);
			toBeArranged.add(maxValue);

			for (int i = reqList.size() - 1; i >= positionOfReqlist; i--) {
				toBeArranged.add(reqList.get(i));
			}
		}
	}

	@Override
	public int getTtlHeadMovement() {
		int total = 0;

		// direction to end
		if (minIndex > maxIndex) {
			for (int i = 0; i < maxIndex; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
			}
			for (int i = minIndex; i < getArrangedList().size() - 1; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
			}
		} // direction to start
		else {

			for (int i = 0; i < minIndex; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
			}
			for (int i = maxIndex; i < getArrangedList().size() - 1; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
			}
		}

		return total;
	}
	
}
