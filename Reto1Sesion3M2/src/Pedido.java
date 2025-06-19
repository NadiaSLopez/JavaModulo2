import java.util.Optional;

public class Pedido {
    private String cliente;
    private String tipoEntrega; // "domicilio" o "local"
    private String telefono;    // puede ser null

    // Constructor
    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    public String getCliente() {
        return cliente;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    // Método que devuelve un Optional
    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefono);
    }
}
