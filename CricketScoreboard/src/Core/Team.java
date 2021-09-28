package Core;

import java.util.*;

public class Team {

    private Integer teamNumber;

    private Integer numPlayers;

    private List<Player> players = new ArrayList<>();

    private List<Integer> playersBowlingOrder = new ArrayList<>();

    private Integer totalTeamScore = 0;

    private Double overBeingPlayed = 0.0;

    private Integer wideBallsPlayed = 0;

    private Integer noBalls = 0;

    private Player playerOnStrikerEnd;

    private Player playerOnNonStrikerEnd;

    private Player playerBowling;

    public Team(Integer teamNumber, Integer numPlayers){
        this.teamNumber = teamNumber;
        this.numPlayers = numPlayers;
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(this));
        }
    }

    public void setBattingOrder(List<Integer> battingOrder){
        final int[] i = {0};
        players.forEach(player -> {
            player.setBattingOrder(battingOrder.get(i[0]));
            i[0]++;
        });
        this.players.sort((o1, o2) -> {
            if (o1.getBattingOrder().equals(o2.getBattingOrder()))
                return 0;
            return o1.getBattingOrder() < o2.getBattingOrder() ? -1 : 1;
        });

        Optional<Player> nextPlayer = players.stream().filter(player-> player.getPlayerStatus().equals(Status.Waiting)).findFirst();
        nextPlayer.ifPresent(player -> this.playerOnStrikerEnd = player);
        this.playerOnStrikerEnd.setPlayerStatus(Status.Playing);
        nextPlayer = players.stream().filter(player-> player.getPlayerStatus().equals(Status.Waiting)).findFirst();
        nextPlayer.ifPresent(player -> this.playerOnNonStrikerEnd = player);
        this.playerOnNonStrikerEnd.setPlayerStatus(Status.Playing);
    }

    public void setBowlingOrder(List<Integer> bowlingOrder){
        this.playersBowlingOrder = bowlingOrder;
        Optional<Player> nextPlayer = players.stream().filter(player-> player.getBattingOrder().equals(0)).findFirst();
        nextPlayer.ifPresent(player -> this.playerBowling = player);
    }


    public Integer getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer getTotalTeamScore() {
        return totalTeamScore;
    }

    public void setTotalTeamScore(Integer totalTeamScore) {
        this.totalTeamScore = totalTeamScore;
    }

    public Double getOverBeingPlayed() {
        return  (int) (overBeingPlayed/6) + (overBeingPlayed%6)/10;
    }

    public void setOverBeingPlayed(Double overBeingPlayed) {
        this.overBeingPlayed = overBeingPlayed;
    }

    public void runsScored(Integer run) {
        incrementBallsPlayed();
        this.playerOnStrikerEnd.runsScored(run);
        if(run%2==1){
            strikeChange();
        }
    }

    public void strikeChange(){
        Player temp = this.playerOnStrikerEnd;
        this.playerOnStrikerEnd = this.playerOnNonStrikerEnd;
        this.playerOnNonStrikerEnd = temp;
    }

    public void wideBallPlayed() {
        this.wideBallsPlayed++;
        this.totalTeamScore++;
    }

    public void playerOut() {
        incrementBallsPlayed();
        this.playerOnStrikerEnd.setBallsPlayed(this.playerOnStrikerEnd.getBallsPlayed()+1);
        this.playerOnStrikerEnd.setPlayerStatus(Status.Out);
        Optional<Player> nextPlayer = players.stream().filter(player-> player.getPlayerStatus().equals(Status.Waiting)).findFirst();
        nextPlayer.ifPresent(player -> this.playerOnStrikerEnd = player);
    }

    public Player getPlayerOnStrikerEnd() {
        return playerOnStrikerEnd;
    }

    public void setPlayerOnStrikerEnd(Player playerOnStrikerEnd) {
        this.playerOnStrikerEnd = playerOnStrikerEnd;
    }

    public Player getPlayerOnNonStrikerEnd() {
        return playerOnNonStrikerEnd;
    }

    public void setPlayerOnNonStrikerEnd(Player playerOnNonStrikerEnd) {
        this.playerOnNonStrikerEnd = playerOnNonStrikerEnd;
    }

    public Integer getTotalWickets(){
        final int[] i = {0};
        players.forEach(player -> {
            if(player.getPlayerStatus().equals(Status.Out))
                i[0]++;
        });
        return i[0];
    }

    public Integer getWideBallsPlayed() {
        return wideBallsPlayed;
    }

    public void setWideBallsPlayed(Integer wideBallsPlayed) {
        this.wideBallsPlayed = wideBallsPlayed;
    }

    public void incrementBallsPlayed() {
        this.overBeingPlayed++;
    }

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }

    public Integer getNoBalls() {
        return noBalls;
    }

    public void setNoBalls(Integer noBalls) {
        this.noBalls = noBalls;
    }

    public void noBall() {
        this.totalTeamScore++;
        this.noBalls++;
    }

    public void runsScoredOnBowling(Integer runs) {
        this.playerBowling.runsScoredOnBowling(runs);
    }

    public void wicketTaken(){
        this.playerBowling.wicketTaken();
    }

    public void ballThrown() {
        this.playerBowling.ballThrown();
    }

    public void nextBolwer(int currentOver) {
        Optional<Player> nextPlayer = players.stream().filter(player-> player.getBattingOrder().equals(currentOver%numPlayers)).findFirst();
        nextPlayer.ifPresent(player -> this.playerBowling = player);
    }

    public Player getPlayerBowling() {
        return playerBowling;
    }

    public void setPlayerBowling(Player playerBowling) {
        this.playerBowling = playerBowling;
    }

    public Integer getTotalWicketsTaken() {
        final int[] i = {0};
        players.forEach(player -> {
                i[0]+= player.getWicketsTaken();
        });
        return i[0];
    }
}
