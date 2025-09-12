package br.edu.infnet.elberthapi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.domain.exceptions.VendedorInvalidoException;
import br.edu.infnet.elberthapi.model.domain.exceptions.VendedorNaoEncontradoException;
import br.edu.infnet.elberthapi.model.repository.VendedorRepository;
import jakarta.transaction.Transactional;

@Service
public class VendedorService implements CrudService<Vendedor, Integer> {
	
	private final VendedorRepository vendedorRepository;
	
	public VendedorService(VendedorRepository vendedorRepository) {
		this.vendedorRepository = vendedorRepository;
	}
	
	private void validar(Vendedor vendedor) {
		if(vendedor == null) {
			throw new IllegalArgumentException("O vendedor não pode estar nulo!");
		}

		if(vendedor.getNome() == null || vendedor.getNome().trim().isEmpty()) {
			throw new VendedorInvalidoException("O nome do vendedor é uma informação obrigatória!");
		}		
	}
	
	@Override
	@Transactional
	public Vendedor incluir(Vendedor vendedor) {
		
		validar(vendedor);
		
		if(vendedor.getId() != null && vendedor.getId() > 0) {
			throw new IllegalArgumentException("Um novo vendedor não pode ter um ID na inclusão!");			
		}

		return vendedorRepository.save(vendedor);
	}

	@Override
	@Transactional
	public Vendedor alterar(Integer id, Vendedor vendedor) {

		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");			
		}
		
		validar(vendedor);
		
		obterPorId(id);
		
		vendedor.setId(id);
		
		return vendedorRepository.save(vendedor);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		
		Vendedor vendedor = obterPorId(id);

		vendedorRepository.delete(vendedor);
	}

	@Transactional
	public Vendedor inativar(Integer id) {

		if(id == null || id == 0) {
			throw new IllegalArgumentException("O ID para inativação não pode ser nulo/zero!");			
		}
		
		Vendedor vendedor = obterPorId(id);
		
		if(!vendedor.isAtivo()) {
			System.out.println("Vendedor " + vendedor.getNome() + " já está inativo!");
			return vendedor;
		}
		
		vendedor.setAtivo(false);

		return vendedorRepository.save(vendedor);
	}
	
	@Override
	public List<Vendedor> obterLista() {
		
		return vendedorRepository.findAll();
	}

	@Override
	public Vendedor obterPorId(Integer id) {

		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID para busca não pode ser nulo/zero!");			
		}

		return vendedorRepository.findById(id).orElseThrow(() -> new VendedorNaoEncontradoException("O vendedor com ID " + id + " não foi encontrado!"));
	}

	public Vendedor obterPorCpf(String cpf) {

		return vendedorRepository.findByCpf(cpf).orElseThrow(() -> new VendedorNaoEncontradoException("O vendedor com CPF " + cpf + " não foi encontrado!"));
	}
}