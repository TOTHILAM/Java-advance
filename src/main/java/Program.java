import entity.Department;
import entity.GroupAccount;
import util.HibernateUtil;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buidSessionFactory()) {
            factory.inTransaction(session -> {
                var groupAccount = new GroupAccount();
                groupAccount.setGroupId(1);
                groupAccount.setAccountId(4);
                session.persist(groupAccount);
            });
            factory.inTransaction(session -> {
                var groupAccount = new GroupAccount();
                groupAccount.setGroupId(7);
                groupAccount.setAccountId(9);
                session.persist(groupAccount);
            });

            factory.inSession(session -> {
                var hql = "FROM GroupAccount";
                var groupAccounts = session
                        .createSelectionQuery(hql, GroupAccount.class)
                        .getResultList();
                for (GroupAccount groupAccount : groupAccounts) {
                    System.out.println("◉ Group Account = " + groupAccount);
                }
            });
        }
    }
}
