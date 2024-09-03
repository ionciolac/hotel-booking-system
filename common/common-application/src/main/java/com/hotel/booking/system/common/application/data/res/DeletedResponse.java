package com.hotel.booking.system.common.application.data.res;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeletedResponse {

    private Boolean deleted;
    private String message;
}
