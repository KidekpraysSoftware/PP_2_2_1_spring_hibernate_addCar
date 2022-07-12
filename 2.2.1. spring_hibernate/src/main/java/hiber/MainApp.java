package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");

        user1.setCar(new Car("Lada", 606));
        user2.setCar(new Car("Honda", 414));
        user3.setCar(new Car("Toyota", 355));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        List<User> users = userService.listUsers();

        for (User currentUser : users) {
            System.out.println("Id = " + currentUser.getId());
            System.out.println("First Name = " + currentUser.getFirstName());
            System.out.println("Last Name = " + currentUser.getLastName());
            System.out.println("Email = " + currentUser.getEmail());
            if (currentUser.getCar() != null) {
                System.out.println("Car Model = " + currentUser.getCar().getModel());
                System.out.println("Car Series = " + currentUser.getCar().getSeries());
            }
            System.out.println();
        }
        System.out.println(userService.getUserByCar("Lada", 606));
        System.out.println(userService.getUserByCar("Honda", 414));
        System.out.println(userService.getUserByCar("Toyota", 355));


        context.close();
    }
}
