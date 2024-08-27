// cf. https://www.baeldung.com/sql-injection

package sql.injection;

import com.biz.org.AccountDTO;
import com.biz.org.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

public class SqlExample {
    public void staticQuery(HttpServletRequest request) throws SQLException {
        Connection c = DB.getConnection();
        // ok:formatted-sql-string
        ResultSet rs = c.createStatement().executeQuery("SELECT * FROM happy_messages");
    }

    public void getAllFields(HttpServletRequest request) throws SQLException {
		String tableName = request.getParameter("tableName");
        Connection c = DB.getConnection();
        // ruleid:formatted-sql-string
        ResultSet rs = c.createStatement().executeQuery("SELECT * FROM " + tableName);
    }

	public void getAllFields(HttpServletRequest request) throws SQLException {
		String tableName = request.getParameter("tableName");
        Connection c = DB.getConnection();
        // ruleid:formatted-sql-string
        ResultSet rs = c.createStatement().execute("SELECT * FROM " + tableName);
    }

	public void getAllFields(HttpServletRequest request) throws SQLException {
		String tableName = request.getParameter("tableName");
        Connection c = DB.getConnection();
		MyStringList list = new MyStringList();
        list.append(tableName);

        String result = list.getResult();
 
        // ruleid:formatted-sql-string
        ResultSet rs = c.createStatement().execute("SELECT * FROM " + result);
    }
}
