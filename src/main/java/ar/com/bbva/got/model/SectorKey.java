package ar.com.bbva.got.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@Embeddable
public class SectorKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "canal")
    String canal ;

    @Column(name = "sector")
    String sector;

    public SectorKey() {
    }

	public SectorKey(String canal, String sector) {
		this.canal = canal;
		this.sector = sector;
	}

    

}
