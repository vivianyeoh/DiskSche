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
		int distanceToEnd = Math.abs(headStart - reqList.get(reqList.size() - 1));
		toBeArraged.add(headStart);
		System.out.println("headStart" + headStart+ " positionofreqlist"+positionOfReqlist);
		if (distanceToZero <= distanceToEnd) {
			// Nearer to zero, direction is to left
			for (int i = positionOfReqlist-1; i >= 0; i--) {
				toBeArraged.add(reqList.get(i));
				System.out.println("nearer "+i+": " + reqList.get(i));
			}
			for (int i = reqList.lastIndexOf(reqList); i > positionOfReqlist; i--) {
				toBeArraged.add(reqList.get(i));
				System.out.println("nearer "+(i+positionOfReqlist-1)+": " + reqList.get(i));
			}
		} else {
			// Nearer to end, direction is to right
			for (int i = positionOfReqlist + 1; i <= reqList.lastIndexOf(reqList); i++) {
				toBeArraged.add(reqList.get(i));
				System.out.println("further "+i+": " + reqList.get(i));
			}
			for (int i = 0; i < positionOfReqlist - 1; i++) {
				toBeArraged.add(reqList.get(i));
				System.out.println("further "+(i+positionOfReqlist-1)+": " + reqList.get(i));
			}
		}
		
		return toBeArraged;
	}

}
