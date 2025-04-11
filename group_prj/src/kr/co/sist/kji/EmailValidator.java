package kr.co.sist.kji;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class EmailValidator {

    // 이메일 형식 정규표현식 (간단한 버전)
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    // 이메일 형식 검사
    public static boolean isValidFormat(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }//isValidFormat

    // 도메인 DNS 확인
    public static boolean isDomainExists(String email) {
        try {
            String domain = email.substring(email.indexOf("@") + 1);
            InetAddress.getByName(domain); // DNS 조회 시도
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }//isDomainExists
    
    public static void main(String[] args) {
        String email = "test@naver.co";  // 테스트용 이메일

        if (!isValidFormat(email)) {
            System.out.println("이메일 형식이 올바르지 않습니다.");
        } else if (!isDomainExists(email)) {
            System.out.println("이메일 도메인이 존재하지 않습니다.");
        } else {
            System.out.println("유효한 이메일입니다.");
        }
    }

}
