package com.sa.bbva.got.bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.bbva.got.model.Autorizado;
import com.sa.bbva.got.model.CampoDisponible;
import com.sa.bbva.got.model.Comision;
import com.sa.bbva.got.model.EstadoTramite;
import com.sa.bbva.got.model.Sector;
import com.sa.bbva.got.model.TipoTramite;
import com.sa.bbva.got.model.TipoTramiteCampo;
import com.sa.bbva.got.model.TramiteDetalle;
import com.sa.bbva.got.service.funcional.AutorizadoService;
import com.sa.bbva.got.service.funcional.TramiteDetalleService;
import com.sa.bbva.got.service.parametria.CampoDisponibleService;
import com.sa.bbva.got.service.parametria.ComisionService;
import com.sa.bbva.got.service.parametria.EstadoTramiteService;
import com.sa.bbva.got.service.parametria.SectorService;
import com.sa.bbva.got.service.parametria.TipoTramiteService;
import com.sa.bbva.got.service.parametria.TipoTramiteCampoService;

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
    private TipoTramiteCampoService tipoTramiteCampoService;
    private AutorizadoService autorizadoService;
    private TramiteDetalleService tramiteDetalleService;

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

    @Autowired
    public void setTipoTramiteCampoService(TipoTramiteCampoService tipoTramiteCampoService) {
        this.tipoTramiteCampoService = tipoTramiteCampoService;
    }

    @Autowired
    public void setAutorizadoService(AutorizadoService autorizadoService) {
        this.autorizadoService = autorizadoService;
    }
    
    @Autowired
    public void setTramiteDetalleService(TramiteDetalleService tramiteDetalleService) {
        this.tramiteDetalleService = tramiteDetalleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadSectores();
        loadComisiones();
        loadEstadosTramite();
        loadCamposDisponible();
        loadTipoTramite();
        loadTipoTramiteCampo();
        loadAutorizado();
        loadTramiteDetalle();
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
            List<TipoTramite> tipoTramites = mapper.readValue(inputStream, typeReference);
            tipoTramiteService.save(tipoTramites);
            log.info("TipoTramite Saved!");
        } catch (IOException e) {
            log.error("Unable to save tipoTramites: " + e.getMessage());
        }
    }

    private void loadTipoTramiteCampo() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<TipoTramiteCampo>> typeReference = new TypeReference<List<TipoTramiteCampo>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/tipoTramiteCampo.json");
        try {
            List<TipoTramiteCampo> tipoTramitesCampo = mapper.readValue(inputStream, typeReference);
            tipoTramiteCampoService.save(tipoTramitesCampo);
            log.info("TipoTramiteCampo Saved!");
        } catch (IOException e) {
            log.error("Unable to save tipoTramitesCampo: " + e.getMessage());
        }
    }

    private void loadAutorizado() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Autorizado>> typeReference = new TypeReference<List<Autorizado>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/autorizado.json");
        try {
            List<Autorizado> autorizado = mapper.readValue(inputStream, typeReference);
            autorizadoService.save(autorizado);
            log.info("Autorizado Saved!");
        } catch (IOException e) {
            log.error("Unable to save autorizado: " + e.getMessage());
        }
    }

    private void loadTramiteDetalle() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<TramiteDetalle>> typeReference = new TypeReference<List<TramiteDetalle>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/tramiteDetalle.json");
        try {
            List<TramiteDetalle> tramiteDetalle = mapper.readValue(inputStream, typeReference);
            tramiteDetalleService.save(tramiteDetalle);
            log.info("TramiteDetalle Saved!");
        } catch (IOException e) {
            log.error("Unable to save TramiteDetalle: " + e.getMessage());
        }
    }
}
