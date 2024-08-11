package com.example.exersise17.Service;

import com.example.exersise17.Model.Category;
import com.example.exersise17.Model.Merchant;
import com.example.exersise17.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchant() {
        return merchantRepository.findAll();
    }
    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }
    public boolean updateMerchant(Integer id ,Merchant merchant) {
        Merchant m=merchantRepository.getReferenceById(id);
        if(m==null) {
            return false;
        }
       m.setName(merchant.getName());


        return true;
    }
    public boolean deleteMerchant(Integer id) {
        Merchant m=merchantRepository.getReferenceById(id);
        if(m==null) {
            return false;
        }
        merchantRepository.delete(m);
        return true;
    }
}
