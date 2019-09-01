package by.yaromka.aviation.safety.domain.util.page;

import lombok.Value;

import java.util.List;

@Value
public class Page<T> {
    private Long current;
    private Long last;
    private Long size;
    private List<T> content;
}
