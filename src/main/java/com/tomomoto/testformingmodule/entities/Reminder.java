package com.tomomoto.testformingmodule.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reminder")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "reminder_text")
    @NotNull(message = "Reminder text shouldn't be empty")
    private String reminderText;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
