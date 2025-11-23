package it.unibo.mvc;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    private static final int PROPORTION = 5;
    private static final int TEXTAREADIMENSION = 7;
    private final JFrame frame = new JFrame();

    /**
     * Construct a new {@code SimpleGUI}.
     */
    public SimpleGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel mainPanel = new JPanel();
        canvas.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        final JTextArea writingSpace = new JTextArea(TEXTAREADIMENSION, TEXTAREADIMENSION);
        mainPanel.add(writingSpace);
        final JButton saveButton = new JButton("save");
        mainPanel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent ingored) {
                final Controller controllerFile = new Controller();
                try {
                    controllerFile.writeOnFile(writingSpace.getText());
                } catch (final IOException e) {
                    e.printStackTrace(); // NOPMD
                }
            }
        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Frame Packing for resizing the frame to the minimum size
         */
        frame.pack();
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args ignored
     */
    public static void main(final String... args) {
        new SimpleGUI().display();
    }
}
