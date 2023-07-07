package com.polling.station.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ASSOCIATE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_ASSOCIATE", nullable = false, precision = 4)
    private Long codAssociate;

    @Column(name = "ASSOCIATE_NAME", nullable = false, length = 150)
    private String associateName;

    @Column(name = "ASSOCIATE_CPF", nullable = false, length = 11)
    private String cpf;

//    @OneToOne(mappedBy = "associate")
//    private VotingByAgenda votingByAgenda;

}
