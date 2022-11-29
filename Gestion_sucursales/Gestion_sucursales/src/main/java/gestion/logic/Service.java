package gestion.logic;
import gestion.data.Data;
import gestion.data.XmlPersister;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import gestion.data.EmpleadoDao;
import gestion.data.SucursalDao;
public class Service {
    private static Service theInstance;
    private Data data;
    private SucursalDao sucursalDao;
    private EmpleadoDao empleadoDao;

    public static Service instance(){
        if (theInstance == null){
            theInstance = new Service();
        }
        return theInstance;
    }
    private Service(){
        sucursalDao= new SucursalDao();
        empleadoDao=new EmpleadoDao();
        data =new Data();
    }

    public List<Empleado> empleadosSearch(String filtro) throws Exception{
        return empleadoDao.findByNombre(filtro);
    }
    public Empleado empleadoGet(String cedula)throws Exception{
        return empleadoDao.read(cedula);
    }
    public void empleadoAdd(Empleado empleado)throws Exception{
        empleadoDao.create(empleado);
    }
    public void empleadoDelete(Empleado empleado)throws Exception{
        empleadoDao.delete(empleado);
    }
    public void empleadoUpdate(Empleado empleado)throws Exception{
        empleadoDao.update(empleado);
    }
    public List<Sucursal> sucursalesSearch(String filtro) throws Exception {

        return sucursalDao.findByReferencia(filtro);
    }
    public Sucursal sucursalGet(String filtro) throws Exception{
        return sucursalDao.read(filtro);
    }
    public void sucursalAdd(Sucursal sucursal) throws Exception{
        sucursalDao.create(sucursal);
    }
    public void sucursalDelete(Sucursal sucursal)throws Exception{
        sucursalDao.delete(sucursal);
    }
    public void sucursalUpdate(Sucursal sucursal) throws Exception{
        sucursalDao.update(sucursal);
    }
    public List<Sucursal> retornaSucursales() throws Exception {return sucursalesSearch("");}

    /*private Service(){

    try{
        data= XmlPersister.instance().load();
    }
    catch(Exception e){
        data =  new Data();
    }
}*/
    /*public List<Empleado> empleadosSearch(String filtro){
        return data.getEmpleados().stream()
                .filter(e->e.getNombre().contains(filtro))
                .sorted(Comparator.comparing(e -> e.getCedula()))
                .collect(Collectors.toList());
    }*/
    /*
    public Empleado empleadoGet(String cedula) throws Exception{
        Empleado result = data.getEmpleados().stream().filter(e->e.getCedula().equals(cedula)).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Empleado no existe");
    }*/
    /*

    public void empleadoAdd(Empleado empleado) throws Exception{
        Empleado result = data.getEmpleados().stream().filter(e->e.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
        if (result==null) data.getEmpleados().add(empleado);
        else throw new Exception("Empleado ya existe");
    }*/
    /*
    public void empleadoDelete(Empleado empleado)throws Exception{
        Empleado result=data.getEmpleados().stream().filter(e->e.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
        if(result!=null)data.getEmpleados().remove(empleado);
        else throw new Exception ("Empleado no existe");
    }*/
    /*

    public void empleadoUpdate(Empleado empleado) throws Exception{
        Empleado result;
        try{
            result = this.empleadoGet(empleado.getCedula());
            data.getEmpleados().remove(result);
            data.getEmpleados().add(empleado);
        }catch (Exception e) {
            throw new Exception("Empleado no existe");
        }
    }*/
    /*
    public List<Sucursal> sucursalesSearch(String filtro){
        return data.getSucursales().stream()
                .filter(e->e.getReferencia().contains(filtro))
                .sorted(Comparator.comparing(e -> e.getReferencia()))
                .collect(Collectors.toList());
    }*/
    /*

    public Sucursal sucursalGet(String codigo) throws Exception{
        Sucursal result = data.getSucursales().stream().filter(e->e.getCodigo().equals(codigo)).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Sucursal no existe");
    }*/
    /*

    public void sucursalAdd(Sucursal sucursal) throws Exception{
        Sucursal result = data.getSucursales().stream().filter(e->e.getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
        if (result==null) data.getSucursales().add(sucursal);
        else throw new Exception("Sucursal ya existe");
    }*/
    /*
    public void sucursalDelete(Sucursal sucursal)throws Exception{
        Sucursal result=data.getSucursales().stream().filter(e->e.getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
        if(result!=null)data.getSucursales().remove(sucursal);
        else throw new Exception ("Sucursal no existe");
    }*/
   /* public void sucursalUpdate(Sucursal sucursal) throws Exception{
        Sucursal result;
        try{
            result = this.sucursalGet(sucursal.getCodigo());
            data.getSucursales().remove(result);
            data.getSucursales().add(sucursal);
        }catch (Exception e) {
            throw new Exception("Sucursal no existe");
        }
    }*/
        /*public List<Sucursal> retornaSucursales(){
        return data.getSucursales();
    }*/
    /*public Sucursal filtraSucursalReferencia(String referencia) {

        for(Sucursal sucursal: data.getSucursales()){
            System.out.print(sucursal.getReferencia());
            if (sucursal.getReferencia().equals(referencia)){
                return sucursal;
            }
        };
        return null;
    }*/
    public void store(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
