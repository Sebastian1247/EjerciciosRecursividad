import java.util.List;

/**
 * Ejercicios clásicos resueltos con recursividad.
 *
 * <p>Todos los métodos son {@code static}, por lo que se invocan con el nombre
 * de la clase, sin crear objetos. Cada método tiene un <b>caso base</b>, que
 * detiene la recursión, y un <b>caso recursivo</b>, que reduce el problema
 * hasta alcanzar el caso base.</p>
 *
 * @author Sebastián Verdugo Bermúdez
 * @version 1.0
 */
public class CalcularRecursividad {

    /**
     * Suma los elementos de un arreglo desde {@code posicion} hasta el índice 0.
     *
     * <p>Fórmula: {@code S(i) = numeros[i] + S(i - 1)}, con {@code S(-1) = 0}.</p>
     *
     * @param numeros  arreglo de enteros a sumar
     * @param posicion índice desde el que se empieza a sumar
     *                 (normalmente {@code numeros.length - 1})
     * @return la suma de los elementos de 0 a {@code posicion};
     *         0 si {@code posicion} es negativa
     */
    public static int sumarArreglo(int[] numeros, int posicion){
        // Caso base: ya no quedan elementos por sumar
        if(posicion < 0) {
            return 0;
        }
        // Caso recursivo: elemento actual + suma de los anteriores
        return numeros[posicion] + sumarArreglo(numeros, posicion - 1);
    }

    /**
     * Construye el texto de un arreglo desde {@code posicion} hasta el final,
     * con los elementos separados por {@code ", "} y cerrado con {@code "]"}.
     *
     * <p>El corchete de apertura {@code "["} lo agrega quien llama al método.</p>
     *
     * @param numeros  arreglo a imprimir; debe tener al menos un elemento
     * @param posicion índice desde el que se empieza a imprimir (normalmente 0)
     * @return los elementos desde {@code posicion}, terminados en {@code "]"}
     * @throws ArrayIndexOutOfBoundsException si el arreglo está vacío
     */
    public static String imprimirArreglo(int[] numeros, int posicion){
        // Caso base: último elemento, se cierra el corchete
        if(posicion == numeros.length - 1){
            return numeros[posicion] + "]";
        }
        // Caso recursivo: elemento actual + ", " + el resto del arreglo
        return numeros[posicion] + ", " + imprimirArreglo(numeros, posicion + 1);
    }

    /**
     * Convierte un número decimal a binario con el método de divisiones
     * sucesivas entre 2.
     *
     * <p>Fórmula: {@code bin(n) = bin(n / 2) + (n % 2)}. La recursión escribe
     * los residuos en orden inverso, que es el orden correcto del binario.</p>
     *
     * @param numero número decimal a convertir; debe ser mayor o igual a 0
     * @return la representación binaria de {@code numero}
     */
    public static String convertirDecimalABinario(int numero){
        // Casos base: 0 y 1 se escriben igual en binario
        if(numero == 0){
            return "0";
        }
        if(numero == 1){
            return "1";
        }
        // Caso recursivo: binario del cociente + el residuo como último bit
        return convertirDecimalABinario(numero / 2) + (numero % 2);
    }

    /**
     * Calcula el Máximo Común Divisor con el algoritmo de Euclides.
     *
     * <p>Fórmula: {@code mcd(a, 0) = a} y {@code mcd(a, b) = mcd(b, a mod b)}.
     * Es recursión de cola: al regresar no queda ninguna operación pendiente.</p>
     *
     * @param numero1 primer número, mayor o igual a 0
     * @param numero2 segundo número, mayor o igual a 0
     * @return el máximo común divisor de {@code numero1} y {@code numero2}
     */
    public static int calcularMCD(int numero1, int numero2){
        // Caso base: el MCD de un número y 0 es el propio número
        if(numero2 == 0){
            return numero1;
        }
        // Caso recursivo: mcd(a, b) = mcd(b, a % b)
        return calcularMCD(numero2, numero1 % numero2);
    }

