package com.greneta.generatorwebserver.constant;

public enum ApiUrl {

    BASE_LOCAL_URL("http://localhost:8081");

    final String url;

    ApiUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
