package com.sa.bbva.got.bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.bbva.got.model.CampoDisponible;
import com.sa.bbva.got.model.Comision;
import com.sa.bbva.got.model.EstadoTramite;
import com.sa.bbva.got.model.Sector;
import com.sa.bbva.got.model.TipoTramite;
import com.sa.bbva.got.service.parametria.CampoDisponibleService;
import com.sa.bbva.got.service.parametria.ComisionService;
import com.sa.bbva.got.service.parametria.EstadoTramiteService;
import com.sa.bbva.got.service.parametria.SectorService;
import com.sa.bbva.got.service.parametria.TipoTramiteService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private SectorService sectorService;
    private ComisionService comisionService;
    private EstadoTramiteService estadoTramiteService;
    private CampoDisponibleService campoDisponibleService;
    private TipoTramiteService tipoTramiteService;

    private Logger log = LogManager.getLogger(SpringJpaBootstrap.class);

    @Autowired
    public void setSectorService(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @Autowired
    public void setComisionService(ComisionService comisionService) {
        this.comisionService = comisionService;
    }

    @Autowired
    public void setEstadoTramiteService(EstadoTramiteService estadoTramiteService) {
        this.estadoTramiteService = estadoTramiteService;
    }

    @Autowired
    public void setCampoDisponibleService(CampoDisponibleService campoDisponibleService) {
        this.campoDisponibleService = campoDisponibleService;
    }

    @Autowired
    public void setTipoTramiteService(TipoTramiteService tipoTramiteService) {
        this.tipoTramiteService = tipoTramiteService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadSectores();
        loadComisiones();
        loadEstadosTramite();
        loadCamposDisponible();
        loadTipoTramite();
    }

    private void loadSectores() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Sector>> typeReference = new TypeReference<List<Sector>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/sectores.json");
        try {
            List<Sector> sectors = mapper.readValue(inputStream, typeReference);
            sectorService.save(sectors);
            log.info("Sectores Saved!");
        } catch (IOException e) {
            log.error("Unable to save sectores: " + e.getMessage());
        }
    }

    private void loadComisiones() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Comision>> typeReference = new TypeReference<List<Comision>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/comisiones.json");
        try {
            List<Comision> comisiones = mapper.readValue(inputStream, typeReference);
            comisionService.save(comisiones);
            log.info("Comisiones Saved!");
        } catch (IOException e) {
            log.error("Unable to save comisiones: " + e.getMessage());
        }
    }

    private void loadEstadosTramite() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<EstadoTramite>> typeReference = new TypeReference<List<EstadoTramite>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/estadosTramite.json");
        try {
            List<EstadoTramite> estadosTramite = mapper.readValue(inputStream, typeReference);
            estadoTramiteService.save(estadosTramite);
            log.info("EstadosTramite Saved!");
        } catch (IOException e) {
            log.error("Unable to save estadosTramite: " + e.getMessage());
        }
    }

    private void loadCamposDisponible() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<CampoDisponible>> typeReference = new TypeReference<List<CampoDisponible>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/camposDisponible.json");
        try {
            List<CampoDisponible> campoDisponible = mapper.readValue(inputStream, typeReference);
            campoDisponibleService.save(campoDisponible);
            log.info("CamposDisponible Saved!");
        } catch (IOException e) {
            log.error("Unable to save camposDisponible: " + e.getMessage());
        }
    }

    private void loadTipoTramite() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<TipoTramite>> typeReference = new TypeReference<List<TipoTramite>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/tipoTramite.json");
        try {
            List<TipoTramite> tipoTramite = mapper.readValue(inputStream, typeReference);
            tipoTramiteService.save(tipoTramite);
            log.info("TipoTramite Saved!");
        } catch (IOException e) {
            log.error("Unable to save tipoTramite: " + e.getMessage());
        }
    }
}
