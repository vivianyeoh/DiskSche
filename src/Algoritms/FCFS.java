/**
 * @author Nurtahirah binti Abdul Rahman
 * @Date 6 Dec 2016
 */

package Algoritms;

import java.util.ArrayList;

public class FCFS extends ScheAlgorithm {

	public FCFS(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);
	}

	public void arrangeList() {
		toBeArranged.add(headStart);
		for (int i : reqList)
			toBeArranged.add(i);
	}

}
