package logica;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class NivelEducativo implements Serializable {
    @Id
    @GeneratedValue
    private Long idNivel;

    @Column(unique = true)
    @NotNull
    private String nivel;

    public NivelEducativo() {
    }

    public NivelEducativo(String nivel) {
        this.nivel = nivel;
    }

    public Long getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Long idNivel) {
        this.idNivel = idNivel;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
