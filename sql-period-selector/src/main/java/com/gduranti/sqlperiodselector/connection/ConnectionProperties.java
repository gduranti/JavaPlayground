package com.gduranti.sqlperiodselector.connection;

public class ConnectionProperties {

    private String url;
    private String user;
    private String password;

    public static ConnectionProperties connectTo(String url) {
        ConnectionProperties attributes = new ConnectionProperties();
        attributes.url = url;
        return attributes;
    }

    public ConnectionProperties withUser(String user) {
        this.user = user;
        return this;
    }

    public ConnectionProperties withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
