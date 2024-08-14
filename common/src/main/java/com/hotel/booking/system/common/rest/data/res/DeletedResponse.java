package com.hotel.booking.system.common.rest.data.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletedResponse {

    private Boolean deleted;
    private String message;
}
