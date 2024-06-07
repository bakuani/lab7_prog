package InputOutput;

import Interfaces.IDGenerator;
import OtherClasses.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.*;


public class RequestReceiver {
    HashMaps hashMaps = new HashMaps();
    Instuction instuction = new Instuction();
    ServerSocketChannel serverSocketChannel = null;

    /**
     * обработка запроса, принятого с клиента
     *
     * @param socketChannel канал для передачи
     * @return запрос
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Request prepareRequest(SocketChannel socketChannel) throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
        Request request = (Request) objectInputStream.readObject();
        System.out.println("Success ");
        return request;
    }

    /**
     * формирование ответа от сервера
     *
     * @param request запрос от клиента
     * @return овтет от сервера
     * @throws SQLException
     */
    public Response formResponse(Request request) throws SQLException {
        if (request.getPerson() != null) {
            request.getPerson().setId(IDGenerator.createID());
        }

        if (request.getUser() == null && !List.of("login", "register").contains(request.getCommand()[0])) {
            return new Response("Вы не зарегестрированы");
        }

        hashMaps.work();
        return instuction.instruction(request);
    }

    /**
     * отправка ответа от сервера
     *
     * @param socketChannel канал
     * @param response      ответ от сервера
     * @throws IOException
     */
    public void sendResponse(SocketChannel socketChannel, Response response) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        outputStream.writeObject(response);
    }

    /**
     * полуечение запроса
     *
     * @throws Exception
     */
    public void requestReceiver() {

        serverSocketChannel = createServerSocketChannel();

        ForkJoinPool forkJoinPool = new ForkJoinPool(1);
        ThreadPoolExecutor handleRequestPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        ThreadPoolExecutor sendResponsePool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

        while (true) {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();

                if (socketChannel == null) {
                    continue;
                }

                ForkJoinTask<Request> requestFuture = forkJoinPool.submit(() -> prepareRequest(socketChannel));
                Request request;
                try {
                    request = requestFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    continue;
                }

                if (request == null) {
                    continue;
                }

                handleRequestPool.execute(() -> {
                    Response response;
                    try {
                        response = formResponse(request);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    sendResponsePool.execute(() -> {
                        try {
                            sendResponse(socketChannel, response);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                });
            } catch (IOException e) {
                System.out.println("Возникла ошибка. Пожалайста, попробуйте ещё раз");
            }

        }
    }

    /**
     * создание канала
     *
     * @return канал
     * @throws IOException
     */
    private ServerSocketChannel createServerSocketChannel() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            SocketAddress socketAddress = new InetSocketAddress(34364);
            serverSocketChannel.bind(socketAddress);
            return serverSocketChannel;
        } catch (IOException e) {
            System.out.println("Возникла ошибка. Пожалайста, попробуйте ещё раз");
        }
        return null;
    }
}
