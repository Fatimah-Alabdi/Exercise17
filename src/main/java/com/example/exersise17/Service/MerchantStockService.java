package com.example.exersise17.Service;

import com.example.exersise17.Model.Merchant;
import com.example.exersise17.Model.MerchantStock;
import com.example.exersise17.Model.Product;
import com.example.exersise17.Repository.MerchantRepository;
import com.example.exersise17.Repository.MerchantStockRepository;
import com.example.exersise17.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository;
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;



    public List<MerchantStock> getAllMerchantstock() {
        return merchantStockRepository.findAll();
    }
    public void addMerchantstock(MerchantStock merchantStock) {
        merchantStockRepository.save(merchantStock);
    }
    public boolean updateMerchantStock(Integer id ,MerchantStock merchantStock) {
        MerchantStock m=merchantStockRepository.getReferenceById(id);
        if(m==null) {
            return false;
        }
       m.setMerchantId(merchantStock.getMerchantId());
        m.setProductId(merchantStock.getProductId());
        m.setStuck(merchantStock.getStuck());


        return true;
    }
    public boolean deleteMerchantstock(Integer id) {
        MerchantStock m=merchantStockRepository.getReferenceById(id);
        if(m==null) {
            return false;
        }
        merchantStockRepository.delete(m);
        return true;
    }
    public String addStok(Integer mSId,int productId,int merchantId,int amount) {

        Product product = productRepository.getReferenceById(productId);
        if(product==null) {
            return "1";
        }
        Merchant merchant = merchantRepository.getReferenceById(merchantId);
        if(merchant==null) {
            return "2";
        }
        MerchantStock merchantStock =merchantStockRepository.getReferenceById(mSId);
        merchantStock.setStuck(merchantStock.getStuck()+amount);
        merchantStockRepository.save(merchantStock);
        return "true";

    }
    }


