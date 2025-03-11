package main.java.implementation;
/*
    Q : 문자열 해독하기
    1. 숫자가 주어질 경우 그대로 출력
    2. 'A' : 여태까지 나온 숫자 합산하여 출력
    3. 'B' : 마지막 두자리 숫자 합산하여 출력

 */
public class StringDecoder {
    public static void main(String[] args){
        // 테스트 케이스
        System.out.println(decode("134A"));    // 출력: 1348
        System.out.println(decode("13450B"));  // 출력: 134505
        System.out.println(decode("987A"));    // 출력: 98724
        System.out.println(decode("10234B"));  // 출력: 102346
        System.out.println(decode("56A12B"));  // 출력: 5613123
    }

    public static String decode(String str) {
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);

            // 1. 숫자가 주어질 경우 그대로 출력
            if(Character.isDigit(ch)){
                sb.append(ch);
            // 2. 알파벳인 경우
            }else {
                if(ch == 'A'){ // 2.1. 'A' : 여태까지 나온 숫자 합산하여 출력
                    int sum = 0;
                    if(!sb.isEmpty()){
                        for (char num : sb.toString().toCharArray() ){
                            sum += Character.getNumericValue(num);
                        }
                    }
                    sb.append(sum);

                }else if(ch =='B') { // 2.2. 'B' : 마지막 두자리 숫자 합산하여 출력
                    String twodigits = sb.length() >= 2
                            // ? sb.toString().split(sb.length()-2)
                            ? sb.substring(sb.length()-2)
                            : sb.toString();
                    int sum2 = 0;
                    for (char num : twodigits.toCharArray()){
                        sum2 += Character.getNumericValue(num);
                    }
                    sb.append(sum2);

                }

            }
        }

        return sb.toString();
    }
}
