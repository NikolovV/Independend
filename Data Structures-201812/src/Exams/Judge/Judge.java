package Exams.Judge;

import java.util.*;
import java.util.stream.Collectors;

public class Judge implements IJudge {
    private final HashSet<Integer> users;
    private final HashSet<Integer> contests;
    private final TreeMap<Integer, Submission> bySubmissionsId;

    public Judge() {
        this.users = new HashSet<>();
        this.contests = new HashSet<>();
        this.bySubmissionsId = new TreeMap<>();
    }

    @Override
    public void addContest(int contestId) {
        this.contests.add(contestId);
    }

    @Override
    public void addSubmission(Submission submission) {
        if (this.bySubmissionsId.containsKey(submission.getId())) {
            return;
        }
        if (!this.users.contains(submission.getUserId())
                || !this.contests.contains(submission.getContestId())) {
            throw new IllegalArgumentException();
        }

        this.bySubmissionsId.put(submission.getId(), submission);
    }

    @Override
    public void addUser(int userId) {
        this.users.add(userId);
    }

    @Override
    public void deleteSubmission(int submissionId) {
        if (!this.bySubmissionsId.containsKey(submissionId)) {
            throw new IllegalArgumentException();
        }
        this.bySubmissionsId.remove(submissionId);
    }

    @Override
    public Iterable<Submission> getSubmissions() {
        return this.bySubmissionsId.values().stream()
                .sorted(Comparator.comparing(Submission::getId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Iterable<Integer> getUsers() {
        return this.users.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public Iterable<Integer> getContests() {
        return this.contests.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public Iterable<Submission> submissionsWithPointsInRangeBySubmissionType(int minPoints, int maxPoints, SubmissionType submissionType) {
        return this.bySubmissionsId.values().stream()
                .filter(x -> x.getPoints() >= minPoints && x.getPoints() <= maxPoints && x.getType() == submissionType)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Iterable<Integer> contestsByUserIdOrderedByPointsDescThenBySubmissionId(int userId) {
        return this.bySubmissionsId.values().stream()
                .filter(x -> x.getUserId() == userId)
                .sorted(Comparator.comparing(Submission::getPoints, Comparator.reverseOrder())
                        .thenComparing(Submission::getId))
                .map(Submission::getContestId)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Iterable<Submission> submissionsInContestIdByUserIdWithPoints(int points, int contestId, int userId) {
        if (!this.users.contains(userId) ||
                !this.contests.contains(contestId)) {
            throw new IllegalArgumentException();
        }

        List<Submission> result = this.bySubmissionsId.values().stream()
                .filter(x -> x.getPoints() == points && x.getContestId() == contestId && x.getUserId() == userId)
                .collect(Collectors.toCollection(ArrayList::new));

        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    @Override
    public Iterable<Integer> contestsBySubmissionType(SubmissionType submissionType) {

        return this.bySubmissionsId.values().stream()
                .filter(x -> x.getType() == submissionType)
                .map(Submission::getContestId)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
