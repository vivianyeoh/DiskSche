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
		System.out.println("headStart" + headStart);
		int distanceToZero = Math.abs(headStart - 0);
		int distanceToEnd = Math.abs(headStart - reqList.get(reqList.size()-1));
		System.out.println("distanceToZero" + distanceToZero);
		System.out.println("distanceToEnd" + distanceToEnd);
		toBeArraged.add(headStart);

		if (distanceToZero <= distanceToEnd) {
			System.out.println("nearer");
			// Nearer to zero, direction is to left
			for (int i = positionOfReqlist - 1; i > 0; i--) {
				System.out.println("nearer 1st i " + i);
				toBeArraged.add(reqList.get(i));
			}
			for (int i = reqList.lastIndexOf(reqList); i > positionOfReqlist; i--) {
				System.out.println("nearer 2nd i " + i);
				toBeArraged.add(reqList.get(i));
			}
		} else {
			System.out.println("further");
			// Nearer to end, direction is to right
			for (int i = positionOfReqlist + 1; i <= reqList.lastIndexOf(reqList); i++) {
				System.out.println("further 1st i " + i);
				toBeArraged.add(reqList.get(i));
			}
			for (int i = 0; i < positionOfReqlist - 1; i++) {
				System.out.println("further 2nd i " + i);
				toBeArraged.add(reqList.get(i));
			}
		}

		return toBeArraged;
	}

}
