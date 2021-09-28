package Helper;

import Core.Team;

public class PrintHelper {
    public static void printScoreCard(Team team, Team team2){
        System.out.println("ScoreCard for Core.Team " + team.getTeamNumber() );
        System.out.format("%s%10s%10s%10s%10s\n", "Core.Player Name", "Score", "4s", "6s", "Balls");
        team.getPlayers().forEach(player -> {
            String ch = "";
            if(player.equals(team.getPlayerOnNonStrikerEnd()) || player.equals(team.getPlayerOnStrikerEnd())){
                ch += "*";
            }
            System.out.format(
                    "%s%15s%10s%10s%10s\n",
                    "P"+ player.getBattingOrder()+ ch,
                    player.getTotalRunsScored().toString(),
                    player.getTotalFoursScored().toString(),
                    player.getTotalSixScored().toString(),
                    player.getBallsPlayed().toString());
        });

        System.out.println("Total Score : " + team.getTotalTeamScore() + "/" + team.getTotalWickets());
        System.out.format("Overs : %.1f \n", team.getOverBeingPlayed());
        System.out.format("WideBalls Played : %s \n", team.getWideBallsPlayed());
        System.out.format("NoBalls : %s ", team.getNoBalls());
        System.out.format("Overs : %.1f \n", team.getOverBeingPlayed());
        System.out.println("Wickets Taken " + team2.getTotalWicketsTaken());
    }

    public static void printWinningTeam(Team T1, Team T2){
        if(T1.getTotalTeamScore() > T2.getTotalTeamScore()){
            int diff = T1.getTotalTeamScore() - T2.getTotalTeamScore();
            System.out.println("Core.Team 1 won the match by "+ diff +" runs");
        } else if(T1.getTotalTeamScore() < T2.getTotalTeamScore() ){
            int diff = T2.getTotalTeamScore() - T1.getTotalTeamScore();
            System.out.println("Core.Team 2 won the match by "+ diff +" runs");
        } else{
            System.out.println("Match Tied");
        }
    }
}
