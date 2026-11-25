# Ejercicios de Recursividad

Implementación en Java de nueve ejercicios clásicos resueltos con recursividad, desarrollada para la materia de Tecnologías de Programación. Cada ejercicio es un método estático que se llama a sí mismo hasta llegar a un caso base, y el programa principal ejecuta todos en orden con datos de ejemplo y muestra sus resultados en consola.

Los ejercicios se eligieron para cubrir los distintos tipos de recursión: lineal (sumas, impresión, potencia, conversión a binario), de cola (MCD y búsqueda binaria), múltiple (números de Catalan) y anidada (función de Ackermann).

## Ejecución

Se requiere Java 8 o superior. El proyecto no depende de ningún IDE ni de bibliotecas externas; para compilarlo y ejecutarlo desde una terminal, en la raíz del proyecto:

```
javac -encoding UTF-8 -d bin src/*.java
java -cp bin App
```

También puede abrirse en cualquier IDE con soporte para Java (VS Code, IntelliJ IDEA, Eclipse, NetBeans), marcando `src` como carpeta de código fuente y ejecutando la clase `App`.

La documentación de las clases puede generarse con `javadoc`:

```
javadoc -encoding UTF-8 -d javadoc src/*.java
```

## Salida del programa

```
Ejercicio 1: Suma numeros en un arreglo
suma del arreglo: 33

Ejercicio 2: Impresion de datos de un arreglo
Impresion del arreglo: [10, 3, 4, 1, 7, 8]

Ejercicio 3: Convertir decimal a binario
Numero decimal: 134 es 10000110 en binario

Ejercicio 4: Calcular MCD
El MCD de 12 y 8 es: 4

Ejercicio 5: Calcular Ackermann
El resultado de A(3, 2) es: 29

Ejercicio 6: Calcular numeros de Catalan
El numero de Catalan para n=5 es: 42

Ejercicio 7: Elevar a una potencia un numero
El resultado de 2^3 es: 8

Ejercicio 8: Busqueda binaria
El numero 7 se encuentra en la posicion: 6

Ejercicio 9: Sumar elementos de una lista
La suma de los elementos de la lista es: 15
```

## Ejercicios

Todo método recursivo tiene dos partes: un caso base, que responde sin volver a llamarse y detiene la recursión, y un caso recursivo, que se llama a sí mismo con un problema más pequeño. Cada llamada ocupa un marco en la pila de llamadas hasta que el caso base devuelve un valor; si el caso base nunca se alcanza, la pila se llena y Java lanza `StackOverflowError`.

| # | Ejercicio | Fórmula recursiva | Origen | Tipo | Complejidad |
|---|---|---|---|---|---|
| 1 | Suma de un arreglo | `S(i) = a[i] + S(i-1)`, `S(-1) = 0` | Definición recursiva de la sumatoria | Lineal | O(n) |
| 2 | Impresión de un arreglo | `imp(i) = a[i] + ", " + imp(i+1)` | Recorrido recursivo | Lineal | O(n) |
| 3 | Decimal a binario | `bin(n) = bin(n/2) + (n mod 2)` | Método de divisiones sucesivas entre 2 | Lineal | O(log n) |
| 4 | Máximo Común Divisor | `mcd(a, b) = mcd(b, a mod b)`, `mcd(a, 0) = a` | Algoritmo de Euclides (*Elementos*, ~300 a. C.) | De cola | O(log min(a, b)) |
| 5 | Función de Ackermann | `A(0,n) = n+1`; `A(m,0) = A(m-1,1)`; `A(m,n) = A(m-1, A(m,n-1))` | Wilhelm Ackermann (1928), versión de Rózsa Péter | Anidada | No primitiva recursiva |
| 6 | Números de Catalan | `C(0) = 1`; `C(n) = Σ C(i)·C(n-1-i)` | Recurrencia de Segner (1758) | Múltiple | Exponencial |
| 7 | Potencia | `b^0 = 1`; `b^e = b · b^(e-1)` | Definición recursiva de la exponenciación | Lineal | O(e) |
| 8 | Búsqueda binaria | Buscar en la mitad izquierda o derecha según `a[medio]` | Divide y vencerás | De cola | O(log n) |
| 9 | Suma de una lista | `S(i) = lista.get(i) + S(i-1)`, `S(-1) = 0` | Definición recursiva de la sumatoria | Lineal | O(n) |

