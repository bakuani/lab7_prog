package OtherClasses;

import java.util.*;

/**
 * создание нового элемента коллекции
 */
public class NewPerson {
    private static ArrayList<Long> IDs = new ArrayList<>(List.of(1212L));

    /**
     * @return функция для получения массива с id
     */
    private static ArrayList<String> data;

    /**
     * функция для установки списка с данными
     * @param data список данных
     */
    public static void setData(ArrayList<String> data) {
        NewPerson.data = data;
    }

    /**
     * функция для установки состояния
     * @param mode состояние
     */
    public static void setMode(boolean mode) {
        NewPerson.mode = mode;
    }

    public static boolean mode=true;

    /**
     * функция для выполнения комнанды в интерактивном режиме
     * @return новый экземпляр класса Person
     */
    public static Person fromScanner() {
        Scanner in = new Scanner(System.in);
        String name;
        while (true) {
            System.out.print("Введите имя (без пробелов): ");
            name = in.next();
            if (name != null & !name.isEmpty()) {
                break;
            }
            System.out.print("Имя введено некоректно. Попробуйте ещё раз\n");
        }

        Coordinates coordinates = null;
        double x = 0;
        Integer y = 0;
        while (true) {
            System.out.print("Введите координаты (сначала координату х, затем энтер, координата у: ");

            try {
                x = in.nextDouble();
                y = in.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Координаты введены некорректно. Попробуйте ещё раз");
                in = new Scanner(System.in);
                continue;
            }

            if (x <= 579 && y != null && y <= 911) {
                coordinates = new Coordinates(x, y);
                break;
            }
        }
        double height;
        while (true) {
            try {
                System.out.print("Введите рост :");
                height = in.nextDouble();
                if (height > 0) {
                    break;
                }
                System.out.print("Рост введён некоректно. Попробуйте ещё раз\n");
            } catch (InputMismatchException e){
                System.out.println("Координаты введены некорректно. Попробуйте ещё раз");
                in = new Scanner(System.in);
                continue;
            }

        }

        double weight;
        while (true) {
            try {
                System.out.print("Введите вес: ");
                weight = in.nextDouble();
                if (weight > 0) {
                    break;
                }
                System.out.print("Вес введён некоректно. Попробуйте ещё раз\n");
            } catch (InputMismatchException e){
                System.out.println("Координаты введены некорректно. Попробуйте ещё раз");
                in = new Scanner(System.in);
                continue;
            }

        }

        String passportID;
        while (true) {
            System.out.println("Введите индетификатор паспорта : ");
            passportID = in.next();
            if (!(passportID.isEmpty()) && (passportID.length() >= 4) && (passportID.length() <= 46)) {
                break;
            }
            System.out.print("Индетификатор введён некоректно. Попробуйте ещё раз\n");

        }

        String color;
        Color eyeColor = null;
        while (true) {
            System.out.println("Выберите цвет глаз: зелёный, синий, белый, коричневый");
            color = in.next();
            switch (color) {
                case ("зелёный"):
                    eyeColor = Color.GREEN;
                    break;
                case ("синий"):
                    eyeColor = Color.BLUE;
                    break;
                case ("белый"):
                    eyeColor = Color.WHITE;
                    break;
                case ("коричневый"):
                    eyeColor = Color.BROWN;
                    break;

            }
            if (eyeColor != null) {
                break;
            }
            System.out.print("Выберите только один цвет из предложенных\n");
        }

        Location location = null;
        int locationX;
        Double locationY;
        Long locationZ;
        String locationName;
        while (true) {
            try {
                System.out.print("Введите местонахождение(координаты) (сначала координату х, энтер, координату у, энтер, координату z, энтер, название локации):");
                locationX = in.nextInt();
                locationY = in.nextDouble();
                locationZ = in.nextLong();
                locationName = in.next();
                if ((locationY != null && locationZ != null && locationName != null)) {
                    location = new Location(locationX, locationY, locationZ, locationName);
                    break;
                }

                if (location != null) {
                    break;
                }
                System.out.print("Локация введена некоректно. Попробуйте ещё раз\n");
            } catch (InputMismatchException e){
                System.out.println("Координаты введены некорректно. Попробуйте ещё раз");
                in = new Scanner(System.in);
                continue;
            }

        }
        Person person = new Person(name, coordinates, height, weight, passportID, eyeColor, location);
        IDs.add(person.getId());
        return person;
    }

