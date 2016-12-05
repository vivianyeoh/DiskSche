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
		System.out.println("getTtlHeadMovement()");
		try{
		for (int i = 0; i < getArragedList().size()-1; i++) {
			System.out.println("total"+total);
			total += Math.abs(getArragedList().get(i)-getArragedList().get(i+1));
		}}catch(Exception e){
			System.out.println("exception"+e.getMessage());
		}
		return total;
	}
}
