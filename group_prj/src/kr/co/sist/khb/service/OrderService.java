package kr.co.sist.khb.service; 

import kr.co.sist.khb.dao.SalesDAO;    
import kr.co.sist.hjy.FixDAO;           
import kr.co.sist.khb.DbConnection;    
import kr.co.sist.khb.vo.OrderVO;    

import java.sql.Connection;
import java.sql.SQLException;

public class OrderService {

    // 1. Singleton 패턴 적용
    private static OrderService instance;

    private OrderService() {}

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    /**
     * 정비 요청 데이터를 받아 Fix와 Sales 테이블에 트랜잭션을 보장하며 저장하고,
     * 생성된 fix_num을 반환합니다. // <<<=== 반환 타입 및 설명 수정
     *
     * @param ovo 저장할 데이터가 담긴 OrderVO 객체
     * @return 생성된 fix_num (String 타입) // <<<=== 반환 타입 String으로 변경
     * @throws SQLException DB 오류 발생 시 (자동 Rollback됨)
     * @throws Exception 데이터 저장 실패 또는 fix_num 생성 실패 등 기타 예외 발생 시 (자동 Rollback됨)
     */
    public String placeMaintenanceRequest(OrderVO ovo) throws SQLException, Exception { // <<<=== 반환 타입 String으로 변경
        FixDAO fDAO = FixDAO.getInstance();
        SalesDAO sDAO = SalesDAO.getInstance();
        DbConnection dbCon = DbConnection.getInstance();

        Connection conn = null;
        boolean commitStatus = false;
        String generatedFixNum = null; // 생성된 fix_num 저장 변수

        try {
            conn = dbCon.getConn();
            conn.setAutoCommit(false);
            System.out.println("[OrderService] 트랜잭션 시작 (AutoCommit=false)");

            // 3. FixDAO 호출 -> FIX 테이블 INSERT 및 생성된 fix_num 반환받기
            System.out.println("[OrderService] FixDAO.insertFixRequest 호출 시도...");
            generatedFixNum = fDAO.insertFixRequest(ovo, conn); // 반환값 저장
            System.out.println("[OrderService] FixDAO.insertFixRequest 완료, 생성된 fix_num: " + generatedFixNum);

            if (generatedFixNum == null || generatedFixNum.isEmpty()) {
                throw new Exception("FixDAO에서 유효한 fix_num을 반환받지 못했습니다.");
            }

            // 4. SalesDAO 호출 -> SALES 테이블 INSERT (반환받은 fix_num 사용)
            System.out.println("[OrderService] SalesDAO.insertSales 호출 시도 (fix_num: " + generatedFixNum + ")");
            int salesResult = sDAO.insertSales(ovo, generatedFixNum, conn);
            System.out.println("[OrderService] SalesDAO.insertSales 완료, 결과: " + salesResult);

            if (salesResult < 1) {
                throw new Exception("Sales 테이블 INSERT 실패 (0건 처리됨)");
            }

            // 5. 모든 작업 성공 시 Commit
            conn.commit();
            commitStatus = true;
            System.out.println("[OrderService] 트랜잭션 Commit 완료");

            // --- 성공 시 생성된 fix_num 반환 ---
            return generatedFixNum; // <<<=== 성공 시 fix_num 반환 추가

        } catch (SQLException e) { // 여러 예외 한 번에 처리 가능
            // 6. 예외 발생 시 Rollback
            if (conn != null) { conn.rollback(); }
            System.err.println("[OrderService] 오류로 Rollback 실행됨: " + e.getMessage());
            e.printStackTrace();
            throw e; 
        } finally {
            // 7. Connection 반환
            if (conn != null) {
                try {
                    if (!commitStatus) { conn.rollback(); }
                    conn.setAutoCommit(true);
                } catch (SQLException e) { e.printStackTrace(); }
                 finally { // 내부 finally
                    dbCon.closeDB(null, null, conn);
                    System.out.println("[OrderService] 데이터베이스 연결 반환됨.");
                 }
            }
        }
       
    }

} // class OrderService