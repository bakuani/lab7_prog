package Commands;

import InputOutput.Instuction;
import InputOutput.Response;
import OtherClasses.NewPerson;
import OtherClasses.Person;
import OtherClasses.Request;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * класс для выполнения команды execute_script
 */
public class ForScript {
    Instuction instuction = new Instuction();
    ArrayList<String> with_person = new ArrayList<>(List.of("add", "add_if_max", "remove_lower", "update"));


    /**
     * функция выполняет чтение файла со скриптом, получает оттуда команды и данные к ним и выполняет
     * @param request запрос от клиента
     * @return ответ от сервера
     */
    public Response forScript(Request request) {
        String result = new String();
        try (BufferedReader reader = new BufferedReader(new FileReader(request.getCommand()[1]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitted_line = line.split(" ");
                if (with_person.contains(splitted_line[0])) {
                    ArrayList<String> data = new ArrayList<>();
                    for (int i = 1; i < 12; i++) {
                        data.add(reader.readLine());
                    }

                    AddIfMax.setMode(false);
                    RemoveLower.setMode(false);
                    NewPerson.setData(data);
                    NewPerson.setMode(false);

                    Person person = NewPerson.createNewPerson();
                    person.setUser(request.getUser());
                    person.setCreator(request.getUser().getUsername());

                    Request newRequest = new Request(splitted_line, person, request.getUser());

                    result += instuction.instruction(newRequest).getMessage();


                } else if (splitted_line[0].equals("execute_script")) {
                    if (Instuction.cycle > 2) {
                        break;
                    } else {
                        Instuction.cycle++;
                        Request newRequest = new Request(splitted_line, null, request.getUser());
                        result += instuction.instruction(newRequest).getMessage();
                    }
                } else {
                    Request newRequest = new Request(splitted_line, null, request.getUser());
                    result += instuction.instruction(newRequest).getMessage();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response(result);
    }
}