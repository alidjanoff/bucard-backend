package com.example.bucard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDetailsDto {
        private String messageBody;
        private int listId;
        private String sendingSource;
        private String smsCampaignName;
}
