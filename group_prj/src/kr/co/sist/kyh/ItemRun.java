package kr.co.sist.kyh;

import kr.co.sist.kji.MemberVO;

public class ItemRun {

    public static void main(String[] args) {

        MemberVO member = new MemberVO();
        int carNum = member.getCarNum(); // 차량식별번호
        new ItemView(carNum, member, "khk"); // ItemView에 carNum과 member를 전달
        
    }
}