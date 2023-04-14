package com.clamos.io.collect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class TopicDTO {
    @NotEmpty
    @Size(min = 1)
    private Set<@Valid @NotEmpty String> topics;
}
