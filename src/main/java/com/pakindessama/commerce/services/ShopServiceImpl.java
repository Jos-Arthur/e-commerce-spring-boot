package com.pakindessama.commerce.services;

import com.pakindessama.commerce.domains.AppUser;
import com.pakindessama.commerce.domains.Shop;
import com.pakindessama.commerce.dtos.ShopDto;
import com.pakindessama.commerce.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@Service
@AllArgsConstructor
@Transactional
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ShopRepository shopRepos;
    @Autowired
    UserService userService;

    @Override
    public void addShop(Shop shop) {
        AppUser user = userService.getUser(shop.getEmail());
        shopRepos.save(shop);
    }

    @Override
    public Shop updateShop(Shop shop) {
        return null;
    }

    @Override
    public void deleteShop(UUID id) {
        shopRepos.deleteById(id);
    }

    @Override
    public Shop getShop(UUID id) {
        return shopRepos.findById(id).get();
    }

    @Override
    public ShopDto getShopDto(UUID id) throws NoSuchElementException {
        Optional<Shop> optionalShop = shopRepos.findById(id);
        Shop shop = optionalShop.get();
        return modelMapper.map(shop, ShopDto.class);
    }

    @Override
    public List<ShopDto> getShops(Pageable pageable) {
        return shopRepos.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Shop enableShop(Shop shop) {
        shop.setEnabled(true);
        shopRepos.save(shop);
        return shop;
    }

    @Override
    public Shop disableShop(UUID id) {
        Shop shop = shopRepos.findById(id).get();
        shop.setEnabled(!shop.getIsEnabled());
        shopRepos.save(shop);
        return shop;
    }

    private ShopDto convertToDto(Shop shop) {
        ShopDto shopDto = modelMapper.map(shop, ShopDto.class);
        return shopDto;
    }
}
