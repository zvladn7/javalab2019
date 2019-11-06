package firstlab;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//Task B
public class Fragment {
  private String[] stringArr;

  public Fragment(String string) {
    int strlen = string.length();
    stringArr = new String[strlen / 3 + (strlen % 3 != 0 ? 1 : 0)];
    int begin = 0;
    int end = (strlen / 3 > 0 ? 3 : strlen % 3);
    Random random = new Random();
    for (int i = 0; i < stringArr.length; ++i) {
      stringArr[i] = string.substring(begin, end);
      if (stringArr[i].length() == 3) {
        char[] c = stringArr[i].toCharArray();
        stringArr[i] = "" + c[0] + (char) random.nextInt(255) + c[2];
      }
      begin += 3;
      end = (strlen - begin) / 3 > 0 ? begin + 3 : (strlen - begin) % 3 + begin;
    }
  }

  public void print() {
    for (int i = 0; i < stringArr.length; ++i) {
      System.out.println(stringArr[i]);
    }
  }

  public void sort() {
    Arrays.sort(stringArr);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Fragment fragment = new Fragment(scanner.nextLine());
    fragment.sort();
    fragment.print();
  }
}

