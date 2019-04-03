package servicios;

public class ServicioUser extends GestionDb<User>  {
    private static ServicioUser instancia;

    private ServicioUser() {
        super(User.class);
    }

    public static ServicioUser getInstancia(){
        if(instancia==null){
            instancia = new ServicioUser();
        }
        return instancia;
    }
}