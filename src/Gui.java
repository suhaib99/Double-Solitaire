import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Gui extends JFrame {
    JLabel pic1,pic2,pic3;
    public Gui(){
        super("Drag and Drop the Cards");
        pic1 = new JLabel();    //A JLabel object can display either text, an image, or both.
        pic2 = new JLabel();

        pic1.setBounds(20, 30, 150, 150); // first two arguments are coords and the other are dimensions.
        pic2.setBounds(250, 30, 150, 150);


        pic1.setIcon(new ImageIcon("C:\\Users\\rajai\\Documents\\GitHub\\Double-Solitaire\\Resources\\Decks\\pngs\\diamondAce.png")); //ImageIcon creates a uninitialized image icon
        pic2.setIcon(new ImageIcon("C:\\Users\\rajai\\Documents\\GitHub\\Double-Solitaire\\Resources\\Decks\\pngs\\clubAce.png"));

        MouseListener listener = new MouseListener() {      //The listener interface for receiving mouse events (press, release, click, enter, and exit) on a component.

            @Override
            public void mouseClicked(MouseEvent mouseevent) { // The MouseEvent interface represents events that occur due to the user interacting with a mouse

            }

            @Override
            public void mousePressed(MouseEvent mouseevent) {

                JComponent jc = (JComponent)mouseevent.getSource();     //The class JComponent is the base class for all Swing components except top-level containers

                                                                        /*An event object contains a reference to the component that generated the event.
                                                                        To extract that reference from the event object use:
                                                                        Object getSource()*/


                TransferHandler th = jc.getTransferHandler();           /*This class is used to handle the transfer of a Transferable to and from Swing components.
                                                                        The Transferable is used to represent data that is exchanged via a cut, copy, or paste to/from a clipboard.
                                                                         It is also used in drag-and-drop operations to represent a drag from a component, and a drop to a component.
                                                                         */


                th.exportAsDrag(jc, mouseevent, TransferHandler.COPY);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };

        pic1.addMouseListener(listener);
        pic2.addMouseListener(listener);


        pic1.setTransferHandler(new TransferHandler("icon"));
        pic2.setTransferHandler(new TransferHandler("icon"));

        add(pic1);
        add(pic2);

        setLayout(null);
        setSize(1800,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }



    public static void main(String[] args){

        new Gui();
    }
}
