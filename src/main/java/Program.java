import entity.Department;
import util.HibernateUtil;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buidSessionFactory()) {
            factory.inTransaction(session -> {
                var department = new Department();
                department.setName("Giám đốc");
                department.setType(Department.Type.PROJECT_MANAGER);
                session.persist(department);
            });
            factory.inTransaction(session -> {
                var department = new Department();
                department.setName("Bảo vệ");
                department.setType(Department.Type.TESTER);
                session.persist(department);
            });

            factory.inSession(session -> {
                var departments = session.createSelectionQuery("FROM Department", Department.class)
                        .getResultList();
                for (Department department : departments) {
                    System.out.println("◉　department = " + department);
                }
            });
        }
    }
}
