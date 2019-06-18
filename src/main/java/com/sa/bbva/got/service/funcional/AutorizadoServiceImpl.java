package com.sa.bbva.got.service.funcional;

import java.util.List;

import com.sa.bbva.got.model.Autorizado;
import com.sa.bbva.got.repository.AutorizadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorizadoServiceImpl implements AutorizadoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private AutorizadoRepository autorizadoRepository;

    @Autowired
    public void setRepository(AutorizadoRepository autorizadoRepository) {
        this.autorizadoRepository = autorizadoRepository;
    }

    @Override
    public Iterable<Autorizado> listAll() {
        logger.debug("listAll called");
        return this.autorizadoRepository.findAll();
    }

    @Override
    public Autorizado getById(Integer id) {
        logger.debug("getById called");
        return this.autorizadoRepository.findById(id).orElse(null);
    }

    @Override
    public Autorizado save(Autorizado autorizado) {
        logger.debug("save called");
        return this.autorizadoRepository.save(autorizado);
    }

    @Override
    public void save(List<Autorizado> autorizado) {
        logger.debug("save called");
        autorizadoRepository.saveAll(autorizado);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        this.autorizadoRepository.deleteById(id);
    }
}
