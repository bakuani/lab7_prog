//package Commands;
//
//import InputOutput.Response;
//import OtherClasses.Person;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.concurrent.CopyOnWriteArrayList;
//
///**
// * класс для команды save
// */
//public class Save extends Command {
//    String fileName;
//    public Save(String fileName) {
//        this.fileName = fileName;
//    }
//
//    /**
//     * сохраняет коллекцию в файл
//     *
//     * @param nothing ничего
//     */
//
//    @Override
//    public Response execute(String nothing) {
//        File file = new File(fileName);
//        CopyOnWriteArrayList<Person> text = CommandList.getCollection();
//
//        try (FileWriter fw = new FileWriter(file);
//             BufferedWriter bf = new BufferedWriter(fw);
//             PrintWriter out = new PrintWriter(bf)) {
//            text.forEach(s -> out.printf("%s\n", s));
//
//
//            return new Response("Successfully written data to the file");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new Response("Data was not written to the file");
//        }
//    }
//}
