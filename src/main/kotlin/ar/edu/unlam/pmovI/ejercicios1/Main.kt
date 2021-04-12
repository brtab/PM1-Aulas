package ar.edu.unlam.pmovI.ejercicios1

fun main(args: Array<String>){
    var opcion = 0
    var actual = Aula(5, 5)
    do {
        actual.mostrarAula()
        opcion = mostrarMenu()
        when(opcion){
            1 -> if(!actual.ocuparUnBanco()) println("ar.edu.unlam.pmovI.ejercicios1.Aula llena!")
            2 -> if(!actual.liberarUnBanco()) println("ar.edu.unlam.pmovI.ejercicios1.Aula vacia!")
            3 -> println("En este momento hay ${actual.obtenerBancosDisponibles()} bancos disponibles.")
            9 -> println("Fin.")
            else -> println("Opcion invalida!")
        }
    }while(opcion != 9)

}

fun mostrarMenu(): Int {

    println("1 - Ocupar un banco")
    println("2 - Liberar un banco")
    println("3 - Conocer lugares disponibles")
    println("9 - Salir")
    return elegirOpcion()
}

fun elegirOpcion(): Int {
    return readLine()!!.toInt()
}

class Aula(val numeroDeFilas: Int, val bancosPorFila: Int){

    var bancos = Array(numeroDeFilas){Array(bancosPorFila) { EstadoDeBancos.LIBRE }}
    val capacidadTotal = numeroDeFilas * bancosPorFila

    fun mostrarAula(){
        for(filas in bancos){
            print("| ")
            for(escritorio in filas){
                print("$escritorio |")
            }
            println()
        }
    }

    fun ocuparUnBanco(): Boolean{
        if(obtenerBancosDisponibles()>0) {
            for (i in 0..numeroDeFilas) {
                for (j in 0..bancosPorFila) {
                    if (bancos[i][j] == EstadoDeBancos.LIBRE) {
                        bancos[i][j] = EstadoDeBancos.OCUPADO
                        return true
                    }
                }
            }
        }
        return false
    }

    fun liberarUnBanco(): Boolean{
        if(!(obtenerBancosDisponibles()==capacidadTotal)) {
            for (i in 0..numeroDeFilas) {
                for (j in 0..bancosPorFila) {
                    if (bancos[i][j] == EstadoDeBancos.OCUPADO) {
                        bancos[i][j] = EstadoDeBancos.LIBRE
                        return true
                    }
                }
            }
        }
        return false
    }

    fun obtenerBancosDisponibles(): Int{
        var total = 0
        for(filas in bancos){
            for(escritorio in filas){
                if(escritorio == EstadoDeBancos.LIBRE)
                    total++
            }
        }
        return total
    }

}