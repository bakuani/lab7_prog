//package InputOutput;
//
//import Commands.CommandList;
//import OtherClasses.Color;
//import OtherClasses.Coordinates;
//import OtherClasses.Location;
//import OtherClasses.Person;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
///**
// * класс для чтения файла
// */
//public class FileRead {
//
//
//    /**
//     * выполняет чтение файла
//     *
//     * @return данные из файла
//     * @throws IOException
//     */
//    public ArrayList<Person> fileRead(String fileName) {
//        try {
//            FileInputStream fileInputStream = new FileInputStream(fileName);
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//            ArrayList<Person> data = new ArrayList<>();
//            StringBuilder line = new StringBuilder();
//            while (bufferedInputStream.available() > 0) {
//                int bytes = bufferedInputStream.read();
//                if (bytes != 10) {
//                    line.append((char) bytes);
//                    continue;
//                }
//                data.add(fromDataToPerson(line.toString()));
//                line = new StringBuilder();
//            }
//            fileInputStream.close();
//            bufferedInputStream.close();
//            return data;
//        } catch (IOException e) {
//            System.out.println("Файл не найден. Попробуйте ещё раз");
//            System.exit(0);
//        }
//        return new ArrayList<>();
//    }
//
//
//    /**
//     * выполняет сбор данных из файла
//     *
//     * @param line полученная строка из файла
//     * @return элемент из файла
//     */
//    public Person fromDataToPerson(String line) {
//        String result = line;
//        String[] splited_line = result.split(",");
//        String[] splited_coordinates = splited_line[2].split(";");
//        String[] splited_location = splited_line[8].split(";");
//
//        long id = Long.parseLong(splited_line[0]);
//        CommandList.getIDs().add(id);
//
//        String name = splited_line[1];
//
//        double x = Double.parseDouble((splited_coordinates[0]));
//        Integer y = Integer.valueOf((splited_coordinates[1]));
//        Coordinates coordinates = new Coordinates(x, y);
//
//        LocalDateTime creationDate = LocalDateTime.parse(splited_line[3]);
//
//        double height = Double.parseDouble(splited_line[4]);
//        double weight = Double.parseDouble(splited_line[5]);
//
//        String passportID = splited_line[6];
//
//        Color eyeColor = Color.valueOf(splited_line[7]);
//
//
//        int locationX = Integer.parseInt(splited_location[0]);
//        Double locationY = Double.valueOf(splited_location[1]);
//        Long locationZ = Long.valueOf(splited_location[2]);
//        String locationName = splited_location[3];
//        Location location = new Location(locationX, locationY, locationZ, locationName);
//
//        Person person = new Person(id, name, coordinates, height, weight, passportID, eyeColor, location);
//        CommandList.getCollection().add(person);
//
//        return person;
//    }
//}
