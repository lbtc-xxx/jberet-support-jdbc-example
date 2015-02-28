package org.nailedtothex.example;

import javax.annotation.Resource;
import javax.batch.api.AbstractBatchlet;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Named
@Dependent
public class MyBatchlet extends AbstractBatchlet {
    @Resource(lookup = "java:jboss/datasources/MyDS")
    DataSource ds;

    @Override
    public String process() throws Exception {
        try (Connection cn = ds.getConnection()) {
            try (Statement st = cn.createStatement()) {
                try {
                    st.executeUpdate("drop table if exists src");
                } catch (Exception e) {
                    // nop
                }
                try {
                    st.executeUpdate("drop table if exists dest");
                } catch (Exception e) {
                    // nop
                }
                st.executeUpdate("create table src (data int)");
                st.executeUpdate("create table dest (data int primary key)");
            }

            cn.setAutoCommit(false);
            try (PreparedStatement ps = cn.prepareStatement("insert into src (data) values (?)")) {
                for (int i = 1; i <= 20; i++) {
                    ps.setInt(1, i);
                    ps.executeUpdate();
                }

                // to make exception happen in ItemWriter due to duplicate key
                ps.setInt(1, 15);
                ps.executeUpdate();

                cn.commit();
            }
        }
        return null;
    }
}
