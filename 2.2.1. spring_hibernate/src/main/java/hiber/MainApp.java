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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        Car car1 = new Car("Lada", 606);
        Car car2 = new Car("Honda", 414);
        Car car3 = new Car("Toyota", 355);


        User user5 = new User("User5", "Lastname5", "user5@mail.ru");
        user5.setCar(car1);
        userService.add(user5);

        User user6 = new User("User6", "Lastname6", "user6@mail.ru");
        user6.setCar(car3);
        userService.add(user6);

        User user7 = new User("User7", "Lastname7", "user7@mail.ru");
        user7.setCar(car2);
        userService.add(user7);

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

        System.out.println(userService.getUserByCar("Honda", 414));
        System.out.println(userService.getUserByCar("Honda", 418));

        context.close();
    }
}
