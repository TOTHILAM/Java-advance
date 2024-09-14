import dto.DepartmentDto;
import entity.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import util.HibernateUtil;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buidSessionFactory()) {
            factory.inTransaction(session -> {
                var sql = "INSERT INTO department(id, name, type, create_at, update_at) VALUES ('VA000001', :name, :type, NOW(), NOW())";
                var result = session.createNativeMutationQuery(sql)
                        .setParameter("name", "Kỹ thuật")
                        .setParameter("type", 'D')
                        .executeUpdate();
                System.out.println("１　Thêm thành công: " + result);
            });

            factory.inSession(session -> {
                var sql = "SELECT * FROM department";
                var departments = session
                        .createNativeQuery(sql, Department.class)
                        .getResultList();
                for (Department department : departments) {
                    System.out.println("2 department = " + department.getId());
                    System.out.println("2 department = " + department.getName());
                }
            });

            factory.inSession(session -> {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Department> query = builder.createQuery(Department.class);
                Root<Department> root = query.from(Department.class);
                query.select(root).where(builder.equal(root.get("name"), "Kỹ thuật"));
                var departments = session.createSelectionQuery(query)
                        .getResultList();
                for (Department department : departments) {
                    System.out.println("3 department = " + department.getId());
                    System.out.println("3 department = " + department.getName());
                }
            });
        }
    }
}
