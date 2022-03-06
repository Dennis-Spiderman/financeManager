package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Expense;
import model.StaticObjects;

public class ExpensesList implements Initializable {
	@FXML
    private Pane MAIN_PANE;
	
    @FXML
    private ImageView BTTN_BACK;
    
    @FXML
    private Button BTTN_EDIT;

    @FXML
    private Button BTTN_REMOVE;

    @FXML
    private TableColumn<Expense, Double> TVCOLUMN_AMOUNT;

    @FXML
    private TableColumn<Expense, String> TVCOLUMN_DATE;

    @FXML
    private TableColumn<Expense, String> TVCOLUMN_DESCRIPTION;

    @FXML
    private TableView<Expense> TV_EXPENSES;
    
    private Expense selectedExpense;

    @FXML
    void back(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/main-functions-window.fxml"));
    	loader.setController(new MainFunctionsWindow());
    	Parent root = loader.load();
    	
    	MAIN_PANE.getChildren().setAll(root);
    	MAIN_PANE.getScene().getWindow().sizeToScene();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Expense> expenses = FXCollections.observableList(StaticObjects.financeManager.getExpenses());
		
		TVCOLUMN_AMOUNT.setCellValueFactory(new PropertyValueFactory<Expense, Double>("amount"));
		TVCOLUMN_DESCRIPTION.setCellValueFactory(new PropertyValueFactory<Expense, String>("description"));
		TVCOLUMN_DATE.setCellValueFactory(new PropertyValueFactory<Expense, String>("dateStr"));
		
		TV_EXPENSES.setItems(expenses);
		
		TV_EXPENSES.setOnMouseClicked(event -> {
			selectedExpense = TV_EXPENSES.getSelectionModel().getSelectedItem();
		});
	}
	
	public void updateTVInfo() {
		ObservableList<Expense> expenses = FXCollections.observableList(StaticObjects.financeManager.getExpenses());
		
		TVCOLUMN_AMOUNT.setCellValueFactory(new PropertyValueFactory<Expense, Double>("amount"));
		TVCOLUMN_DESCRIPTION.setCellValueFactory(new PropertyValueFactory<Expense, String>("description"));
		TVCOLUMN_DATE.setCellValueFactory(new PropertyValueFactory<Expense, String>("dateStr"));
		
		TV_EXPENSES.setItems(expenses);
	}
	
    @FXML
    void editSelectedItem(ActionEvent event) {

    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
    	try {
			if(!selectedExpense.equals(null)) {
				StaticObjects.financeManager.getExpenses().remove(selectedExpense);
				updateTVInfo();
			}
		} catch (NullPointerException e) {
			
		}
    }
}
