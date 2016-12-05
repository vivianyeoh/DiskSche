package Algoritms;

import java.util.ArrayList;

public class LookAlgo extends ScheAlgorithm {

	private ArrayList<Integer> reqList;
	private int headStart;
	
	public LookAlgo(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);
	}

	public ArrayList<Integer> getArragedList() {
		ArrayList<Integer> toBeArraged = reqList;
		toBeArraged.add(headStart);
		return toBeArraged;
	}


}
