package thirdlab;

public class Main {
  public static void main(String[] args) {
    UndoStringBuilder usb = new UndoStringBuilder();
    System.out.println("=========check append=======");
    usb.append(true);
    usb.append('a');
    usb.append(12345);
    usb.append("LONG:");
    usb.append(1234512321213L);
    char[] c = "hello".toCharArray();
    usb.append(c);
    usb.append(c, 3, 2);
    usb.append(2.5f);
    usb.append(62038.2123);
    System.out.println(usb);
    for (int i = 0; i < 6; ++i)
      usb.undo();
    System.out.println(usb);

    System.out.println("=========check insert=======");
    usb.insert(4, false);
    System.out.println(usb);
    usb.insert(9, 'A');
    System.out.println(usb);
    usb.insert(usb.length(), c);
    System.out.println(usb);
    usb.insert(usb.length(), c, 3, 2);
    System.out.println(usb);
    for (int i = 0; i < 4; ++i) {
      usb.undo();
      System.out.println(usb);
    }
    usb.insert(4, 123.123);
    usb.insert(0, 1.5f);
    System.out.println(usb);
    usb.insert(7, 512);
    usb.insert(usb.length(), 123456789L);
    System.out.println(usb);
    usb.insert(3, "SMTH");
    System.out.println(usb);
    for (int i = 0; i < 5; ++i) {
      usb.undo();
      System.out.println(usb);
    }

    System.out.println("========check replace and reverse========");
    usb.reverse();
    System.out.println(usb);
    usb.undo();
    System.out.println(usb);

    usb.replace(0, 4, "false");
    System.out.println(usb);
    usb.undo();
    System.out.println(usb);

    usb.setCharAt(0, 'T');
    System.out.println(usb);
    usb.undo();
    System.out.println(usb);
  }
}
