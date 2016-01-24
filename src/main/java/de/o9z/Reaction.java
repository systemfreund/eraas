package de.o9z;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.URI;

@RequiredArgsConstructor
@Getter
public class Reaction {

    @JsonIgnore
    private final URI uri;

    private final String emoji;

    private final int count;

    public Reaction add() {
        return new Reaction(uri, emoji, count + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reaction reaction = (Reaction) o;

        if (!uri.equals(reaction.uri)) return false;
        return emoji.equals(reaction.emoji);

    }

    @Override
    public int hashCode() {
        int result = uri.hashCode();
        result = 31 * result + emoji.hashCode();
        return result;
    }
}
