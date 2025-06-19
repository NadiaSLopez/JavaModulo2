public class Main {
    public static void main(String[] args) throws InterruptedException {
        AeropuertoControl control = new AeropuertoControl();
        control.autorizarAterrizaje();

        // Esperar suficiente tiempo para que las tareas asincrónicas terminen
        Thread.sleep(6000); // Puedes ajustar según el número de verificaciones
    }
}
