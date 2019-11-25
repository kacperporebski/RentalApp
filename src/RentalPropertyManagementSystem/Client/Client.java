package RentalPropertyManagementSystem.Client;

import RentalPropertyManagementSystem.Controller.RPMSController;
import RentalPropertyManagementSystem.GUI.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class Client
{
    private String host;
    private int port;
    private Socket socket;
    private PrintWriter socketOutput;
    private BufferedReader socketInput;

    public Client(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public void establishConnection() throws IOException
    {
        System.out.println("Connecting to Server");
        socket = new Socket(host, port);
        socketOutput = new PrintWriter(socket.getOutputStream(), true);
        socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    public void start() throws SQLException, IOException
    {
        establishConnection();
        System.out.println("Connecting to Server");

        GUI gui = new GUI();
        RenterWebsite model = new RenterWebsite();
        RPMSController controller = new RPMSController(gui, model);
    }

    public static void main(String[] args) throws SQLException, IOException
    {
        Client client = new Client("localhost", 9090);
        client.start();
    }
}
