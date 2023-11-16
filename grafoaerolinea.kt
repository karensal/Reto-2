// Definición de la clase AerolineaGrafo
class AerolineaGrafo {
    // Definición de la clase interna Ciudad que representa una ciudad en el grafo
    data class Ciudad(val nombre: String) {
        // Lista de conexiones salientes, que almacena pares (Ciudad, Triple) representando el destino y la información del vuelo
        val conexionesSalientes = mutableListOf<Pair<Ciudad, Triple<Double, Double, String>>>()
        // Referencia a la siguiente ciudad en una ruta (puede ser null si no hay siguiente ciudad)
        var siguiente: Ciudad? = null
    }

    // Lista privada de ciudades en el grafo
    private val ciudades = mutableListOf<Ciudad>()

    // Método para agregar una ciudad al grafo
    fun agregarCiudad(ciudad: Ciudad) {
        ciudades.add(ciudad)
    }

    // Método para agregar un vuelo entre dos ciudades con información de costo, distancia y hora
    fun agregarVuelo(ciudadOrigen: Ciudad, ciudadDestino: Ciudad, costo: Double, distancia: Double, hora: String) {
        // Se añade la conexión saliente desde la ciudad de origen a la de destino
        ciudadOrigen.conexionesSalientes.add(ciudadDestino to Triple(costo, distancia, hora))
        // Se añade la conexión saliente inversa desde la ciudad de destino a la de origen
        ciudadDestino.conexionesSalientes.add(ciudadOrigen to Triple(costo, distancia, hora))
    }

    // Método para obtener las conexiones salientes de una ciudad dada
    fun obtenerConexiones(ciudad: Ciudad): List<Pair<Ciudad, Triple<Double, Double, String>>> {
        return ciudad.conexionesSalientes
    }

    // Método para agregar rutas al grafo de la aerolínea
    fun agregarRutas(aerolineaGrafo: AerolineaGrafo) {
        println("Ingrese el nombre de la ciudad de origen:")
        val nombreCiudadOrigen = readLine() ?: ""
        val ciudadOrigen = AerolineaGrafo.Ciudad(nombreCiudadOrigen)
    
        println("Ingrese el nombre de la ciudad de escala (deje en blanco si no hay escala):")
        val nombreCiudadEscala = readLine() ?: ""
        val ciudadEscala = AerolineaGrafo.Ciudad(nombreCiudadEscala)
    
        println("Ingrese el nombre de la ciudad de destino:")
        val nombreCiudadDestino = readLine() ?: ""
        val ciudadDestino = AerolineaGrafo.Ciudad(nombreCiudadDestino)
    
        // Configura la secuencia de ciudades en la ruta
        ciudadOrigen.siguiente = ciudadEscala
        ciudadEscala.siguiente = ciudadDestino
    
        // Agrega las ciudades al grafo
        aerolineaGrafo.agregarCiudad(ciudadOrigen)
        aerolineaGrafo.agregarCiudad(ciudadEscala)
        aerolineaGrafo.agregarCiudad(ciudadDestino)
    
        if (nombreCiudadEscala?.isNotEmpty() == true) {
            println("Ingrese el costo del vuelo de $nombreCiudadOrigen a $nombreCiudadEscala:")
            val costoOE = readLine()?.toDouble() ?: 0.0
    
            println("Ingrese la distancia del vuelo de $nombreCiudadOrigen a $nombreCiudadEscala:")
            val distanciaOE = readLine()?.toDouble() ?: 0.0
    
            println("Ingrese la hora del vuelo de $nombreCiudadOrigen a $nombreCiudadEscala:")
            val horaOE = readLine() ?: ""
    
            println("Ingrese el costo del vuelo de $nombreCiudadEscala a $nombreCiudadDestino:")
            val costoED = readLine()?.toDouble() ?: 0.0
    
            println("Ingrese la distancia del vuelo de $nombreCiudadEscala a $nombreCiudadDestino:")
            val distanciaED = readLine()?.toDouble() ?: 0.0
    
            println("Ingrese la hora del vuelo de $nombreCiudadEscala a $nombreCiudadDestino:")
            val horaED = readLine() ?: ""
    
            aerolineaGrafo.agregarVuelo(ciudadOrigen, ciudadEscala, costoOE, distanciaOE, horaOE)
            aerolineaGrafo.agregarVuelo(ciudadEscala, ciudadDestino, costoED, distanciaED, horaED)
    
            println("Rutas agregadas con éxito.")
        } else {
            println("Ingrese el costo del vuelo de $nombreCiudadOrigen a $nombreCiudadDestino:")
            val costoOD = readLine()?.toDouble() ?: 0.0
    
            println("Ingrese la distancia del vuelo de $nombreCiudadOrigen a $nombreCiudadDestino:")
            val distanciaOD = readLine()?.toDouble() ?: 0.0
    
            println("Ingrese la hora del vuelo de $nombreCiudadOrigen a $nombreCiudadDestino:")
            val horaOD = readLine() ?: ""
    
            aerolineaGrafo.agregarVuelo(ciudadOrigen, ciudadDestino, costoOD, distanciaOD, horaOD)
    
            println("Ruta directa agregada con éxito.")
        }
    }
    
    // Método para mostrar las rutas del grafo de la aerolínea
    fun mostrarRutas(aerolineaGrafo: AerolineaGrafo) {
        println("Mostrando rutas:")
        // Itera sobre todas las ciudades en el grafo
        for (ciudad in aerolineaGrafo.ciudades) {
            // Obtiene las conexiones salientes de la ciudad
            val conexiones = aerolineaGrafo.obtenerConexiones(ciudad)
            // Imprime la información de la ciudad y sus conexiones
            println("Desde ${ciudad.nombre}: $conexiones")
        }
    }
}

fun main() {
    // Crear una instancia de la clase AerolineaGrafo
    val aerolineaGrafo = AerolineaGrafo()   

    while (true) {
        println("\nAerolinea:")
        println("1. Agregar Rutas")
        println("2. Mostrar Rutas")
        println("3. Salir")

        val opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> aerolineaGrafo.agregarRutas(aerolineaGrafo)
            2 -> aerolineaGrafo.mostrarRutas(aerolineaGrafo)
            3 -> {
                println("Saliendo del programa. ¡Hasta luego!")
                return
            }
            else -> println("Opción no válida. Por favor, elija una opción válida.")
        }
    }
}

