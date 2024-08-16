package com.hotel.booking.system.common.rest.data.res;

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
