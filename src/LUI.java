package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LUI {

     LGameManager Lgm;

    Rectangle maxBounds;
    GraphicsEnvironment ge;
    JFrame window ,textbx ;
    public  TextArea messageText;
    private JButton button;
    public  JPanel bgPanel[] = new JPanel[10];
    public  JLabel bgLabel[] = new JLabel[10];
    public  JLabel objLabel[]= new JLabel[23];

    public ArrayList<JLabel> imagelist = new ArrayList<>();
    public ArrayList<ObjectHidingButton> buttonlist = new ArrayList<>();


    public  JComponent obj[]=new  JComponent[10];


    int objX = 0;

    int objY = 0;

    public  LUI(LGameManager Lgm){


        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        maxBounds = ge.getMaximumWindowBounds();
        this.Lgm = Lgm;
        createMainField();
        createBackground(1,"images/LevelOneMain.png");

        generateScreen();
        window.setVisible(true);
    }


    public void  createMainField(){
        window = new JFrame();
        textbx = new JFrame();

        window.setSize(maxBounds.width, maxBounds.height);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.GRAY);
        window.setResizable(false);
        window.setLayout(null);


    }


    public void  CreateButton(int bgnum, String location, int posx, int posy, int sizex, int sizey)
    {
        JButton jButton = new JButton();
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(location));

        jButton.setContentAreaFilled(false);
        jButton.setIcon(imageIcon);
        jButton.setBounds(posx,posy,sizex,sizey);
        jButton.setBackground(new Color(0,0,0,0));
        jButton.setFocusPainted(false);
        jButton.setBorderPainted(false);
        jButton.setFocusable(false);
        jButton.setPressedIcon(imageIcon);


        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //   jButton.setBackground(null);
                jButton.setVisible(false);

            }
        });



        bgPanel[bgnum].add(jButton);

    }


    public  void createBackground(int bgnum, String bgfilename) {
        bgPanel[bgnum] = new JPanel();


        bgPanel[bgnum].setBounds(0, 0, maxBounds.width, maxBounds.height);//size of the background image
        bgPanel[bgnum].setBackground(Color.black);
        bgPanel[bgnum].setLayout(null);
        window.add(bgPanel[1]);




        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(bgfilename));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(maxBounds.width, maxBounds.height-100, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        bgLabel[bgnum] = new JLabel();
        bgLabel[bgnum].setBounds(0,0, maxBounds.width,maxBounds.height);
        bgLabel[bgnum].setIcon(imageIcon);




    }
    public  void createObject (int bgnum, int objx, int objy,int objw,int objh,String objfilename,String choice1name,String choice2name,String choice3name){




        JLabel objectLabel = new JLabel();

        objectLabel.setLayout(null);
        objectLabel.setBounds(objx,objy,objw,objh);
        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(objfilename));
        int objectimageWidth = objectIcon.getIconWidth();
        int objectimageHeight = objectIcon.getIconHeight();
        objectLabel.setIcon(objectIcon);



        objectLabel.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int leftX = objX;
                int rightX = objX+objectimageWidth;
                int leftY = 750/2 + objY;
                int rightY = (750/2 + objY) + objectimageHeight;


                System.out.println("clicked " + x + " " + y);
                if(SwingUtilities.isLeftMouseButton(e)) {
                    if ( x < rightX && x > leftX && y > leftY && y <rightY) {
                        objectLabel.setVisible(false);
                        //popupMenu.sobjectLabel,e.getX(),e.getY());

                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {



            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        bgPanel[bgnum].add(objectLabel);
        bgPanel[bgnum].add(bgLabel[bgnum]);



    }


    public void  createObject( String image )
    {
        JLabel objtLabel = new JLabel();
        objtLabel.setBounds(0,0,maxBounds.width,maxBounds.height);



        ImageIcon  obj1icon= new ImageIcon(getClass().getClassLoader().getResource(image));
        Image image1 = obj1icon.getImage();
        image1 = image1.getScaledInstance(maxBounds.width, maxBounds.height-100, Image.SCALE_DEFAULT);
        obj1icon = new ImageIcon(image1);

        objtLabel.setIcon(obj1icon);
        imagelist.add(objtLabel);
        bgPanel[1].add(objtLabel);

    }


    public void  createButton(String image,int posx, int posy, int sizex,int sizey)
    {
        createObject(image);
        ObjectHidingButton objectHidingButton = new ObjectHidingButton(posx,posy,sizex,sizey,imagelist.get(imagelist.size()-1));
        //imagelist.get(imagelist.size-1)
        bgPanel[1].add(objectHidingButton);
        buttonlist.add(objectHidingButton);

    }



    public  void generateScreen()
    {

        createButton("images/01.png",308,450,65,84);
        createButton("images/02.png",1190,520,18,30);
        createButton("images/03.png",1242,440,78,54);
        createButton("images/04.png",1130,750,60,26);
        createButton("images/05.png",330,740,43,32);
        createButton("images/06.png",410,689,69,13);
        createButton("images/07.png",1104,712,68,22);
        createButton("images/08.png",860,410,25,12);
        createButton("images/09.png",484,390,50,32);
        createButton("images/10.png",544,440,68,24);
        createButton("images/11.png",94,750,158,24);
        createButton("images/12.png",744,555,98,60);
        createButton("images/13.png",523,610,34,68);
        createButton("images/14.png",184,470,80,80);
        createButton("images/15.png",1034,440,28,20);
        createButton("images/16.png",894,524,28,19);
        createButton("images/17.png",1204,730,98,43);
        createButton("images/18.png",724,50,160,20);
        createButton("images/19.png",924,524,30,19);
        createButton("images/20.png",1368,760,48,14);
        createButton("images/21.png",934,485,40,24);
        createButton("images/22.png",494,435,18,14);



        bgPanel[1].add(bgLabel[1]);

    }


}
