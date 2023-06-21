public class Aula {
    String horaLimpieza;
    public final int CAPACIDAD = 20;
    private String id;
    public Reservas reservas;
    public Aula(int numId){
        this.id = "A"+numId;
        reservas = new Reservas();

    }
    String getId(){
        return this.id;
    }
    void horaLimpieza(String date){
        reservas.reservar(date);
        horaLimpieza = date;
    }
}