package Algoritms;

import java.util.ArrayList;
import java.util.Collections;

public class CLookAlgo extends ScheAlgorithm {

	public CLookAlgo(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);
	}

	public ArrayList<Integer> getArragedList() {
		ArrayList<Integer> toBeArraged = new ArrayList<Integer>();
		reqList.add(headStart);
		Collections.sort(reqList);
		int positionOfReqlist = 0;
		for (int i = 0; i < reqList.size(); i++) {
			if (reqList.get(i) == headStart) {
				positionOfReqlist = i;
				break;
			}
		}

		int distanceToZero = Math.abs(headStart - 0);
		int distanceToEnd = Math.abs(headStart - reqList.get(reqList.lastIndexOf(reqList)));

		toBeArraged.set(0, headStart);

		if (distanceToZero <= distanceToEnd) {
			// Nearer to zero, direction is to left
			for (int i = positionOfReqlist - 1; i > 0; i--)
				toBeArraged.get(reqList.get(i));
			for (int i = reqList.lastIndexOf(reqList); i > positionOfReqlist; i--)
				toBeArraged.get(reqList.get(i));
		} else {
			// Nearer to end, direction is to right
			for (int i =positionOfReqlist+1; i < reqList.lastIndexOf(reqList); i++)
				toBeArraged.get(reqList.get(i));
			for (int i = 0; i < positionOfReqlist - 1; i++)
				toBeArraged.get(reqList.get(i));
		}

		return toBeArraged;
	}
	
}
