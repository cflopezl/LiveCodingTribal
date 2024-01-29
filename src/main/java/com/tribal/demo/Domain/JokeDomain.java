package com.tribal.demo.Domain;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JokeDomain {
    private ArrayList<String> categories;
    private String created_at;
    private String icon_url;
    private String id;
    private String updated_at;
    private String url;
    private String value;
}
