package gestion.logic;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sucursal {
    @XmlID
    private String codigo;
    private String referencia;
    private String direccion;
    private double zonaje;
    private int ubicacionX;
    private int ubicacionY;


    public Sucursal(String codigo,String referencia,String direccion,double zonaje,int ubicacionX,int ubicacionY ){
        this.codigo=codigo;
        this.referencia=referencia;
        this.direccion=direccion;
        this.zonaje=zonaje;
        this.ubicacionX=ubicacionX;
        this.ubicacionY=ubicacionY;
    }
    public Sucursal(){this("","","",0.0,0,0);}
    public String getCodigo()
    {
        return codigo;
    }
    public String getReferencia()
    {
        return referencia;
    }
    public String getDireccion()
    {
        return direccion;
    }
    public double getZonaje()
    {
        return zonaje;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setZonaje(double zonaje) {
        this.zonaje = zonaje;
    }

    public int getUbicacionX() {
        return ubicacionX;
    }

    public int getUbicacionY() {
        return ubicacionY;
    }

    public void setUbicacionX(int ubicacionX) {
        this.ubicacionX = ubicacionX;
    }

    public void setUbicacionY(int ubicacionY) {
        this.ubicacionY = ubicacionY;
    }
}
