package org.olegmell.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ServiceItem {
    public Integer id;
    public String name;
    public List<String> organisationName;
    public Integer count;
}
