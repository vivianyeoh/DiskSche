package Algoritms;

import java.util.ArrayList;

public abstract class ScheAlgorithm {
	protected ArrayList<Integer> reqList; 
	protected int headStart;
	
	public ScheAlgorithm(ArrayList<Integer> reqList, int headStart) {
		this.reqList = reqList;
		this.headStart = headStart;
	}
	
	public abstract ArrayList<Integer> getArragedList();

	public int getTtlHeadMovement(){
		int total = 0;
		for (int i = 0; i < getArragedList().size()-1; i++) {
			total += Math.abs(getArragedList().get(i)-getArragedList().get(i+1));
		}
		return total;
	}
}
