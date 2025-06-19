import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Crear encuestas por sucursal
        Sucursal centro = new Sucursal("Centro", Arrays.asList(
                new Encuesta("Ana", "El tiempo de espera fue largo", 2),
                new Encuesta("Luis", null, 3),
                new Encuesta("Carlos", "Todo bien", 5)
        ));

        Sucursal norte = new Sucursal("Norte", Arrays.asList(
                new Encuesta("Marta", "La atención fue buena, pero la limpieza puede mejorar", 3),
                new Encuesta("Pedro", null, 2),
                new Encuesta("Julia", "Muy ruidoso", 4)
        ));

        List<Sucursal> sucursales = Arrays.asList(centro, norte);

        // Procesar las encuestas
        sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(encuesta -> encuesta.getCalificacion() <= 3)
                                .map(encuesta ->
                                        encuesta.getComentario()
                                                .map(comentario ->
                                                        "Sucursal " + sucursal.getNombre() +
                                                                ": Seguimiento a paciente con comentario: \"" + comentario + "\""
                                                )
                                )
                                .flatMap(Optional::stream)
                )
                .forEach(System.out::println);
    }
}
