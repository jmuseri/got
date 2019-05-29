package com.sa.bbva.got.bootstrap;

import com.sa.bbva.got.model.Product;
import com.sa.bbva.got.model.Sector;
import com.sa.bbva.got.repositories.ProductRepository;
import com.sa.bbva.got.repositories.SectorRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;
    private SectorRepository sectorRepository;

    private Logger log = LogManager.getLogger(SpringJpaBootstrap.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setSectorRepository(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadSectors();
    }

    private void loadSectors() {
        Sector sector1 = new Sector();
        sector1.setCanal("canal 1");
        sector1.setSector("sector 1");
        sector1.setDescription("description 1");
        sector1.setUsuAlta("usu1");
        sector1.setFechaAlta(new Date());
        sector1.setUsuModif("usu1");
        sector1.setFechaModif(new Date());
        sectorRepository.save(sector1);
        log.info("Saved sector1 - id: " + sector1.getId());

        Sector sector2 = new Sector();
        sector2.setCanal("canal 2");
        sector2.setSector("sector 2");
        sector2.setDescription("description 2");
        sector2.setUsuAlta("usu2");
        sector2.setFechaAlta(new Date());
        sector2.setUsuModif("usu2");
        sector2.setFechaModif(new Date());
        sectorRepository.save(sector2);
        log.info("Saved sector2 - id: " + sector2.getId());
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
