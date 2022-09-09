package com.gabriel.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.os.model.Cliente;
import com.gabriel.os.model.OS;
import com.gabriel.os.model.Tecnico;
import com.gabriel.os.model.enums.Prioridade;
import com.gabriel.os.model.enums.Status;
import com.gabriel.os.repositories.ClienteRepository;
import com.gabriel.os.repositories.OSRepository;
import com.gabriel.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public void instaciaDB() {
		Tecnico t1 = new Tecnico(null, "Doidinho", "165.959.522-38", "(88) 98888-9999");
		Tecnico t2 = new Tecnico(null, "Pirado", "165.959.522-38", "(88) 98888-9999");
		Cliente c1 = new Cliente(null, "Maluco", "899.135.369-01", "(21) 0303-20102");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}
}
