package server;

import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {

    private static int port = 8089;
    private ServerSocket serverSocket;
    private static final String FILE_SETTINGS = "src\\main\\java\\server\\settings.txt";

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start(){
        System.out.println("Server is running....");
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("new client has connected");
                ClientHandler connection = new ClientHandler(socket);
                Thread thread = new Thread(connection);
                thread.start();

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static int setSetting(String fileName){
        int port = 0;
        try(FileReader reader = new FileReader(fileName))
        {
            StringBuilder stringBuilder = new StringBuilder();
            int c;
            while((c=reader.read())!=-1){
               stringBuilder.append ((char)c);
            }
            List<String> settings = Arrays.asList(stringBuilder.toString().split("\r\n"));
            port = Integer.parseInt(settings.get(0).substring(5));

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        return port;
    }


    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(Server.setSetting(Server.FILE_SETTINGS));
        Server server = new Server(socket);
        server.start();

    }
}