    /**
     * функция для выполнения программы в режиме чтения файла
     * @param data список данных
     * @return новый экземпляр класса Person
     **/
    public static Person fromFile(ArrayList<String> data){
        String name = data.get(0);
        while (true) {
            if (name != null & !name.isEmpty()) {
                break;
            }
            System.out.print("Имя введено некоректно. Попробуйте ещё раз\n");
        }

        Coordinates coordinates = null;
        while (true) {
            try {
                if ((Double.parseDouble(data.get(1)) <= 579)  && (Integer.parseInt(data.get(2)) <= 911)) {
                    coordinates = new Coordinates(Double.parseDouble(data.get(1)), Integer.parseInt(data.get(2)));
                    break;
                }

                if (coordinates != null) {
                    break;
                }
                System.out.print("Координаты введены некоректно. Попробуйте ещё раз\n");
            } catch (InputMismatchException e){
                System.out.println("Координаты введены некорректно. Попробуйте ещё раз");

            }

        }
        Double height;
        while (true) {
            try {
                height = Double.parseDouble(data.get(3));
                if (height > 0) {
                    break;
                }
                System.out.print("Рост введён некоректно. Попробуйте ещё раз\n");
            } catch (InputMismatchException e){
                System.out.println("Координаты введены некорректно. Попробуйте ещё раз");
            }

        }
        Double weight;
        while (true) {
            try {
                weight = Double.parseDouble(data.get(4));
                if (weight > 0) {
                    break;
                }
                System.out.print("Вес введён некоректно. Попробуйте ещё раз\n");
            } catch (InputMismatchException e){
                System.out.println("Координаты введены некорректно. Попробуйте ещё раз");
            }

        }

        String passportID;
        while (true) {
            passportID = data.get(5);
            if (!(passportID.isEmpty()) && (passportID.length() >= 4) && (passportID.length() <= 46)) {
                break;
            }
            System.out.print("Индетификатор введён некоректно. Попробуйте ещё раз\n");
        }

        String color;
        Color eyeColor = null;
        while (true) {

            color = data.get(6);
            switch (color) {
                case ("зелёный"):
                    eyeColor = Color.GREEN;
                    break;
                case ("синий"):
                    eyeColor = Color.BLUE;
                    break;
                case ("белый"):
                    eyeColor = Color.WHITE;
                    break;
                case ("коричневый"):
                    eyeColor = Color.BROWN;
                    break;

            }
            if (eyeColor != null) {
                break;
            }
            System.out.print("Выберите только один цвет из предложенных\n");
        }

        Location location = null;
        Double locationY;
        Long locationZ;
        String locationName;
        while (true) {
            try {
                locationY = Double.parseDouble(data.get(8));
                locationZ = Long.parseLong(data.get(9));
                locationName = data.get(10);
                if ((locationY != null && locationZ != null && locationName != null)) {
                    location = new Location(Integer.parseInt(data.get(7)), Double.parseDouble(data.get(8)), Long.parseLong(data.get(9)), data.get(10));
                    break;
                }

                if (location != null) {
                    break;
                }
                System.out.print("Локация введена некоректно. Попробуйте ещё раз\n");
            } catch (InputMismatchException e){
                System.out.println("Координаты введены некорректно. Попробуйте ещё раз");
            }

        }
        Person person = new Person(name, coordinates, height, weight, passportID, eyeColor, location);
        IDs.add(person.getId());
        return person;
    }

    /**
     * создает новый элемент коллекции
     *
     * @return новый элемент коллекции
     */
    public static Person createNewPerson() {
        if (mode == true) {
            return fromScanner();

        } else {
            return fromFile(data);
        }
    }
}