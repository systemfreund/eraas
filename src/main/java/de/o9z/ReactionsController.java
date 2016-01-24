package de.o9z;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javaslang.control.Option;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;

import javax.validation.Valid;

@Controller
@RequestMapping("/reactions")
public class ReactionsController {

    @Autowired
    private ReactionsService reactionsService;

    @RequestMapping(method = GET)
    @ResponseBody
    public ResponseEntity<?> get(@RequestParam URI uri) {
        Option<Page<Reaction>> reactions = reactionsService.getReactions(uri);
        return reactions
                .<ResponseEntity<?>>map(page -> ResponseEntity.ok(page.getContent()))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public ResponseEntity<?> add(@RequestParam URI uri, @Valid AddReaction reaction) {
        reactionsService.addReaction(uri, reaction.emoji);

        return ResponseEntity.noContent().build();
    }

    @Data
    public static class AddReaction {
        @NotEmpty
//        @Emoji
        public String emoji;
    }

}
