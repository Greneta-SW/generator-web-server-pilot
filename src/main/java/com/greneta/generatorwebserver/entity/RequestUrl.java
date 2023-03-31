package com.greneta.generatorwebserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class RequestUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long urlId;

    @Column(nullable = false)
    String  requestServerUrl;

    RequestUrl(String url) {
        this.requestServerUrl = url;
    }

    public static RequestUrl of(String url) {
        return new RequestUrl(url);
    }
}
