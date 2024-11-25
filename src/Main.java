import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.ActionEvent;

public class Main {
    private JFrame displayZoneFrame;
    private RenderEngine renderEngine;
    private GameEngine gameEngine;
    private PhysicEngine physicEngine;

    public Main(JFrame frame) throws Exception {

        this.displayZoneFrame = frame;
        displayZoneFrame.setSize(710, 600);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        DynamicSprite hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);


        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);


        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();


        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);


        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());


        displayZoneFrame.addKeyListener(gameEngine);
        displayZoneFrame.requestFocusInWindow();


        int gameDuration = 20; // Temps total en secondes
        Timer countdownTimer = new Timer(1000, new AbstractAction() {
            int timeLeft = gameDuration;

            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                renderEngine.setTimerText("Temps restant : " + timeLeft + "s"); // Mettre à jour le texte du Timer

                if (timeLeft <= 0) {

                    renderTimer.stop();
                    gameTimer.stop();
                    physicTimer.stop();


                    JOptionPane.showMessageDialog(frame, "Temps écoulé ! Game Over !");


                    System.exit(0);
                }
            }
        });

        countdownTimer.start();
    }

    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame();
        new TitleScreen(frame);
    }
}
