package com.gabriel.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.os.model.Pessoa;
import com.gabriel.os.model.Cliente;
import com.gabriel.os.model.dto.ClienteDTO;
import com.gabriel.os.repositories.PessoaRepository;
import com.gabriel.os.repositories.ClienteRepository;
import com.gabriel.os.services.exceptions.DataIntegratyViolationException;
import com.gabriel.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " 
						+ Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente create(ClienteDTO objDTO) {
		if(findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException(
					"CPF já cadastrado na base de dados!");
		}
		Cliente newObj = new Cliente(null, objDTO.getNome(), 
				objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);
		
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException(
					"CPF já cadastrado na base de dados!");
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException(""
					+ "Pessoa possui Ordens de Serviço. Não pode ser deletado!!");
		}
		repository.deleteById(id);
	}
	
	private Pessoa findByCPF(ClienteDTO objDto) {
		Pessoa obj = pessoaRepository.findByCPF(objDto.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}
}
