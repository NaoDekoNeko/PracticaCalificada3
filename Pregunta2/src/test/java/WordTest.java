import domain.Letter;
import domain.Score;
import domain.Word;
import org.junit.jupiter.api.Test;

import static domain.Letter.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WordTest {
    @Test
    public void oneIncorrectLetter() {
        var word = new Word("A");
        var score = word.guess("Z");
        assertScoreForGuess(score, INCORRECT);
    }
    @Test
    public void oneCorrectLetter() {
        var word = new Word("A");
        var score = word.guess("A");
        assertScoreForGuess(score, CORRECT);
    }

    @Test
    void secondLetterWrongPosition() // test para una segunda letra
    {
        var word = new Word("AR");
        var score = word.guess("ZA");
        assertScoreForGuess(score, INCORRECT,
                Letter.PART_CORRECT);
    }
    @Test
    void allScoreCombinations() {
        var word = new Word("ARI");
        var score = word.guess("ZAI");
        assertScoreForGuess(score, INCORRECT, PART_CORRECT,
                CORRECT);
    }

    private void assertScoreForGuess(Score score, Letter... expectedScores){
        for(int position=0; position < expectedScores.length;position++){
            Letter expected = expectedScores[position];
            assertThat(score.letter(position)).isEqualTo(expected);
        }
    }
}
