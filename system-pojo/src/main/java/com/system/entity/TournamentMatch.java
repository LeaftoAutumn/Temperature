// TournamentMatch.java
package com.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TournamentMatch implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID tournamentId;
    private String groupName;
    private UUID player1Id;
    private UUID player2Id;
    private LocalDateTime scheduledTime;
    private String result;
    private UUID winnerId;
    private String status; // scheduled, in_progress, completed
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}