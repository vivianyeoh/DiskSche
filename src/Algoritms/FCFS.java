package Algoritms;

import java.util.ArrayList;

public class FCFS implements ScheAlgorithm {

	private ArrayList<Integer> reqList;
	private int headStart;

	public FCFS(ArrayList<Integer> reqList, int headStart) {
		this.reqList = reqList;
		this.headStart = headStart;
	}

	public ArrayList<Integer> getArragedList() {
		ArrayList<Integer> toBeArraged = reqList;

		return toBeArraged;
	}

	public int getTtlHeadMovement() {
		int total = 0;
		ArrayList<Integer> distance = new ArrayList<Integer>();

		for (int i = 0; i < reqList.size(); i++) {
			if (reqList.get(i) > headStart) {
				distance.add(reqList.get(i) - headStart);
			} else {
				distance.add(headStart - reqList.get(i));
			}
			headStart = reqList.get(i);
		}

		for (int i = 0; i < reqList.size(); i++) {
			total = total + distance.get(i);
		}
		return total;
	}
}
