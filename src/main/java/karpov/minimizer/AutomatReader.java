package karpov.minimizer;

import karpov.minimizer.service.ConfigService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * Polytech
 * Created by Игорь on 21.02.2017.
 */
public class AutomatReader {
    Reader reader;
    private final List<List<Integer>> automat = new ArrayList<>();
    private List<Set<Integer>> groups = new ArrayList<>();

    public class StateDto {
        private BufferedReader bufferedReader;
        private Scanner scanner;

        public List<Integer> getState() {
            List<Integer> state = null;
            bufferedReader = ConfigService.getBufferedReader();
            try {
                final String s = bufferedReader.readLine();
                if ("end".equals(s))
                    return null;
                scanner = new Scanner(s);

                state = new ArrayList<>(3);
                while (scanner.hasNextInt()) {
                    state.add(scanner.nextInt());

                    //todo Вообще-то может
                    if (state.size() > 3) {
                        throw new IllegalArgumentException("Не может быть больше трех элементов");
                    }
                }

                //todo the same
                if (state.size() != 3) {
                    throw new IllegalArgumentException("Получилось не 3 элемента");
                }
            } catch (final IOException ioe) {
                System.out.println(ioe.getMessage());
            } catch (final IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            } catch (final NullPointerException npe) {
                return null;
            }

            return state;
        }
    }

    public class GroupDto {
        private BufferedReader bufferedReader;
        private Scanner scanner;

        public List<Set<Integer>> groups;

        public void generateGroups() {
            bufferedReader = ConfigService.getBufferedReader();
            groups = new ArrayList<>();
            groups.add(new HashSet<>());
            groups.add(new HashSet<>());
            try {
                scanner = new Scanner(bufferedReader.readLine());

                while (scanner.hasNextInt()) {
                    final Integer temp = scanner.nextInt();
                    final int numberGroup = scanner.next().charAt(0);

                    if (numberGroup == '-') {
                        groups.get(0).add(temp);
                    } else if (numberGroup == '+') {
                        groups.get(1).add(temp);
                    } else {
                        throw new IllegalArgumentException("В файле + означает, что узел принимает последовательность - нет" +
                                "ни то ни другое не было считано");
                    }
                }
            } catch (final IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }

        public List<Set<Integer>> getGroups() {
            return groups;
        }
    }

    public void readAutomat() {
        final StateDto stateDto = new StateDto();

        List<Integer> temp = stateDto.getState();
        while (temp != null) {
            automat.add(temp);
            temp = stateDto.getState();
        }
    }

    public void readGroup() {
        final GroupDto groupDto = new GroupDto();

        groupDto.generateGroups();
        groups = groupDto.getGroups();
    }

    public void showAutomat() {
        System.out.println("Таблица переходов");
        automat.stream().forEach((list) ->
                System.out.println(Arrays.toString(list.toArray())));
        System.out.println("Принимающие и непринимающие узлы");
        groups.stream().forEach((set) ->
                System.out.println(Arrays.toString(set.stream().toArray())));
    }

    public void minimize() {
        final List<List<Integer>> workSpace = new ArrayList<>(automat);

        while (true) {
            final List<List<Integer>> stateInGroups = new ArrayList<>(workSpace.size());
            for (int i = 0; i < workSpace.size(); ++i) {
                stateInGroups.add(new ArrayList<>(2));
                stateInGroups.get(i).add(0);
                stateInGroups.get(i).add(0);
            }

            for (int i = 0; i < workSpace.size(); ++i) {
                final List<Integer> state = workSpace.get(i);
                int res = findInGroups(state.get(1), groups);
                stateInGroups.get(i).set(0, res);

                res = findInGroups(state.get(2), groups);
                stateInGroups.get(i).set(1, res);
            }

            final List<Set<Integer>> newGroups = new ArrayList<>();

            for (int i = 0; i < stateInGroups.size(); ++i) {
                if (newGroups.isEmpty()) {
                    newGroups.add(new HashSet<>());
                    newGroups.get(0).add(i);
                } else {
                    boolean flag = false;
                    for (int j = i - 1; j >= 0; --j) {
                        if (comparingTwoList(stateInGroups.get(j), stateInGroups.get(i))) {
                            final int resi = findInGroups(i, groups);
                            final int resj = findInGroups(j, groups);

                            if (resi == resj) {
                                flag = true;
                                final int whereInNewGroup = findInGroups(j, newGroups);
                                newGroups.get(whereInNewGroup).add(i);
                                break;
                            }
                        }
                    }
                    if (!flag) {
                        newGroups.add(new HashSet<>());
                        newGroups.get(newGroups.size() - 1).add(i);
                    }
                }
            }
            if (comparingTwoListSet(groups, newGroups)) {
                break;
            }
            groups = newGroups;
            /*workSpace.stream().forEach(
                    list -> {
                        list.set(1, automat.get(list.get(1)).get(1));
                        list.set(2, automat.get(list.get(2)).get(2));
                    }
            );*/
        }
    }

    private static <T> boolean comparingTwoList(final List<T> a, final List<T> b) {
        return Arrays.equals(a.toArray(), b.toArray());
    }

    private static <T> boolean comparingTwoSets(final Set<T> a, final Set<T> b) {
        return Arrays.equals(a.toArray(), b.toArray());
    }

    private static <T> boolean comparingTwoListSet(final List<Set<T>> a, final List<Set<T>> b) {
        if (a.size() != b.size())
            return false;
        for (int i = 0; i < a.size(); ++i) {
            if (!comparingTwoSets(a.get(i), b.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static int findInGroups(final Integer state, final List<Set<Integer>> groups) {
        for (int i = 0; i < groups.size(); ++i) {
            final Set<Integer> set = groups.get(i);
            if (set.contains(state)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Не смог найти состояние ни в одной из групп");
    }
}
