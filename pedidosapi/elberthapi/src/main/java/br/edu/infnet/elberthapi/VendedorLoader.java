package br.edu.infnet.elberthapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.elberthapi.model.domain.Endereco;
import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.service.VendedorService;

@Order(1)
@Component
public class VendedorLoader implements ApplicationRunner {
    
    private final VendedorService vendedorService;
    
    public VendedorLoader(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        FileReader arquivo = new FileReader("vendedor.txt");
        BufferedReader leitura = new BufferedReader(arquivo);
        
        String linha = leitura.readLine();
        String[] campos = null;

        System.out.println("[VendedorLoader] Iniciando carregamento de vendedores do arquivo...");
        
        while(linha != null) {
            
            campos = linha.split(";");
            
            Endereco endereco = new Endereco();			
            endereco.setCep(campos[7]);
            endereco.setLogradouro(campos[8]); 
            endereco.setBairro(campos[9]);
            endereco.setLocalidade(campos[10]);
            endereco.setUf(campos[11]);
            endereco.setEstado(campos[12]);

            Vendedor vendedor = new Vendedor();
            vendedor.setNome(campos[0]);
            vendedor.setMatricula(Integer.valueOf(campos[1]));
            vendedor.setSalario(Double.valueOf(campos[2]));
            vendedor.setAtivo(Boolean.valueOf(campos[3]));
            vendedor.setCpf(campos[4]);
            vendedor.setEmail(campos[5]);
            vendedor.setTelefone(campos[6]);
            
            vendedor.setEndereco(endereco);
            
            try {
                vendedorService.incluir(vendedor);
                System.out.println("  [OK] Vendedor " + vendedor.getNome() + " incluído com sucesso.");
            } catch (Exception e) {
                System.err.println("  [ERRO] Problema na inclusão do vendedor " + vendedor.getNome() + ": " + e.getMessage());
            }

            linha = leitura.readLine();
        }
        
        System.out.println("[VendedorLoader] Carregamento concluído.");

        List<Vendedor> vendedores = vendedorService.obterLista();
        System.out.println("--- Vendedores Carregados ---");
        vendedores.forEach(System.out::println);
        System.out.println("-----------------------------");
        
        leitura.close();
    }
}