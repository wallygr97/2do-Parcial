package entidades;

import logical.Sector;
import servicios.MetodosDB;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServiciosSectores extends MetodosDB<Sector> {
    private static entidades.ServiciosSectores instancia;

    private ServiciosSectores(){super(Sector.class);}

    public static entidades.ServiciosSectores getInstancia(){
        if(instancia==null){
            instancia = new entidades.ServiciosSectores();
        }
        return instancia;
    }

    public boolean crearSectores(String stringSectores){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select s from Sector s");
        if(query.getResultList().size()==0){
            for(String s : stringSectores.split("\n"))
                entidades.ServiciosSectores.getInstancia().crear(new Sector(s.trim()));
            return true;
        }
        return false;
    }

    public List<Sector> listatOrdenados(){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select s from Sector s order by s.sector");
        List<Sector> resultado = query.getResultList();
        return resultado;
    }

    public Sector findBySector(String sector){
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("select s from Sector s where s.sector = :sector");
            query.setParameter("sector", sector);
            return (Sector) query.getSingleResult();
        }catch (Exception ex){
            return null;
        }
    }
}
