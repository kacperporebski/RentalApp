package RentalPropertyManagementSystem;

import RentalPropertyManagementSystem.Client.RenterWebsite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server
{
    private int port;
    private Socket socket;
    private ServerSocket serverSocket;
    private BufferedReader socketInput;
    private PrintWriter socketOutput;

    private RenterWebsite renterWebsite;

    public Server()
    {
        try
        {
            renterWebsite = new RenterWebsite();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        try
        {
            serverSocket = new ServerSocket(9090);
            System.out.println("Server is running\n");
            socket = serverSocket.accept();
            socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOutput = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e)
        {
            System.out.println("Error in Server\n");
            e.printStackTrace();
        }
    }

    public void getUserInput()
    {
        StringBuffer stringBuffer;
        try
        {
            while(true)
            {
                stringBuffer = new StringBuffer(socketInput.readLine());
                String input = stringBuffer.toString();
            }
        } catch (IOException e)
        {
            System.out.println("Error in Server retrieval of user input\n");
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Server ds = new Server();
    }

}
