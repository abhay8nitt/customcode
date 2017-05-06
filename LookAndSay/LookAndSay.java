public class LookAndSay {

    public static void main(String[] args) {
        System.out.println(LookAndSay("1",1));
        System.out.println(LookAndSay("11",1));
        System.out.println(LookAndSay("111221",1));
        System.out.println(LookAndSay("21",1));
        System.out.println(LookAndSay("1211",3));
    }

    static String LookAndSay(String start, int steps) {
        System.out.println("..............");
        String s = start;
        for(int i=0;i < steps; i++){
            System.out.println(s);
            s = LookAndSay(s);
        }
        return s;
    }

    static String LookAndSay(String input){
        if(input.length() ==0 ) return input;
        if(input.length() ==1){
            return "1"+input;
        }
        int count = 1;
        char prevChar = input.charAt(0);
        int i =1;
        char currentChar;
        StringBuilder buff = new StringBuilder();
        for( ;i < input.length();i++){
            currentChar = input.charAt(i);
            if(prevChar == currentChar){
                count++;
            }else{
                buff.append(count).append(prevChar);
                count = 1;
                if(i == input.length()-1){
                    buff.append(count).append(currentChar);
                }
                prevChar = currentChar;

            }
        }
        if(count > 1){
            buff.append(count).append(prevChar);
        }
        return buff.toString();
    }
}
