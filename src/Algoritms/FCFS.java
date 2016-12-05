package Algoritms;

import java.util.ArrayList;

public class FCFS extends ScheAlgorithm {


	public FCFS(ArrayList<Integer> reqList, int headStart) {
		this.reqList = reqList;
		this.headStart = headStart;
	}

	public ArrayList<Integer> getArragedList() {
		ArrayList<Integer> toBeArraged = reqList;
		toBeArraged.add(headStart);
		return toBeArraged;
	}

	
}
