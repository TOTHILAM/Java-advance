import entity.Department;
import entity.GroupAccount;
import util.HibernateUtil;

public class Program {
    public static void main(String[] args) {
        try (var factory = HibernateUtil.buidSessionFactory()) {
            factory.inTransaction(session -> {
                var groupAccount = new GroupAccount();
                var pk = new GroupAccount.PrimaryKey();
                pk.setGroupId(1);
                pk.setAccountId(4);
                groupAccount.setPk(pk);
                session.persist(groupAccount);
            });
            factory.inTransaction(session -> {
                var groupAccount = new GroupAccount();
                var pk = new GroupAccount.PrimaryKey();
                pk.setGroupId(7);
                pk.setAccountId(9);
                groupAccount.setPk(pk);
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
