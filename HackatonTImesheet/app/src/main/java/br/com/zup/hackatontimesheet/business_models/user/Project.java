package br.com.zup.hackatontimesheet.business_models.user;

import com.google.gson.annotations.Expose;

/**
 * Created by joaoh on 07/05/2018.
 */

public class Project {

    @Expose
    String id;
    @Expose
    String name;
    @Expose
    String businessUnitId;
    @Expose
    String managerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
