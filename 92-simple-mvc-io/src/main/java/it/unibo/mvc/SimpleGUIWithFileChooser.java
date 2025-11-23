package it.unibo.mvc;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 5;
    private static final int TEXTAREADIMENSION = 7;
    private final JFrame frame = new JFrame();
    private final Controller controllerFile = new Controller();

    /**
     * Construct a new {@code SimpleGUIWithFileChooser}.
     */
    public SimpleGUIWithFileChooser() {
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

        final JPanel upperPanel = new JPanel();
        canvas.add(upperPanel, BorderLayout.NORTH);
        upperPanel.setLayout(new BorderLayout());
        final JTextArea upperSpace = new JTextArea(1, TEXTAREADIMENSION);
        upperSpace.setEditable(false);
        upperSpace.setText(controllerFile.getFile().getName());
        mainPanel.add(upperSpace, BorderLayout.CENTER);
        final JButton upperButton = new JButton("choose file");
        mainPanel.add(upperButton, BorderLayout.LINE_END);

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent ignored) {
                try {
                    controllerFile.writeOnFile(writingSpace.getText());
                } catch (final IOException e) {
                    e.printStackTrace(); // NOPMD
                }
            }
        });

        upperButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent ignored) {
                showSaveDialog(upperButton);
                upperSpace.setText(controllerFile.getFile().getName());
            }

        });
    }

    private void showSaveDialog(final JButton parentButton) {
        final JFileChooser chooser = new JFileChooser();
        final int returnValue = chooser.showOpenDialog(parentButton);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            controllerFile.setFile(chooser.getSelectedFile());
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            System.out.println("nothing happens"); // NOPMD
        } else {
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    "An unexpected error",
                    "File Selection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
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
        new SimpleGUIWithFileChooser().display();
    }
}
