import controllers.RestController;
import model.Book;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Main
{
    private static final String ALL = "all";
    private static final String EDIT = "edit";
    private static final String REMOVE = "remove";
    private static final String ADD = "add";
    private static final String QUIT = "q";


    private static String arrayToString(String[] str)
    {
        StringBuilder builder = new StringBuilder("\"");
        for (int i = 0; i < str.length - 1; ++i)
            builder.append(str[i] + " ");
        builder.append(str[str.length - 1]);
        builder.append("\"");
        return builder.toString();
    }

    public static void main(String[] args) throws Exception
    {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:context.xml");

        RestController controller = (RestController) context.getBean("restController");

        String[] str;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Books library...");
        while (scanner.hasNextLine())
        {
            str = scanner.nextLine().split(" ");
            if (str.length == 0)
                continue;

            str[0] = str[0].toLowerCase();

            if (str.length == 1 && str[0].equals(QUIT))
            {
                System.out.println("Closing...");
                break;
            }
            else if (str.length == 2 && str[0].equals(ALL) && str[1].toLowerCase().equals("books"))
            {
                List<Book> list = controller.all();
                if (list == null)
                    System.out.println("unfortunately, library is empty");
                else
                    for (Book b : list)
                    {
                        System.out.println(b);
                    }
            }
            else if (str.length >= 2)
            {
                String command = str[0];
                String author = "";
                String name = "";
                if (command.equals(EDIT) || command.equals(REMOVE))
                {
                    for (int i = 1; i < str.length; ++i)
                        name += str[i] + " ";
                    name = name.trim();
                    if (command.equals(EDIT))
                        controller.edit(name);
                    else
                        controller.remove(name);
                }
                else if (command.equals(ADD))
                {
                    author = str[1];
                    name = "";
                    for (int i = 2; i < str.length; ++i)
                        name += str[i] + " ";
                    name = name.trim();
                    if (name.startsWith("\"") && name.endsWith("\"") && name.length() > 3)
                    {
                        name = name.substring(1, name.length() - 1);
                        controller.add(author, name);
                    }
                    else
                        System.out.println("Illegal attribute");
                }
                else
                    System.out.println("Command " + arrayToString(str) + " does not exists");
            }
            else
                System.out.println("Command " + arrayToString(str) + " does not exists");
        }

        context.close();
    }
}
