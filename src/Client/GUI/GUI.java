package Client.GUI;

import javax.swing.*;

public class GUI extends JFrame
{
    MenuScreen menuScreen;

    public GUI()
    {
        menuScreen = new MenuScreen();
    }

    public MenuScreen getMenuScreen()
    {
        return menuScreen;
    }
}
