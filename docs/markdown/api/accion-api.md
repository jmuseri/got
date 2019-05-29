# API Services Documentation - Accion

[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md) | [Back to Api Services](/docs/markdown/api.md)

---

### Method
![get](../../images/method-get.png "get")

### Description
Return a complete objects list.

### URL
    http://localhost:8080/accion
### Parameters
- void

### Response
- 200 

    ``` 
    Request completed successfully.
    ```

- Example
   
    
        {
            "db_name": "string",
            "db_uuid": 0,
            "disk_format_version": 0,
            "disk_size": 0,
            "instance_start_time": "string",
            "state": "string",
            "update_seq": "string"
        }
    

- 404  

    ``` 
    Not Found. Request not found.
    ```  


---
[Go to Top](#markdown-header-api-services-documentation-accion)  
[Back to Home](/README.md) | [Back to General Doc](/docs/readme.md) | [Back to Delivery](/docs/markdown/delivery.md) | [Back to Api Services](/docs/markdown/api.md)