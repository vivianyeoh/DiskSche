
package application;

import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Algoritms.CLookAlgo;
import Algoritms.FCFS;
import Algoritms.ScheAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
	private JFXTextField fldHeadMove;
	private JFXTextField[] jtfReq;
	public boolean isFilledA, isFilledB, isFilledC;
	private final static Logger LOGGER = Logger.getLogger(LineGrpController.class.getName());

	public void initialize() {
		initializeFields();
		initializeCylinderReqField();
		initializeComboBox();

		yAxis.setLabel("Request Number");
		xAxis.setLabel("Movement");
	}

	public void initializeCylinderReqField() {

		numOfRequest.textProperty().addListener((observable, oldValue, newValue) -> {
			clearGraph();
			sclReq.getChildren().clear();
			isFilledA = validateTextFields(numOfRequest, newValue);
			if (isFilledA) {
				numOfRequest.setPromptText("Total Number of Request");
				int num = Integer.parseInt(newValue);
				if (isFilledC) {
					initializeReqList(num);
					btnRad.setDisable(false);

					if (isFilledB) {
						btnIllustr.setDisable(false);
					} else {
						btnIllustr.setDisable(true);
					}
				} else {
					btnRad.setDisable(true);
				}
			}
		});

		headStart.textProperty().addListener((observable, oldValue, newValue) -> {
			isFilledB = validateTextFields(headStart, newValue);
			if (isFilledB) {
				headStart.setPromptText("Number of Head Start");
				if (isFilledA && isFilledC) {
					btnIllustr.setDisable(false);
				} else {
					btnIllustr.setDisable(true);
				}
			}
		});

		maxCyl.textProperty().addListener((observable, oldValue, newValue) -> {
			clearGraph();
			sclReq.getChildren().clear();
			isFilledC = validateTextFields(maxCyl, newValue);
			if (isFilledC) {
				maxCyl.setPromptText("Maximum No of Cylinder");
				if (isFilledA) {
					int numOfReq = !numOfRequest.getText().trim().equals("")
							? Integer.parseInt(numOfRequest.getText().trim()) : 0;
					initializeReqList(numOfReq);
					btnRad.setDisable(false);
					if (isFilledB) {
						btnIllustr.setDisable(false);
					} else {
						btnIllustr.setDisable(true);
					}
				} else {
					btnRad.setDisable(true);
				}
			}

		});

	}

	public boolean validateTextFields(TextField txt, String newValue) {

		if (!newValue.trim().matches("\\d*") || newValue.trim().equals("")) {		
			txt.setPromptText("Insert a POSITIVE NUMBER please.");
		}else {
			txt.setEffect(null);
			return true;
		}
		txt.setText("");
		txt.setEffect(drawBorder());
		txt.requestFocus();
		return false;
	}

	// When num of cylinder is different, VBox has to change the number of
	// request text field it has
	public void initializeReqList(int numOfCy) {

		jtfReq = new JFXTextField[numOfCy];

		for (int i = 0; i < numOfCy; i++) {

			jtfReq[i] = new JFXTextField();
			jtfReq[i].setPromptText("Req: " + (i + 1));
			jtfReq[i].setLabelFloat(true);
			int j = i;
			jtfReq[i].textProperty().addListener((observable, oldValue, newValue) -> {
				boolean val = validateTextFields(jtfReq[j], newValue);
				if (val) {
					int fieldValue = (!jtfReq[j].getText().trim().equals("")
							? Integer.parseInt(jtfReq[j].getText().trim()) : 0);
					int maxValue = (!maxCyl.getText().trim().equals("") ? Integer.parseInt(maxCyl.getText().trim())
							: 0);
					if (fieldValue > maxValue) {
						
						jtfReq[j].setPromptText("Req no. more than max number!");
					}else{
						jtfReq[j].setPromptText("Req: " + (j + 1));
						return;
					}
					jtfReq[j].setText("");
					jtfReq[j].setEffect(drawBorder());
					jtfReq[j].requestFocus();
				}

			});

			jtfReq[i].setId("Req" + i);
			sclReq.getChildren().add(jtfReq[i]);

		}
	}

	public void initializeComboBox() {
		diskSchCombo.getItems().addAll("First-Come/First-Served (FCFS)", "Shortest Seek Time First (SSTF)", "SCAN",
				"CSCAN", "LOOK", "CLOOK");
		diskSchCombo.setValue("First-Come/First-Served (FCFS)");
	}

	public void initializeFields() {
		btnIllustr.setDisable(true);
		btnRad.setDisable(true);
	}

	public ArrayList<Integer> createFullReqList(int numOfReq) {
		ArrayList<Integer> reqList = new ArrayList<Integer>();
		if (numOfReq > 0) {
			for (int i = 0; i < numOfReq; i++) {
				reqList.add(!jtfReq[i].getText().trim().equals("") ? Integer.parseInt(jtfReq[i].getText().trim()) : 0);
			}
		}
		return reqList;
	}

	public void submitReq() {
		clearGraph();

		int startValue = (!headStart.getText().trim().equals("") ? Integer.parseInt(headStart.getText().trim()) : 0);
		int num = (!numOfRequest.getText().trim().equals("") ? Integer.parseInt(numOfRequest.getText().trim()) : 0);

		if (num > 1) {
			ArrayList<Integer> reqList = createFullReqList(num);
			XYChart.Series series = new XYChart.Series();
			series.setName(diskSchCombo.getValue().toString());

			ScheAlgorithm alg = new FCFS(reqList, startValue);

			switch (diskSchCombo.getValue().toString()) {
			case "First-Come/First-Served (FCFS)":
				alg = new FCFS(reqList, startValue);
				break;
			case "Shortest Seek Time First (SSTF)":
				alg = new CLookAlgo(reqList, startValue);
				break;
			case "SCAN":
				alg = new CLookAlgo(reqList, startValue);
				break;
			case "CSCAN":
				alg = new CLookAlgo(reqList, startValue);
				break;
			case "LOOK":
				alg = new CLookAlgo(reqList, startValue);
				break;
			case "CLOOK":
				alg = new CLookAlgo(reqList, startValue);
				break;
			default:
				// For exception handling
				LOGGER.log(Level.SEVERE, "ScheAlgorithm switch case");
				System.exit(0);
			}

			series.getData().add(new XYChart.Data(0, startValue));
			for (int i = 1; i <= num; i++) {
				series.getData().add(new XYChart.Data(i, alg.getArragedList().get(i - 1)));
			}
			fldHeadMove.setText(alg.getTtlHeadMovement() + "");
			lineGrp.getData().add(series);
		}
	}

	public void clear() {
		clearGraph();
		sclReq.getChildren().clear();
		numOfRequest.setText(0 + "");
		headStart.setText(0 + "");
		maxCyl.setText(0 + "");
		fldHeadMove.setText(0 + "");
		diskSchCombo.setValue("First-Come/First-Served (FCFS)");
	}

	public void clearGraph() {

		lineGrp.getData().clear();

	}

	public DropShadow drawBorder() {
		int depth = 30;
		DropShadow borderGlow = new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.RED);
		borderGlow.setWidth(depth);
		borderGlow.setHeight(depth);
		return borderGlow;
	}

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
