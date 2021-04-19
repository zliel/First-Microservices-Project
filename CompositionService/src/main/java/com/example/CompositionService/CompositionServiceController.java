package com.example.CompositionService;

import com.example.CompositionService.models.Composition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CompositionServiceController {

    @GetMapping("/api/{compositionId}")
    public Mono<Composition> getCompositionById(@PathVariable("compositionId") String compositionId) {
        return Mono.just(
                new Composition(compositionId, "Nocturne No. 0", "Choppin")
        );
    }
}
