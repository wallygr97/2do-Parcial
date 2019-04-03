package logica;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Encuesta implements Serializable {
    @Id
    @GeneratedValue
    private long idEncuesta;

    @NotNull
    private String nombre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idSector")
    private Sector sector;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idNivel")
    private NivelEducativo nivelEducacion;

    private double latitud;

    private double longitud;

    public Encuesta() {
    }

    public Encuesta(String nombre, Sector sector, NivelEducativo nivelEducacion, double latitud, double longitud) {
        this.nombre = nombre;
        this.sector = sector;
        this.nivelEducacion = nivelEducacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public long getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(long idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public NivelEducativo getNivelEducacion() {
        return nivelEducacion;
    }

    public void setNivelEducacion(NivelEducativo nivelEducacion) {
        this.nivelEducacion = nivelEducacion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String latitudString(){
        return Double.toString(latitud);
    }
    public String longitudString(){
        return Double.toString(longitud);
    }

    public String latitudCorta(){
        return Double.toString(latitud).substring(0,6);
    }
    public String longitudCorta(){
        return Double.toString(longitud).substring(0,6);
    }
}

