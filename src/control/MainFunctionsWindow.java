package control;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainFunctionsWindow {
	@FXML
    private ImageView IMAGEV_BALANCE;

    @FXML
    private ImageView IMAGEV_EXPENSES;

    @FXML
    private ImageView IMAGEV_INCOMES;

    @FXML
    private Pane MAIN_PANE;
    
    @FXML
    void seeBalance(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/balanceList-window.fxml"));
    	loader.setController(new BalanceList());
    	Parent root = loader.load();
    	
    	MAIN_PANE.getChildren().setAll(root);
    	MAIN_PANE.getScene().getWindow().sizeToScene();
    }

    @FXML
    void seeExpenses(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/expensesList-window.fxml"));
    	loader.setController(new ExpensesList());
    	Parent root = loader.load();
    	
    	MAIN_PANE.getChildren().setAll(root);
    	MAIN_PANE.getScene().getWindow().sizeToScene();
    }

    @FXML
    void seeIncomes(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/incomesList-window.fxml"));
    	loader.setController(new IncomesList());
    	Parent root = loader.load();
    	
    	MAIN_PANE.getChildren().setAll(root);
    	MAIN_PANE.getScene().getWindow().sizeToScene();
    }
    
    @FXML
    void showTooltipBalance(MouseEvent event) {
		Tooltip t = new Tooltip("Ver balance");
		Tooltip.install(IMAGEV_BALANCE, t);
    }

    @FXML
    void showTooltipExpenses(MouseEvent event) {
    	Tooltip t = new Tooltip("Ver gastos");
		Tooltip.install(IMAGEV_EXPENSES, t);
    }

    @FXML
    void showTooltipIncomes(MouseEvent event) {
    	Tooltip t = new Tooltip("Ver ingresos");
		Tooltip.install(IMAGEV_INCOMES, t);
    }
}
