package de.o9z;

import javaslang.collection.Stream;
import javaslang.control.Option;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stub implementation
 */
public class InMemoryReactions implements ReactionsService {

    private final Map<URI, Page<Reaction>> reactions = new HashMap<>();

    @Override
    public Option<Page<Reaction>> getReactions(URI id) {
        Page<Reaction> result = this.reactions.get(id);
        return Option.of(result);
    }

    @Override
    public void addReaction(URI uri, String emoji) {
        Page<Reaction> reactions = this.reactions.getOrDefault(uri, new PageImpl<>(new ArrayList<>()));

        Reaction needle = new Reaction(uri, emoji, 1);
        int existing = reactions.getContent().indexOf(needle);
        if (existing != -1) {
            List<Reaction> updated = Stream.ofAll(reactions)
                    .map(reaction -> {
                        if (reaction.equals(needle)) {
                            return reaction.add();
                        } else {
                            return reaction;
                        }
                    }).toJavaList();
            this.reactions.put(uri, new PageImpl<>(updated));
        } else {
            List<Reaction> updated = Stream.ofAll(reactions).append(needle).toJavaList();
            this.reactions.put(uri, new PageImpl<>(updated));
        }
    }

}
