import java.util.*;
import java.util.stream.Stream;


/*
Input
10
1000001 female 10 16 400
1000002 male 10 17 500
1000003 male 11 17 505
1000004 male 11 16 405
1000005 female 11 17 450
1000006 female 10 15 480
1000007 male 9 15 445
1000008 male 6 12 350
1000009 male 8 13 399
1000010 male 10 16 430
3
IOI
EGOI
BaltOI
*/
public class Main {

    public static void main(String[] args) {
        // штука для отримання вводу з консолі
        Scanner in = new Scanner(System.in);

        // отримання кількості учасників олімпіад
        final int n = in.nextInt();
        List<Member> members = new ArrayList<>();
        // пропускання пустого рядка (без нього поламається все!!!)
        in.nextLine();
        // цикл у якому в масив даних додаються всі учасники
        for (int i = 0; i < n; i++) {
            /* отримання лінії з консолі у вигляді (1000001 female 10 16 400)
            * і подальше розділення на окремі рядки ("1000001", "female", "10", "16", "400") */
            String[] split = in.nextLine().split(" ");
            members.add(
                    new Member(
                            Integer.parseInt(split[0]),
                            split[1],
                            Integer.parseInt(split[2]),
                            Integer.parseInt(split[3]),
                            Integer.parseInt(split[4])
                    )
            );
        }
        // отримання кількості олімпіад
        int m = in.nextInt();
        String[] olympyads = new String[m];
        // знову ж таки пропуск а то все поламається)
        in.nextLine();
        for (int i = 0; i < m; i++) {
            // додавання назв олімпіад у масив даних
            olympyads[i] = in.nextLine();
        }
        // цикл який розподіляє учасників по олімпіадах
        for (String olympyad : olympyads) {
            // перевірка яка саме зараз олімпіада у циклі
            if (olympyad.equals("IOI")) {
                // по умові потрібно надрукувати назву олімпіади
                System.out.println("IOI");
                // спеціальний тип даних для маніпуляції з ними
                Stream<Member> limit = members.stream()
                        .sorted() // сортування по власному алгоритму !!!
                        .limit(4); // ліміт учасників (по умові)
                limit
                        .map(Member::getId) // перетворення наших учасників на їхні ID
                        .sorted() // повторне сортування
                        .forEach(System.out::println); // вивід у консоль всіх ID учасників що йдуть на цю олімпіаду
                System.out.println(" ");
            }
            if (olympyad.equals("CEOI")) {
                System.out.println("CEOI");
                Stream<Member> limit = members.stream().sorted().limit(4);
                limit.map(Member::getId).sorted().forEach(System.out::println);
                System.out.println(" ");
            }
            if (olympyad.equals("EGOI")) {
                System.out.println("EGOI");
                Stream<Member> limit = members.stream().sorted()
                        // фільтр щоб учасниками були лише дівчата
                        .filter(member -> member.getGender().equalsIgnoreCase("female"))
                        .limit(4);
                limit.map(Member::getId).sorted().forEach(System.out::println);
                System.out.println(" ");
            }
            if (olympyad.equals("EJOI")) {
                System.out.println("EJOI");
                Stream<Member> limit = members.stream().sorted().filter(member -> member.getAge() <= 15).limit(4);
                limit.map(Member::getId).sorted().forEach(System.out::println);
                System.out.println(" ");
            }
            if (olympyad.equals("BaltOI")) {
                System.out.println("BaltOI");
                Stream<Member> limit = members.stream().sorted().limit(6);
                limit.map(Member::getId).sorted().forEach(System.out::println);
                System.out.println(" ");
            }
            if (olympyad.equals("BalkOI")) {
                System.out.println("BalkOI");
                Stream<Member> limit = members.stream().sorted().filter(member -> member.getGrade() != 11).limit(4);
                limit.map(Member::getId).sorted().forEach(System.out::println);
                System.out.println(" ");
            }
            if (olympyad.equals("JBOI")) {
                System.out.println("JBOI");
                Stream<Member> limit = members.stream().sorted().filter(member -> member.getGrade() != 11 || member.getGrade() != 10).limit(4);
                limit.map(Member::getId).sorted().forEach(System.out::println);
                System.out.println(" ");
            }
        }
    }

    /**
     * Comparable спеціальний для нашого сортування
     * @see java.lang.Comparable
     * */
    static class Member implements Comparable<Member> {
        private int id;
        private String gender;
        private int grade;
        private int age;
        private int score;
        // конструктор для нашого учасника (завдяки ньому у всіх учасників унікальні характеристики)
        public Member(int id, String gender, int grade, int age, int score) {
            this.id = id;
            this.gender = gender;
            this.grade = grade;
            this.age = age;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        // порівняння двох учасників
        @Override
        public int compareTo(Member o) {
            if (this.score > o.getScore()) {
                // -1 значить в учасника менше балів
                return -1;
            } else if (this.score < o.getScore()) return 1; // 1 = більше балів чим у іншого
            // 0 однаково балів
            return 0;
        }
    }
}