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
