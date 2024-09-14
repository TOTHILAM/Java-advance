import dto.DepartmentDto;
import entity.*;
import util.HibernateUtil;

import java.util.Arrays;

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
                var hql = "FROM Department";
                var departments = session
                        .createSelectionQuery(hql, Department.class)
                        .getResultList();
                for (Department department : departments) {
                    System.out.println("✨department = " + department.getId());
                    System.out.println("✨department = " + department.getName());
                }
            });
            factory.inSession(session -> {
//                var hql = "FROM Department WHERE id = ?1";
                var hql = "FROM Department WHERE id = :id";
                var department = session
                        .createSelectionQuery(hql, Department.class)
//                        .setParameter(1, "VA000001")
                        .setParameter("id", "VA000001")
                        .uniqueResult();
                System.out.println("2✨department = " + department.getId());
                System.out.println("2✨department = " + department.getName());
            });

            factory.inSession(session -> {
                var hql = "SELECT COUNT(*) FROM Department";
                var count = session
                        .createSelectionQuery(hql, Long.class)
                        .uniqueResult();
                System.out.println("3: count = " + count);
            });
//            dto = data transfer object đối tượng trung chuyển dữ liệu
            factory.inSession(session -> {
                var hql = "SELECT new DepartmentDto(name) FROM Department ";
                var departments = session
                        .createSelectionQuery(hql, DepartmentDto.class)
                        .getResultList();
                for (DepartmentDto department : departments) {
                    System.out.println("4 department = " + department.getName());
                }
            });
//phân trang hiển thị mỗi lần 1 trang chứ k hiển thị hết
            factory.inSession(session -> {
                var page = 1;
                var size = 1;
                var hql = "FROM Department";
                var departments = session
                        .createSelectionQuery(hql, Department.class)
                        .setMaxResults(size)
                        .setFirstResult((page - 1) * size)
                        .getResultList();
                for (Department department : departments) {
                    System.out.println("✨department = " + department.getId());
                    System.out.println("✨department = " + department.getName());
                }
            });

            factory.inTransaction(session -> {
                var hql = "DELETE FROM Department WHERE id = :id";
                var result = session
                        .createMutationQuery(hql)
                        .setParameter("id", "VA000001")
                        .executeUpdate();
                System.out.println("Xóa thành công: " + result);
            });
        }
    }
}
