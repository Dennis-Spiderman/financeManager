package control;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Income;
import model.StaticObjects;

public class IncomesFiltered implements Initializable {
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
    private TableColumn<Income, Double> TVCOLUMN_AMOUNT;

    @FXML
    private TableColumn<Income, String> TVCOLUMN_DATE;

    @FXML
    private TableColumn<Income, String> TVCOLUMN_DESCRIPTION;

    @FXML
    private TableView<Income> TV_INCOMES;

    @FXML
    void back(MouseEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/main-functions-window.fxml"));
    	loader.setController(new MainFunctionsWindow());
    	Parent root = loader.load();
    	
    	MAIN_PANE.getChildren().setAll(root);
    	MAIN_PANE.getScene().getWindow().sizeToScene();
    }

    @FXML
    void filterExpenses(ActionEvent event) throws ParseException {
    	try {
			Date initDate = new SimpleDateFormat("yyyy-MM-dd").parse(DP_INITDATE.getValue().toString());
			Date finalDate = new SimpleDateFormat("yyyy-MM-dd").parse(DP_FINALDATE.getValue().toString());
			
			StaticObjects.financeManager.filterIncomes(initDate, finalDate);
			updateTVInfo();
		} catch (NullPointerException e) {
			
		}
    }

    public void updateTVInfo() {
    	ObservableList<Income> filteredIncomes = FXCollections.observableList(StaticObjects.financeManager.getFilteredIncomes());
		
		TVCOLUMN_AMOUNT.setCellValueFactory(new PropertyValueFactory<Income, Double>("amount"));
		TVCOLUMN_DESCRIPTION.setCellValueFactory(new PropertyValueFactory<Income, String>("description"));
		TVCOLUMN_DATE.setCellValueFactory(new PropertyValueFactory<Income, String>("dateStr"));
		
		TV_INCOMES.setItems(filteredIncomes);
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Income> incomes = FXCollections.observableList(StaticObjects.financeManager.getIncomes());
		
		TVCOLUMN_AMOUNT.setCellValueFactory(new PropertyValueFactory<Income, Double>("amount"));
		TVCOLUMN_DESCRIPTION.setCellValueFactory(new PropertyValueFactory<Income, String>("description"));
		TVCOLUMN_DATE.setCellValueFactory(new PropertyValueFactory<Income, String>("dateStr"));
		
		TV_INCOMES.setItems(incomes);
	}
}
