package Algoritms;

import java.util.ArrayList;
import java.util.Collections;

public class CLookAlgo extends ScheAlgorithm {

	public CLookAlgo(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);
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

		int distanceToZero = Math.abs(headStart - 0);
		int distanceToEnd = Math.abs(headStart - reqList.get(reqList.size() - 1));

		toBeArranged.add(headStart);

		if (distanceToZero <= distanceToEnd) {
			// Nearer to zero, direction is to start, largest value after
			// smallest value
			for (int i = positionOfReqlist - 1; i >= 0; i--) {
				toBeArranged.add(reqList.get(i));
			}
			for (int i = reqList.size() - 1; i >= positionOfReqlist; i--) {
				toBeArranged.add(reqList.get(i));
			}
		} else {
			// Nearer to end, direction is to end, smallest value after larger
			// value
			for (int i = positionOfReqlist; i < reqList.size(); i++) {
				toBeArranged.add(reqList.get(i));
			}
			for (int i = 0; i < positionOfReqlist; i++) {
				toBeArranged.add(reqList.get(i));
			}
		}

	}

	@Override
	public int getTtlHeadMovement() {
		int total = 0;
		int max = 1;
		int min = 1;
		for (int i = 1; i < getArrangedList().size(); i++) {
			if (getArrangedList().get(i) > getArrangedList().get(max))
				max = i;
			if (getArrangedList().get(i) < getArrangedList().get(min))
				min = i;
		}

		// direction to end
		if (min > max) {
			for (int i = 0; i < max; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
			}
			for (int i = min; i < getArrangedList().size() - 1; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
			}
		} // direction to start
		else {

			for (int i = 0; i < min; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
			}
			for (int i = max; i < getArrangedList().size() - 1; i++) {
				total += Math.abs(getArrangedList().get(i) - getArrangedList().get(i + 1));
			}
		}

		return total;
	}

}
