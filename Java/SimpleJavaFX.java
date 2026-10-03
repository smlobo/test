import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SimpleJavaFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        //sample program with only one root node
        Group root = new Group();
        Scene scene = new Scene(root,280,280,Color.WHITE);

        //add leaf nodes 
        int colorCount = 0;
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                Rectangle sq = new Rectangle(i*10,j*10,10,10);
                sq.setFill(Color.rgb(colorCount, colorCount, colorCount));
                root.getChildren().add(sq);
                colorCount++;
                if (colorCount > 255)
                    colorCount = 0;
            }
        }
        
        primaryStage.setTitle("JavaFX Scene Graph Example");
        primaryStage.setScene(scene);
        primaryStage.show();
             
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
