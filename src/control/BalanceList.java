package control;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Expense;
import model.Income;
import model.StaticObjects;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class BalanceList implements Initializable {
    @FXML
    private ImageView BTTN_BACK;

    @FXML
    private Button BTTN_FILTER;

    @FXML
    private DatePicker DP_FINALDATE;

    @FXML
    private DatePicker DP_INITDATE;

    @FXML
    private Pane MAIN_PANE;

    @FXML
    private TableColumn<Expense, Double> TVCOLUMN_EXPENSE_AMOUNT;

    @FXML
    private TableColumn<Expense, String> TVCOLUMN_EXPENSE_DATE;

    @FXML
    private TableColumn<Income, Double> TVCOLUMN_INCOME_AMOUNT;

    @FXML
    private TableColumn<Income, String> TVCOLUMN_INCOME_DATE;

    @FXML
    private TableView<Expense> TV_EXPENSES;

    @FXML
    private TableView<Income> TV_INCOMES;

    @FXML
    private TextField TXT_BALANCE;

    @FXML
    void back(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/main-functions-window.fxml"));
    	loader.setController(new MainFunctionsWindow());
    	Parent root = loader.load();
    	
    	MAIN_PANE.getChildren().setAll(root);
    	MAIN_PANE.getScene().getWindow().sizeToScene();
    }

    @FXML
    void filterData(ActionEvent event) throws ParseException {
    	try {
    		Date initDate = new SimpleDateFormat("yyyy-MM-dd").parse(DP_INITDATE.getValue().toString());
        	Date finalDate = new SimpleDateFormat("yyyy-MM-dd").parse(DP_FINALDATE.getValue().toString());
        	
        	StaticObjects.financeManager.calculateFilteredBalance(initDate, finalDate);
        	updateTVsInfo();
		} catch (NullPointerException e) {
			
		}    	
    }
    
    public void updateTVsInfo() {
    	ObservableList<Expense> filteredExpenses = FXCollections.observableList(StaticObjects.financeManager.getFilteredExpenses());
		
		TVCOLUMN_EXPENSE_AMOUNT.setCellValueFactory(new PropertyValueFactory<Expense,Double>("amount"));
		TVCOLUMN_EXPENSE_DATE.setCellValueFactory(new PropertyValueFactory<Expense,String>("dateStr"));
		
		TV_EXPENSES.setItems(filteredExpenses);
		
		ObservableList<Income> filteredIncomes = FXCollections.observableList(StaticObjects.financeManager.getFilteredIncomes());
		
		TVCOLUMN_INCOME_AMOUNT.setCellValueFactory(new PropertyValueFactory<Income,Double>("amount"));
		TVCOLUMN_INCOME_DATE.setCellValueFactory(new PropertyValueFactory<Income,String>("dateStr"));
		
		TV_INCOMES.setItems(filteredIncomes);
		
		TXT_BALANCE.setText(StaticObjects.financeManager.getBalance() + "");
	}
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Expense> expenses = FXCollections.observableList(StaticObjects.financeManager.getExpenses());
		
		TVCOLUMN_EXPENSE_AMOUNT.setCellValueFactory(new PropertyValueFactory<Expense,Double>("amount"));
		TVCOLUMN_EXPENSE_DATE.setCellValueFactory(new PropertyValueFactory<Expense,String>("dateStr"));
		
		TV_EXPENSES.setItems(expenses);
		
		ObservableList<Income> incomes = FXCollections.observableList(StaticObjects.financeManager.getIncomes());
		
		TVCOLUMN_INCOME_AMOUNT.setCellValueFactory(new PropertyValueFactory<Income,Double>("amount"));
		TVCOLUMN_INCOME_DATE.setCellValueFactory(new PropertyValueFactory<Income,String>("dateStr"));
		
		TV_INCOMES.setItems(incomes);
		
		StaticObjects.financeManager.calculateBalance();
		TXT_BALANCE.setText(StaticObjects.financeManager.getBalance() + "");
    }
}
