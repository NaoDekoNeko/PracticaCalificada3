package domain;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private final String correct;
    private int position;
    private final List<Letter> results =
            new ArrayList<>();

    public Score(String correct) {
        this.correct = correct;
    }

    // funcion que retorna el valor del resultado que se almaceno en la lista
    public Letter letter(int position) {
        return results.get(position);
    }
    // funcion que rellena la lista de resultados por letra
    public void assess(String attempt) {
        for (char current: attempt.toCharArray()) {
            results.add( scoreFor(current) );
            position++;
        }
    }
    //esto ayuda al codigo de llamada, es una mejor forma de comentar
    //desde el nombre de la funcion nos especifica su responsabilidad

    // funcion que verifica si la letra esta contenida en la palabra correcta
    private boolean occursInWord(char current){
        return
                correct.contains(String.valueOf(current));
    }
    // funcion que verifica si la letra es la correcta en la posicion actual
    private boolean isCorrectLetter(char currentLetter){
        return correct.charAt(position) == currentLetter;
    }
    //va a asignar un score
    private Letter scoreFor(char current) {
        if (isCorrectLetter(current)) {
            return Letter.CORRECT;
        }
        if (occursInWord(current)) {
            return Letter.PART_CORRECT;
        }
        return Letter.INCORRECT;
    }
}