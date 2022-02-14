package com.pakindessama.commerce.services;

import com.pakindessama.commerce.domains.Product;
import com.pakindessama.commerce.domains.Shop;
import com.pakindessama.commerce.dtos.ProductDto;
import com.pakindessama.commerce.dtos.ShopDto;
import com.pakindessama.commerce.repository.ProductRepository;
import com.pakindessama.commerce.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Configuration
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShopRepository shopRepository;

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProduct(UUID id) {
        return productRepository.findById(id).get();
    }

    @Override
    public ProductDto getProductDto(UUID id) {
        return modelMapper.map(productRepository.findById(id).get(), ProductDto.class);
    }

    @Override
    public List<ProductDto> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByShop(UUID shopId, Pageable pageable) {
        Shop shop = shopRepository.findById(shopId).get();
        return productRepository.findByShop(shop, pageable).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(UUID categoryId, Pageable pageable) {
        return productRepository.findAllByCategories_id(categoryId, pageable).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void editProduct(Product product) {

    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }


    private ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }
}
