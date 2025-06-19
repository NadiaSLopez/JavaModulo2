import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
                new Pedido("Ana", "domicilio", "555-1234"),
                new Pedido("Luis", "local", "555-0000"),
                new Pedido("Carla", "domicilio", null),
                new Pedido("Miguel", "domicilio", "555-5678")
        );

        pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))
                .map(Pedido::getTelefono)                  // Stream<Optional<String>>
                .filter(Optional::isPresent)               // Solo si hay número
                .map(Optional::get)                        // Obtener el número
                .map(tel -> "📞 Confirmación enviada al número: " + tel)
                .forEach(System.out::println);
    }
}
