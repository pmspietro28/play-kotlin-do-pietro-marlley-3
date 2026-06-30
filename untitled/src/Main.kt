
open class Produto(
    val nome: String,
    private var preco: Double,
    var quantidadeEstoque: Int
) {

    fun getPreco(): Double {
        return preco
    }

    fun setPreco(valor: Double) {
        if (valor >= 0) {
            preco = valor
        } else {
            println("Preço não aceito!")
        }
    }

    fun aplicarDesconto(percentual: Double) {
        if (percentual >= 0 && percentual <= 100) {
            val desconto = preco * percentual / 100
            setPreco(preco - desconto)
        } else {
            println("Percentual não aceito!")
        }
    }
}

class ProdutoPerecivel(
    nome: String,
    preco: Double,
    quantidadeEstoque: Int,
    val dataValidade: String
) : Produto(nome, preco, quantidadeEstoque) {

    fun estaVencido(dataHoje: String): Boolean {
        return dataValidade < dataHoje
    }
}

abstract class FuncionarioBase(
    val nome: String,
    val salarioBase: Double
) {

    abstract fun calcularSalario(): Double
}

class Vendedor(
    nome: String,
    salarioBase: Double,
    var totalVendas: Double
) : FuncionarioBase(nome, salarioBase) {

    override fun calcularSalario(): Double {
        return salarioBase + (totalVendas * 0.05)
    }
}

class Gerente(
    nome: String,
    salarioBase: Double,
    val bonusFixo: Double
) : FuncionarioBase(nome, salarioBase) {

    override fun calcularSalario(): Double {
        return salarioBase + bonusFixo
    }
}

fun finalizarVenda(carrinho: List<Produto>, vendedor: Vendedor) {

    var totalVenda = 0.0

    println("\nITENS DA VENDA:")

    for (produto in carrinho) {

        println("${produto.nome} - R$ ${produto.getPreco()}")

        totalVenda += produto.getPreco()

        produto.quantidadeEstoque--
    }

    vendedor.totalVendas += totalVenda

    println("Total da venda: R$ $totalVenda")
    println("Salário atualizado do vendedor: R$ ${vendedor.calcularSalario()}")
}

fun main() {




    val arroz = Produto("Arroz", 5.50, 400)
    val feijao = Produto("Feijão", 6.00, 530)
    val macarrao = Produto("Macarrão", 4.50, 100)

    println("Produto: ${arroz.nome} | Preço: R$ ${arroz.getPreco()} | Estoque: ${arroz.quantidadeEstoque}")
    println("Produto: ${feijao.nome} | Preço: R$ ${feijao.getPreco()} | Estoque: ${feijao.quantidadeEstoque}")
    println("Produto: ${macarrao.nome} | Preço: R$ ${macarrao.getPreco()} | Estoque: ${macarrao.quantidadeEstoque}")



    arroz.aplicarDesconto(10.0)

    println("Novo preço do arroz: R$ ${arroz.getPreco()}")

    arroz.aplicarDesconto(10.0)


    val leite = ProdutoPerecivel(
        "Leite",
        3.50,
        20,
        "2026/06/31"
    )

    println("Leite vencido? ${leite.estaVencido("2026/06/22")}")



    val vendedor = Vendedor("João", 4000.0, 6000.0)
    val gerente = Gerente("Maria", 7000.0, 3000.0)
    val vendedor2 = Vendedor("Carlos", 5500.0, 7000.0)

    val funcionarios = listOf<FuncionarioBase>(
        vendedor,
        gerente,
        vendedor2
    )

    var totalFolha = 0.0

    for (funcionario in funcionarios) {

        val salario = funcionario.calcularSalario()

        println("${funcionario.nome} -> R$ $salario")

        totalFolha += salario
    }

    println("Total da folha: R$ $totalFolha")



    val carrinho = listOf(
        arroz,
        macarrao,
        leite
    )

    finalizarVenda(carrinho, vendedor)
}