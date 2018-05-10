package br.com.zup.hackatontimesheet.business_models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaoh on 06/05/2018.
 */

public class Employee {

    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("locationId")
    @Expose
    String locationId;
    @SerializedName("expenseApprover")
    @Expose
    String expenseApprover;
    @SerializedName("timeApprover")
    @Expose
    String timeApprover;
    @SerializedName("classificationId")
    @Expose
    String classificationId;
    @SerializedName("projects")
    @Expose
    List<Project> projects;

    public String[] getProjectNameList() {
        if(projects == null) {
            return new String[0];
        }
        List<String> result = new ArrayList<>();
        for(Project p : projects) {
            result.add(p.getName());
        }

        return result.toArray(new String[projects.size()]);
    }

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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getExpenseApprover() {
        return expenseApprover;
    }

    public void setExpenseApprover(String expenseApprover) {
        this.expenseApprover = expenseApprover;
    }

    public String getTimeApprover() {
        return timeApprover;
    }

    public void setTimeApprover(String timeApprover) {
        this.timeApprover = timeApprover;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
