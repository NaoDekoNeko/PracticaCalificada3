# Practica Calificada 3 - CC3S2

## Prueba 1

Dentro de la carpeta Pregunta1 se encuentra el código fuente

### Pregunta 1

Una primera implementación sin requisitos

```java
public class Clumps {
    public static int countClumps(int[] nums){
        //si nums es null o de longitud 0
        if (nums == null || nums.length == 0){
            return 0;
        }
        int count = 0;
        int prev = nums[0];
        boolean inClump = false;
        //para nums con longitud mayor a 1
        for(int i = 1; i < nums.length; i++){
            //si el elemento de la posición i del array es igual
            //al número anterior (i-1) y no está en contado en el clump
            if (nums[i] == prev && !inClump){
                //se marca que sí está en el clump y se aumenta el contador
                inClump = true;
                count += 1;
            }
            //si el elemento de posición i no es igual al elemento (i-1)
            if(nums[i] != prev){
                //se asigna el elemento actual como anterior y se marca que no está dentro del clump
                prev = nums[i];
                inClump = false;
            }
        }
        //si el array tiene longitud 1 o se ha terminado el conteo
        return count;
    }
}
```

### Pregunta 2

T1: array es null o de longitud = 0

T2: array es de longitud = 1

T3: array es de longitud >1 y no tiene clumps 

T4: array es de longitud >1 y tiene clumps ≥ 1

### Pregunta 3

```java
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BranchCoverClumpTest {
    //T1
    @Test
    public void emptyAndNullArray(){
        int[] array = new int[0];
        assertThat(Clumps.countClumps(array)).isEqualTo(0);
        assertThat(Clumps.countClumps(null)).isEqualTo(0);
    }
    //T2
    @Test
    public void arrayLength1(){
        int[] array = {1};
        assertThat(Clumps.countClumps(array)).isEqualTo(0);
    }
    //T3
    @Test
    public void arrayLength3Clumps0(){
        int[] array = {1,0,2};
        assertThat(Clumps.countClumps(array)).isEqualTo(0);
    }
    //T4
    @Test
    public void arrayLength3Clumps1(){
        int[] array = {1,1,1};
        assertThat(Clumps.countClumps(array)).isEqualTo(1);
    }
}
```

> La cobertura de código está en: /Pregunta1/htmlReport
> 

Un pequeño vistazo

![Untitled](Practica%20Calificada%203%20-%20CC3S2/Untitled.png)

Notamos que se cubren todas las branchs, no se logra un 100% ya que Clumps solo tiene un método estático por lo que en las pruebas no llame a su constructor, solo al método countClumps

![Untitled](Practica%20Calificada%203%20-%20CC3S2/Untitled%201.png)

### Pregunta 4

En este caso, al ya haber tenido mi cobertura en lo máximo posible, no hubo un cambio en la misma

> Adicional a los dos test, hice una pequeña modificación al código para que se utilice un único objeto array, que es un array de enteros y que se inicialice en cada test. Esto con el fin de evitar la duplicación de código
> 

```java
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BranchCoverClumpTest {
    private int[] array;
    //T1
    @Test
    public void emptyAndNullArray(){
        array = new int[0];
        assertThat(Clumps.countClumps(array)).isEqualTo(0);
        assertThat(Clumps.countClumps(null)).isEqualTo(0);
    }
    //T2
    @Test
    public void arrayLength1(){
        array = new int[]{1};
        assertThat(Clumps.countClumps(array)).isEqualTo(0);
    }
    //T3
    @Test
    public void arrayLength3Clumps0(){
        array = new int[]{1, 0, 2};
        assertThat(Clumps.countClumps(array)).isEqualTo(0);
    }
    //T4
    @Test
    public void arrayLength3Clumps1(){
        array = new int[]{1, 1, 1};
        assertThat(Clumps.countClumps(array)).isEqualTo(1);
    }
    //TAd1
    @Test
    public void clumpIsLast(){
        array = new int[]{1, 2, 3, 4, 4};
        assertThat(Clumps.countClumps(array)).isEqualTo(1);
    }
    //TAd2
    @Test
    public void clumpIsFirst(){
        array = new int[]{1,1,2,3,4};
        assertThat(Clumps.countClumps(array)).isEqualTo(1);
    }
}
```

Resultado de la cobertura de código:

![Untitled](Practica%20Calificada%203%20-%20CC3S2/Untitled%202.png)

<aside>
💡 Se puede hacer con pruebas paramétricas, pero ya no me da tiempo.

</aside>

## Prueba 2

### Pregunta 1

