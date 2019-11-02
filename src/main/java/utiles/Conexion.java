package utiles;

import org.jooq.*;
import org.jooq.impl.DSL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {


    private String user;
    private String pass;
    private String url;
    Connection conn;
    DSLContext ctx;

    public Conexion(){

        user = "postgres";
        pass = "postgres";
        url = "jdbc:postgresql://localhost:5432/escuela";
        conn = null;
        ctx = null;

    }


    public DSLContext iniciar() throws SQLException {

        conn = DriverManager.getConnection(url , user, pass);
        ctx = DSL.using(conn, SQLDialect.POSTGRES);

        return ctx;
    }

    public void cerrar() throws SQLException {

        ctx.close();
        conn.close();

    }



}
