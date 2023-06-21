public class ReservarAula {
    Aula[] aulas;
    public ReservarAula(Aula[] aulas){
        this.aulas = aulas;
    }
    public void reservarAula(String id,String date){
        for(var aula: aulas){
            if(!aula.getId().equals(id)){
                continue;
            }
            try{
                aula.reservas.reservar(date);
                break;
            } catch (RuntimeException e){
                System.out.println("Horarios ocupados:");
                for(var i:aula.reservas.getReservas()){
                    System.out.println(i);
                }
            }
        }
    }
}