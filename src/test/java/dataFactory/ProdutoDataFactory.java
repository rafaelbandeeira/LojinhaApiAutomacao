package dataFactory;

import pojo.ComponentePOJO;
import pojo.ProdutoPOJO;

import java.util.Arrays;

public class ProdutoDataFactory {
    public static ProdutoPOJO criarProdutoComumComOValorIgualA(
            double valor
    ) {
        ProdutoPOJO produto = new ProdutoPOJO();
        ComponentePOJO componente = new ComponentePOJO();

        produto.setProdutoCores(Arrays.asList("azul"));
        produto.setProdutoNome("Samsung S10");
        produto.setProdutoValor(0.00);
        produto.setProdutoUrlMock("");
        componente.setComponenteNome("Capa");
        componente.setComponenteQuantidade(1);
        produto.setComponentes(Arrays.asList(componente));

        return produto;
    }
}
