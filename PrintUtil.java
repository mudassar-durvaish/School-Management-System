package sms.first;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PrintUtil {

    public static void saveScreen(FileChooser fileChooser, Scene scene) {
        // Show the FileChooser dialog
        File file = fileChooser.showSaveDialog(null);

        // If a file is selected, save the image
        if (file != null) {
            saveImage(scene, file);
        }
    }

    public static void saveCanvas(FileChooser fileChooser, Canvas canvas) {
        // Show the FileChooser dialog
        File file = fileChooser.showSaveDialog(null);

        // If a file is selected, save the canvas
        if (file != null) {
            saveCanvasImage(canvas, file);
        }
    }

    private static void saveImage(Scene scene, File file) {
        // Capture the content of the scene
        WritableImage image = scene.snapshot(null);

        // Save the WritableImage to the specified file
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("Screenshot saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveCanvasImage(Canvas canvas, File file) {
        // Capture the content of the canvas
        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage);

        // Save the WritableImage to the specified file
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            System.out.println("Canvas saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
