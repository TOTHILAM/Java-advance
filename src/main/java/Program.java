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
                groupAccount1.setAccount(account1);
                groupAccount1.setGroup(group1);
                session.persist(groupAccount1);

                var groupAccount2 = new GroupAccount();
                groupAccount2.setAccount(account2);
                groupAccount2.setGroup(group1);
                session.persist(groupAccount2);

                var groupAccount3 = new GroupAccount();
                groupAccount3.setAccount(account1);
                groupAccount3.setGroup(group2);
                session.persist(groupAccount3);

                var groupAccount4 = new GroupAccount();
                groupAccount4.setAccount(account2);
                groupAccount4.setGroup(group2);
                session.persist(groupAccount4);
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
