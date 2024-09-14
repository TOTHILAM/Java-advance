package util;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buidSessionFactory() {
        var url = "jdbc:mysql://localhost:3306/lesson_04?createDatabaseIfNotExist=true";
        var configuration = new Configuration()
                .addAnnotatedClass(Group.class)
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(GroupAccount.class)
                .addAnnotatedClass(Shape.class)
                .addAnnotatedClass(Circle.class)
                .addAnnotatedClass(Rectangle.class)
                .setProperty(AvailableSettings.URL, url)
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.GLOBALLY_QUOTED_IDENTIFIERS, "true")
                .setProperty(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, "true")
                .setProperty(AvailableSettings.PASS, "Ritto2024")
                .setProperty(AvailableSettings.HBM2DDL_AUTO, "create")
                .setProperty(AvailableSettings.SHOW_SQL, "true")
                .setProperty(AvailableSettings.HIGHLIGHT_SQL, "true");
        return configuration.buildSessionFactory();
    }
}
