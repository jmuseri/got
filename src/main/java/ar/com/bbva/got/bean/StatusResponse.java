package ar.com.bbva.got.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class StatusResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private String message;
    private String description;

    public StatusResponse(String status, String message, String description) {
        super();
        this.status = status;
        this.message = message;
        this.description = description;
    }

}