
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShopPanel extends JPanel implements ActionListener {

    Player player;
    GamePanel gp;
    int Gold = 0;
    int[] Cost = new int[]{50, 150, 500};
    public boolean visible = false;
    JPanel gridPanel = new JPanel();
    public JButton[][] buttons = new JButton[4][3];
    JLabel label = new JLabel();
    JButton SuperPower = new JButton("Super : 10000");

    ShopPanel(Player player, GamePanel gp) {
        this.gp = gp;
        this.player = player;
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        gridPanel.setLayout(new GridLayout(4, 3, 1, 1));
        gridPanel.setBounds(14, 140, 770, 400);
        setButtons();
        SuperPower.setBackground(Color.red);
        SuperPower.setForeground(Color.BLUE.darker());
        SuperPower.setBounds(500, 20, 200, 50);
        SuperPower.addActionListener(this);
        label.setText("Gold : " + 0);
        label.setBackground(Color.DARK_GRAY);
        label.setForeground(Color.YELLOW);
        label.setAlignmentX(LEFT_ALIGNMENT);
        label.setBounds(20, 15, 200, 80);
        label.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
        this.add(SuperPower);
        this.add(gridPanel);
        this.add(label);
        this.setBounds(200, 50, 800, 550);
        Display();
    }

    public void setGold(int Gold) {
        this.Gold = Gold;
        label.setText("Gold : " + this.Gold);
    }

    public void setButtons() {
        String[] str = new String[4];
        str[0] = "♦️️️ Level";
        str[1] = "️️♨ Level";
        str[2] = "❄️ Level";
        str[3] = "Bullet️ Power Level  ";
        for (int i = 0; i < buttons.length; i++) {
            String s = str[i];
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial Unicode MS", Font.PLAIN, 17));
                buttons[i][j].setText(s + (j + 1) + "  COST : " + Cost[j]);
                buttons[i][j].setPreferredSize(new Dimension(264, 80));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setBackground(Color.DARK_GRAY);
                buttons[i][j].setForeground(Color.YELLOW);
                gridPanel.add(buttons[i][j]);
            }
        }
    }

    public void Display() {
        this.setVisible(visible);
    }

    public void Enable(int r, int c) {
        for (int i = c; i >= 0; i--) {
            buttons[r][i].setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source == SuperPower) {
            if (Gold >= 10000) {
                Gold -= 10000;
                setGold(Gold);
                GamePanel.superPower = true;
                SuperPower.setEnabled(false);
                gp.Score -= 10000;
            }
        }
        if (source == buttons[0][0]) {
            if (Gold >= Cost[0]) {
                Gold -= Cost[0];
                player.max = 2;
                gp.Score -= Cost[0];
                Enable(0, 0);
            }
        } else if (source == buttons[0][1]) {
            if (Gold >= Cost[0]) {
                Gold -= Cost[1];
                player.max = 3;
                gp.Score -= Cost[1];
                Enable(0, 1);
            }

        } else if (source == buttons[0][2]) {
            if (Gold >= Cost[2]) {
                Gold -= Cost[2];
                player.max = 5;
                gp.Score -= Cost[2];
                Enable(0, 2);
            }
        } else if (source == buttons[1][0]) {
            if (Gold >= Cost[0]) {
                Gold -= Cost[0];
                gp.ReloadSpeed = 2;
                gp.Score -= Cost[0];
                Enable(1, 0);
            }
        } else if (source == buttons[1][1]) {
            if (Gold >= Cost[1]) {
                Gold -= Cost[1];
                gp.ReloadSpeed = 4;
                gp.Score -= Cost[1];
                Enable(1, 1);
            }
        } else if (source == buttons[1][2]) {
            if (Gold >= Cost[2]) {
                Gold -= Cost[2];
                gp.ReloadSpeed = 8;
                gp.Score -= Cost[2];
                Enable(1, 2);
            }
        } else if (source == buttons[2][0]) {
            if (Gold >= Cost[0]) {
                Gold -= Cost[0];
                gp.bulletSize = 12;
                gp.Score -= Cost[0];
                Enable(2, 0);
            }
        } else if (source == buttons[2][1]) {
            if (Gold >= Cost[1]) {
                Gold -= Cost[1];
                gp.bulletSize = 14;
                gp.Score -= Cost[1];
                Enable(2, 1);
            }
        } else if (source == buttons[2][2]) {
            if (Gold >= Cost[2]) {
                Gold -= Cost[2];
                gp.bulletSize = 16;
                gp.Score -= Cost[2];
                Enable(2, 2);
            }
        } else if (source == buttons[3][0]) {
            if (Gold >= Cost[0]) {
                Gold -= Cost[0];
                gp.bulletPower = 2;
                gp.Score -= Cost[0];
                Enable(3, 0);
            }

        } else if (source == buttons[3][1]) {
            if (Gold >= Cost[1]) {
                Gold -= Cost[1];
                gp.bulletPower = 3;
                gp.Score -= Cost[1];
                Enable(3, 1);
            }
        } else if (source == buttons[3][2]) {
            if (Gold >= Cost[2]) {
                Gold -= Cost[2];
                gp.bulletPower = 4;
                gp.Score -= Cost[2];
                Enable(3, 2);
            }
        }
        setGold(Gold);
    }
}
