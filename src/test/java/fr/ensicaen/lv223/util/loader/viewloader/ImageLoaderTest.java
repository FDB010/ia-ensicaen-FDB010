package fr.ensicaen.lv223.util.loader.viewloader;

import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageLoaderTest {
    @Test
    void ImageLoaderConstructor() {
        ImageLoader imageLoader = ImageLoader.getInstance(10,10);
        assertNotNull(imageLoader);
    }

    @Test
    void JavafxImageCreationRobotType() {
        ImageLoader imageLoader = ImageLoader.getInstance(10,10);
        for (RobotType type : RobotType.values()) {
            try {
                assertNotNull(imageLoader.getRobotImage(type));
            } catch (Exception e) {
                fail(e);
            }
        }
    }

    @Test
    @Disabled
    void JavaFxImageCreationCellType() {
        ImageLoader imageLoader = ImageLoader.getInstance(10,10);
        for (CellType type : CellType.values()) {
            try {
                assertNotNull(imageLoader.getCellImage(type));
            } catch (Exception e) {
                fail(e);
            }
        }
    }
}