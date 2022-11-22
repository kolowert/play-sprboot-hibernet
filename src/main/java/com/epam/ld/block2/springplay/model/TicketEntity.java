package com.epam.ld.block2.springplay.model;

import com.epam.ld.block2.springplay.model.abstraction.Tiket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="TBL_TICKET")
public class TicketEntity implements Tiket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="event_id")
    private long eventId;

    @Column(name="user_id")
    private long userId;

    @Column(name="category")
    private Category category;

    @Column(name="place")
    private int place;

}
