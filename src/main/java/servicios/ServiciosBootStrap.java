package servicios;

import org.h2.tools.Server;

import java.sql.SQLException;

public class ServiciosBootStrap {

    /**
     *
     * @throws SQLException
     */
    private static ServiciosBootStrap instancia;

    private ServiciosBootStrap(){

    }

    public static ServiciosBootStrap getInstancia(){
        if(instancia == null){
            instancia=new ServiciosBootStrap();
        }
        return instancia;
    }

    public void iniciarBD() {
        try {
            Server.createTcpServer("-tcpPort",
                    "9092",
                    "-tcpAllowOthers",
                    "-tcpDaemon").start();
        }catch (SQLException ex){
            System.out.println("Problema con la base de datos: "+ex.getMessage());
        }
    }

    public void init(){
        iniciarBD();
    }

}