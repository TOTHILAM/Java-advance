import entity.*;
import util.HibernateUtil;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buidSessionFactory()) {
            factory.inTransaction(session -> {
                var circle = new Circle();
                circle.setId(1);
                circle.setColor("red");
                circle.setRadius(5);
                session.persist(circle);

                var rectangle = new Rectangle();
                rectangle.setId(2);
                rectangle.setColor("blue");
                rectangle.setWidth(3);
                rectangle.setHeight(4);
                session.persist(rectangle);
            });

            factory.inSession(session -> {
                var hql = "FROM Circle";
                var shapes = session
                        .createSelectionQuery(hql, Circle.class)
                        .getResultList();
                for (Shape shape : shapes) {
                    System.out.println("shape = " + shape.getColor());
                    System.out.println("shape = " + shape.getId());
                }
            });
        }
    }
}
