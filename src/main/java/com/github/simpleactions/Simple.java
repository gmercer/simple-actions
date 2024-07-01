package com.github.simpleactions;


import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Simple {

    private static final Logger logger = LogManager.getLogger(Simple.class);

    public static DataSource getDataSource() {
        String connectURI = "jdbc:postgresql://localhost:5432/postgres";
        String driver = "org.postgresql.Driver";

        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(driver);
        bds.setUrl(connectURI);
        bds.setUsername("runner"); // see ci actions file

        return bds;
    }

    public static void migrate() {
        try {
            DataSource ds = getDataSource();
            Connection connection = ds.getConnection(); //your openConnection logic here

            Database database = null;
            database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new liquibase.Liquibase("db-migration/changelog.xml", new ClassLoaderResourceAccessor(), database);

            liquibase.update();

//      THe new way - but the class loader is trickier - liquibase.update is going to be un-deprecated in a new release
//            new CommandScope(UpdateCommandStep.COMMAND_NAME)
//                    .addArgumentValue(DbUrlConnectionArgumentsCommandStep.DATABASE_ARG, database)
//                    .addArgumentValue(UpdateCommandStep.CONTEXTS_ARG, new ClassLoaderResourceAccessor())
//                    .addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, "db-migration/changelog.xml")
//                    .execute();

            //        Flyway flyway = Flyway.configure().dataSource(getDataSource()).load();
            //        flyway.migrate();
        } catch (SQLException | LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        logger.debug("Hello World! from {}", new Simple().getName());
    }

    public String getName() {
        return Simple.class.getSimpleName();
    }

    public String[] getUsers() {
        String usersNameQuery = "SELECT name FROM user";
        logger.debug("Setting up data source.");

        // This just demonstrates that we can drop down to only java.sql namespace
        // once the DataSource is setup
        DataSource dataSource = getDataSource();
        logger.debug("Done.");

        //
        // Now, we can use JDBC DataSource as we normally would.
        //
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        ArrayList<String> results = new ArrayList<>();

        try {
            logger.debug("Creating connection.");
            conn = dataSource.getConnection();
            logger.debug("Creating statement.");
            stmt = conn.createStatement();
            logger.debug("Executing statement.");
            rset = stmt.executeQuery(usersNameQuery);
            logger.debug("Results:");
            int numcols = rset.getMetaData().getColumnCount();
            while (rset.next()) {
                for (int i = 1; i <= numcols; i++) {
                    System.out.print("\t" + rset.getString(i));
                    results.add(rset.getString(i));
                }
                logger.debug("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null)
                    rset.close();
            } catch (Exception e) {
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }

        return results.toArray(new String[0]);
    }
}
