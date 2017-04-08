package statistic.modeling.lab1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */
public class Sequencer {
    List<List<Double>> sequence;
    List<Pair> params;
    List<List<Double>> correlation;
    List<Pair> tableParams;
    List<List<Integer>> f;
    List<List<Double>> F;
    List<Double> x;

    public static final int SEQUENCES_N = 4;
    public static final int DIVIDE_N = 10;
    public static final double LENGTH = 1.0;
    public static final double PART = LENGTH/DIVIDE_N;
    Random random = new Random();
    public void generateSequence(){
        sequence = new ArrayList<>(SEQUENCES_N);
        for (int i = 0; i < SEQUENCES_N; ++i){
            sequence.add(new ArrayList<>());
        }

        for (int n = 10, i = 0; i < SEQUENCES_N; ++i, n *= 10){
            final List<Double> currentSequence = sequence.get(i);

            for (int j = 0; j < n; ++j){
                currentSequence.add(random.nextDouble());
            }
        }
    }

    public class Pair{
        //математическое ожидание
        private Double m;
        //дисперсия
        private Double d;

        public Pair(final Double m, final Double d){
            this.m = m;
            this.d = d;
        }

        public Double getD() {
            return d;
        }

        public Double getM() {
            return m;
        }

        public void setM(final Double m) {
            this.m = m;
        }

        public void setD(final Double d) {
            this.d = d;
        }
    }

    public void createEmpiricFunctions(){
        f = new ArrayList<>(SEQUENCES_N);
        F = new ArrayList<>(SEQUENCES_N);
        x = new ArrayList<>(DIVIDE_N);

        for (int i = 0; i < DIVIDE_N; ++i){
            x.add(LENGTH / DIVIDE_N*i);
        }

        for (int i = 0; i < SEQUENCES_N; ++i){
            final List<Double> currentSequence = sequence.get(i);
            f.add(new ArrayList<>(DIVIDE_N));

            final List<Integer> currentf = f.get(i);

            for (int j = 0; j < DIVIDE_N; ++j){
                currentf.add(0);
            }

            for (final Double d : currentSequence){
                final int pos = (int)(d/PART);
                currentf.set(pos,currentf.get(pos) + 1);
            }

            F.add(currentf.
                    stream().
                    mapToDouble(value -> (double)value/(double)currentSequence.size())
                    .boxed()
                    .collect(Collectors.toList()));
        }

        for (int i = 0; i < F.size(); ++i){
            final List<Double> currentF = F.get(i);
            for (int j = 1; j < currentF.size(); ++j){
                currentF.set(j,currentF.get(j-1) + currentF.get(j));
            }
        }
    }

    public void readTableParam(){
        tableParams = new ArrayList<>(SEQUENCES_N);

        FileReader fileReader = null;

        try {
            fileReader = new FileReader("E:\\IdeaProject\\Polytech\\src\\main\\resources\\statistic\\modeling\\file.txt");
            final BufferedReader bufferedReader = new BufferedReader(fileReader);
            final Scanner scanner = new Scanner(bufferedReader.readLine());
            scanner.useLocale(Locale.US);

            for (int i = 0; i < SEQUENCES_N; ++i){
                final double m;
                final double d;
                if (scanner.hasNextDouble()){
                    m = scanner.nextDouble();
                } else {
                    throw new IllegalArgumentException("В файйле содержится неправильная информация");
                }
                if (scanner.hasNextDouble()){
                    d = scanner.nextDouble();
                } else {
                    throw new IllegalArgumentException("В файйле содержится неправильная информация");
                }

                tableParams.add(new Pair(m,d));
            }
        } catch (final IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    public List<List<Double>> getCorrelation() {
        return correlation;
    }

    public List<List<Integer>> getF() {
        return f;
    }
    public List<List<Double>> getFF() {
        return F;
    }

    public void evaluateParams(){
        params = new ArrayList<>();

        sequence.stream().forEach(list -> {
            final double m = list.stream().
                    mapToDouble(value -> value).
                    average().
                    getAsDouble();
            final double d = list.stream().
                    mapToDouble(value -> (value - m)*(value - m)).
                    average().
                    getAsDouble();
            params.add( new Pair(m,d));
        });

        correlation = new ArrayList<>(SEQUENCES_N);

        for (int i = 0; i < SEQUENCES_N; ++i){
            final List<Double> currentList = sequence.get(i);
            correlation.add(new ArrayList<>(currentList.size()));
            final List<Double> currentCorrelationList = correlation.get(i);
            final double currentM = params.get(i).getM();
            for (int j = 0; j < currentList.size(); ++j){
                double dividing = 0.0;
                for (int k = 0; k < currentList.size() - j; ++k){
                    dividing += (currentList.get(k) - currentM)*(currentList.get(k + j) - currentM);
                }
                double divider = 0.0;
                for (int k = 0; k < currentList.size(); ++k){
                    divider += (currentList.get(k) - currentM)*(currentList.get(k) - currentM);
                }

                currentCorrelationList.add(dividing/divider);
            }
        }
    }

    public void show(){
        sequence.stream()
                .forEach(list ->System.out.println(Arrays.toString(list.toArray())));

        params.stream().forEach(
                pair -> System.out.println("M = " + pair.getM() + " D = " + pair.getD())
        );

        correlation.stream()
                .forEach(list ->System.out.println(Arrays.toString(list.toArray())));

        f.stream()
                .forEach(list ->System.out.println(Arrays.toString(list.toArray())));

        F.stream()
                .forEach(list ->{
                    System.out.println(Arrays.toString(list.toArray()));
                    System.out.println("Sum = " + list.stream().mapToDouble(value->value).sum());
                });
    }

    public void createTable(){
        final OutputStream outputStream;
        try {
            final File file = new File("E:\\IdeaProject\\Polytech\\src\\main\\resources\\statistic\\modeling\\res.txt");
            outputStream = new FileOutputStream(file);

            final StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0, n = 10; i < SEQUENCES_N; ++i, n *= 10){
                final double exprM = params.get(i).getM();
                final double tableM = tableParams.get(i).getM();
                stringBuilder.append(n).append(" @M@ ").append(exprM).append(" ").append(tableM).append(" ").append(Math.abs(tableM - exprM)).append('\n');
                final double exprD = params.get(i).getD();
                final double tableD = tableParams.get(i).getD();
                stringBuilder.append("  @D@ ").append(exprD).append(" ").append(tableD).append(" ").append(Math.abs(tableD - exprD)).append('\n');
            }

            outputStream.write(stringBuilder.toString().getBytes());

            outputStream.close();
        } catch (final IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    public List<List<Double>> getSequence(){
        return sequence;
    }
}
