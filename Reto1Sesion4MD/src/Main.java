public class Main {
    public static void main(String[] args) throws InterruptedException {
        MovilidadApp app = new MovilidadApp();
        app.procesarSolicitud();

        // Mantener vivo el hilo principal lo suficiente para que las tareas asíncronas terminen
        Thread.sleep(5000);
    }
}
