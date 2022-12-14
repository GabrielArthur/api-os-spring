package com.gabriel.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.os.model.Cliente;
import com.gabriel.os.model.OS;
import com.gabriel.os.model.Tecnico;
import com.gabriel.os.model.dto.OSDTO;
import com.gabriel.os.model.enums.Prioridade;
import com.gabriel.os.model.enums.Status;
import com.gabriel.os.repositories.OSRepository;
import com.gabriel.os.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OSRepository repository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontado! Id: " + id + ", Tipo: " + OS.class.getName()));
	}

	public List<OS> findAll() {
		return repository.findAll();
	}

	public OS create(@Valid OSDTO obj) throws IllegalAccessException {
		return fromDTO(obj);
	}
	

	public OS update(@Valid OSDTO obj) throws IllegalAccessException {
		findById(obj.getId());
		return fromDTO(obj);
	}

	private OS fromDTO(OSDTO obj) throws IllegalAccessException {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservcões(obj.getObservcões());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));

		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());

		newObj.setTecnico(tec);
		newObj.setCliente(cli);

		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}

}
