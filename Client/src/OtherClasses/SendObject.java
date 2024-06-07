package OtherClasses;

import Authentication.Authentication;
import InputOutput.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * отправка и принятие запроса
 */
public class SendObject {
    private User currentUser = null;
    private User testUser = null;
    ArrayList<String> with_person = new ArrayList<>(List.of("add", "add_if_max", "remove_lower", "update"));
    ArrayList<String> stop = new ArrayList<>(List.of("exit"));
    ArrayList<String> withCredentials = new ArrayList<>(List.of("login", "register"));
    boolean isConnected = false;
    private SocketChannel socketChannel;

    public SendObject() {
    }

    /**
     * отправка запроса на выполнение команды и принятие ответа с сервера
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void sendObject() {
        try {
            ObjectOutputStream outputStream;
            socketChannel = createCnannel();


            while (!isConnected) {
                isConnected = true;
                try{
                    outputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
                } catch (NullPointerException | IOException e){
                    break;
                }
                if (with_person.contains(InputedCommand.getInputed_command()[0])) {
                    Request request = new Request(InputedCommand.getInputed_command(), NewPerson.createNewPerson(), currentUser);
                    outputStream.writeObject(request);
                } else if (stop.contains(InputedCommand.getInputed_command()[0])) {
                    System.exit(0);
                } else if (withCredentials.contains(InputedCommand.getInputed_command()[0])) {
                    String[] finalCommmand = {InputedCommand.getInputed_command()[0], Authentication.inputCredentials()};
                    testUser = new User(Authentication.getUsername(), Authentication.getPassword());
                    Request request = new Request(finalCommmand, null, testUser);
                    outputStream.writeObject(request);
                } else {
                    Request request = new Request(InputedCommand.getInputed_command(), null, currentUser);
                    outputStream.writeObject(request);
                }
                ObjectInputStream objectInputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
                Response response = (Response) objectInputStream.readObject();
                if (response.getMessage().equals("Вход успешно выполнен")){
                    currentUser = testUser;
                    testUser = null;
                }

                System.out.println(response.getMessage());
            }
            isConnected = false;
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Ошибка во время взаимодействия с сервером");
        }

    }

    /**
     * создание канала для передачи
     * @return socketChannel
     */
    private SocketChannel createCnannel() {
        while (true) {
            try {
                socketChannel = SocketChannel.open();
                SocketAddress address = new InetSocketAddress("localhost", 34364);
                socketChannel.connect(address);
                return socketChannel;
            } catch (IOException e) {
                System.out.println("Сервер не подключён");
                break;
            }
        }
        return null;
    }
}