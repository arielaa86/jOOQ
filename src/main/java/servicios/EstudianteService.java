package servicios;

import utiles.Conexion;
import modelo.Estudiante;

import java.sql.SQLException;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DSL.field;
import org.jooq.impl.SQLDataType;

public class EstudianteService {

    private static final Table ESTUDIANTE = table(name("estudiante"));

    private static final Field ID = field(name("id"));
    private static final Field NOMBRE = field(name("nombre"));
    private static final Field EDAD = field(name("edad"));

    public static int adicionar(Estudiante estudiante) throws SQLException {

        Conexion conexion = new Conexion();
        final DSLContext ctx = conexion.iniciar();

        Record registro = ctx.insertInto(ESTUDIANTE, NOMBRE, EDAD)
                .values(estudiante.getNombre(), estudiante.getEdad())
                .returning(ID)
                .fetchOne();

        conexion.cerrar();

        return registro.into(Estudiante.class).getId();

    }

    public static int actualizar(Estudiante estudiante) throws SQLException {

        Conexion conexion = new Conexion();
        final DSLContext ctx = conexion.iniciar();

        Record registro = ctx.update(ESTUDIANTE)
                .set(NOMBRE, estudiante.getNombre())
                .set(EDAD, estudiante.getEdad())
                .where(ID.eq(estudiante.getId()))
                .returning(ID)
                .fetchOne();

        conexion.cerrar();

        return registro.into(Estudiante.class).getId();

    }

    public static Estudiante obtener(int id) throws SQLException {

        Conexion conexion = new Conexion();
        final DSLContext ctx = conexion.iniciar();

        Estudiante estudiante = (Estudiante) ctx.selectFrom(ESTUDIANTE)
                .where(ID.eq(id))
                .fetchOneInto(Estudiante.class);

        conexion.cerrar();

        return estudiante;

    }

    public static void eliminar(Estudiante estudiante) throws SQLException {

        Conexion conexion = new Conexion();
        final DSLContext ctx = conexion.iniciar();

        ctx.deleteFrom(ESTUDIANTE)
                .where(ID.eq(estudiante.getId()))
                .execute();

        conexion.cerrar();

    }

    public static List<Estudiante> getListaEstudiantes() throws SQLException {

        Conexion conexion = new Conexion();
        final DSLContext ctx = conexion.iniciar();

        List<Estudiante> listaEstudiantes = ctx.selectFrom(ESTUDIANTE).fetchInto(Estudiante.class);

        conexion.cerrar();

        return listaEstudiantes;

    }

    public static void crearTabla() throws SQLException {

        Conexion conexion = new Conexion();
        final DSLContext ctx = conexion.iniciar();

        try {

            ctx.createTable(ESTUDIANTE)
                    .column(ID, SQLDataType.BIGINT.identity(true))
                    .column(NOMBRE, SQLDataType.VARCHAR.length(50))
                    .column(EDAD, SQLDataType.INTEGER)
                    .constraints(
                            DSL.constraint("pk_estudiante").primaryKey(ID),
                            DSL.constraint().unique(NOMBRE)
                    ).execute();

            
            System.out.println("La tabla se ha creado satisfactoriamente");
            
            
        } catch (DataAccessException ex) {

            System.out.println("La tabla estudiante ya existe");

        }

        conexion.cerrar();

    }

    public static void eliminarTabla() throws SQLException {

        Conexion conexion = new Conexion();
        final DSLContext ctx = conexion.iniciar();

        try {

            ctx.dropTable(ESTUDIANTE).execute();

        } catch (DataAccessException ex) {

            System.out.println("La tabla no existe");

        }

        conexion.cerrar();

    }

}
