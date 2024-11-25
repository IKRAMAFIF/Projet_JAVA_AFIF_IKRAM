import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList;
    private String timerText = ""; // Texte pour le Timer

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public void setTimerText(String text) {
        this.timerText = text;
    }

    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayables) {
        if (!renderList.containsAll(displayables)) {
            renderList.addAll(displayables);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject : renderList) {
            renderObject.draw(g);
        }


        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLUE);
        g.drawString(timerText, 10, 20); // Position : en haut à gauche
    }

    @Override
    public void update() {
        this.repaint();
    }
}
