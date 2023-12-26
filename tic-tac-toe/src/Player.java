public class Player {
    private String playerName;
    private int playerId;
    private Character playerCharacter;

    public Player(String name, Character symbol, int id) {
        playerName = name;
        System.out.println(name);
        playerCharacter = symbol;
        playerId = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Character getPlayerCharacter() {
        return playerCharacter;
    }

}
