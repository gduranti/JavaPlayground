package com.gduranti.processengine.model;

import java.util.List;

public class ProcessType {

    private Long id;
    private List<ProcessTypeVersion> versions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessTypeVersion getCurrentVersion() {
        return versions.get(versions.size() - 1);
    }

    public List<ProcessTypeVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<ProcessTypeVersion> versions) {
        this.versions = versions;
    }

}
