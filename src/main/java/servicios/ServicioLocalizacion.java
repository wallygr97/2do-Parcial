package servicios;

import com.maxmind.geoip2.DatabaseReader;

import java.io.File;

public class ServicioLocalizacion {
    private static ServicioLocalizacion instancia;
    public static DatabaseReader locationReader;

    private ServicioLocalizacion() {

        File database = new File("src/main/resources/Localizacion/GeoLite2-City.mmdb");
        try {locationReader = new DatabaseReader.Builder(database).build();}
        catch (Exception e) {System.out.println(e);}
    }

    public static ServicioLocalizacion getInstancia(){
        if(instancia==null){
            instancia = new ServicioLocalizacion();
        }
        return instancia;
    }
}