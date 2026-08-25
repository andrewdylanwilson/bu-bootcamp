import java.io.*;
import java.util.ArrayList;

public class GradeAnalyzer {
    public static int invalidLinesSkipped = 0;
    public static int blankLinesSkipped = 0;
    public static int negativeScoresSkipped = 0;
    public static int aboveHundredScoresSkipped = 0;
    
    public static void main(String[] args) {
        String filename = "scores.txt";
        if (args.length > 0) {
            filename = args[0];
        }

        System.out.println("Using score file: " + filename);
        ArrayList<Integer> scores = readScores(filename);

        if (scores.isEmpty()) {
            System.out.println("No valid scores were found, but a report will still be created.");
        }

        double avg = calculateAverage(scores);

        int high = 0;
        int low = 0;

        if (!scores.isEmpty()) {
            high = Integer.MIN_VALUE;
            low = Integer.MAX_VALUE;
            for (int score : scores) {
                if (score > high) {
                    high = score;
                }
                if (score < low) {
                    low = score;
                }
            }
        }

        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;
        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        writeReport(scores, avg, high, low, countA, countB, countC, countD, countF, "report.txt");
    }

    public static ArrayList<Integer> readScores(String filename) {
        invalidLinesSkipped = 0;
        blankLinesSkipped = 0;
        negativeScoresSkipped = 0;
        aboveHundredScoresSkipped = 0;

        ArrayList<Integer> validScores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    blankLinesSkipped++;
                    continue;
                }
                try {
                    int n = Integer.parseInt(line);

                    if (n < 0) {
                        negativeScoresSkipped++;
                        System.out.println("Skipping negative score: " + line);
                        continue;
                    }

                    if (n > 100) {
                        aboveHundredScoresSkipped++;
                        System.out.println("Skipping score above 100: " + line);
                        continue;
                    }

                    validScores.add(n);
                } catch (NumberFormatException e) {
                    invalidLinesSkipped++;
                    System.out.println("Skipping invalid value(s): " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return validScores;
    }

    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (int score : scores) {
            total += score;
        }
        return total / scores.size();
    }

    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   int countA, int countB, int countC, int countD, int countF,
                                   String outputFile) {

        boolean reportWritten = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String header = "=== Grade Analysis Report ===";
            writer.write(header);
            writer.newLine();
            System.out.println(header);

            String spacer = "";
            writer.write(spacer);
            writer.newLine();
            System.out.println(spacer);

            String totalLine = String.format("%-25s %10d", "Total scores processed:", scores.size());
            writer.write(totalLine);
            writer.newLine();
            System.out.println(totalLine);

            String invalidLine = String.format("%-25s %10d", "Non-number lines skipped:", invalidLinesSkipped);
            writer.write(invalidLine);
            writer.newLine();
            System.out.println(invalidLine);

            String blankLine = String.format("%-25s %10d", "Blank lines skipped:", blankLinesSkipped);
            writer.write(blankLine);
            writer.newLine();
            System.out.println(blankLine);

            String negativeLine = String.format("%-25s %10d", "Negative scores skipped:", negativeScoresSkipped);
            writer.write(negativeLine);
            writer.newLine();
            System.out.println(negativeLine);

            String aboveLine = String.format("%-25s %10d", "Scores above 100 skipped:", aboveHundredScoresSkipped);
            writer.write(aboveLine);
            writer.newLine();
            System.out.println(aboveLine);

            int totalInvalidSkipped = invalidLinesSkipped + negativeScoresSkipped + aboveHundredScoresSkipped;
            String totalInvalidLine = String.format("%-25s %10d", "Total invalid skipped:", totalInvalidSkipped);
            writer.write(totalInvalidLine);
            writer.newLine();
            System.out.println(totalInvalidLine);

            String spacer2 = "";
            writer.write(spacer2);
            writer.newLine();
            System.out.println(spacer2);

            String avgLine = String.format("%-25s %10.2f", "Average score:", avg);
            writer.write(avgLine);
            writer.newLine();
            System.out.println(avgLine);

            String highLine = String.format("%-25s %10d", "Highest score:", high);
            writer.write(highLine);
            writer.newLine();
            System.out.println(highLine);

            String lowLine = String.format("%-25s %10d", "Lowest score:", low);
            writer.write(lowLine);
            writer.newLine();
            System.out.println(lowLine);

            String spacer3 = "";
            writer.write(spacer3);
            writer.newLine();
            System.out.println(spacer3);

            String distributionHeader = "Grade distribution:";
            writer.write(distributionHeader);
            writer.newLine();
            System.out.println(distributionHeader);

            String aLine = String.format("%-25s %10d", "A (90-100):", countA);
            writer.write(aLine);
            writer.newLine();
            System.out.println(aLine);

            String bLine = String.format("%-25s %10d", "B (80-89):", countB);
            writer.write(bLine);
            writer.newLine();
            System.out.println(bLine);

            String cLine = String.format("%-25s %10d", "C (70-79):", countC);
            writer.write(cLine);
            writer.newLine();
            System.out.println(cLine);

            String dLine = String.format("%-25s %10d", "D (60-69):", countD);
            writer.write(dLine);
            writer.newLine();
            System.out.println(dLine);

            String fLine = String.format("%-25s %10d", "F (below 60):", countF);
            writer.write(fLine);
            writer.newLine();
            System.out.println(fLine);

            String spacer4 = "";
            writer.write(spacer4);
            writer.newLine();
            System.out.println(spacer4);

            reportWritten = true;
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }

        if (reportWritten) {
            System.out.println("Report written to " + outputFile);
        }
    }
}