## Decisiones de diseño

### Separación entre lógica y presentación

Los métodos recursivos están en `CalcularRecursividad` y la demostración en `App`. Ningún método recursivo imprime en consola: todos reciben sus datos como parámetros y devuelven el resultado, de modo que pueden reutilizarse o probarse sin depender de la entrada y salida. `App` solo prepara los datos, invoca cada método e imprime lo que devuelve.

### Métodos estáticos

`CalcularRecursividad` no guarda estado: cada resultado depende únicamente de los parámetros. Por eso todos sus métodos son `static` y se invocan con el nombre de la clase, sin crear objetos.

### Recorrido con índice

Los ejercicios que recorren un arreglo o una lista reciben la posición actual como parámetro en lugar de crear copias más pequeñas de la colección. Así cada llamada trabaja sobre la misma estructura y solo cambia el índice, lo que evita copiar datos en cada nivel de la pila. Las sumas recorren de la última posición a la primera, con `posicion < 0` como caso base; la impresión recorre de la primera a la última para conservar el orden de los elementos.

### Casos base

El caso base se eligió en cada ejercicio para que corresponda a la definición matemática: la suma vacía vale 0, cualquier número elevado a 0 vale 1, el MCD de un número y 0 es el propio número, y `C(0)` y `A(0, n)` están definidos directamente. En la conversión a binario y en Catalan se agregó el 1 como caso base adicional, porque su resultado se conoce sin calcular y ahorra llamadas.

### Límites conocidos

Los métodos no validan entradas fuera de su dominio, ya que los ejercicios se enfocan en la recursión:

- `elevarAPotencia` con exponente negativo nunca llega al caso base y produce `StackOverflowError`.
- `imprimirArreglo` con un arreglo vacío produce `ArrayIndexOutOfBoundsException`.
- `convertirDecimalABinario` con números negativos no devuelve un binario válido.
- `calcularAckerman` devuelve -1 con argumentos negativos; con valores como `A(4, 1)` la recursión es tan profunda que produce `StackOverflowError`.
- `calculaNumerosCatalan` recalcula los mismos valores muchas veces (135 llamadas para `C(5)`). Con memoización, guardando los resultados ya calculados en un arreglo, su costo bajaría a O(n²).

## Clases

### App

Punto de entrada del programa. Ejecuta los nueve ejercicios con datos de ejemplo e imprime el resultado de cada uno.

| Método | Acceso | Descripción |
|---|---|---|
| `main(String[] args)` | public static | Prepara los datos de cada ejercicio, invoca el método correspondiente de `CalcularRecursividad` y muestra el resultado |

### CalcularRecursividad

Agrupa los métodos recursivos. Todos son estáticos y no guardan estado.

| Método | Acceso | Descripción |
|---|---|---|
| `sumarArreglo(int[] numeros, int posicion)` | public static | Suma los elementos desde `posicion` hasta el índice 0 |
| `imprimirArreglo(int[] numeros, int posicion)` | public static | Devuelve los elementos desde `posicion` separados por comas y terminados en `]`; el `[` lo agrega quien llama |
| `convertirDecimalABinario(int numero)` | public static | Devuelve la representación binaria de un número no negativo |
| `calcularMCD(int numero1, int numero2)` | public static | Máximo Común Divisor por el algoritmo de Euclides |
| `calcularAckerman(int numero1, int numero2)` | public static | Valor de la función de Ackermann `A(m, n)`; -1 con argumentos negativos |
| `calculaNumerosCatalan(int n)` | public static | n-ésimo número de Catalan por la recurrencia de Segner |
| `elevarAPotencia(int base, int exponente)` | public static | `base` elevada a `exponente`, con exponente no negativo |
| `busquedaBinaria(int[] arreglo, int inicio, int fin, int objetivo)` | public static | Índice de `objetivo` en un arreglo ordenado, o -1 si no está |
| `sumarLista(List<Integer> lista, int posicion)` | public static | Suma los elementos de la lista desde `posicion` hasta el índice 0 |

## Autores

- Sebastián Verdugo Bermúdez ([Sebastian1247](https://github.com/Sebastian1247))

Tecnologías de Programación, Maestría en Ciencias de la Computación, 1er semestre. [Tecnológico Nacional de México, Campus Culiacán](https://www.culiacan.tecnm.mx/posgrados/maestria-en-ciencias-de-la-computacion/)
