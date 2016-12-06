/**
 * @author Nurtahirah binti Abdul Rahman
 * @Date 6 Dec 2016
 */

package Algoritms;

import java.util.ArrayList;
import java.util.Collections;

public class SSTF extends ScheAlgorithm {

	public SSTF(ArrayList<Integer> reqList, int headStart) {
		super(reqList, headStart);
	}

	public void arrangeList() {
		Collections.sort(reqList);
		toBeArranged.add(headStart);

		for (int i = 1; i < reqList.size(); i++) {

			ArrayList<Integer> distance = new ArrayList<Integer>();
			distance.add(headStart - reqList.get(i));

			while (reqList.size() > 0) // SSTF implementation
			{
				int minDis = 0;
				for (int j = 1; j < reqList.size(); j++)
					// find next minimum distance
					if (Math.abs(headStart - reqList.get(j).intValue()) < Math
							.abs(headStart - reqList.get(minDis).intValue()))
						minDis = j; // minimum distance index

				// Total += Math.abs(headStart -
				// reqList.get(minDis).intValue());
				headStart = reqList.get(minDis).intValue();
				// reposition the head

				reqList.remove(minDis); // remove processed request
				toBeArranged.add(headStart);
			}

		}
	}

}
