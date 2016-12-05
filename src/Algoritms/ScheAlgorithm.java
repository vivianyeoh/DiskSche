package Algoritms;

import java.util.ArrayList;

public abstract class ScheAlgorithm {
	protected ArrayList<Integer> reqList; 
	protected int headStart;
	protected ArrayList<Integer> toBeArranged = new ArrayList<Integer>();
	
	public ScheAlgorithm(ArrayList<Integer> reqList, int headStart) {
		this.reqList = reqList;
		this.headStart = headStart;
		arrangeList();
	}
	
	public abstract void arrangeList();

	public int getTtlHeadMovement(){
		int total = 0;
		for (int i = 0; i < getArrangedList().size()-1; i++) {
			total += Math.abs(getArrangedList().get(i)-getArrangedList().get(i+1));
		}
		return total;
	}
	
	public ArrayList<Integer> getArrangedList() {
		return toBeArranged;
	}
}
