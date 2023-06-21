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
