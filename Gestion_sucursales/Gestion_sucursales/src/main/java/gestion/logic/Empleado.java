package gestion.logic;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Empleado {
    private String cedula;
    private String nombre;
    private String telefono;
    private double salario;
    @XmlIDREF
    private Sucursal sucursal;
    public Empleado(String cedula,String nombre,String telefono,double salario, Sucursal sucursal){
        this.cedula=cedula;
        this.nombre=nombre;
        this.telefono=telefono;
        this.salario=salario;
        this.sucursal=sucursal;
    }
    public Empleado(){this("","","",0.0,new Sucursal());}
    public String getCedula(){return cedula;}
    public String getNombre(){return nombre;}
    public String getTelefono(){return telefono;}
    public double getSalario(){return salario;}
    public Sucursal getSucursal(){return sucursal;}
    public double porctZonaje(){
        return (sucursal!=null)? sucursal.getZonaje(): 0.0;
    }
    public double getSalarioTotal(){return salario+(salario*porctZonaje())/100;}

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
