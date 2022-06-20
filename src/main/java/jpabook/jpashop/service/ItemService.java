package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional // readOnly=true면 저장이 안되니까 필요
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name,int price,int quantity){
        Item findItem=itemRepository.findOne(itemId); //findItem은 영속상태
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(quantity);
        //여기까지하고 transactional로 transaction이 commit이 되면, jpa는 flush를 날림.
        // flush는 영속성 엔티티 중에서 변경된걸 다 찾음. 변경된 엔티티에 대해 db로 업데이트 쿼리를 날림


    }
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }


}
