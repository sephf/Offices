package gestion.data;

import gestion.logic.Empleado;
import gestion.logic.Sucursal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao {
    Database db;

    public EmpleadoDao() {
        db = Database.instance();
    }

    public void create(Empleado e) throws Exception {
        String sql = "insert into " +
                "Empleado " +
                "(cedula, nombre, telefono, salario, sucursal) " +
                "values(?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCedula());
        stm.setString(2, e.getNombre());
        stm.setString(3, e.getTelefono());
        stm.setDouble(4, e.getSalario());
        stm.setString(5, e.getSucursal().getCodigo());
        db.executeUpdate(stm);
    }

    public Empleado read(String cedula) throws Exception {
        SucursalDao sucursalDao=new SucursalDao();
        String sql= "select * "+
                "from "+
                "Empleado e "+
                " inner join Sucursal s on e.sucursal=s.codigo "+
                "where e.cedula = ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, cedula);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            Empleado empledo;
            empledo = from(rs, "e");
            empledo.setSucursal(sucursalDao.from(rs, "s"));
            return empledo;
        } else {
            throw new Exception("EMPLEADO NO EXISTE");
        }
    }

    public void update(Empleado e) throws Exception {
        String sql = "update " +
                "Empleado " +
                "set nombre=?, telefono=?, salario=?, sucursal=?  " +
                "where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getNombre());
        stm.setString(2, e.getTelefono());
        stm.setDouble(3, e.getSalario());
        stm.setString(4, e.getSucursal().getCodigo());
        stm.setString(5,e.getCedula());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("SUCURSAL NO EXISTE");
        }

    }

    public void delete(Empleado e) throws Exception {
        String sql = "delete " +
                "from Empleado " +
                "where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCedula());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("EMPLEADO NO EXISTE");
        }
    }

    public List<Empleado> findByNombre(String nombre) throws Exception {
        SucursalDao sucursalDao=new SucursalDao();
        List<Empleado> resultado= new ArrayList<>();
        String sql= "select * "+
                "from "+
                "Empleado e "+
                " inner join Sucursal s on e.sucursal=s.codigo "+
                "where e.nombre like ?";
        PreparedStatement stm =db.prepareStatement(sql);
        stm.setString(1,"%"+ nombre +"%");
        ResultSet rs= db.executeQuery(stm);
        Empleado empledo;
        while (rs.next()) {
            empledo = from(rs, "e");
            empledo.setSucursal(sucursalDao.from(rs, "s"));
            resultado.add(empledo);
        }
        return resultado;
    }

    public Empleado from(ResultSet rs, String alias) throws Exception {
        Empleado e = new Empleado();
        e.setCedula(rs.getString(alias+".cedula"));
        e.setNombre(rs.getString(alias+".nombre"));
        e.setTelefono(rs.getString(alias+".telefono"));
        e.setSalario(rs.getFloat(alias+".salario"));
        return e;
    }

}