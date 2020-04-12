package TP1.e9;

public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("neuquen"));
        System.out.println(isPalindrome("neuqquen"));
        System.out.println(isPalindrome("neuq23quen"));
    }

    public static boolean isPalindrome(String s) {
        int length = s.length();
        int half = length / 2;
        char[] letters = s.toCharArray();
        for(int i = 0; i < half; i++) {
            if(letters[i] != letters[length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

}
