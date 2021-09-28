import Core.Team;
import Helper.PrintHelper;

import java.util.*;

public class Driver {

    public static void main(String[] args) {

        int players = 5;
        int overs = 2;

        Team T1 = new Team(1, players);
        T1.setBattingOrder(Arrays.asList(1, 2, 3, 4, 5));
        T1.setBowlingOrder(Arrays.asList(1, 2, 3, 4, 5));
        Team T2 = new Team(2, players);
        T2.setBattingOrder(Arrays.asList(1, 2, 3, 4, 5));
        T2.setBowlingOrder(Arrays.asList(1, 2, 3, 4, 5));
        List<String> Over = Arrays.asList("1", "1", "1", "1", "Nb", "W");
        List<String> Over2 = Arrays.asList("Wd", "1", "W");

        playOver(T1,T2, Over, 1);
        playOver(T1,T2, Over2, 2);


        List<String> Over3 = Arrays.asList("6");

        playOver(T2,T1, Over3, 1);

        PrintHelper.printWinningTeam(T1, T2);

    }

    public static void playOver(Team currentBattingTeam, Team currentBowlingTeam, List<String> oversPlayArray, int currentOver){
        currentBowlingTeam.nextBolwer(currentOver);
        oversPlayArray.forEach(run -> {
            if(isNumeric(run)){
                currentBattingTeam.runsScored(Integer.parseInt(run));
                currentBowlingTeam.runsScoredOnBowling(Integer.parseInt(run));
            } else if(run.equals("Wd")){
                currentBattingTeam.wideBallPlayed();
            } else if(run.equals("W")){
                currentBattingTeam.playerOut();
                currentBowlingTeam.wicketTaken();
            } else if(run.equals("Nb")){
                currentBattingTeam.noBall();
            }
            currentBowlingTeam.ballThrown();
        });
        currentBattingTeam.strikeChange();
        PrintHelper.printScoreCard(currentBattingTeam, currentBowlingTeam);
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
