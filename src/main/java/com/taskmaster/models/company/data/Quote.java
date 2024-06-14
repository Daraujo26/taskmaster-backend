package com.taskmaster.models.company.data;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import com.taskmaster.models.company.data.usage.ContractItem;

@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String jobTitle;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "quote_id")
    private List<ContractItem> contractItems;

    private String message;
    private String status;
    private Date dateIssued;
    private Date dateUpdated;
    private Long teamId;

    // Getters and setters...
}
