package thirdlab;

import java.util.LinkedList;
import java.util.function.Consumer;

public class UndoStringBuilder {
  private StringBuilder sb;
  private LinkedList<Consumer<StringBuilder>> undoOperations = new LinkedList<>();

  public UndoStringBuilder undo() {
    undoOperations.removeLast().accept(sb);
    return this;
  }

  public UndoStringBuilder() {
    sb = new StringBuilder();
  }

  public UndoStringBuilder(int capacity) {
    sb = new StringBuilder(capacity);
  }

  public UndoStringBuilder(String str) {
    sb = new StringBuilder(str);
  }

  public UndoStringBuilder(CharSequence seq) {
    sb = new StringBuilder(seq);
  }

  public UndoStringBuilder append(boolean b) {
    int begin = sb.length();
    sb.append(b);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public UndoStringBuilder append(char c) {
    sb.append(c);
    undoOperations.add(x -> x.delete(x.length() - 1, x.length()));
    return this;
  }

  public UndoStringBuilder append(char[] str) {
    int begin = sb.length();
    sb.append(str);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public UndoStringBuilder append(char[] str, int offset, int len) {
    int begin = sb.length();
    sb.append(str, offset, len);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public UndoStringBuilder append(CharSequence s) {
    int begin = sb.length();
    sb.append(s);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public UndoStringBuilder append(CharSequence s, int start, int end) {
    int begin = sb.length();
    sb.append(s, start, end);
    int endInd = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public UndoStringBuilder append(double d) {
    int begin = sb.length();
    sb.append(d);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public UndoStringBuilder append(float f) {
    int begin = sb.length();
    sb.append(f);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public UndoStringBuilder append(int i) {
    int begin = sb.length();
    sb.append(i);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public UndoStringBuilder append(long l) {
    int begin = sb.length();
    sb.append(l);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public UndoStringBuilder append(Object obj) {
    int begin = sb.length();
    sb.append(obj);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin ,end));
    return this;
  }

  public UndoStringBuilder append(String str) {
    int begin = sb.length();
    sb.append(str);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public UndoStringBuilder append(StringBuffer stringBuffer) {
    int begin = sb.length();
    sb.append(stringBuffer);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public UndoStringBuilder appendCodePoint(int codePoint) {
    int begin = sb.length();
    sb.appendCodePoint(codePoint);
    int end = sb.length();
    undoOperations.add(x -> x.delete(begin, end));
    return this;
  }

  public int capacity() {
    return sb.capacity();
  }

  public char charAt(int index) {
    return sb.charAt(index);
  }

  public int codePointAt(int index) {
    return sb.codePointAt(index);
  }

  public int codePointBefore(int index) {
    return sb.codePointBefore(index);
  }

  public int codePointCount(int beginIndex, int endIndex) {
    return sb.codePointCount(beginIndex, endIndex);
  }

  public UndoStringBuilder delete(int start, int end) {
    String substr = sb.substring(start, end);
    undoOperations.add(x -> x.insert(start, substr));
    sb.delete(start, end);
    return this;
  }

  public UndoStringBuilder deleteCharAt(int index) {
    char c = sb.charAt(index);
    undoOperations.add(x -> x.insert(index, c));
    sb.deleteCharAt(index);
    return this;
  }

  public void ensureCapacity(int minimumCapacity) {
    sb.ensureCapacity(minimumCapacity);
  }

  public void getChart(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
    sb.getChars(srcBegin, srcEnd, dst, dstBegin);
  }

  public int indexOf(String str) {
    return sb.indexOf(str);
  }

  public int indexOf(String str, int fromIndex) {
    return sb.indexOf(str, fromIndex);
  }

  public UndoStringBuilder insert(int offset, boolean b) {
    int begin = sb.length();
    sb.insert(offset, b);
    int end = sb.length();
    undoOperations.add(x -> x.delete(offset, offset + (end - begin)));
    return this;
  }

  public UndoStringBuilder insert(int offset, char c) {
    sb.insert(offset, c);
    undoOperations.add(x -> x.delete(offset, offset + 1));
    return this;
  }

  public UndoStringBuilder insert(int offset, char[] str) {
    sb.insert(offset, str);
    undoOperations.add(x -> x.delete(offset, offset + str.length));
    return this;
  }

  public UndoStringBuilder insert(int index, char[] str, int offset, int len) {
    sb.insert(index, str, offset, len);
    undoOperations.add(x -> x.delete(index, index + len));
    return this;
  }

  public UndoStringBuilder insert(int dstOffset, CharSequence s) {
    sb.insert(dstOffset, s);
    undoOperations.add(x -> x.delete(dstOffset, dstOffset + s.length()));
    return this;
  }

  public UndoStringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
    sb.insert(dstOffset, s, start, end);
    undoOperations.add(x -> x.delete(dstOffset, dstOffset + (end - start)));
    return this;
  }

  public UndoStringBuilder insert(int offset, double d) {
    int begin = sb.length();
    sb.insert(offset, d);
    int end = sb.length();
    undoOperations.add(x -> x.delete(offset, offset + (end - begin)));
    return this;
  }

  public UndoStringBuilder insert(int offset, float f) {
    int begin = sb.length();
    sb.insert(offset, f);
    int end = sb.length();
    undoOperations.add(x -> x.delete(offset, offset + (end - begin)));
    return this;
  }

  public UndoStringBuilder insert(int offset, int i) {
    int begin = sb.length();
    sb.insert(offset, i);
    int end = sb.length();
    undoOperations.add(x -> x.delete(offset, offset + (end - begin)));
    return this;
  }

  public UndoStringBuilder insert(int offset, long l) {
    int begin = sb.length();
    sb.insert(offset, l);
    int end = sb.length();
    undoOperations.add(x -> x.delete(offset, offset + (end - begin)));
    return this;
  }

  public UndoStringBuilder insert(int offset, Object obj) {
    int begin = sb.length();
    sb.insert(offset, obj);
    int end = sb.length();
    undoOperations.add(x -> x.delete(offset, offset + (end - begin)));
    return this;
  }

  public UndoStringBuilder insert(int offset, String str) {
    sb.insert(offset, str);
    undoOperations.add(x -> x.delete(offset, offset + str.length()));
    return this;
  }

  public int lastIndexOf(String str) {
    return sb.lastIndexOf(str);
  }

  public int lastIndexOf(String str, int fromIndex) {
    return sb.lastIndexOf(str, fromIndex);
  }

  public int length() {
    return sb.length();
  }

  public int offsetByCodePoints(int index, int codePointOffset) {
    return sb.offsetByCodePoints(index, codePointOffset);
  }

  public UndoStringBuilder replace(int start, int end, String str) {
    String substr = sb.substring(start, end);
    undoOperations.add(x -> x.replace(start, start + str.length(), substr));
    sb.replace(start, end, str);
    return this;
  }

  public UndoStringBuilder reverse() {
    undoOperations.add(x -> x.reverse());
    sb.reverse();
    return this;
  }

  public void setCharAt(int index, char ch) {
    char c = sb.charAt(index);
    undoOperations.add(x -> x.setCharAt(index, c));
    sb.setCharAt(index, ch);
  }

  public void setLength(int newLength) {
    int length = sb.length();
    undoOperations.add(x -> x.setLength(length));
    sb.setLength(newLength);
  }

  public CharSequence subSequence(int start, int end) {
    return sb.subSequence(start, end);
  }

  public String substring(int start) {
    return sb.substring(start);
  }

  public String substring(int start, int end) {
    return sb.substring(start, end);
  }

  public String toString() {
    return sb.toString();
  }

  public void trimToSize() {
    sb.trimToSize();
  }
}
