package org.example.lcs.repository.entity;

import lombok.*;
import org.example.lcs.common.enums.TransactionEntryType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TRANSACTIONS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Transaction extends TimestampedEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TRANSACTION_ENTRY_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private TransactionEntryType transactionEntryType;

    @Column(name="POINTS_AMOUNT")
    private float pointsAmount;

    @ManyToOne
    @JoinColumn(name = "CASHIER_ID")
    private Cashier cashier;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserAccount userAccount;

}
