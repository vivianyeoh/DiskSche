package Algoritms;

import java.util.ArrayList;

public class CLookAlgo implements ScheAlgorithm {

	private ArrayList<Integer> reqList;
	private int headStart;

	public CLookAlgo(ArrayList<Integer> reqList, int headStart) {
		this.reqList = reqList;
		this.headStart = headStart;
	}

	public ArrayList<Integer> returnArragedList() {
		ArrayList<Integer> toBeArraged = reqList;

		return toBeArraged;
	}

	public int returnTtlHeadMovement() {
		int headStart = 0;
		
		return headStart;
	}
}
