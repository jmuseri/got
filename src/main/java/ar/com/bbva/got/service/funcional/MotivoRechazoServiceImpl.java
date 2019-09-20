package ar.com.bbva.got.service.funcional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bbva.got.model.MotivoRechazo;
import ar.com.bbva.got.repository.MotivoRechazoRepository;

@Service
public class MotivoRechazoServiceImpl implements MotivoRechazoService {

	
	private MotivoRechazoRepository motivoRechazoRepository;

	@Autowired
	public void setRepository(MotivoRechazoRepository motivoRechazoRepository) {
		this.motivoRechazoRepository = motivoRechazoRepository;
	}
	
	public MotivoRechazoServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Iterable<MotivoRechazo> listAll() {
		return motivoRechazoRepository.findAll();
	}

	@Override
	public Iterable<MotivoRechazo> listByTipoTramiteId(Integer tipoTramiteId) {
		return motivoRechazoRepository.findAllByTipoTramiteId(tipoTramiteId);
	}

	@Override
	public MotivoRechazo getById(Integer id) {
		return motivoRechazoRepository.findById(id).orElse(null);
		
	}

	@Override
	public MotivoRechazo save(MotivoRechazo motivoRechazo) {
		return motivoRechazoRepository.save(motivoRechazo);
	}

	@Override
	public void save(List<MotivoRechazo> motivosRechazo) {
		motivoRechazoRepository.saveAll(motivosRechazo);

	}

	@Override
	public void delete(Integer id) {
		motivoRechazoRepository.deleteById(id);

	}

}
