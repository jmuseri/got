package com.sa.bbva.got.bean;

import java.io.Serializable;
import lombok.Data;

@Data
public class ResponseStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String status;

    public ResponseStatus(Long crudId, String status) {
        this.id = crudId;
        this.status = status;
    }

}