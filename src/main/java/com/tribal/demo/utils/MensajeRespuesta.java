package com.tribal.demo.utils;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MensajeRespuesta  implements Serializable {
    private String mensaje;
    private Object data;
}
