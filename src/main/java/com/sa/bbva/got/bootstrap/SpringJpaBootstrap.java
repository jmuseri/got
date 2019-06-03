package com.sa.bbva.got.bootstrap;

import com.sa.bbva.got.model.Product;
import com.sa.bbva.got.model.Sector;
import com.sa.bbva.got.model.Comision;
import com.sa.bbva.got.model.EstadoTramite;
import com.sa.bbva.got.repository.ProductRepository;
import com.sa.bbva.got.service.parametria.SectorService;
import com.sa.bbva.got.service.parametria.ComisionService;
import com.sa.bbva.got.service.parametria.EstadoTramiteService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.InputStream;
import java.util.List;
import java.io.IOException;
import java.math.BigDecimal;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;
    private SectorService sectorService;
    private ComisionService comisionService;
    private EstadoTramiteService estadoTramiteService;

    private Logger log = LogManager.getLogger(SpringJpaBootstrap.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadSectores();
        loadComisiones();
        loadEstadosTramite();
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
            System.out.println("Sectores Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save sectores: " + e.getMessage());
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
            System.out.println("Comisiones Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save comisiones: " + e.getMessage());
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
            System.out.println("EstadosTramite Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save estadosTramite: " + e.getMessage());
        }
    }

    private void loadProducts() {
        Product shirt = new Product();
        shirt.setDescription("Shirt");
        shirt.setPrice(new BigDecimal("18.95"));
        shirt.setProductId("235268845711068308");
        productRepository.save(shirt);
        log.info("Saved Shirt - id: " + shirt.getId());

        Product mug = new Product();
        mug.setDescription("Mug");
        mug.setProductId("168639393495335947");
        mug.setPrice(new BigDecimal("11.95"));
        productRepository.save(mug);
        log.info("Saved Mug - id:" + mug.getId());
    }

}
