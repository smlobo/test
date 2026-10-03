public class Game implements GameMBean {
    private String playerName;
    private int playerAge;

    @Override
    public void playFootball(String clubName) {
        System.out.println(
          this.playerName + ":" + playerAge + " playing football for " + clubName);
    }

    @Override
    public String getPlayerName() {
        System.out.println("Return playerName " + this.playerName + " : {age=" + 
            this.playerAge + "}");
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        System.out.println("Set playerName to " + playerName + " : {age=" + 
            this.playerAge + "}");
        this.playerName = playerName;
    }

    @Override
    public int getPlayerAge() {
        System.out.println(this.playerName + " is " + this.playerAge + " years old");
        return playerAge;
    }

    @Override
    public void setPlayerAge(int playerAge) {
        System.out.println("Set " + playerName + " age to value " + playerAge);
        this.playerAge = playerAge;
    }
}
