package ar.com.bbva.got.bootstrap;

// import java.io.File;
// import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.model.CampoDisponible;
import ar.com.bbva.got.model.Comision;
import ar.com.bbva.got.model.MotivoRechazo;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.model.TipoTramiteCampo;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.model.TramiteAutorizado;
import ar.com.bbva.got.model.TramiteDetalle;
import ar.com.bbva.got.service.funcional.AutorizadoService;
import ar.com.bbva.got.service.funcional.MotivoRechazoService;
import ar.com.bbva.got.service.funcional.TramiteAutorizadoService;
import ar.com.bbva.got.service.funcional.TramiteDetalleService;
import ar.com.bbva.got.service.funcional.TramiteService;
import ar.com.bbva.got.service.parametria.CampoDisponibleService;
import ar.com.bbva.got.service.parametria.ComisionService;
import ar.com.bbva.got.service.parametria.SectorService;
import ar.com.bbva.got.service.parametria.TipoTramiteCampoService;
import ar.com.bbva.got.service.parametria.TipoTramiteService;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private SectorService sectorService;
    private ComisionService comisionService;
    private CampoDisponibleService campoDisponibleService;
    private TipoTramiteService tipoTramiteService;
    private TipoTramiteCampoService tipoTramiteCampoService;
    private AutorizadoService autorizadoService;
    private TramiteService tramiteService;
    private TramiteDetalleService tramiteDetalleService;
    private TramiteAutorizadoService tramiteAutorizadoService;
    private MotivoRechazoService motivoRechazoService;

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

    @Autowired
    public void setTramiteService(TramiteService tramiteService) {
        this.tramiteService = tramiteService;
    }

    @Autowired
    public void setTramiteAutorizadoService(TramiteAutorizadoService tramiteAutorizadoService) {
        this.tramiteAutorizadoService = tramiteAutorizadoService;
    }
    
    
    @Autowired
    public void setMotivoRechazoService(MotivoRechazoService motivoRechazoService) {
        this.motivoRechazoService = motivoRechazoService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadSectores();
        loadComisiones();
        loadCamposDisponible();
        loadTipoTramite();
        loadTipoTramiteCampo();
        loadMotivoRechazo();
        loadTramite();
        loadAutorizado();
        loadTramiteDetalle();
        loadTramiteAutorizado();

    }

    private void loadSectores() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Sector>> typeReference = new TypeReference<List<Sector>>() {
        };
        try {
            // File initialFile = new File("resources/json/sectores.json");
            // InputStream targetStream = new FileInputStream(initialFile);
            InputStream targetStream = TypeReference.class.getResourceAsStream("/json/sectores.json");
            List<Sector> sectors = mapper.readValue(targetStream, typeReference);
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
        try {
            // File initialFile = new File("resources/json/comisiones.json");
            // InputStream targetStream = new FileInputStream(initialFile);
            InputStream targetStream = TypeReference.class.getResourceAsStream("/json/comisiones.json");
            List<Comision> comisiones = mapper.readValue(targetStream, typeReference);
            comisionService.save(comisiones);
            log.info("Comisiones Saved!");
        } catch (IOException e) {
            log.error("Unable to save comisiones: " + e.getMessage());
        }
    }

    private void loadCamposDisponible() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<CampoDisponible>> typeReference = new TypeReference<List<CampoDisponible>>() {
        };
        try {
            // File initialFile = new File("resources/json/camposDisponible.json");
            // InputStream targetStream = new FileInputStream(initialFile);
            InputStream targetStream = TypeReference.class.getResourceAsStream("/json/camposDisponible.json");
            List<CampoDisponible> campoDisponible = mapper.readValue(targetStream, typeReference);
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
        try {
            // File initialFile = new File("resources/json/tipoTramite.json");
            // InputStream targetStream = new FileInputStream(initialFile);
            InputStream targetStream = TypeReference.class.getResourceAsStream("/json/tipoTramite.json");
            List<TipoTramite> tipoTramites = mapper.readValue(targetStream, typeReference);
            
            log.info("tipoTramites: " + tipoTramites.toString());
            
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
        try {
            // File initialFile = new File("resources/json/tipoTramiteCampo.json");
            // InputStream targetStream = new FileInputStream(initialFile);
            InputStream targetStream = TypeReference.class.getResourceAsStream("/json/tipoTramiteCampo.json");
            List<TipoTramiteCampo> tipoTramitesCampo = mapper.readValue(targetStream, typeReference);
            tipoTramiteCampoService.save(tipoTramitesCampo);
            log.info("TipoTramiteCampo Saved!");
        } catch (IOException e) {
            log.error("Unable to save tipoTramitesCampo: " + e.getMessage());
        }
    }

    private void loadTramite() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Tramite>> typeReference = new TypeReference<List<Tramite>>() {
        };
        try {
            // File initialFile = new File("resources/json/tramites.json");
            // InputStream targetStream = new FileInputStream(initialFile);
            InputStream targetStream = TypeReference.class.getResourceAsStream("/json/tramites.json");
            List<Tramite> tramite = mapper.readValue(targetStream, typeReference);
            tramiteService.save(tramite);
            log.info("Tramite Saved!");
        } catch (IOException e) {
            log.error("Unable to save Tramite: " + e.getMessage());
        }
    }

    private void loadAutorizado() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Autorizado>> typeReference = new TypeReference<List<Autorizado>>() {
        };
        try {
            // File initialFile = new File("resources/json/autorizados.json");
            // InputStream targetStream = new FileInputStream(initialFile);
            InputStream targetStream = TypeReference.class.getResourceAsStream("/json/autorizados.json");
            List<Autorizado> autorizado = mapper.readValue(targetStream, typeReference);
            autorizadoService.save(autorizado);
            log.info("Autorizados Saved!");
        } catch (IOException e) {
            log.error("Unable to save autorizados: " + e.getMessage());
        }
    }

    private void loadTramiteDetalle() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<TramiteDetalle>> typeReference = new TypeReference<List<TramiteDetalle>>() {
        };
        try {
            // File initialFile = new File("resources/json/tramitesDetalle.json");
            // InputStream targetStream = new FileInputStream(initialFile);
            InputStream targetStream = TypeReference.class.getResourceAsStream("/json/tramitesDetalle.json");
            List<TramiteDetalle> tramiteDetalle = mapper.readValue(targetStream, typeReference);
            tramiteDetalleService.save(tramiteDetalle);
            log.info("TramiteDetalle Saved!");
        } catch (IOException e) {
            log.error("Unable to save TramiteDetalle: " + e.getMessage());
        }
    }

    private void loadTramiteAutorizado() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<TramiteAutorizado>> typeReference = new TypeReference<List<TramiteAutorizado>>() {
        };
        try {
            // File initialFile = new File("resources/json/tramitesAutorizado.json");
            // InputStream targetStream = new FileInputStream(initialFile);
            InputStream targetStream = TypeReference.class.getResourceAsStream("/json/tramitesAutorizado.json");
            List<TramiteAutorizado> tramiteAutorizado = mapper.readValue(targetStream, typeReference);
            tramiteAutorizadoService.save(tramiteAutorizado);
            log.info("TramiteAutorizado Saved!");
        } catch (IOException e) {
            log.error("Unable to save TramiteAutorizado: " + e.getMessage());
        }
    }
    
    
    
    private void loadMotivoRechazo() {
        /*
         * Read json sector test and write to db
         */
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<MotivoRechazo>> typeReference = new TypeReference<List<MotivoRechazo>>() {
        };
        try {
            // File initialFile = new File("resources/json/tramitesAutorizado.json");
            // InputStream targetStream = new FileInputStream(initialFile);
            InputStream targetStream = TypeReference.class.getResourceAsStream("/json/motivoRechazo.json");
            List<MotivoRechazo> motivosRechazo = mapper.readValue(targetStream, typeReference);
            motivoRechazoService.save(motivosRechazo);
            log.info("MotivosRechazo Saved!");
        } catch (IOException e) {
            log.error("Unable to save MotivosRechazo: " + e.getMessage());
        }
    }
    

}
