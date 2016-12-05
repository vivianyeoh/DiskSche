package Algoritms;

import java.util.ArrayList;

public class FCFS extends ScheAlgorithm {


	public FCFS(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);
	}

	public ArrayList<Integer> getArragedList() {
		ArrayList<Integer> toBeArraged = new ArrayList<Integer>();
		toBeArraged.add(headStart);
		for(int i:reqList)
			toBeArraged.add(i);
		return toBeArraged;
	}

	
}
