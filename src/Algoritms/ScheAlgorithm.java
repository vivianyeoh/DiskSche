package Algoritms;

import java.util.ArrayList;

public abstract class ScheAlgorithm {
	protected ArrayList<Integer> reqList; 
	protected int headStart;
	
	public abstract ArrayList<Integer> getArragedList();

	public int getTtlHeadMovement(){
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
