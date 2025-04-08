package kr.co.sist.kyh;

import java.util.List;

public class ItemService {
    
    private ItemRecommendDAO itemDAO;

    public ItemService() {
        itemDAO = ItemRecommendDAO.getInstance();
    }

    /**
     * 전체 상품 조회
     */
    public List<ItemVO> getAllItems() {
        try {
            return itemDAO.getAllItems();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 추천 상품 조회
     */
    public List<ItemVO> getRecommendedItems(int carNum) {
        try {
            return itemDAO.getRecommendedItems(carNum);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}