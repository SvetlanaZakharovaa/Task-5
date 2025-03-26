package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("Volvo");
      Car car2 = new Car("Mercedes");
      Car car3 = new Car("Mini Cooper");
      Car car4 = new Car("Toyota");

      User user1 = new User("Bob", "Pefti", "avovo@test.ru" );
      User user2 = new User("Polina", "Lagashkina", "lagalaga@mail.io" );
      User user3 = new User("Juriy", "Tsoy", "tsoy@gmail.com");
      User user4 = new User("Vasya", "Basta", "basta@mail.ru" );

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toString()+"\n");
      }
      System.out.println("Получение пользователя по марке существующей машины");
      System.out.println(userService.getUserByCar("Volvo"));

      System.out.println("Получение пользователя по марке не существующей машины");
      try {
         userService.getUserByCar("Lada");
      } catch (NoResultException e) {
         System.err.println("Пользователь не найден");

      }

      context.close();
   }
}