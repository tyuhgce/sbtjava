package main.com.homeworks.work1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by god on 8/7/2016.
 */

public class Solver {

    public static void main(String[] args) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("C:\\Users\\god\\IdeaProjects\\sbtjava\\src\\com\\homeworks\\work1\\input.txt"), Charset.forName("UTF-8"));
        } catch (IOException e) {
            System.out.println("there is a some error with read file");
            return;
        }

        int size = Integer.parseInt(lines.get(0).split(" ")[1]);
        IntStream numbers = Arrays.stream(lines.get(1).split(" ")).mapToInt(value -> Integer.parseInt(value));
        //numbers.get().forEach(System.out::println);

        Pair<Integer, Integer> pair = runEngine(size, numbers);
        if (pair == null)
            return;
        System.out.println(pair.getLeft() + " " +  pair.getRight());
    }

    public static Pair<Integer, Integer> runEngine(int param, IntStream numbers)  {
        if (numbers == null || param < 1)
            return null;
        final int[] params = {0, param};

        try {
            IntStream result = numbers.
                    filter(
                            value ->
                            {
                                params[0] += value;
                                if (params[0] < params[1])
                                    return true;
                                else
                                {
                                    params[0] -= value;
                                    return false;
                                }
                            }
                    );
            int[] res = result.toArray();
            return new Pair<>(res.length, IntStream.of(res).reduce((s1, s2) -> s1 + s2).getAsInt());
        } catch (IllegalStateException e) {
            return null;
        }
    }
}
