fun main() {
    val pedidos: MutableMap<Int, Double> = mutableMapOf<Int, Double>(Pair(1, 20.00), Pair(2, 34.00), 3 to 50.0)

    pedidos[4] = 90.00
    pedidos.put(5, 100.0)
    pedidos.putIfAbsent(6, 12.0)

    println(pedidos)

    pedidos.remove(6)

    println("Pedido ${pedidos[3]}")

    for (pedido: Map.Entry<Int, Double> in pedidos) {
        println("Numero do  pedido ${pedido.key}");
        println("Valor do  pedido ${pedido.value}");

    }
}


