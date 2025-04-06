import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> phoneBook = new HashSet<>();

        for (String phone : phone_book) {
            phoneBook.add(phone);
        }

        for (String phone : phone_book) {
            for (int i = 1; i < phone.length(); i++) {
                if (phoneBook.contains(phone.substring(0, i))) {
                    return false;
                }
            }
        }

        return true;
    }
}
