import entity.Account;
import entity.Department;
import entity.Group;
import entity.GroupAccount;
import util.HibernateUtil;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buidSessionFactory()) {
            factory.inTransaction(session -> {
                var group1 = new Group();
                group1.setName("Hibernate Core");
                session.persist(group1);

                var group2 = new Group();
                group2.setName("Spring FrameWork");
                session.persist(group2);

                var account1 = new Account();
                account1.setName("Long");
                account1.setEmail("long@gmail.com");
                session.persist(account1);

                var account2 = new Account();
                account2.setName("Thao");
                account2.setEmail("thao@gmail.com");
                session.persist(account2);

                var groupAccount1 = new GroupAccount();
                var pk1 = new GroupAccount.PrimaryKey();
                pk1.setGroupId(1);
                pk1.setAccountId(1);
                groupAccount1.setPk(pk1);
                session.persist(groupAccount1);

                var groupAccount2 = new GroupAccount();
                var pk2 = new GroupAccount.PrimaryKey();
                pk2.setGroupId(1);
                pk2.setAccountId(2);
                groupAccount2.setPk(pk2);
                session.persist(groupAccount2);
            });

            factory.inSession(session -> {
                var hql = "FROM Group";
                var groups = session
                        .createSelectionQuery(hql, Group.class)
                        .getResultList();
                for (var group : groups) {
                    System.out.println("👉 group = " + group.getName());
                    var groupAccounts = group.getGroupAccounts();
                    for (var groupAccount : groupAccounts) {
                        System.out.println("👉 account = " + groupAccount.getAccount().getName());
                        System.out.println("👉 joined at = " + groupAccount.getJoinedAt());
                    }
                }
            });
        }
    }
}
