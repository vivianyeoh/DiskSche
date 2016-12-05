package Algoritms;

import java.util.ArrayList;
import java.util.Collections;

public class CLookAlgo extends ScheAlgorithm {

	public CLookAlgo(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);
	}

	public ArrayList<Integer> getArrangedList() {
		ArrayList<Integer> toBeArranged = new ArrayList<Integer>();
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
		toBeArranged.add(headStart);
		System.out.println("headStart" + headStart+ " positionofreqlist"+positionOfReqlist);
		if (distanceToZero <= distanceToEnd) {
//			 Nearer to zero, direction is to left
			for (int i = positionOfReqlist-1; i >= 0; i--) {
				toBeArranged.add(reqList.get(i));
				System.out.println("nearer 1 "+i+": " + reqList.get(i));
			}
			for (int i = reqList.size()-1; i > positionOfReqlist; i--) {
				toBeArranged.add(reqList.get(i));
				System.out.println("nearer 2 "+(i+positionOfReqlist-1)+": " + reqList.get(i));
			}
		} else {
			// Nearer to end, direction is to right
			for (int i = positionOfReqlist + 1; i <= reqList.size()-1; i++) {
				toBeArranged.add(reqList.get(i));
				System.out.println("further 1 "+i+": " + reqList.get(i));
			}
			for (int i = 0; i < positionOfReqlist - 1; i++) {
				toBeArranged.add(reqList.get(i));
				System.out.println("further 2 "+(i+positionOfReqlist-1)+": " + reqList.get(i));
			}
		}
		
		return toBeArranged;
	}

}
