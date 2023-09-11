package effective.item44;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    // 코드 45-1 사전 하나를 훑어 원소 수가 많은 아나그램 그룹들을 출력한다.
    public class Anagrams {
        public void code1 (String[] args) throws IOException {
            File dictionary = new File(args[0]);
            int minGroupSize = Integer.parseInt(args[1]);

            Map<String, Set<String>> groups = new HashMap<>();
            try (Scanner s = new Scanner(dictionary)) {
                while (s.hasNext()) {
                    String word = s.next();
                    groups.computeIfAbsent(alphabetize(word),  (unused) -> new TreeSet<>()).add(word);
                }
            }

            for (Set<String> group : groups.values())
                if (group.size() >= minGroupSize)
                    System.out.println(group.size() + ": " + group);
        }

        private String alphabetize(String s) {
            char[] a = s.toCharArray();
            Arrays.sort(a);
            return new String(a);
        }

        // 코드 45-3 스트림을 적절히 활용하면 깔끔하고 명료해진다.
        public void code2(String[] args) throws IOException {
            Path dictionary = Paths.get(args[0]);
            int minGroupSize = Integer.parseInt(args[1]);

            try (Stream<String> words = Files.lines(dictionary)) {
                words.collect(Collectors.groupingBy(word -> alphabetize(word)))
                .values().stream()
                .filter(group -> group.size() >= minGroupSize)
                .forEach(g -> System.out.println(g.size() + ": " + g));
            }
        }
    }


    public static void main(String[] args) {

    }
}
