package entidadesLogica;

import logica.NivelEducativo;
import servicios.MetodosDB;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServiciosNivelEducativo extends MetodosDB<NivelEducativo> {
    private static ServiciosNivelEducativo instancia;

    private ServiciosNivelEducativo(){super(NivelEducativo.class);}

    public static ServiciosNivelEducativo getInstancia(){
        if(instancia==null){
            instancia = new ServiciosNivelEducativo();
        }
        return instancia;
    }

    public boolean crearNiveles(String stringNiveles){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select n from NivelEducativo n");
        if(query.getResultList().size()==0) {
            for (String s : stringNiveles.split(","))
                ServiciosNivelEducativo.getInstancia().crear(new NivelEducativo(s.trim()));
            return true;
        }
        return false;
    }

    public List<NivelEducativo> listatOrdenados(){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select n from NivelEducativo n order by n.nivel");
        List<NivelEducativo> resultado = query.getResultList();
        return resultado;
    }

    public NivelEducativo findByNivel(String nivel){
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("select n from NivelEducativo n where n.nivel = :nivel");
            query.setParameter("nivel", nivel);
            return (NivelEducativo) query.getSingleResult();
        }catch (Exception ex){
            return null;
        }
    }
}
