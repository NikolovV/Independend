package _4_BestLecturesSchedule;

import java.util.*;
import java.util.stream.Collectors;

public class BestLecturesSchedule {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lecture = Integer.parseInt(in.nextLine().substring(10));

        List<Lecture> lectureList = new ArrayList<>();
        for (int i = 0; i < lecture; i++) {
            String[] input = in.nextLine().split("[:\\s\\-]");
            Lecture current = new Lecture(input[0], Integer.parseInt(input[2]), Integer.parseInt(input[5]));
            lectureList.add(current);
        }

        List<Lecture> result = new ArrayList<>();
        while (!lectureList.isEmpty()) {
            Lecture minLecture = Collections.min(lectureList, Comparator.comparing(l -> l.finishTime));
            lectureList.removeAll(lectureList.stream()
                    .filter(s -> s.startTime < minLecture.finishTime && s.startTime != minLecture.startTime)
                    .collect(Collectors.toList()));

            result.add(minLecture);
            lectureList.remove(minLecture);
        }
        printLectures(result);
    }

    private static void printLectures(List<Lecture> lectureList) {
        System.out.printf("Lectures (%d):\n", lectureList.size());
        lectureList.forEach(l -> System.out.println(l.toString()));
    }

    static class Lecture {
        String name;
        public int startTime;
        public int finishTime;
        public int length;

        public Lecture(String name, int startTime, int finishTime) {
            this.name = name;
            this.startTime = startTime;
            this.finishTime = finishTime;
            this.setLength(startTime, finishTime);
        }

        public void setLength(int startTime, int finishTime) {
            this.length = finishTime - startTime;
        }

        @Override
        public String toString() {
            return this.startTime + "-" + this.finishTime + " -> " + this.name;
        }
    }
}