Se ha copiado las clases de la aplicación Wordz. A través de TDD hemos creado y completado las clases WordSelection, WordSelectionException, WordRepositoryException, WordSelectionTest y la interfaz WordRepository

```java
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
```

```java
public interface WordRepository {
    String fetchWordByNumber(int wordNumber);
}
```

```java
public class WordRepositoryException extends RuntimeException {
    public WordRepositoryException(){
        super();
    }
}
```

```java
public class WordSelectionException extends RuntimeException{
    public WordSelectionException(){
        super();
    }
}
```

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class WordSelectionTest {
    @Mock
    private WordRepository repository;

    @Test
    public void reportsWordNotFound() {
        doThrow(new WordRepositoryException())
                .when(repository)
                .fetchWordByNumber(anyInt());
        var selection = new WordSelection(repository);
        assertThatExceptionOfType(WordSelectionException.class)
                .isThrownBy(
                        ()->selection.getRandomWord());
    }
}
```

Referente al código de prueba:

Tenemos nuestro Mock repository de tipo WordRepository que nos ayudará.

```java
@Mock
    private WordRepository repository;
```

Para cualquiera sea el número que le demos como parámetro al método fetchWordByNumber que será llamado desde nuestro mock repository, este lanzará una excepción de tipo WordRepositoryException

```java
doThrow(new WordRepositoryException())
                .when(repository)
                .fetchWordByNumber(anyInt());
```

Luego tendremos un objeto selection que instanciaremos con nuestro mock repository

```java
var selection = new WordSelection(repository);
```

Y afirmaremos que se va a lanzar una excepción de tipo WordSelectionException cuando llamemos al método getRandomWord desde nuestro objeto selection

```java
assertThatExceptionOfType(WordSelectionException.class)
                .isThrownBy(
                        ()->selection.getRandomWord());
```

### Pregunta 2

Act: lanza una excepción de tipo WordRepositoryException cuando el objeto repository llame al método fetchWordByNumber() con cualquier número

Assert: espera una excepción de tipo WordSelectionException cuando al objeto selection llame al método getRandomWord

### Pregunta 3

En un principio la prueba no pasaba pues faltaba implementar, por ejemplo, un try catch en el método getRandomWord de la clase WordSelection. Quizá otras cuantas líneas de código más, pero las agregué y mis pruebas pasan.

### Pregunta 4

![Untitled](Practica%20Calificada%203%20-%20CC3S2/Untitled%203.png)

<aside>
💡 No están en html con jacoco por algún error que no me da tiempo de arreglar :C así que mejor pasé esta imagen

</aside>

Se está cubriendo el 100% de pruebas, la diferencia es que esta vez no estamos trabajando con todas las implementaciones, sino que, además estamos usando mocks para poder alcanzar esta cobertura sin tener que implementarlas más allá de tener el diseño de clases y pedazos de lógica

### Pregunta 5

```java
public String getRandomWord() {
        //si es que wordRepository es válido
        try{
            //Genera un número aleatorio entre 0 y 9 que luego sirve para que se retorne el llamado
            //a fetchWordByNumber que hace hará wordRepository
            var rnd = RandomGenerator.getDefault();
            var aux = rnd.nextInt(10);
            return wordRepository.fetchWordByNumber(aux);
        }
        //si no lo es (lo que genera una excepción)
        catch (WordRepositoryException e){
            //lanza una excepción WordSelectionException
            throw new WordSelectionException();
        }
    }
```

Podríamos usar un stub para crear un test en el que sí obtendremos una palabra “aleatoria”, que usando una interfaz e inversión de dependecias tal como en la actividad de doble de pruebas, para que nos devuelva una palabra que nosotros esperamos recibir en vez de una aleatoria. E.g: una prueba que obtenga una palabra digamos “ayuda” que tiene la posición 4, llamamos a getRandomWord mediante una interfaz, con lo que lograremos que en vez de devolver una palabra en un lugar aleatorio que puede o no estar, que devuelva la palabra “ayuda” que está en el lugar 4.

## Prueba 3

Clase Reservas

```java
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
```

```java
public class Aula {
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
}
```

```java
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
```

> Hora de limpieza
> 

Le agregaremos este método para agregar una hora de limpieza

```java
String horaLimpieza;
void horaLimpieza(String date){
        reservas.reservar(date);
        horaLimpieza = date;
    }
```

Esta implementación es una clara violación del principio Open/Close, pero es una primera implementación

`¿Recuerda cuando le dije que sí podía terminarlo a tiempo? No sabía cúan equivocado estaba. Efectivamente, no terminé :'`s