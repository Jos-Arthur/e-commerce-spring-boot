package com.pakindessama.commerce.services;

import com.pakindessama.commerce.domains.Shop;
import com.pakindessama.commerce.dtos.ShopDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public interface ShopService {

    public void addShop(Shop shop);

    public Shop updateShop(Shop shop);

    public void deleteShop(UUID id);

    public Shop getShop(UUID id);

    public List<ShopDto> getShops(Pageable pageable);

    public ShopDto getShopDto(UUID id);

    public Shop enableShop(Shop shop);

    public Shop disableShop(UUID id);

}
