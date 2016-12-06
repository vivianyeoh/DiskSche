package Algoritms;

import java.util.ArrayList;
import java.util.Collections;

public class LookAlgo extends ScheAlgorithm {

	private int minIndex = 1;
	private int maxIndex = 1;

	public LookAlgo(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);

		for (int i = 1; i < getArrangedList().size(); i++) {
			if (getArrangedList().get(i) > getArrangedList().get(maxIndex))
				maxIndex = i;
			if (getArrangedList().get(i) < getArrangedList().get(minIndex))
				minIndex = i;
		}

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

		if (minIndex > maxIndex) {
			// Nearer to end, direction is to end, smallest value after larger
			// value
			for (int i = positionOfReqlist; i < reqList.size(); i++) {
				toBeArranged.add(reqList.get(i));
			}
			for (int i = positionOfReqlist - 1; i > 0; i--) {
				toBeArranged.add(reqList.get(i));
			}
		} else {
			// Nearer to zero, direction is to start, largest value after
			// smallest value
			for (int i = positionOfReqlist - 1; i >= 0; i--) {
				toBeArranged.add(reqList.get(i));
			}
			for (int i = positionOfReqlist; i < reqList.size();  i++) {
				toBeArranged.add(reqList.get(i));
			}
		}

	}

}
