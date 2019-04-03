package servicios;

public class ServicioFormulario  extends GestionDb<Formulario>  {
    private static ServicioFormulario instancia;

    private ServicioFormulario() {
        super(Formulario.class);
    }

    public static ServicioFormulario getInstancia(){
        if(instancia==null){
            instancia = new ServicioFormulario();
        }
        return instancia;
    }
}