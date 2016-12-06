package Algoritms;

import java.util.ArrayList;
import java.util.Collections;

public class Scan extends ScheAlgorithm {


	public Scan(ArrayList<Integer> reqList, int headStart, int maxValue) {
		super(reqList, headStart, maxValue);
	}

	public void arrangeList() {

		toBeArranged.add(headStart);
		reqList.add(0);
		int totalDistance = 0;
		int []distance = new int [reqList.size() + 1];
		Collections.sort(reqList);

		// assume that the reqList initially goes from left to right
		int startIndex =0;
		// search for start request
		for (int i = 0; i < reqList.size(); i++) {	
			if(reqList.get(i)>=headStart)
			{
				startIndex = i;
				break;
			}
		}
		
		int iterator = 1;
		totalDistance+= Math.abs(headStart - reqList.get(startIndex));
		

		for (int i = startIndex; i < reqList.size()-1; i++, iterator++) {

			totalDistance+= Math.abs(reqList.get(i+1) - reqList.get(i));
			distance[iterator] = reqList.get(i);
		}
		// to add the last request

		distance[iterator] = reqList.get(reqList.size()-1);
		iterator++;
		
		
		totalDistance+= Math.abs(reqList.get(reqList.size()-1) - reqList.get(startIndex -1));
		for (int i = startIndex-1; i > 0; i--, iterator++) {

			totalDistance+= Math.abs(reqList.get(i-1) - reqList.get(i));
			distance[iterator] = reqList.get(i);
		}
		distance[iterator] = reqList.get(0);

		for(int i =startIndex-1; i >= 0; --i){
			toBeArranged.add(reqList.get(i));
		}
		for(int i =startIndex; i < reqList.size(); ++i){
			toBeArranged.add(reqList.get(i));
		}
		
		distance[0] = totalDistance;
		System.out.println(toBeArranged);
	}

	
}
