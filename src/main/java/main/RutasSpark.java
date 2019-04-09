package main;


import entidadesLogica.ServiciosEncuestas;
import entidadesLogica.ServiciosNivelEducativo;
import entidadesLogica.ServiciosSectores;
import freemarker.template.Configuration;
import logica.Encuesta;
import logica.NivelEducativo;
import logica.Sector;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class RutasSpark {
    public void iniciarSpark() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        cfg.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(cfg);
        List<Encuesta> encuestasLocales = new ArrayList<>();
        List<Sector> listaSectores = ServiciosSectores.getInstancia().listatOrdenados();
        List<NivelEducativo> listaNiveles = ServiciosNivelEducativo.getInstancia().listatOrdenados();
        port(4562);

        get("/", (request, response) -> {
            response.redirect("/offlineApp");
            return "";
        });

        get("/formulario", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("sectores",listaSectores);
            attributes.put("nivelesEducativos",listaNiveles);
            return new ModelAndView(attributes, "formulario.ftl");
        }, freeMarkerEngine);

        post("/registrarEncuesta", (request, response) -> {
            try {
                String nombre = request.queryParams("nombre");
                String sector = request.queryParams("sector");
                String nivel = request.queryParams("nivel");
                String geolocation = request.queryParams("geolocalizacion").replace("Ubicación Actual: (",
                        "").replace(")","");

                double latitud = 0;
                double longitud = 0;

                if(!geolocation.equals("No se pudo obtener su localización...")) {
                    latitud = Double.parseDouble(geolocation.split(",")[0].trim());
                    longitud = Double.parseDouble(geolocation.split(",")[1].trim());
                }

                //Encuesta nuevaEncuesta = new Encuesta(nombre,new Sector(sector), new NivelEducativo(nivel),latitud,longitud);
                Encuesta nuevaEncuesta = new Encuesta(nombre,ServiciosSectores.getInstancia().findBySector(sector),
                        ServiciosNivelEducativo.getInstancia().findByNivel(nivel),latitud,longitud);
                ServiciosEncuestas.getInstancia().crear(nuevaEncuesta);

                System.out.println("    - Nombre: " + nombre + "\n"
                        + " - Sector: " + sector + "\n"
                        + " - Nivel: " + nivel + "\n"
                        + " - Latitud: " + latitud
                        + " - Longitud: " + longitud);

                response.redirect("/formulario");
            } catch (Exception e) {
                System.out.println("Error al registrar la encuesta!" + e.toString());
                response.redirect("/formulario");
            }
            return "";
        });

        get("/listaEncuestasDB", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("encuestas",ServiciosEncuestas.getInstancia().findAll());
            return new ModelAndView(attributes, "tablaOnline.html");
        }, freeMarkerEngine);

        get("/listaEncuestasDB", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("encuestas",ServiciosEncuestas.getInstancia().findAll());
            return new ModelAndView(attributes, "tabla.ftl");
        }, freeMarkerEngine);

        get("mapa/:lat/:lon", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Double latitud = Double.valueOf(request.params("lat"));
            Double longitud = Double.valueOf(request.params("lon"));
            //String weatherJSON = WeatherAPI.getInstancia().getWeatherData(19.22,-70.53);
            System.out.println("Latitud:" +latitud + " Longitud: " + longitud );
            attributes.put("lat", Double.toString(latitud));
            attributes.put("lon", Double.toString(longitud));
            return new ModelAndView(attributes,"mapa.html");
        }, freeMarkerEngine);

        get("/offlineApp", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("sectores",listaSectores);
            attributes.put("nivelesEducativos",listaNiveles);
            return new ModelAndView(attributes, "offline.html");
        }, freeMarkerEngine);

        post("/sincronizarEncuestas",((request, response) -> {
            String encuestasJSON = request.body();
            System.out.println(encuestasJSON);
            ServiciosEncuestas.getInstancia().guardarEncuestas(encuestasJSON);
            System.out.println("Las encuestas han sido almacenadas con éxito!");
            return "";
        }));


    }


}