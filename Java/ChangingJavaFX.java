import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChangingJavaFX extends Application implements Runnable {
    
    private static Rectangle[][] squares = new Rectangle[28][28];

    @Override
    public void start(Stage primaryStage) {
        
        //sample program with only one root node
        Group root = new Group();
        Scene scene = new Scene(root,280,280,Color.WHITE);

        //add leaf nodes 
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                root.getChildren().add(squares[i][j]);
            }
        }
        
        primaryStage.setTitle("JavaFX Scene Graph Example");
        primaryStage.setScene(scene);
        primaryStage.show();
             
    }

    @Override
    public void run() {
        launch();
    }

    public static void main(String[] args) {
        // Initialize squares
        int colorCount = 0;
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                squares[i][j] = new Rectangle();
                squares[i][j].setX(i*10);
                squares[i][j].setY(j*10);
                squares[i][j].setWidth(10);
                squares[i][j].setHeight(10);
                squares[i][j].setFill(
                    Color.rgb(colorCount, colorCount, colorCount));
                colorCount++;
                if (colorCount > 255)
                    colorCount = 0;
            }
        }

        Thread uiThread = new Thread(new ChangingJavaFX());
    }
    
}
