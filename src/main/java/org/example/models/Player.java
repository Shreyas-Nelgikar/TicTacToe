package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class Player {

    protected User user;
    protected PlayerType playerType;

    public abstract Cell makeMove(Board board);

}
