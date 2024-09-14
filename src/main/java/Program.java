import entity.*;
import util.HibernateUtil;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buidSessionFactory()) {
            factory.inTransaction(session -> {
                var circle = new Circle();
                circle.setColor("red");
                circle.setRadius(5);
                session.persist(circle);

                var rectangle = new Rectangle();
                rectangle.setColor("blue");
                rectangle.setWidth(3);
                rectangle.setHeight(4);
                session.persist(rectangle);
            });

            factory.inSession(session -> {
                var hql = "FROM Shape";
                var shapes = session
                        .createSelectionQuery(hql, Shape.class)
                        .getResultList();
                for (Shape shape : shapes) {
                    System.out.println("shape = " + shape.getColor());
                }
            });
        }
    }
}
