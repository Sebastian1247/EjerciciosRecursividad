import java.util.Arrays;
import java.util.List;

/**
 * Punto de entrada del programa. Demuestra cada ejercicio de recursividad de
 * {@link CalcularRecursividad} con datos de ejemplo e imprime su resultado.
 *
 * @author Sebastián Verdugo Bermúdez
 * @version 1.0
 */
public class App {

    /**
     * Ejecuta los nueve ejercicios en orden y muestra el resultado de cada uno
     * en la consola.
     *
     * @param args argumentos de la línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        int[] numeros = {10, 3, 4, 1, 7, 8};

        // Ejercicio 1: se suma desde el último índice hasta el 0
        System.out.println("Ejercicio 1: Suma numeros en un arreglo");
        int suma = CalcularRecursividad.sumarArreglo(numeros, numeros.length - 1);
        System.out.println("suma del arreglo: " + suma);
        System.out.println();

        // Ejercicio 2: el método devuelve el texto con el "]" final; aquí se agrega el "["
        System.out.println("Ejercicio 2: Impresion de datos de un arreglo");
        String arreglo = "[" + CalcularRecursividad.imprimirArreglo(numeros, 0);
        System.out.println("Impresion del arreglo: " + arreglo);
        System.out.println();

        // Ejercicio 3: divisiones sucesivas entre 2
        System.out.println("Ejercicio 3: Convertir decimal a binario");
        int decimal = 134;
        String binario = CalcularRecursividad.convertirDecimalABinario(decimal);
        System.out.println("Numero decimal: " + decimal + " es " + binario + " en binario");
        System.out.println();

        // Ejercicio 4: algoritmo de Euclides
        System.out.println("Ejercicio 4: Calcular MCD");
        int numero1 = 12;
        int numero2 = 8;
        int mcd = CalcularRecursividad.calcularMCD(numero1, numero2);
        System.out.println("El MCD de " + numero1 + " y " + numero2 + " es: " + mcd);
        System.out.println();

        // Ejercicio 5: A(3, 2) = 2^(2+3) - 3 = 29
        System.out.println("Ejercicio 5: Calcular Ackermann");
        int ackerman = CalcularRecursividad.calcularAckerman(3, 2);
        System.out.println("El resultado de A(3, 2) es: " + ackerman);
        System.out.println();

        // Ejercicio 6: sucesión 1, 1, 2, 5, 14, 42, ...
        System.out.println("Ejercicio 6: Calcular numeros de Catalan");
        int catalan = CalcularRecursividad.calculaNumerosCatalan(5);
        System.out.println("El numero de Catalan para n=5 es: " + catalan);
        System.out.println();

        // Ejercicio 7: potencia con exponente no negativo
        System.out.println("Ejercicio 7: Elevar a una potencia un numero");
        int base = 2;
        int exponente = 3;
        int potencia = CalcularRecursividad.elevarAPotencia(base, exponente);
        System.out.println("El resultado de " + base + "^" + exponente + " es: " + potencia);
        System.out.println();

        // Ejercicio 8: el arreglo debe estar ordenado; parámetros (arreglo, inicio, fin, objetivo)
        System.out.println("Ejercicio 8: Busqueda binaria");
        int[] arregloBusqueda = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numeroABuscar = 7;
        int posicion = CalcularRecursividad.busquedaBinaria(arregloBusqueda, 0, arregloBusqueda.length - 1, numeroABuscar);
        if (posicion != -1) {
            System.out.println("El numero " + numeroABuscar + " se encuentra en la posicion: " + posicion);
        } else {
            System.out.println("El numero " + numeroABuscar + " no se encuentra en el arreglo");
        }
        System.out.println();

        // Ejercicio 9: misma idea que el ejercicio 1, aplicada a una List
        System.out.println("Ejercicio 9: Sumar elementos de una lista");
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        int sumaLista = CalcularRecursividad.sumarLista(lista, lista.size() - 1);
        System.out.println("La suma de los elementos de la lista es: " + sumaLista);
    }
}
