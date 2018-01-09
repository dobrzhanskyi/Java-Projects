import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame
{

    private Game game;
    private final int IMAGE_SIZE=50;
    private final int COLS=9;
    private final int ROWS=9;
    private final int BOMBS=3;

    private JPanel panel;
    private JLabel label;
    public static void main(String[] args)
    {
           new JavaSweeper().setVisible(true);
    }
    private JavaSweeper()
    {
        game = new Game(COLS,ROWS,BOMBS);
        game.Start();
        SetImages();
        initLabel();
        InitFrame();

    }

    private void initLabel()
    {
        label = new JLabel(GetMessage());
        Font font = new Font("Tahoma",Font.BOLD,18);
        label.setFont(font);

        add(label,BorderLayout.SOUTH);
    }

    private void InitPanel()
    {
        panel=new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for(Coord coord : Ranges.getAllCoords())
                    g.drawImage((Image)game.GetBox(coord).image,
                            coord.x * IMAGE_SIZE,
                            coord.y * IMAGE_SIZE, this);
                }
        };


        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int x =e.getX()/IMAGE_SIZE;
                int y =e.getY()/IMAGE_SIZE;

                Coord coord = new Coord(x,y);
                switch(e.getButton())
                {
                    case MouseEvent.BUTTON1 : game.PressLeftButton(coord);break;
                    case MouseEvent.BUTTON3 : game.PressRightButton(coord);break;
                    case MouseEvent.BUTTON2 : game.Start();break;
                }
                label.setText(GetMessage());
                panel.repaint();

            }
        });

        panel.setPreferredSize(new Dimension(Ranges.GetSize().x*IMAGE_SIZE,
                                             Ranges.GetSize().y*IMAGE_SIZE));
        add(panel);
    }
    private void SetImages()
    {
        for(Box box: Box.values())
        {
            box.image = GetImage(box.name().toLowerCase());
        }
        setIconImage(GetImage("icon"));
    }
    private void InitFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setResizable(false);
        InitPanel();
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
    private Image GetImage(String name)
    {
        String filename="img/" + name+ ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
    private String GetMessage()
    {
        switch(game.GetState())
        {
            case BOMBED: return "BA-DA-BOOM! You Lose!";
            case WINNER: return "Congratulations! All bombs have been marked";
            case PLAYED:
            default: if(game.getTotalFlaged()==0)
                     return "Welcome!";
                     else
                         return"Think twice! Flagged "+game.getTotalFlaged()+" of "+game.getTotalBombs()+ " bombs.";
        }
    }
}
