import javax.swing.*;
import java.awt.*;

public class FlowerWithBlueBackground extends JFrame {
    private static final int SIZE = 16; // Grid size for better visualization
    private JPanel[][] grid = new JPanel[SIZE][SIZE];
    private final Color[] PETAL_COLORS = {
        new Color(255, 182, 193), // Light pink
        new Color(255, 105, 180), // Deep pink
        new Color(255, 192, 203), // Light purple
        new Color(148, 0, 211),   // Purple
        new Color(75, 0, 130)     // Indigo
    };
    private final Color CENTER_COLOR = new Color(255, 215, 0); // Gold
    private final Color STEM_COLOR = new Color(34, 139, 34); // Forest green
    private final Color BACKGROUND_COLOR = new Color(135, 206, 250); // Light blue

    public FlowerWithBlueBackground() {
        setTitle("Flower with Blue Background");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));
        initializeFlower();
    }

    private void initializeFlower() {
        int center = SIZE / 2;
        int petalRadius = SIZE / 3; // Radius for petals
        int stemWidth = 2; // Width of the stem

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = new JPanel();
                grid[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                grid[row][col].setBackground(BACKGROUND_COLOR); // Set blue background

                double distance = Math.sqrt(Math.pow(row - center, 2) + Math.pow(col - center, 2));
                
                if (distance <= petalRadius) {
                    // Calculate petal angle
                    double angle = Math.toDegrees(Math.atan2(row - center, col - center));
                    if (angle < 0) angle += 360;

                    // Assign color based on angle
                    int colorIndex = (int) (angle / (360.0 / PETAL_COLORS.length)) % PETAL_COLORS.length;
                    grid[row][col].setBackground(PETAL_COLORS[colorIndex]);
                } else if (col >= center - stemWidth / 2 && col <= center + stemWidth / 2 && row > center) {
                    // Stem area
                    grid[row][col].setBackground(STEM_COLOR);
                } else if (distance <= center) {
                    // Center area
                    grid[row][col].setBackground(CENTER_COLOR);
                }

                add(grid[row][col]);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FlowerWithBlueBackground().setVisible(true);
        });
    }
}