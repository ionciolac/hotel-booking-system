package com.hotel.booking.system.hotel.service.data.access.entity;

import com.hotel.booking.system.common.data.access.FeedbackEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room_feedbacks")
@Entity
public class RoomFeedbackEntity extends FeedbackEntity {

    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "rooms_id", referencedColumnName = "id")
    private RoomEntity room;
}
