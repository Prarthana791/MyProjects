import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Controller.UserController;

public class MyMain extends Application {
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			 this.primaryStage = primaryStage;
	           
			//Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
			//System.out.println(loader.getLocation());
			//Inflate the view using the loader
            AnchorPane root = (AnchorPane) loader.load();
            //Set window title
             primaryStage.setTitle("Login Page");
            //Create a scene with the inflated view
            Scene scene = new Scene(root);
            //Set the scene to the stage
            primaryStage.setScene(scene);
            //Get the controller instance from the loader
            UserController controller = loader.getController();
            //Set the parent stage in the controller
            controller.setDialogStage(primaryStage);
            //Show the view
            primaryStage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view in main: " + e);
			e.printStackTrace();
		}
	}

		public static void main(String[] args) {
		launch(args);
	}
}
