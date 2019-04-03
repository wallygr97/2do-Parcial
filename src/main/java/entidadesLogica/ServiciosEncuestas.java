package entidadesLogica;

import com.google.gson.JsonArray;
import logica.Encuesta;
import servicios.JsonTransformer;
import servicios.MetodosDB;

import java.util.ArrayList;
import java.util.List;

public class ServiciosEncuestas extends MetodosDB<Encuesta> {
    private static ServiciosEncuestas instancia;

    private ServiciosEncuestas(){super(Encuesta.class);}

    public static ServiciosEncuestas getInstancia(){
        if(instancia==null){
            instancia = new ServiciosEncuestas();
        }
        return instancia;
    }

    public boolean instanciarDB(){
        entidadesLogica.ServiciosSectores.getInstancia().crearSectores("Altamira\n" +
                "Arenoso\n" +
                "Azua de Compostela\n" +
                "Bajos de Haina\n" +
                "Baní\n" +
                "Bánica\n" +
                "Barahona\n" +
                "Bayaguana\n" +
                "Bisonó\n" +
                "Boca Chica\n" +
                "Bohechío\n" +
                "Bonao\n" +
                "Cabral\n" +
                "Cabrera\n" +
                "Cambita Garabitos\n" +
                "Castañuelas\n" +
                "Castillo\n" +
                "Cayetano Germosén\n" +
                "Cevicos\n" +
                "Comendador\n" +
                "Constanza\n" +
                "Consuelo\n" +
                "Cotuí\n" +
                "Cristóbal\n" +
                "Dajabón\n" +
                "Duvergé\n" +
                "El Cercado\n" +
                "El Factor\n" +
                "El Llano\n" +
                "El Peñón\n" +
                "El Pino\n" +
                "El Seibo\n" +
                "El Valle\n" +
                "Enriquillo\n" +
                "Esperanza\n" +
                "Estebanía\n" +
                "Eugenio María de Hostos\n" +
                "Fantino\n" +
                "Fundación\n" +
                "Galván\n" +
                "Gaspar Hernández\n" +
                "Guananico\n" +
                "Guayabal\n" +
                "Guayacanes\n" +
                "Guaymate\n" +
                "Guayubín\n" +
                "Hato Mayor\n" +
                "Higüey\n" +
                "Hondo Valle\n" +
                "Imbert\n" +
                "Jamao Al Norte\n" +
                "Jánico\n" +
                "Jaquimeyes\n" +
                "Jarabacoa\n" +
                "Jima Abajo\n" +
                "Jimaní\n" +
                "Juan de Herrera\n" +
                "Juan Santiago\n" +
                "La Ciénaga\n" +
                "La Descubierta\n" +
                "Laguna Salada\n" +
                "La Mata\n" +
                "La Romana\n" +
                "Las Charcas\n" +
                "Las Guáranas\n" +
                "Las Matas de Farfán\n" +
                "Las Matas de Santa Cruz\n" +
                "Las Salinas\n" +
                "Las Terrenas\n" +
                "Las Yayas de Viajama\n" +
                "La Vega\n" +
                "Licey al Medio\n" +
                "Loma de Cabrera\n" +
                "Los Alcarrizos\n" +
                "Los Cacaos\n" +
                "Los Hidalgos\n" +
                "Los Llanos\n" +
                "Los Ríos\n" +
                "Luperón\n" +
                "Maimón\n" +
                "Mao\n" +
                "Mella\n" +
                "Miches\n" +
                "Moca\n" +
                "Monción\n" +
                "Monte Cristi\n" +
                "Monte Plata\n" +
                "Nagua\n" +
                "Neiba\n" +
                "Nizao\n" +
                "Oviedo\n" +
                "Padre Las Casas\n" +
                "Paraíso\n" +
                "Partido\n" +
                "Pedernales\n" +
                "Pedro Brand\n" +
                "Pedro Santana\n" +
                "Pepillo Salcedo\n" +
                "Peralta\n" +
                "Peralvillo\n" +
                "Piedra Blanca\n" +
                "Pimentel\n" +
                "Polo\n" +
                "Postrer Río\n" +
                "Pueblo Viejo\n" +
                "Puerto Plata\n" +
                "Puñal\n" +
                "Quisqueya\n" +
                "Ramón Santana\n" +
                "Rancho Arriba\n" +
                "Restauración\n" +
                "Río San Juan\n" +
                "Sabana de la Mar\n" +
                "Sabana Grande de Boyá\n" +
                "Sabana Grande de Palenque\n" +
                "Sabana Iglesia\n" +
                "Sabana Larga\n" +
                "Sabana Yegua\n" +
                "Salcedo\n" +
                "Samaná\n" +
                "San Antonio de Guerra\n" +
                "Sánchez\n" +
                "San Cristóbal\n" +
                "San Francisco de Macorís\n" +
                "San Gregorio de Nigua\n" +
                "San Ignacio de Sabaneta\n" +
                "San Juan\n" +
                "San José de Las Matas\n" +
                "San José de Ocoa\n" +
                "San Pedro de Macorís\n" +
                "San Rafael del Yuma\n" +
                "Santiago de los Caballeros\n" +
                "Santo Domingo Este\n" +
                "Santo Domingo Norte\n" +
                "Santo Domingo Oeste\n" +
                "Sosúa\n" +
                "Tábara Arriba\n" +
                "Tamayo\n" +
                "Tamboril\n" +
                "Tenares\n" +
                "Vallejuelo\n" +
                "Vicente Noble\n" +
                "Villa Altagracia\n" +
                "Villa González\n" +
                "Villa Hermosa\n" +
                "Villa Isabela\n" +
                "Villa Jaragua\n" +
                "Villa Los Almácigos\n" +
                "Villa Montellano\n" +
                "Villa Riva\n" +
                "Villa Tapia\n" +
                "Villa Vásquez\n" +
                "Yaguate\n" +
                "Yamasá\n");

        ServiciosNivelEducativo.getInstancia().crearNiveles("Básico, Medio, Grado Universitario, Postgrado y Doctorado");

        return false;
    }

