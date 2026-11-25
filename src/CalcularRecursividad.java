import java.util.List;

/**
 * Ejercicios clásicos resueltos con recursividad.
 *
 * @author Sebastián Verdugo Bermúdez
 */
public class CalcularRecursividad {

    /**
     * Suma los elementos de un arreglo desde la posición dada hasta el índice 0.
     * Fórmula: S(i) = numeros[i] + S(i - 1), con S(-1) = 0.
     *
     * @param numeros  arreglo de enteros a sumar
     * @param posicion índice desde el que se empieza a sumar
     * @return la suma de los elementos de 0 a posicion
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
     * Construye el texto del arreglo desde la posición dada hasta el final,
     * separado por comas y cerrado con "]". El "[" lo agrega quien lo llama.
     *
     * @param numeros  arreglo a imprimir, con al menos un elemento
     * @param posicion índice desde el que se empieza a imprimir
     * @return los elementos desde posicion, terminados en "]"
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
     * Convierte un número decimal a binario con divisiones sucesivas entre 2.
     * Fórmula: bin(n) = bin(n / 2) + (n % 2).
     *
     * @param numero número decimal mayor o igual a 0
     * @return el número en binario
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
     * Fórmula: mcd(a, 0) = a y mcd(a, b) = mcd(b, a mod b).
     *
     * @param numero1 primer número
     * @param numero2 segundo número
     * @return el máximo común divisor de ambos números
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
     * Calcula la función de Ackermann A(m, n).
     * Fórmula: A(0, n) = n + 1, A(m, 0) = A(m - 1, 1) y
     * A(m, n) = A(m - 1, A(m, n - 1)).
     *
     * @param numero1 valor de m, mayor o igual a 0
     * @param numero2 valor de n, mayor o igual a 0
     * @return el valor de A(m, n), o -1 si algún valor es negativo
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
        // Valores negativos: fuera del dominio de la función
        return -1;
    }

    /**
     * Calcula el n-ésimo número de Catalan.
     * Fórmula: C(0) = 1 y C(n) = suma de C(i) * C(n - 1 - i) para i = 0 hasta n - 1.
     *
     * @param n posición en la sucesión, mayor o igual a 0
     * @return el número de Catalan de n
     */
    public static int calculaNumerosCatalan(int n){
        // Casos base: C(0) = C(1) = 1
        if(n == 0 || n == 1){
            return 1;
        }
        int resultado = 0;
        // Caso recursivo: suma de C(i) * C(n - 1 - i)
        for(int i = 0; i < n; i++){
            resultado += calculaNumerosCatalan(i) * calculaNumerosCatalan(n - i - 1);
        }
        return resultado;
    }

    /**
     * Eleva un número a una potencia.
     * Fórmula: b^0 = 1 y b^e = b * b^(e - 1).
     *
     * @param base      número que se multiplica
     * @param exponente veces que se multiplica la base, mayor o igual a 0
     * @return base elevada a exponente
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
     * Busca un valor en un arreglo ordenado con búsqueda binaria.
     *
     * @param arreglo  arreglo ordenado de menor a mayor
     * @param inicio   índice inicial del rango de búsqueda
     * @param fin      índice final del rango de búsqueda
     * @param objetivo valor que se busca
     * @return la posición del valor, o -1 si no se encuentra
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
     * Suma los elementos de una lista desde la posición dada hasta el índice 0.
     * Fórmula: S(i) = lista.get(i) + S(i - 1), con S(-1) = 0.
     *
     * @param lista    lista de enteros a sumar
     * @param posicion índice desde el que se empieza a sumar
     * @return la suma de los elementos de 0 a posicion
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
