import javax.swing.*;
import java.awt.*;

public class FlowerWithBlueBackground extends JFrame {
    private static final int SIZE = 16; // 增加网格大小以更好地展示花瓣和茎杆
    private JPanel[][] grid = new JPanel[SIZE][SIZE];
    private final Color[] PETAL_COLORS = {
        new Color(255, 182, 193), // 淡粉色
        new Color(255, 105, 180), // 深粉色
        new Color(255, 192, 203), // 浅紫色
        new Color(148, 0, 211),    // 紫色
        new Color(75, 0, 130)      // 靛蓝
    };
    private final Color CENTER_COLOR = new Color(255, 215, 0); // 金色
    private final Color STEM_COLOR = new Color(34, 139, 34); // 森林绿
    private final Color BACKGROUND_COLOR = new Color(135, 206, 250); // 浅蓝色

    public FlowerWithBlueBackground() {
        setTitle("Flower with Blue Background");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));
        initializeFlower();
    }

    private void initializeFlower() {
        int center = SIZE / 2;
        int petalRadius = SIZE / 3; // 花瓣半径
        int stemWidth = 2; // 茎杆宽度

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = new JPanel();
                grid[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                grid[row][col].setBackground(BACKGROUND_COLOR); // 设置蓝色背景

                double distance = Math.sqrt(Math.pow(row - center, 2) + Math.pow(col - center, 2));
                
                if (distance <= petalRadius) {
                    // 计算花瓣角度
                    double angle = Math.toDegrees(Math.atan2(row - center, col - center));
                    if (angle < 0) angle += 360;

                    // 根据角度分配颜色
                    int colorIndex = (int) (angle / (360.0 / PETAL_COLORS.length)) % PETAL_COLORS.length;
                    grid[row][col].setBackground(PETAL_COLORS[colorIndex]);
                } else if (col >= center - stemWidth / 2 && col <= center + stemWidth / 2 && row > center) {
                    // 茎杆区域
                    grid[row][col].setBackground(STEM_COLOR);
                } else if (distance <= center) {
                    // 中心区域
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