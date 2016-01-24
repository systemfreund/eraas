package de.o9z;

import javaslang.control.Option;

import org.springframework.data.domain.Page;

import java.net.URI;

public interface ReactionsService {

    Option<Page<Reaction>> getReactions(URI uri);

    void addReaction(URI uri, String emoji);

}
