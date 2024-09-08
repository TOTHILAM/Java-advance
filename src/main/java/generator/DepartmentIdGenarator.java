package generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class DepartmentIdGenarator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        String hql = "SELECT COUNT(*) FROM Department";
        long count = session
                .createSelectionQuery(hql, Long.class)
                .uniqueResult();
        return String.format("VA%06d", count + 1);
    }
}
