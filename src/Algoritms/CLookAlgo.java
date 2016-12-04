package Algoritms;

import java.util.ArrayList;

public class CLookAlgo implements ScheAlgorithm {

	public ArrayList<Integer> reqList;

	public CLookAlgo(ArrayList<Integer> reqList) {
		this.reqList = reqList;
	}

	public ArrayList<Integer> returnArragedList() {
		ArrayList<Integer> toBeArraged = reqList;

		return toBeArraged;
	}

	public int returnHeadStart() {
		int headStart = 0;
		
		return headStart;
	}
}
