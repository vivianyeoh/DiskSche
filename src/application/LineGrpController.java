
package application;

import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;

import java.util.ArrayList;
import java.util.Random;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
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
	private ArrayList<Integer> reqList = null;
	public boolean isFilledA, isFilledB, isFilledC;

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
			clearReqList();

			isFilledA = validateCylFields(numOfRequest, newValue);
			if (!newValue.matches("[0-9]+")) {
				initializeFields();
			} else {
				initializeReqList(numOfRequest.getText());
			}

			if (isFilledA && isFilledB && isFilledC) {
				enableSomeButtons(false);
			} else {
				enableSomeButtons(true);
			}

		});

		headStart.textProperty().addListener((observable, oldValue, newValue) -> {
			isFilledB = validateCylFields(headStart, newValue);
			if (isFilledA && isFilledB && isFilledC) {
				enableSomeButtons(false);
			} else {
				enableSomeButtons(true);
			}
		});

		maxCyl.textProperty().addListener((observable, oldValue, newValue) -> {
			isFilledC = validateCylFields(maxCyl, newValue);
			if (isFilledA && isFilledB && isFilledC) {
				enableSomeButtons(false);
			} else {
				enableSomeButtons(true);
			}
		});

	}

	public boolean validateCylFields(TextField txt, String newValue) {

		if (!newValue.trim().matches("\\d*") || newValue.trim().equals("")) {
			txt.setText("");
			txt.setEffect(drawBorder());
			txt.requestFocus();
			txt.setPromptText("Insert a number please.");
		} else {
			txt.setEffect(null);
			return true;
		}
		return false;
	}

	// When num of cylinder is different, VBox has to change the number of
	// request text field it has
	public void initializeReqList(String numOfCy) {
		clearReqList();

		if (!numOfCy.equals("")) {
			int num = Integer.parseInt(numOfCy);
			jtfReq = new JFXTextField[num];

			for (int i = 0; i < num; i++) {

				jtfReq[i] = new JFXTextField();
				jtfReq[i].setPromptText("Req: " + (i + 1));
				jtfReq[i].setLabelFloat(true);

				jtfReq[i].textProperty().addListener((observable, oldValue, newValue) -> {

					if (isFilledA && isFilledB && isFilledC) {
						btnIllustr.setDisable(false);
					} else {
						btnIllustr.setDisable(true);
					}

				});

				jtfReq[i].setId("Req" + i);
				sclReq.getChildren().add(jtfReq[i]);
			}

		}
	}

	public void initializeComboBox() {
		diskSchCombo.getItems().addAll("First-Come/First-Served (FCFS)", "Shortest Seek Time First (SSTF)", "SCAN",
				"CSCAN", "LOOK", "CLOOK");
		diskSchCombo.setValue("First-Come/First-Served (FCFS)");
	}

	public void initializeFields() {
		enableSomeButtons(true);
		fldHeadMove.setDisable(true);
	}

	public void enableSomeButtons(boolean boo) {
		btnIllustr.setDisable(boo);
		btnRad.setDisable(boo);

	}

	public void createFullReqList(int numOfReq) {
		reqList = new ArrayList<Integer>();
		if (numOfReq > 0) {
			for (int i = 0; i < numOfReq; i++) {
				reqList.add(!jtfReq[i].getText().trim().equals("") ? Integer.parseInt(jtfReq[i].getText().trim()) : 0);
			}
		}
	}

	public void submitReq() {
		clearGraph();
		int num = (!numOfRequest.getText().trim().equals("") ? Integer.parseInt(numOfRequest.getText().trim()) : 0);
		if (num > 1) {
			createFullReqList(num);
			XYChart.Series series = new XYChart.Series();
			series.setName(diskSchCombo.getValue().toString());
//			for (int i = 0; i < num; i++) {
//				series.getData().add(new XYChart.Data(i, reqList.get(i)));
//			}
			series.getData().add(new XYChart.Data( 1,50));
			series.getData().add(new XYChart.Data(2, 5));
			series.getData().add(new XYChart.Data(3, 119));
			series.getData().add(new XYChart.Data(4, 119));
			series.getData().add(new XYChart.Data(5, 42));
			series.getData().add(new XYChart.Data(6, 167));
			series.getData().add(new XYChart.Data(7, 5));
			series.getData().add(new XYChart.Data(8, 57));
			series.getData().add(new XYChart.Data(9, 52));
			series.getData().add(new XYChart.Data(10,75));
			lineGrp.getData().add(series);
		}
	}

	public void clear() {
		clearGraph();
		clearReqList();
		numOfRequest.setText("");
		headStart.setText("");
		maxCyl.setText("");
		fldHeadMove.setText("");
		diskSchCombo.setValue("First-Come/First-Served (FCFS)");
	}

	public void clearReqList() {
		sclReq.getChildren().clear();
		if (reqList != null) {
			reqList.removeAll(reqList);
			reqList = null;
		}
	}

	public void clearGraph() {
		if (reqList != null) {
			lineGrp.getData().clear();
		}
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
		clearGraph();
		int numOfReq = !numOfRequest.getText().trim().equals("") ? Integer.parseInt(numOfRequest.getText().trim()) : 0;
		int maxValue = !maxCyl.getText().trim().equals("") ? Integer.parseInt(maxCyl.getText().trim()) : 0;
		Random rand = new Random();
		for (int i = 0; i < numOfReq; i++) {
			int randomNum = rand.nextInt(maxValue + 1);
			jtfReq[i].setText(randomNum + "");
		}
	}

}
