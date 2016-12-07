/**
 * @author Yeoh Hui Wen
 * @Date 6 Dec 2016
 */

package application;

import javafx.scene.effect.DropShadow;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Algoritms.CLook;
import Algoritms.CScan;
import Algoritms.FCFS;
import Algoritms.Look;
import Algoritms.SSTF;
import Algoritms.Scan;
import Algoritms.ScheAlgorithm;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LineGrpController {
	@FXML
	private NumberAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private LineChart<Number, Number> lineGrp;
	@FXML
	private JFXComboBox diskSchCombo;
	@FXML
	private JFXTextField numOfRequest;
	@FXML
	private JFXTextField headStart;
	@FXML
	private JFXTextField maxCyl;
	@FXML
	private VBox sclReq;
	@FXML
	private JFXButton btnIllustr;
	@FXML
	private JFXButton btnClr;
	@FXML
	private JFXButton btnRad;
	@FXML
	private JFXTextArea fldHeadMove;
	@FXML
	private JFXTextArea fldSequence;
	@FXML
	private JFXTextField weightPtg;
	@FXML
	private ProgressBar progressWeight;

	private JFXTextField[] jtfReq;
	public boolean isFilledA, isFilledB, isFilledC;
	private final static Logger LOGGER = Logger.getLogger(LineGrpController.class.getName());

	public void initialize() {
		disableButtons();
		initializeCylinderReqField();
		initializeComboBox();
		progressWeight.setStyle("-fx-accent: #e51c23");
		yAxis.setLabel("Cylinder");
		xAxis.setLabel("Time Unit");

		// add dots on line graph
		lineGrp.setCreateSymbols(true);

		// to solve java.lang.IllegalArgumentException: The start must be <= the
		// end
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// let numofRequest be the first field that user type
				numOfRequest.requestFocus();
			}
		});

	}

	// when user change the value of numOfRequest, headStart, maxCyl;
	public void initializeCylinderReqField() {

		numOfRequest.textProperty().addListener((observable, oldValue, newValue) -> {
			// Reset graph
			clearGraph();

			// Reset vbox
			sclReq.getChildren().clear();

			// to check if value in numOfRequest is zero or positive number
			isFilledA = validateTextFields(numOfRequest, newValue);

			if (isFilledA) {
				numOfRequest.setPromptText("Total Number of Requests");
				// make sure num is number to solve
				// java.lang.NumberFormatException: For input string:""
				int num = (!newValue.equals("") ? Integer.parseInt(newValue) : 0);

				// if user has typed maximum cylinder
				if (isFilledC) {
					//allow user to key in headstart
					headStart.setDisable(false);
					// add request textfield in sclReq(vbox)
					initializeReqList(num);

					// enable button that generate random values
					btnRad.setDisable(false);

					// if user has filled in all 3 fields
					if (isFilledB && checkReqMax()) {
						// enable user to click illustrate graph button
						btnIllustr.setDisable(false);
					} else {
						// disable the illustrate graph button
						btnIllustr.setDisable(true);
					}
				} else {
					// disable the button that generate random values because
					// there is no textfields in vbox
					btnRad.setDisable(true);
					
					//force user to key in headstart
					headStart.setDisable(true);
				}
			} else {
				numOfRequest.setPromptText("Num of Req must be a number!");
			}
		});

		maxCyl.textProperty().addListener((observable, oldValue, newValue) -> {
			// Reset graph
			clearGraph();

			// Reset vbox
			sclReq.getChildren().clear();

			// to check if value in maxCyl is zero or positive number
			isFilledC = validateTextFields(maxCyl, newValue);
			if (isFilledC) {
				maxCyl.setPromptText("Maximum No of Cylinder");
				headStart.setDisable(false);
				if (isFilledA) {

					// make sure num is number to solve
					// java.lang.NumberFormatException: For input string:""
					int numOfReq = !numOfRequest.getText().trim().equals("")
							? Integer.parseInt(numOfRequest.getText().trim()) : 0;

					// reset req list
					initializeReqList(numOfReq);

					// allow user to click button that generates random values
					btnRad.setDisable(false);

					// if user has filled in all 3 fields
					if (isFilledB && checkReqMax()) {
						// enable user to click illustrate graph button
						btnIllustr.setDisable(false);

					} else {
						// disable the illustrate graph button
						btnIllustr.setDisable(true);

					}
				} else {
					// disable the button that generate random values because
					// there is no textfields in vbox
					btnRad.setDisable(true);
					headStart.setDisable(true);
				}
			} else {
				maxCyl.setPromptText("Max No of Cylinder must be number!");
			}

		});

		headStart.textProperty().addListener((observable, oldValue, newValue) -> {

			int headStartValue = !newValue.trim().equals("") ? Integer.parseInt(newValue.trim()) : 0;
			int maxCylValue = !maxCyl.getText().trim().equals("") ? Integer.parseInt(maxCyl.getText().trim()) : 0;

			// to check if value in headStart is positive number
			isFilledB = validateTextFields(headStart, newValue);
			if (isFilledB) {
				headStart.setPromptText("No of Head Start");
				if (headStartValue > maxCylValue) {

					headStart.requestFocus();
					headStart.setEffect(new javafx.scene.effect.DropShadow(1, Color.RED));
					headStart.setPromptText("Head start can't more than Max Cylinder!");

				} else {
					headStart.setPromptText("Head start");
					// if user has filled in all 3 fields
					if (isFilledA && isFilledC && checkReqMax()) {
						// enable user to click illustrate graph button
						btnIllustr.setDisable(false);
					} else {
						// disable the illustrate graph button
						btnIllustr.setDisable(true);
					}
				}
			} else {
				maxCyl.setPromptText("Head Start must be a number");
			}
		});

	}

	public boolean validateTextFields(JFXTextField txt, String newValue) {
		// check if the value in textfields is a zero or positive number
		if (!(newValue.trim().matches("\\d+") || newValue.equals(""))) {
			txt.setEffect(new javafx.scene.effect.DropShadow(1, Color.RED));
			txt.requestFocus();
			return false;
		} else {
			txt.setEffect(null);
			return true;
		}

	}

	// When num of cylinder is different, VBox has to change the number of
	// request text field it has
	public void initializeReqList(int numOfCy) {

		// generate textfields
		jtfReq = new JFXTextField[numOfCy];

		for (int i = 0; i < numOfCy; i++) {

			jtfReq[i] = new JFXTextField();
			jtfReq[i].setPromptText("Req: " + (i + 1));
			jtfReq[i].setLabelFloat(true);

			// set j=i because local variable i defined in an enclosing scope
			// must be final
			int j = i;
			jtfReq[i].textProperty().addListener((observable, oldValue, newValue) -> {

				// to check if value in jtfReq is zero or positive number
				boolean val = validateTextFields(jtfReq[j], newValue);

				if (val) {
					// make sure fieldValue and maxValue is number to solve
					// java.lang.NumberFormatException: For input string:""
					int fieldValue = (!jtfReq[j].getText().equals("") ? Integer.parseInt(jtfReq[j].getText().trim())
							: 0);
					int maxValue = (!maxCyl.getText().equals("") ? Integer.parseInt(maxCyl.getText().trim()) : 0);

					// request number that user keyed in must be smaller than
					// maximum cylinder
					if (fieldValue > maxValue) {
						jtfReq[j].setPromptText("Req: " + (j + 1) + " must be smaller than max cylinder!");
						jtfReq[j].requestFocus();
						jtfReq[j].setEffect(new javafx.scene.effect.DropShadow(1, Color.RED));
						addOverCount(j);
					} else {
						jtfReq[j].setPromptText("Req: " + (j + 1));
						jtfReq[j].setEffect(null);
						minusOverCount(j);
					}

				} else {
					jtfReq[j].setPromptText("Req: " + (j + 1) + " must be number!");
				}

				if (isFilledA && isFilledB && isFilledC && checkReqMax()) {
					// enable user to click illustrate graph button
					btnIllustr.setDisable(false);
				} else {
					// disable the illustrate graph button
					btnIllustr.setDisable(true);
				}

			});

			// add textfield into vbox sclReq
			sclReq.getChildren().add(jtfReq[i]);

		}
	}

	public static int overlimitCount = 0;
	public ArrayList<Integer> list = new ArrayList<>();

	public void addOverCount(int j) {
		boolean notFound = true;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == j) {
				notFound = false;
			}
		}
		if (notFound) {
			list.add(j);
			overlimitCount++;
		}
	}

	public void minusOverCount(int j) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == j) {
				list.remove(i);
				overlimitCount--;
			}
		}
	}

	public boolean checkReqMax() {
		int num = (!numOfRequest.getText().equals("") ? Integer.parseInt(numOfRequest.getText()) : 0);
		if (overlimitCount != 0)
			return false;
		else
			return true;
	}

	// add information inside combo box
	public void initializeComboBox() {
		diskSchCombo.getItems().addAll("First-Come/First-Served (FCFS)", "Shortest Seek Time First (SSTF)", "SCAN",
				"CSCAN", "LOOK", "CLOOK");
		diskSchCombo.setValue("First-Come/First-Served (FCFS)");
	}

	// disable buttons to prevent user pressed them before user keyed in
	// everything
	public void disableButtons() {
		btnIllustr.setDisable(true);
		btnRad.setDisable(true);
		headStart.setDisable(true);
	}

	// create an arraylist with request numbers that user keyed in
	public ArrayList<Integer> createFullReqList(int numOfReq) {
		ArrayList<Integer> reqList = new ArrayList<Integer>();
		if (numOfReq > 0) {
			for (int i = 0; i < numOfReq; i++) {
				reqList.add(!jtfReq[i].getText().trim().equals("") ? Integer.parseInt(jtfReq[i].getText().trim()) : 0);
			}
		}
		return reqList;
	}

	public void updateProgressBar(int totalheadmovement, int numberofrequest, int maxCylinder) {

		float value = (float)totalheadmovement / (float)numberofrequest/ (float)maxCylinder * 100;
		weightPtg.setText(String.format("%.2f",value)+"%");
		progressWeight.setProgress(value/100);
	}

	// when button illustrate graoh is pressed, generate graph
	public void submitReq() {
		clearGraph();

		// make sure startValue, num and maxValue are numbers to solve
		// java.lang.NumberFormatException: For input string:""
		int startValue = (!headStart.getText().trim().equals("") ? Integer.parseInt(headStart.getText().trim()) : 0);
		int num = (!numOfRequest.getText().trim().equals("") ? Integer.parseInt(numOfRequest.getText().trim()) : 0);
		int maxValue = (!maxCyl.getText().trim().equals("") ? Integer.parseInt(maxCyl.getText().trim()) : 0);

		// number must more than 0 to construct a line between headstart and
		// reqlist
		if (num > 0) {

			// create reqlist with arraylist from createFullReqList
			ArrayList<Integer> reqList = createFullReqList(num);

			// create line chart
			XYChart.Series series = new XYChart.Series();
			series.setName(diskSchCombo.getValue().toString());

			ScheAlgorithm alg = new FCFS(reqList, startValue);

			// get value from combo box to determine which algorithm to use
			switch (diskSchCombo.getValue().toString()) {
			case "First-Come/First-Served (FCFS)":
				alg = new FCFS(reqList, startValue);
				break;
			case "Shortest Seek Time First (SSTF)":
				alg = new SSTF(reqList, startValue);
				break;
			case "SCAN":
				alg = new Scan(reqList, startValue, maxValue);
				break;
			case "CSCAN":
				alg = new CScan(reqList, startValue, maxValue);
				break;
			case "LOOK":
				alg = new Look(reqList, startValue);
				break;
			case "CLOOK":
				alg = new CLook(reqList, startValue);
				break;
			default:
				// For exception handling
				LOGGER.log(Level.SEVERE, "ScheAlgorithm switch case");
				System.exit(0);
			}

			// Starting point has added into scheAlgorithm's subclass
			ArrayList<Integer> algoList = alg.getArrangedList();

			// insert data into linegraph
			for (int i = 0; i < algoList.size(); i++) {
				series.getData().add(new XYChart.Data(i, algoList.get(i)));
			}
			lineGrp.getData().add(series);
			
		
			// set text of sequence field
			fldSequence.setText(printSequence(algoList));

			// set text of total head movement
			fldHeadMove.setText(alg.getTtlHeadMovement() + "");	
			
			//set value of progress bar
			updateProgressBar(alg.getTtlHeadMovement(), num+1, maxValue);
			
		}
	}

	// Create a string that contains data from algolist
	public String printSequence(ArrayList<Integer> algoList) {
		String sequence = "";
		for (int i = 0; i < algoList.size(); i++) {
			sequence += algoList.get(i);
			if (i != algoList.size() - 1)
				sequence += ", ";
			else
				sequence += ".";
		}
		return sequence;
	}

	// clear everything on graph
	public void clear() {
		clearGraph();
		sclReq.getChildren().clear();
		numOfRequest.setText(0 + "");
		headStart.setText(0 + "");
		maxCyl.setText(0 + "");
		diskSchCombo.setValue("First-Come/First-Served (FCFS)");
	}

	// clear all data on graph
	public void clearGraph() {
		weightPtg.setText(0 + "");
		progressWeight.setProgress(0);
		fldSequence.setText(0 + "");
		fldHeadMove.setText(0 + "");
		lineGrp.getData().clear();
	}

	// generate random values when button random is pressed
	public void setValues() {
		int numOfReq = !numOfRequest.getText().trim().equals("") ? Integer.parseInt(numOfRequest.getText().trim()) : 0;
		int maxValue = !maxCyl.getText().trim().equals("") ? Integer.parseInt(maxCyl.getText().trim()) : 0;
		Random rand = new Random();
		for (int i = 0; i < numOfReq; i++) {
			int randomNum = rand.nextInt((maxValue - 1) + 1) + 1;
			jtfReq[i].setText(randomNum + "");
		}
	}

}
