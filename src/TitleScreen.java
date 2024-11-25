import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen {
    private JFrame frame;

    public TitleScreen(JFrame frame) {
        this.frame = frame;
        initialize();
    }

    private void initialize() {
        frame.setTitle("Ecran Titre");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        JLabel titleLabel = new JLabel("Bienvenue dans le Jeu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.CENTER);

        JButton playButton = new JButton("Jouer");
        panel.add(playButton, BorderLayout.SOUTH);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Supprimer l'écran titre et lancer le jeu principal
                frame.getContentPane().removeAll(); // Supprimer le contenu précédent
                try {
                    new Main(frame); // Lancer le jeu
                    frame.revalidate(); // Revalider le contenu
                    frame.repaint();    // Redessiner la fenêtre
                    frame.requestFocusInWindow(); // Assurer que le jeu prend le focus clavier
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }
}
