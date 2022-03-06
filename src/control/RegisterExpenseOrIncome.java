package control;

import java.io.IOException;
import java.text.ParseException;

import exceptions.InformationNotFilledException;
import exceptions.TypeNotSelectedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.StaticObjects;

public class RegisterExpenseOrIncome {
    @FXML
    private Button BTTN_ADD;

    @FXML
    private DatePicker DP_DATE;

    @FXML
    private RadioButton RDBTTN_EXPENSE;

    @FXML
    private RadioButton RDBTTN_INCOME;

    @FXML
    private TextField TXT_AMOUNT;

    @FXML
    private TextArea TXT_DESCRIPTION;

    @FXML
    private ToggleGroup groupOne;
    
    @FXML
    private ImageView BTTN_BACK;
    
    @FXML
    private Pane MAIN_PANE;
    
    @FXML
    void registerInfo(ActionEvent event) {
    	try {
        	double amount = Double.parseDouble(TXT_AMOUNT.getText());
        	String description = TXT_DESCRIPTION.getText();
        	String dateStr = DP_DATE.getValue().toString();
        	int type = 0;
        	
        	if(RDBTTN_EXPENSE.isSelected()) {
        		type = 1;
        	}else if(RDBTTN_INCOME.isSelected()) {
        		type = 2;
        	}
        	
        	StaticObjects.financeManager.addExpenseOrIncome(amount, description, dateStr, type);
        	
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Agregado");
        	alert.setHeaderText("Movimiento registrado");
        	alert.setContentText("El movimiento ha sido registrado correctamente. Felicidades.");
        	alert.show();
    	}catch (InformationNotFilledException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error de información incompleta");
			alert.setHeaderText("¡La información no ha sido llenada!");
			alert.setContentText("No has llenado toda la información requerida."
					+ " Intenta nuevamente");
			alert.show();
    	}catch (TypeNotSelectedException e) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error de tipo no elegido");
			alert.setHeaderText("¡No has seleccionado un tipo!");
			alert.setContentText("No has seleccionado el tipo de movimiento que quieres registrar."
					+ " Intenta nuevamente");
			alert.show();
    	}catch (ParseException | NullPointerException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error de fecha inválida");
			alert.setHeaderText("¡La fecha no está bien escrita!");
			alert.setContentText("No has escrito bien la fecha del ingreso/egreso."
					+ " Intenta nuevamente");
			alert.show();
		}catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error monto inválido");
			alert.setHeaderText("¡El monto es inválido!");
			alert.setContentText("No has escrito bien la el monto del ingreso/egreso."
					+ " Intenta nuevamente");
			alert.show();
		}
    	
    	TXT_AMOUNT.clear();
    	TXT_DESCRIPTION.clear();
    	DP_DATE.setValue(null);
    	RDBTTN_EXPENSE.setSelected(false);
    	RDBTTN_INCOME.setSelected(false);
    }
    
    @FXML
    void back(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/main-functions-window.fxml"));
    	loader.setController(new MainFunctionsWindow());
    	Parent root = loader.load();
    	
    	MAIN_PANE.getChildren().setAll(root);
    	MAIN_PANE.getScene().getWindow().sizeToScene();
    }
}
