package utilities;

import entities.Player;

public interface Interactable {
    void interact(Player player);

    String getDescription();
}