    public List<String> listaSectores(String listString){
        List<String> sectores = new ArrayList<>();
        for(String s : listString.split("\n"))
            sectores.add(s.trim());
        return sectores;
    }

    public List<String> nivelesEducativos(String listString){
        List<String> niveles = new ArrayList<>();
        for(String s : listString.split(","))
            niveles.add(s.trim());
        return niveles;
    }

    public void guardarEncuestas(String encuestasJSON){
        JsonArray encuestas = JsonTransformer.stringToJsonArray(encuestasJSON);
        System.out.printf("Encuestas JSON: %s" , encuestas.get(0).getAsJsonObject().get("nombre"));

        for (int i=0; i < encuestas.size(); i++){
            //System.out.println("Nombre: " + encuesta.get("nombre").getAsString() + " Sector: "+ encuesta.get("sector").getAsString() + "Nivel: " + encuesta.get("nivel").getAsString() + " Longitud: " + encuesta.get("longitud").getAsString() + "Latitud: " + encuesta.get("latitud").getAsString());
            getInstancia().crear(new Encuesta(encuestas.get(i).getAsJsonObject().get("nombre").getAsString(), entidadesLogica.ServiciosSectores.getInstancia().findBySector(encuestas.get(i).getAsJsonObject().get("sector").getAsString()),
                    ServiciosNivelEducativo.getInstancia().findByNivel(encuestas.get(i).getAsJsonObject().get("nivel").getAsString()),Double.valueOf(encuestas.get(i).getAsJsonObject().get("latitud").getAsString()),Double.valueOf(encuestas.get(i).getAsJsonObject().get("longitud").getAsString())));
        }

        System.out.println("Las encuestas han sido almacenadas correctamente");
    }
}
