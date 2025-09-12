package br.edu.infnet.elberthapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.elberthapi.model.domain.Produto;
import br.edu.infnet.elberthapi.model.domain.TipoProduto;
import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.domain.exceptions.VendedorNaoEncontradoException;
import br.edu.infnet.elberthapi.model.service.ProdutoService;
import br.edu.infnet.elberthapi.model.service.VendedorService;

@Order(2)
@Component
public class ProdutoLoader implements ApplicationRunner {
    
    private final ProdutoService produtoService;
    private final VendedorService vendedorService;
    
    public ProdutoLoader(ProdutoService produtoService, VendedorService vendedorService) {
        this.produtoService = produtoService;
        this.vendedorService = vendedorService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        FileReader arquivo = new FileReader("produtos.txt");
        BufferedReader leitura = new BufferedReader(arquivo);
        
        String linha = leitura.readLine();
        String[] campos = null;

        System.out.println("[ProdutoLoader] Iniciando carregamento de produtos do arquivo...");
        
        while(linha != null) {
            
            campos = linha.split(";");
            
            String codigo = campos[0];
            String nome = campos[1];
            TipoProduto tipo = TipoProduto.valueOf(campos[2]);
            BigDecimal valor = new BigDecimal(campos[3]);
            String cpfVendedor = campos[4];

            Vendedor responsavel = null;
            
            try {
            	responsavel = vendedorService.obterPorCpf(cpfVendedor);
            	if(responsavel == null) {
                    System.err.println("  [ERRO] vendedor com CPF " + cpfVendedor + " não encontrado para o produto " + nome);
    	            linha = leitura.readLine();
                	continue;
            	}
            	
			} catch (VendedorNaoEncontradoException e) {
	            linha = leitura.readLine();
            	continue;
			}

            Produto produto = new Produto();
            produto.setCodigo(codigo);
            produto.setNome(nome);
            produto.setTipo(tipo);
            produto.setValor(valor);
            
            produto.setVendedor(responsavel);
            
            try {
            	produtoService.incluir(produto);
                System.out.println("  [OK] Produto " + produto.getNome() + " incluído com sucesso.");
            } catch (Exception e) {
                System.err.println("  [ERRO] Problema na inclusão do produto " + produto.getNome() + ": " + e.getMessage());
            }

            linha = leitura.readLine();
        }
        
        System.out.println("[ProdutoLoader] Carregamento concluído.");

        List<Produto> produtos = produtoService.obterLista();
        System.out.println("--- Produtos Carregados ---");
        produtos.forEach(System.out::println);
        System.out.println("-----------------------------");
        
        leitura.close();
    }
}