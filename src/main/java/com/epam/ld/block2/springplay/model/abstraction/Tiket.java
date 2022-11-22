package com.epam.ld.block2.springplay.model.abstraction;

public interface Tiket {
    //enum Category { STANDARD, PREMIUM, BAR }

    /**
     * Ticket Id. UNIQUE.
     * @return Ticket Id.
     */
    Long getId();
    void setId(Long id);

}