    /**
     * Calcula la función de Ackermann-Péter {@code A(m, n)}.
     *
     * <pre>
     * A(0, n) = n + 1
     * A(m, 0) = A(m - 1, 1)               si m &gt; 0
     * A(m, n) = A(m - 1, A(m, n - 1))     si m &gt; 0 y n &gt; 0
     * </pre>
     *
     * <p>Es recursión anidada y crece muy rápido: con valores como
     * {@code A(4, 1)} se produce {@code StackOverflowError}.</p>
     *
     * @param numero1 valor de {@code m}, mayor o igual a 0
     * @param numero2 valor de {@code n}, mayor o igual a 0
     * @return el valor de {@code A(m, n)}, o -1 si algún argumento es negativo
     */
    public static int calcularAckerman(int numero1, int numero2){
        // Caso base: A(0, n) = n + 1
        if(numero1 == 0){
            return numero2 + 1;
        }
        // A(m, 0) = A(m - 1, 1)
        if(numero1 > 0 && numero2 == 0){
            return calcularAckerman(numero1 - 1, 1);
        }
        // A(m, n) = A(m - 1, A(m, n - 1)); la llamada interna se resuelve primero
        if(numero1 > 0 && numero2 > 0){
            return calcularAckerman(numero1 - 1, calcularAckerman(numero1, numero2 - 1));
        }
        // Argumentos negativos: fuera del dominio de la función
        return -1;
    }

    /**
     * Calcula el n-ésimo número de Catalan con la recurrencia de Segner.
     *
     * <p>Fórmula: {@code C(0) = 1} y {@code C(n) = Σ C(i)·C(n-1-i)} para
     * {@code i = 0..n-1}. Es recursión múltiple con costo exponencial.</p>
     *
     * @param n posición en la sucesión, mayor o igual a 0
     * @return el número de Catalan {@code C(n)}
     */
    public static int calculaNumerosCatalan(int n){
        // Casos base: C(0) = C(1) = 1
        if(n == 0 || n == 1){
            return 1;
        }
        int resultado = 0;
        // Suma de todos los repartos: i elementos a la izquierda y n-1-i a la derecha
        for(int i = 0; i < n; i++){
            resultado += calculaNumerosCatalan(i) * calculaNumerosCatalan(n - i - 1);
        }
        return resultado;
    }

    /**
     * Eleva {@code base} a la potencia {@code exponente}.
     *
     * <p>Fórmula: {@code b^0 = 1} y {@code b^e = b · b^(e - 1)}.</p>
     *
     * @param base      número que se multiplica
     * @param exponente veces que se multiplica la base; debe ser mayor o igual
     *                  a 0, de lo contrario se produce {@code StackOverflowError}
     * @return {@code base} elevado a {@code exponente}
     */
    public static int elevarAPotencia(int base, int exponente){
        // Caso base: cualquier número elevado a 0 es 1
        if(exponente == 0){
            return 1;
        }
        // Caso recursivo: b^e = b * b^(e-1)
        return base * elevarAPotencia(base, exponente - 1);
    }

    /**
     * Busca un valor en un arreglo ordenado mediante búsqueda binaria
     * (divide y vencerás).
     *
     * @param arreglo  arreglo ordenado de forma ascendente
     * @param inicio   índice inicial del rango de búsqueda (normalmente 0)
     * @param fin      índice final del rango de búsqueda
     *                 (normalmente {@code arreglo.length - 1})
     * @param objetivo valor que se busca
     * @return el índice donde está {@code objetivo}, o -1 si no se encuentra
     */
    public static int busquedaBinaria(int[] arreglo, int inicio, int fin, int objetivo){
        // Caso base: el rango quedó vacío, el valor no existe
        if(inicio > fin){
            return -1;
        }
        int medio = (inicio + fin) / 2;
        // Caso base: se encontró el valor
        if(arreglo[medio] == objetivo){
            return medio;
        }
        // El objetivo es menor: se busca en la mitad izquierda
        if(arreglo[medio] > objetivo){
            return busquedaBinaria(arreglo, inicio, medio - 1, objetivo);
        }
        // El objetivo es mayor: se busca en la mitad derecha
        return busquedaBinaria(arreglo, medio + 1, fin, objetivo);
    }

    /**
     * Suma los elementos de una lista desde {@code posicion} hasta el índice 0.
     *
     * <p>Fórmula: {@code S(i) = lista.get(i) + S(i - 1)}, con {@code S(-1) = 0}.</p>
     *
     * @param lista    lista de enteros a sumar
     * @param posicion índice desde el que se empieza a sumar
     *                 (normalmente {@code lista.size() - 1})
     * @return la suma de los elementos de 0 a {@code posicion};
     *         0 si {@code posicion} es negativa
     */
    public static int sumarLista(List<Integer> lista, int posicion){
        // Caso base: ya no quedan elementos por sumar
        if(posicion < 0){
            return 0;
        }
        // Caso recursivo: elemento actual + suma de los anteriores
        return lista.get(posicion) + sumarLista(lista, posicion - 1);
    }
}
