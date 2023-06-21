import java.util.ArrayList;

public class Reservas {
    private final ArrayList<String> horarios;
    public Reservas(){
        this.horarios = new ArrayList<>();
    }
    public void reservar(String date) throws RuntimeException {
        /**
         * @param date es la hora de la reservación en string, debe tener el formato "HH:mm"
         * @precond date tiene el formato adecuado y está dentro de las horas de atención
         * @precond date no se debe encontrar presente en la lista de reservaciones
         * @postcond se agrega la resevación a la lista de horarios
         */
        var hrMin = date.split(":");
        //se asegura de que sea en horarios de tipo "HH:00" y ya no esté reservado
        if(!hrMin[1].equals("00") && horarios.contains(date)){
            throw  new RuntimeException("No se puede reservar este horario");
        }
        horarios.add(date);
        System.out.println("Horario reservado con éxito");
    }
    public ArrayList<String> getReservas(){
        return horarios;
    }
}