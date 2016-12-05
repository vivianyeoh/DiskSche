package Algoritms;

import java.util.ArrayList;

public class LookAlgo extends ScheAlgorithm {

	
	public LookAlgo(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);
	}

	public void arrangeList() {
		toBeArranged.add(headStart);
	}


}
