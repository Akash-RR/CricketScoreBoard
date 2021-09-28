package Core;

public class Player {

    private Integer totalRunsScored = 0;

    private Integer totalFoursScored = 0;

    private Integer totalSixScored = 0;

    private Integer ballsPlayed = 0;

    private Team team;

    private Status playerStatus = Status.Waiting;

    private Integer battingOrder;

    private Integer dotBalls = 0;

    private Integer wicketsTaken = 0;

    private Integer totalBalls = 0;

    private Integer runsGiven = 0;

    Player(Team team){
        this.team = team;
    }

    public void runsScored(Integer runsScored){
        if(runsScored == 4){
            this.totalFoursScored++;
        } else if(runsScored == 6){
            this.totalSixScored++;
        }
        totalRunsScored += runsScored;
        team.setTotalTeamScore(team.getTotalTeamScore() + runsScored);
        ballsPlayed++;
    }

    public Integer getTotalRunsScored() {
        return totalRunsScored;
    }

    public void setTotalRunsScored(Integer totalRunsScored) {
        this.totalRunsScored = totalRunsScored;
    }

    public Integer getTotalFoursScored() {
        return totalFoursScored;
    }

    public void setTotalFoursScored(Integer totalFoursScored) {
        this.totalFoursScored = totalFoursScored;
    }

    public Integer getTotalSixScored() {
        return totalSixScored;
    }

    public void setTotalSixScored(Integer totalSixScored) {
        this.totalSixScored = totalSixScored;
    }

    public Integer getBallsPlayed() {
        return ballsPlayed;
    }

    public void setBallsPlayed(Integer ballsPlayed) {
        this.ballsPlayed = ballsPlayed;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Status getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(Status playerStatus) {
        this.playerStatus = playerStatus;
    }

    public Integer getBattingOrder() {
        return battingOrder;
    }

    public void setBattingOrder(Integer battingOrder) {
        this.battingOrder = battingOrder;
    }

    public Integer getDotBalls() {
        return dotBalls;
    }

    public void setDotBalls(Integer dotBalls) {
        this.dotBalls = dotBalls;
    }

    public Integer getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(Integer wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public Integer getTotalBalls() {
        return totalBalls;
    }

    public void setTotalBalls(Integer totalBalls) {
        this.totalBalls = totalBalls;
    }

    public Integer getRunsGiven() {
        return runsGiven;
    }

    public void setRunsGiven(Integer runsGiven) {
        this.runsGiven = runsGiven;
    }

    public void runsScoredOnBowling(Integer runs){
        this.runsGiven += runs;
    }

    public void wicketTaken() {
        this.wicketsTaken++;
    }

    public void ballThrown() {
        this.totalBalls++;
    }
}
