package com.gduranti.processengine.model;

import java.util.ArrayList;
import java.util.List;

public class ProcessType {

    private Integer id;
    private List<ProcessTypeVersion> versions;

    public ProcessType() {
    }

    public ProcessType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProcessTypeVersion getCurrentVersion() {
        return getVersions().isEmpty() ? null : getVersions().get(getVersions().size() - 1);
    }

    public List<ProcessTypeVersion> getVersions() {
        return versions != null ? versions : (versions = new ArrayList<>());
    }

    public void setVersions(List<ProcessTypeVersion> versions) {
        this.versions = versions;
    }

    public ProcessTypeVersion newVersion() {
        ProcessTypeVersion newVersion = new ProcessTypeVersion(this, getNextVersion());
        getVersions().add(newVersion);
        return newVersion;
    }

    private int getNextVersion() {
        ProcessTypeVersion currentVersion = getCurrentVersion();
        return currentVersion == null ? 1 : currentVersion.getVersion() + 1;
    }

    @Override
    public boolean equals(Object obj) {
        return id.equals(((ProcessType) obj).id);
    }

}
