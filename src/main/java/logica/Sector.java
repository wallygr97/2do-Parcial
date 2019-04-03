package logica;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Sector implements Serializable {
    @Id
    @GeneratedValue
    private Long idSector;

    @Column(unique = true)
    @NotNull
    private String sector;

    public Sector() {
    }

    public Sector(String sector) {
        this.sector = sector;
    }

    public Long getIdNivel() {
        return idSector;
    }

    public void setIdNivel(Long idNivel) {
        this.idSector = idNivel;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
