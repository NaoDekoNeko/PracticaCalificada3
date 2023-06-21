import java.util.random.RandomGenerator;

public class WordSelection {
    WordRepository wordRepository;

    public WordSelection(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }


    public String getRandomWord() {
        //si es que wordRepository es válido
        try{
            //Genera un número aleatorio entre 1 y 9 que luego sirve para que se retorne el llamado
            //a fetchWordByNumber que hace hará wordRepository
            var rnd = RandomGenerator.getDefault();
            var aux = rnd.nextInt(1,10);
            return wordRepository.fetchWordByNumber(aux);
        }
        //si no lo es (lo que genera una excepción)
        catch (WordRepositoryException e){
            //lanza una excepción WordSelectionException
            throw new WordSelectionException();
        }
    }
}
